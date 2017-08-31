package model;

import java.util.Map;

import org.w3c.dom.NamedNodeMap;

public class NotificationDataModel {
	
	private String username;
	private String password;
	private String notificationTitle;
	private String notificationBody;
	private Map<String,String> xmlData;
	private Map<String,NamedNodeMap> xmlAttributes;
	
	public NotificationDataModel() {
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
		if(this.username == "" || this.username == null)
			return this.xmlData.get("AdminUsername");
		else
			return this.username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		if(this.password == "" || this.password == null)
			return this.xmlData.get("AdminPassword");
		else
			return this.password;
	}
	
	public void setNotificationTitle(String notificationTitle) {
		this.notificationTitle = notificationTitle;
	}
	
	public String getNotificationTitle() {
		if(this.notificationTitle == "" || this.notificationTitle == null)
			return this.xmlData.get("NotificationTitle");
		else
			return this.notificationTitle;
	}
	
	public void setNotificationBody(String notificationBody) {
		this.notificationBody = notificationBody;
	}
	
	public String getNotificationBody() {
		if(this.notificationBody == "" || this.notificationBody == null)
			return this.xmlData.get("NotificationBody");
		else
			return this.notificationBody;
	}
		
	public String toString() {
		return "Data:\n Username = "+this.getUsername()+";\n Password = "+this.getPassword()+
				";\n Notification Body="+this.getNotificationBody()+";\n Notification Title="+this.getNotificationTitle();
	}

}
