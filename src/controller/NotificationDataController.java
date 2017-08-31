package controller;

import model.NotificationDataModel;
import model.XMLFileManipulation;
import model.globals.Paths;

public class NotificationDataController {
	private XMLFileManipulation xmlParser;

	public NotificationDataController(){
		xmlParser  = new XMLFileManipulation(Paths.SettingsXmlLocalFile);
	}
	
	public NotificationDataModel getNotificationData() throws Exception {
		return xmlParser.Read(new NotificationDataModel().getClass());
	}
}
