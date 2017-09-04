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

import java.io.IOException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import java.util.Date;

import Utils.Util;
import model.web.WebPageManipulation;

public class NotificationModel implements Runnable{

	private WebClient conn;
	private WebPageManipulation wpm;
	private HtmlPage page;
	private Date date = new Date();

	private String PostNumber;
	private static String USERNAME = "Qendra e studimit 'Future'";
	private static String PASSWORD = "Klklkl007";
	private static String njoftimeUrl= "http://www.njoftime.com/editpost.php?p=!&do=editpost";
	private static String NOTIFICATION_TITLE = "Title";
	private static String NOTIFICATION_BODY;
	
	/*= "" 
			+ "[B][SIZE=4][COLOR=#0000CD]"
				+ "OFROJME KURSE PROFESIONALE PROGRAMIMI :"
			+ "[/COLOR][/SIZE][/B]<br/><br/>"
			+ "Programim C, Programim C++, Programim Java, Visual Basic, "
			+ "Oracle DB, Programim Php, Linux, Rrjetat kompjuterike, Siguria ne rrjet, Telefoni, Windows 7, Algoritmike, "
			+ "Programim PLC, Sisteme multimediale, Arkitekture Kompjuteri,SPSS ... etj.<br/><br/>"
			+ "Web Design: (HTML 5- CSS-JavaScript -JQyery), Joomla, Web Programming(DB. SQL Server- ASP.net)/(PHP-MY SQL), Google Map Aplication,MVC Visual Studio,"
			+ "Mobile Application,JQyery Mobile Application, Programim C, C++,C#, Visual Studio, Java,MatLab, Oracle DB,?.etj<br/><br/>"
			+ "[U][COLOR=#0000CD][B]"
				+ "OFERTAT"
			+ "[/B][/COLOR][/U]<br/><br/>"
			+ "[B]"
				+ "(Individual)<br/>"
			+ "[/B]"
			+ "Cmimi per 1-3 persona: 2000L seanca<br/><br/>"
			+ "[B]"
				+ "(Grup)<br/>"
			+ "[/B]"
			+ "Cmimi per me shume se 3 persona: 1500L seanca<br/><br/>"
			+ "[U]"
				+ "PER ME SHUME INFO:<br/>"
			+ "[/U]"
			+ "cel: 0696433538<br/>"
			+ "email: [EMAIL=&quot;future@delimeta.info&quot;]"
				+ "qendrafuture@hotmail.com"
			+ "[/EMAIL]<br/>";*/
	
	public NotificationModel(String postNr) throws Exception{
		conn = new WebClient();
		conn.getOptions().setJavaScriptEnabled(false);
		conn.getOptions().setCssEnabled(false);
		PostNumber = postNr;

	}
	
	@Override
	public void run() {
		String url =  njoftimeUrl.replaceAll("!", ""+PostNumber);
		try {
			Util.writeToFile(
				Util.userPath()+"\\Desktop\\indexNjoftime.txt",
				"\n ----------- \n"+"Refreshed " + PostNumber + " on date: " +date+" \n ",
				true
			);

			this.login();
			this.getXMLData();
			System.out.println("---" + NOTIFICATION_TITLE + "*********");
			page = conn.getPage(url);
			wpm = new WebPageManipulation(page);
			
			HtmlTextInput title = (HtmlTextInput) wpm.getElementById("titlefield");
			HtmlTextArea body = (HtmlTextArea) wpm.getElementById("vB_Editor_001_editor");
			HtmlSubmitInput refresh = (HtmlSubmitInput) wpm.getElementById("vB_Editor_001_save");
			body.setText(NOTIFICATION_BODY);
			title.setValueAttribute(NOTIFICATION_TITLE);
			title.setText(NOTIFICATION_TITLE);
			System.out.println(title.asText() + "---" + NOTIFICATION_TITLE);
			//refresh.click();
		} catch (Exception e) {
			e.printStackTrace();
			Util.writeToFile("C:\\Users\\Silver\\Desktop\\indexNjoftime.html",e.getMessage()+" \n\r ",true);
		}finally {
			System.exit(0);
		}
	}
	
	private void login() throws IOException{
		page = conn.getPage("http://www.njoftime.com/");
		wpm = new WebPageManipulation(page);

		HtmlTextInput username = (HtmlTextInput) wpm.getElementById("navbar_username");
		HtmlPasswordInput password = (HtmlPasswordInput) wpm.getElementById("navbar_password");
		HtmlSubmitInput  submit = (HtmlSubmitInput)wpm.getByXPath("//input[@class='loginbutton']").get(0);
		
		username.setValueAttribute(USERNAME);
		password.setValueAttribute(PASSWORD);
		HtmlPage loginPage = submit.click();

		loginPage.getWebClient().waitForBackgroundJavaScript(30000);
		loginPage.getWebClient().waitForBackgroundJavaScriptStartingBefore(30000);
		
		wpm.setPage(loginPage);
	}
	
	public void getXMLData() throws Exception{		
		NotificationDataModel data = new NotificationDataModel();
		USERNAME = data.getUsername();
		PASSWORD = data.getPassword();
		NOTIFICATION_BODY = data.getNotificationBody();
		data.setRandomness(true);
		NOTIFICATION_TITLE = data.getNotificationTitle();
		
		System.out.println(NOTIFICATION_TITLE);
	}
}
