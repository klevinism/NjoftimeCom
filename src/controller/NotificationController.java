package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlOptionGroup;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import model.NotificationModel;
import model.web.WebConnection;
import model.web.WebPageManipulation;

public class NotificationController {
	private WebClient webClient;

	private static String USERNAME = "Qendra e studimit 'Future'";
	private static String PASSWORD = "Klklkl007";
	private static String[] NOTIFICATION_PAGES;
	private ExecutorService executor = Executors.newFixedThreadPool(5); 
	
	
	private WebConnection conn = null;
	private WebPageManipulation pageManipulation = null;
	
	public NotificationController() throws MalformedURLException, IOException{		
		conn = new WebConnection();
		webClient = new WebClient();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		//conn.connect("http://www.njoftime.com/");
		HtmlPage page = webClient.getPage("http://www.njoftime.com/");
		pageManipulation = new WebPageManipulation(page);
		
		login();
	}
	
	public void setArguments(String[] args){
		NOTIFICATION_PAGES = args;
	}
	
	private void login() throws IOException{
		HtmlTextInput username = (HtmlTextInput) pageManipulation.getElementById("navbar_username");
		HtmlPasswordInput password = (HtmlPasswordInput) pageManipulation.getElementById("navbar_password");
		HtmlSubmitInput  submit = (HtmlSubmitInput)pageManipulation.getByXPath("//input[@class='loginbutton']").get(0);
		
		username.setValueAttribute(USERNAME);
		password.setValueAttribute(PASSWORD);
		HtmlPage loginPage = submit.click();

		loginPage.getWebClient().waitForBackgroundJavaScript(30000);
		loginPage.getWebClient().waitForBackgroundJavaScriptStartingBefore(30000);
		
		pageManipulation.setPage(loginPage);
	}
	
	public void run(){
		for (int i = 0; i < NOTIFICATION_PAGES.length; i++) {  

            Runnable worker = new NotificationModel(webClient, NOTIFICATION_PAGES[i]);  
            executor.execute(worker);//calling execute method of ExecutorService  
        }  
        executor.shutdown();  
                
        while (!executor.isTerminated()) { }//System.exit(0); }
	}
	
	
	//Opens njoftime page
	//logs in
	//creates 3 model tasks to be executed
	//done

}
