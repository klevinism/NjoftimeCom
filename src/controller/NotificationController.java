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

package controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.NotificationModel;
import utils.Util;

public class NotificationController {
	private static String[] NOTIFICATION_PAGES;
	private ExecutorService executor = Executors.newFixedThreadPool(5);
	
	public void setArguments(String[] args){
		if(args.length != 0 && args != null)
			NOTIFICATION_PAGES = args;
		else
			NOTIFICATION_PAGES = Util.getFileArgs();
	}
	
	public void run() throws Exception{
		
		for (int i = 0; i < NOTIFICATION_PAGES.length; i++) {
            Runnable worker = new NotificationModel(NOTIFICATION_PAGES[i]);
            executor.execute(worker);	//calling execute method of NotificationModel  
        }  
        executor.shutdown();  
                
        while (!executor.isTerminated()){ }
	}
}