package model;

import java.util.Map;
import java.util.Random;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.globals.Paths;

public class NotificationDataModel {
	
	private String username;
	private String password;
	private String notificationTitle;
	private String notificationBody;
	private XMLFileManipulation dataController;
	private Map<String,String> xmlData;
	private Map<String,NamedNodeMap> xmlAttributes;
	private Random rand = new Random();
	private boolean randomness;
	private NodeList titleElement;
	private NodeList bodyElement;
	
	public NotificationDataModel() {
		dataController  = new XMLFileManipulation(Paths.SettingsXmlLocalFile);
	}
	
	public NotificationDataModel(Map<String,String> xmlData, Map<String,NamedNodeMap> xmlAttributes){
		this.xmlData = xmlData;
		this.xmlAttributes = xmlAttributes;
	}
	
	public Map<String,NamedNodeMap> getXmlAttributes() {
		return xmlAttributes;
	}

	public void setXmlAttributes(Map<String,NamedNodeMap> xmlAttributes) {
		this.xmlAttributes = xmlAttributes;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		if(this.username == "" || this.username == null){
			return this.dataController.getElement("AdminUsername").item(0).getTextContent();
		}
		else
			return this.username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		if(this.password == "" || this.password == null)
			return this.dataController.getElement("AdminPassword").item(0).getTextContent();
		else
			return this.password;
	}
	
	public void setNotificationTitle(String notificationTitle) {
		this.notificationTitle = notificationTitle;
	}
	
	public String getNotificationTitle() {
		if(this.notificationTitle == "" || this.notificationTitle == null){
			titleElement = this.dataController.getElement("Title"); 
			return this.getRandomnessForElement(titleElement).getTextContent();
		}
		else
			return this.notificationTitle;
	}
	
	public void setRandomness(boolean randomness){
		this.randomness = randomness;
	}
	
	private Node getRandomnessForElement(NodeList nodeList){
		int randomNr = (randomness) ? rand.nextInt(nodeList.getLength()) : 0;
		return nodeList.item(randomNr);
	}
	
	public void setNotificationBody(String notificationBody) {
		this.notificationBody = notificationBody;
	}
	
	public String getNotificationBody() {
		if(this.notificationBody == "" || this.notificationBody == null){
			bodyElement = this.dataController.getElement("NotificationBody");
			return this.getRandomnessForElement(bodyElement).getTextContent();
		}
		else
			return this.notificationBody;
	}
		
	public String toString() {
		return "Data:\n Username = "+this.getUsername()+";\n Password = "+this.getPassword()+
				";\n Notification Body="+this.getNotificationBody()+";\n Notification Title="+this.getNotificationTitle();
	}

}
