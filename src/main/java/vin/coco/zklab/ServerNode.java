package vin.coco.zklab;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class ServerNode extends Thread{
	int sleep;
	public ServerNode(int sleep)
	{
		this.sleep = sleep;
	}
	@Override
	public void run() {
		try
		{
			ZooKeeper zk = new ZooKeeper("127.0.0.1:2181/", 500000, null);
			System.out.println("create node : "+zk.create("/cn","cn".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL));
			Thread.sleep(this.sleep);
			System.out.println(""+sleep+" sleep done.");
			zk.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
