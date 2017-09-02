/*
 * MIT License

Copyright (c) 2017 Klevin Delimeta

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

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
		this.setXmlData(xmlData);
		this.xmlAttributes = xmlAttributes;
	}
	
	public Map<String,NamedNodeMap> getXmlAttributes() {
		return xmlAttributes;
	}
	
	public Map<String,String> getXmlData() {
		return xmlData;
	}

	public void setXmlData(Map<String,String> xmlData) {
		this.xmlData = xmlData;
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
