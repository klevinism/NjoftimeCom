package njoftimeal;

import java.awt.EventQueue;

import controller.NotificationController;
import controller.VersionController;
import model.updater.Updater;


public class Main {

	public static void main(String[] args) {
		VersionController vController = new VersionController();		
		if(vController.isUpToDate()){
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						NotificationController nc = new NotificationController();
						nc.setArguments(args);
						nc.run();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}else{
			Updater updater = new Updater();
			updater.initComponents();
			updater.setVisible(true);
			updater.download();
		}

	}

}
