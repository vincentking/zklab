package vin.coco.zklab;

import java.util.Date;
import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ClusterTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ZooKeeper zk = new ZooKeeper("127.0.0.1:2181/clustertest", 500000, new Watcher() {
			public void process(WatchedEvent event) {
				if (!event.getPath().equals("null"))
					System.out.println(new Date().toLocaleString()+" WatchedEvent: "+event.getPath()+" "+event.getType()+" "+event.getState());
//					System.out.println("WatchedWrapperEvent: "+event.getWrapper().getPath()+" "+event.getWrapper().getType()+" "+event.getWrapper().getState());
			}
		});

		while(true)
		{
			List<String> childs = zk.getChildren("/", true);
//			System.out.println("root childs "+childs);
			for(String c : childs)
//				System.out.println(c+" data is "+new String(zk.getData("/"+c, true, null)));
				zk.getData("/"+c, true, null);
			Thread.sleep(1000);
		}		

	}
}
