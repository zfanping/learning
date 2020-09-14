package com.example.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;

import static org.apache.zookeeper.AsyncCallback.DataCallback;
import static org.apache.zookeeper.AsyncCallback.StringCallback;
import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * <b>功能：</b>XXX<br>
 * <b>Copyright TCSL</b>
 * <li>版本| 修改日期| 作者| 变更内容</li>
 * <hr>
 * <li>v1.0| 20191028| 曾凡平| 创建</li>
 */
public class Master implements Watcher {
	private static final Logger log = LoggerFactory.getLogger(Master.class);
	private ZooKeeper zk;
	private String hostPort;

	String serverId = new Random().nextLong() + "";
	static boolean isLeader = false;

	StringCallback masterCreateCallback = new StringCallback() {
		@Override
		public void processResult(int rc, String path, Object ctx, String name) {
			log.info("begin masterCreateCallback({},{},{},{})", Code.get(rc), path, ctx, name);
			switch (Code.get(rc)) {
				case CONNECTIONLOSS:
					checkMaster();
					break;
				case OK:
					isLeader = true;
					break;
				default:
					isLeader = false;
			}
			log.info("I'm " + (isLeader ? "" : "not ") + "the leader");
			log.info("end masterCreateCallback");
		}
	};

	DataCallback masterCheckCallback = new DataCallback() {
		@Override
		public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
			log.info("begin masterCheckCallback({}, {}, {}, {}, {})", Code.get(rc), path, ctx, new String(data), stat);
			switch (Code.get(rc)) {
				case CONNECTIONLOSS:
					checkMaster();
					break;
				case NONODE:
					runForMaster();
					break;
			}
			log.info("end masterCheckCallback");
		}
	};

	StringCallback createParentCallback = new StringCallback() {
		@Override
		public void processResult(int rc, String path, Object ctx, String name) {
			log.info("begin createParentCallback({}, {}, {})", Code.get(rc), path, ctx,name);
			switch (Code.get(rc)) {
				case CONNECTIONLOSS:
					createParent(path, (byte[]) ctx);
					break;
				case OK:
					log.info("Parent created");
					break;
				case NODEEXISTS:
					log.warn("Parent already registered: " + path);
					break;
				default:
					log.error("Something went wrong: ",
							KeeperException.create(Code.get(rc), path));
			}
			log.info("end createParentCallback");
		}
	};

	public Master(String hostPort) {
		this.hostPort = hostPort;
	}

	public void startZk() throws IOException {
		zk = new ZooKeeper(hostPort, 15000, this);
	}

	@Override
	public void process(WatchedEvent watchedEvent) {
		System.out.println("process: " + watchedEvent);
	}

	public void checkMaster() {
		log.info("begin checkMaster");
		zk.getData("/master", false, masterCheckCallback, null);
		log.info("end checkMaster");
	}

	public void runForMaster() {
		log.info("begin runForMaster");
		zk.create("/master", serverId.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, masterCreateCallback, null);
		log.info("end runForMaster");
	}

	public void bootstrap() {
		createParent("/workers", new byte[0]);
		createParent("/tasks", new byte[0]);
		createParent("/assign", new byte[0]);
		createParent("/status", new byte[0]);
	}

	private void createParent(String path, byte[] data) {
		zk.create(path, data, OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, createParentCallback, data);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		Master m = new Master("127.0.0.1:2181");
		m.startZk();
		m.bootstrap();
		m.runForMaster();
		
		Thread.sleep(600000);
		if (isLeader) {
			System.out.println("I'm the leader");
		} else {
			System.out.println("some one is the leader");
		}

//		m.stopZK();
	}
}
