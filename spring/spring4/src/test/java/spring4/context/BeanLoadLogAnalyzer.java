package spring4.context;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.*;

/**
 * <b>功能：</b>spring bean加载日志分析<br>
 * <b>Copyright TCSL</b>
 * <li>版本| 修改日期| 作者| 变更内容</li>
 * <hr>
 * <li>v1.0| 20201027| 曾凡平| 创建</li>
 */
public class BeanLoadLogAnalyzer {
	@Test
	public void test() throws IOException {
		File file = new File("E:\\doc\\2020\\10-26 门店启动速度优化\\88\\canyin7-info.log");

		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		Map<String, Stack<Item>> stackMap = new HashMap<>(); // <threadName,stack of threadName>
		List<Item> root = new ArrayList<>();

		String time = null;
		String threadName = null;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("[")) {
				// 第1行 [2020-10-27 14:37:17.587][][DEBUG][localhost-startStop-1][org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:220)]
				time = StringUtils.substringBetween(line, "[", "]");
				threadName = StringUtils.substringBetween(line, "[DEBUG][", "]");
			} else {
				if (time != null && threadName != null) {
					// 第2行 Creating instance of bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
					if (line.startsWith("Creating instance of bean ")) {
						Stack<Item> stack = getItemStack(stackMap, threadName);
						Item parent = stack.isEmpty() ? null : stack.peek();

						// 开始创建bean
						Item item = new Item();
						item.start = time;
						item.beanName = StringUtils.substringBetween(line, "'", "'");
						item.threadName = threadName;
						stack.push(item);

						if (parent != null) {
							parent.addChild(item);
						} else {
							root.add(item);
						}

					} else if (line.startsWith("Finished creating instance of bean ")) {
						// 完成创建bean Finished creating instance of bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
						Stack<Item> stack = getItemStack(stackMap, threadName);
						Item item = stack.pop();
						if (item != null) {
							String beanName = StringUtils.substringBetween(line, "'", "'");
							if (Objects.equals(beanName, item.beanName)) {
								item.end = time;
							} else {
								throw new IllegalStateException(item + " beanName不匹配 " + line);
							}
						} else {
							throw new IllegalStateException("找不到开始创建bean的item " + line);
						}
					}
					// 将时间设置为初始值
					time = null;
				}
			}
		}

		FileWriter write = new FileWriter(new File(file.getParent(), "bean_load.log"));

		long startupTime = parseTime(root.get(0).start).getTime();
		print(write, root, 0, startupTime);
		write.flush();
	}

	private Stack<Item> getItemStack(Map<String, Stack<Item>> stackMap, String threadName) {
		Stack<Item> stack = stackMap.get(threadName);
		if (stack == null) {
			stack = new Stack<>();
			stackMap.put(threadName, stack);
		}
		return stack;
	}

	private void print(FileWriter write, List<Item> items, int depth, long startupTime) throws IOException {
		if (CollectionUtils.isEmpty(items)) {
			return;
		}

		for (Item item : items) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < depth; i++) {
				sb.append("|  ");
			}
			sb.append("+- ");
			sb.append(item.beanName);
			if (item.end != null) {
				sb.append("(").append(parseTime(item.end).getTime() - startupTime).append(")");
			}
			sb.append("(").append(item.threadName).append(")");
			// 耗时
			sb.append("(").append(calcCosts(item)).append(")");
			sb.append("\r\n");
			write.write(sb.toString());

			// 打印依赖的bean
			print(write, item.children, depth + 1, startupTime);
		}
	}

	private long calcCosts(Item item) {
		if (item.start == null || item.end == null) {
			return -1;
		}

		try {
			Date start = parseTime(item.start);
			Date end = parseTime(item.end);
			return end.getTime() - start.getTime();
		} catch (Throwable e) {
			throw new RuntimeException("calcCosts " + item, e);
		}
	}


	private Date parseTime(String time) {
		try {
			// 时间格式 2020-10-27 14:37:17.587
			return DateUtils.parseDate(time, new String[]{"yyyy-MM-dd HH:mm:ss.SSS"});
		} catch (Throwable e) {
			throw new RuntimeException("parseTime " + time, e);
		}
	}

	static class Item {
		String start;
		String end;
		String beanName;
		String threadName;
		List<Item> children;

		void addChild(Item item) {
			if (children == null) {
				children = new ArrayList<>();
			}
			children.add(item);
		}

		@Override
		public String toString() {
			return "Item{" +
					"start='" + start + '\'' +
					", end='" + end + '\'' +
					", beanName='" + beanName + '\'' +
					'}';
		}
	}
}
