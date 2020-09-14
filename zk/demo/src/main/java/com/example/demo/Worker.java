package com.example.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;

import static org.apache.zookeeper.AsyncCallback.StatCallback;
import static org.apache.zookeeper.AsyncCallback.StringCallback;
import static org.apache.zookeeper.KeeperException.Code;

/**
 * <b>功能：</b>XXX<br>
 * <b>Copyright TCSL</b>
 * <li>版本| 修改日期| 作者| 变更内容</li>
 * <hr>
 * <li>v1.0| 20191028| 曾凡平| 创建</li>
 */
public class Worker implements Watcher {
	public static final Logger LOG = LoggerFactory.getLogger(Worker.class);
	ZooKeeper zk;
	String hostPort;
	String serverId = new Random().nextLong() + "";
	String status;

	public Worker(String hostPort) {
		this.hostPort = hostPort;
	}

	public void startZk() throws IOException {
		zk = new ZooKeeper(hostPort, 15000, this);
	}

	@Override
	public void process(WatchedEvent watchedEvent) {
		System.out.println("process: " + watchedEvent);
	}

	void register() {
		zk.create("/workers/worker_" + serverId, "IDLE".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new StringCallback() {
			@Override
			public void processResult(int rc, String path, Object ctx, String name) {
				switch (Code.get(rc)) {
					case CONNECTIONLOSS:
						register();
						break;
					case OK:
						LOG.info("Registered successfully: {}", serverId);
						break;
					case NODEEXISTS:
						LOG.warn("Already registered: {}", serverId);
						break;
					default:
						LOG.error("Something went wrong: {}", KeeperException.create(Code.get(rc), path));
				}
			}
		}, null);
	}

	StatCallback statusUpdateCallback = new StatCallback() {
		@Override
		public void processResult(int rc, String path, Object ctx, Stat stat) {
			switch (Code.get(rc)) {
				case CONNECTIONLOSS:
					updateStatus((String) ctx);
			}
		}
	};

	synchronized private void updateStatus(String status) {
		if (status == this.status) {
			zk.setData("/workers/worker_" + serverId, status.getBytes(), -1, statusUpdateCallback, status);
		}
	}

	public void setStatus(String status) {
		this.status = status;
		updateStatus(status);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		Worker worker = new Worker("127.0.0.1:2181");
		worker.startZk();
		worker.register();
		Thread.sleep(30000);
	}
}
