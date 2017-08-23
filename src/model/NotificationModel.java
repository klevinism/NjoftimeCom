package model;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;

import model.web.WebConnection;
import model.web.WebPageManipulation;

public class NotificationModel implements Runnable{

	private String PostNumber; 
	private WebClient conn;
	private WebPageManipulation wpm;
	private static String njoftimeUrl; 
	private static String text = "" 
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
				+ "future@delimeta.info"
			+ "[/EMAIL]<br/>";
	
	public NotificationModel(WebClient wc,String postNr){
		conn = wc;
		PostNumber = postNr;
		njoftimeUrl= "http://www.njoftime.com/editpost.php?p=!&do=editpost";
	}
	
	@Override
	public void run() {
		String url =  njoftimeUrl.replaceAll("!", ""+PostNumber);
		System.out.println(url);

		try {
			HtmlPage pg = conn.getPage(url);
			wpm = new WebPageManipulation(pg);
			writeToFile("C:\\Users\\Silver\\Desktop\\indexNjoftime.html",pg.asXml(),false);
			HtmlTextArea txt = (HtmlTextArea) wpm.getElementById("vB_Editor_001_editor");
			//txt.setText("-");
			txt.setText(text);
			HtmlSubmitInput refresh = (HtmlSubmitInput) wpm.getElementById("vB_Editor_001_save");
			refresh.click();
			//System.out.println(refresh.asXml());
		} catch (Exception e) {
			e.printStackTrace();
			writeToFile("C:\\Users\\Silver\\Desktop\\indexNjoftime.html",e.getMessage()+" \n\r ",true);
		}
	}
	
	
	private void writeToFile(String path, String context, boolean append){
		BufferedWriter out = null;
		try  
		{
		    FileWriter fstream = new FileWriter(path, append); //true tells to append data.
		    out = new BufferedWriter(fstream);
		    out.write(context);
		}
		catch (IOException e)
		{
		    System.err.println("Error: " + e.getMessage());
		}
		finally
		{
		    if(out != null) {
		        try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
	}

}
