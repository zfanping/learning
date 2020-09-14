package com.example.demo.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>功能：</b>XXX<br>
 * <b>Copyright TCSL</b>
 * <li>版本| 修改日期| 作者| 变更内容</li>
 * <hr>
 * <li>v1.0| 20191122| 曾凡平| 创建</li>
 */
public class ConcurrentModificationExceptionTest {
	/**
	 *
	 */
	@Test
	public void test_single_thread_1() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add(i);
		}

		for (Integer i : list) {
			if (i == 10) {
				list.remove(i);
			}
		}
	}
}
