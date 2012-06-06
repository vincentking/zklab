package vin.coco.zklab;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ServerNode extends Thread{
	int sleep;
	String name;
	public ServerNode(int sleep, String name)
	{
		this.sleep = sleep;
		this.name = name;
	}
	@Override
	public void run() {
		try
		{
			ZooKeeper zk = new ZooKeeper("127.0.0.1:2181/clustertest", 500000, new Watcher(){
				@Override
				public void process(WatchedEvent event) {
					if (!event.getPath().equals("null"))
						System.out.println(name + " " + event.getPath() +" "+event.getType());
				}				
			});
			String path = zk.create("/cn","cn".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
//			System.out.println("create node : "+path);
			Thread.sleep(this.sleep);
			System.out.println(this.name + " getData " +new String(zk.getData(path,true, null)));
			Thread.sleep(this.sleep);
			System.out.println(this.name +" sleep done.");
			zk.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
