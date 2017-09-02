package tester;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.NotificationDataController;
import model.NotificationDataModel;
import model.NotificationModel;

public class JUnitXMLTester {

	private NotificationModel dataController;

	@Test
	public void test() {

		try {
			dataController = new NotificationModel("454545");
			System.out.println(dataController);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
