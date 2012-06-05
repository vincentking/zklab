package vin.coco.zklab;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ClusterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ZooKeeper zk = new ZooKeeper("127.0.0.1:2181/", 500000, new Watcher() {
			public void process(WatchedEvent event) {
				System.out.println("WatchedEvent: "+event.getPath()+" "+event.getType());
			}
		});
		for(int i=1; i<6; i++)
		{
			ServerNode cn = new ServerNode(i*3000);
			cn.start();
		}			
		while(true)
		{
			System.out.println("root childs "+zk.getChildren("/", true));
			Thread.sleep(1000);
		}
//		System.out.println(zk.getChildren("/znode1", true));
//		System.out.println(new String(zk.getData("/znode1", true, null)));
//		System.out.println(zk.setData("/znode1", ("new znode1 data"+System.currentTimeMillis()+"").getBytes(), -1));
		//zk.delete(zk.create("/znode1/zn1","testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL), -1);
//		System.out.println(zk.create("/cn1","testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL));
//		Thread.sleep(3000);
//		zk.close();
	}
}
