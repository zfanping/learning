package architect.tranning.ch05;

/**
 * 一致性哈希
 */
public interface ConsistentHash {
	/**
	 * 添加节点
	 *
	 * @param nodeId 节点id
	 */
	void addNode(String nodeId);

	/**
	 * 移除节点
	 *
	 * @param nodeId 节点id
	 */
	void removeNode(String nodeId);

	/**
	 * 获取key所在节点id
	 *
	 * @param key key
	 * @return key所在节点id
	 */
	String getNodeId(String key);
}
