package com.example.demo.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>功能：</b>XXX<br>
 * <b>Copyright TCSL</b>
 * <li>版本| 修改日期| 作者| 变更内容</li>
 * <hr>
 * <li>v1.0| 20190612| 曾凡平| 创建</li>
 */

/**
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
	static class OOMObject {

	}

	/*public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<>();
		while (true) {
			list.add(new OOMObject());
		}
	}*/
	public static void main(String[] args) {
		while (true) {
			doNew();
		}
	}

	private static void doNew() {
		List<OOMObject> list = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			list.add(new OOMObject());
		}
	}
}
