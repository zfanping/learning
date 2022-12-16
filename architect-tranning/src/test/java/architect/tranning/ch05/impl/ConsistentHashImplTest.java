package architect.tranning.ch05.impl;

import architect.tranning.ch05.ConsistentHash;
import architect.tranning.ch05.util.MathUtil;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

/**
 * <b>功能：</b>XXX<br>
 * <b>Copyright TCSL</b>
 * <li>版本| 修改日期| 作者| 变更内容</li>
 * <hr>
 * <li>v1.0| 20201119| 曾凡平| 创建</li>
 */
public class ConsistentHashImplTest {
	@Test
	public void test() {
		// 每个节点虚拟节点的数量
		int replicasPerNode = 200;
		ConsistentHash consistentHash = new ConsistentHashImpl(replicasPerNode);
		int nodeCount = 10; // 节点总数
		Map<String, Integer> statisticsMap = new TreeMap<>(); // key:nodeId；value:节点上key的数量
		for (int i = 0; i < nodeCount; i++) {
			String nodeId = "192.168.1.10" + i + ":6666";
			consistentHash.addNode(nodeId);
			statisticsMap.put(nodeId, 0);
		}

		for (int i = 0; i < 1000000; i++) {
			String key = "key-" + i;
			String nodeId = consistentHash.getNodeId(key);
			statisticsMap.put(nodeId, statisticsMap.get(nodeId) + 1);
		}

		double[] x = new double[nodeCount];
		int i = 0;
		for (Map.Entry<String, Integer> entry : statisticsMap.entrySet()) {
			String nodeId = entry.getKey();
			Integer count = entry.getValue();
			System.out.println("节点" + nodeId + "路由次数：" + count);
			x[i] = count;
			i++;
		}

		System.out.println(replicasPerNode + "个虚拟节点标准差：" + MathUtil.standardDiviation(x));

	}
}