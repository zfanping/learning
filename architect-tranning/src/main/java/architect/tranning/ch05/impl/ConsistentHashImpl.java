package architect.tranning.ch05.impl;

import architect.tranning.ch05.ConsistentHash;
import architect.tranning.ch05.HashFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 基于虚拟节点的一致性Hash算法
 */
public class ConsistentHashImpl implements ConsistentHash {
	private static final Logger log = LoggerFactory.getLogger(ConsistentHashImpl.class);
	/**
	 * 物理节点
	 */
	private List<String> nodes = new LinkedList<>();

	/**
	 * 每个节点虚拟节点的数量
	 */
	private int replicasPerNode;

	/**
	 * 虚拟节点
	 * key: 虚拟节点的hash值
	 * value: 物理节点id
	 */
	private SortedMap<Long, String> virtualNodes = new TreeMap<>();

	/**
	 * 哈希函数接口
	 */
	private HashFunction hashFunction = new HashFunctionImpl();

	public ConsistentHashImpl(int replicasPerNode) {
		this.replicasPerNode = replicasPerNode;
		log.info("虚拟节点的数量：{}", replicasPerNode);
	}

	public ConsistentHashImpl() {
		//默认每个节点虚拟节点的数量150
		this(150);
	}

	@Override
	public void addNode(String nodeId) {
		if (nodeId != null && !nodes.contains(nodeId)) {
			// 添加物理节点
			nodes.add(nodeId);
			// 添加虚拟节点
			for (int i = 0; i < replicasPerNode; i++) {
				Long hash = hashFunction.hash(nodeId + "-" + i);
				virtualNodes.put(hash, nodeId);
			}
		}
		log.info("添加节点{},物理节点总数：{}，虚拟节点总数：{}", nodeId, nodes.size(), virtualNodes.size());
	}

	@Override
	public void removeNode(String nodeId) {
		if (nodeId != null && !nodes.contains(nodeId)) {
			// 移除物理节点
			nodes.remove(nodeId);
			// 移除虚拟节点
			for (int i = 0; i < replicasPerNode; i++) {
				Long hash = hashFunction.hash(nodeId + "-" + i);
				virtualNodes.remove(hash);
			}
		}
		log.info("移除节点{},物理节点总数：{}，虚拟节点总数：{}", nodeId, nodes.size(), virtualNodes.size());
	}

	@Override
	public String getNodeId(String key) {
		if (key != null) {
			Long hash = hashFunction.hash(key);
			SortedMap<Long, String> tailMap = virtualNodes.tailMap(hash);
			// 所有虚拟节点hash值
			Long nodeHash = tailMap.isEmpty() ? virtualNodes.firstKey() : tailMap.firstKey();
			return virtualNodes.get(nodeHash);
		}
		return null;
	}
}
