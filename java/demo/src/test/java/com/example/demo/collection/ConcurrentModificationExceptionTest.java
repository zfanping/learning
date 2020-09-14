package com.example.demo.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <b>功能：</b>XXX<br>
 * <b>Copyright TCSL</b>
 * <li>版本| 修改日期| 作者| 变更内容</li>
 * <hr>
 * <li>v1.0| 20191122| 曾凡平| 创建</li>
 */
public class ConcurrentModificationExceptionTest {
	/**
	 * for 正序遍历
	 * remove 5后， 0~4的index保持不变 6~9的index -1
	 * 会输出index=6, val = 7，跳过val为6的元素
	 * index=0, val = 0
	 * index=1, val = 1
	 * index=2, val = 2
	 * index=3, val = 3
	 * index=4, val = 4
	 * index=5, val = 5
	 * remove 5
	 * index=6, val = 7
	 * index=7, val = 8
	 * index=8, val = 9
	 */
	@Test
	public void test_single_thread_for_asc_bad() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i + "");
		}

		for (int i = 0; i < list.size(); i++) {
			String val = list.get(i);
			System.out.println("index=" + i + ", val = " + val);
			if (i == 5) {
				list.remove(i);
				System.out.println("remove " + val);
			}
		}
	}

	/**
	 * for 倒序遍历
	 * remove 5后， 0~4的index保持不变 6~9的index -1
	 * index=9, val = 9
	 * index=8, val = 8
	 * index=7, val = 7
	 * index=6, val = 6
	 * index=5, val = 5
	 * remove 5
	 * index=4, val = 4
	 * index=3, val = 3
	 * index=2, val = 2
	 * index=1, val = 1
	 * index=0, val = 0
	 */
	@Test
	public void test_single_thread_for_desc() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i + "");
		}

		for (int i = list.size() - 1; i >= 0; i--) {
			String val = list.get(i);
			System.out.println("index=" + i + ", val = " + val);
			if (i == 5) {
				list.remove(i);
				System.out.println("remove " + val);
			}
		}

	}

	/**
	 * iterator 遍历
	 * 调用{@link List#remove(Object)}，{@link ArrayList#modCount}会增加1，但{@link java.util.ArrayList.Itr#expectedModCount}
	 * 还是之前的值，因此再百纳遍历下一条记录时{@link ArrayList.Itr#checkForComodification()}会抛出异常
	 *
	 * @throws java.util.ConcurrentModificationException
	 */
	@Test
	public void test_single_iterator_bad() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i + "");
		}

		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String val = it.next();
			System.out.println(val);
			if ("5".equals(val)) {
				list.remove(val);
				System.out.println("remove " + val);
			}
		}
	}

	/**
	 * iterator 遍历
	 * {@link Iterator#remove()}同样调用了{@link List#remove(Object)}，{@link ArrayList#modCount}会增加1，
	 * 但{@link java.util.ArrayList.Itr#expectedModCount}也会增加1，因此modCount和expectedModCount值还是一样
	 **/
	@Test
	public void test_single_iterator() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i + "");
		}

		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String val = it.next();
			System.out.println(val);
			if ("5".equals(val)) {
				it.remove();
				System.out.println("remove " + val);
			}
		}
	}

	/**
	 * 原因同{@link #test_single_iterator_bad()}，但没有修改方案
	 *
	 * @throws java.util.ConcurrentModificationException
	 */
	@Test
	public void test_single_for_each_bad() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i + "");
		}

		for (String val : list) {
			System.out.println(val);
			if ("5".equals(val)) {
				list.remove(val);
				System.out.println("remove " + val);
			}
		}
	}

	@Test
	public void test_concurrent_map() {
		ConcurrentMap<Integer, String> map = new ConcurrentHashMap<>();
		for (int i = 0; i < 10; i++) {
			map.put(i, i + "");
		}

		for (Integer key : map.keySet()) {
			if (5 == key) {
				map.remove(key);
			}
		}
	}

}
