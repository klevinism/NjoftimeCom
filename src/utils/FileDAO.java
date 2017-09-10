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

package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileDAO {

	public String read(String path){
		FileReader reader = null;
		BufferedReader in = null;
		String text="",line="";
		try  
		{
			reader = new FileReader(path);
		    in = new BufferedReader(reader);
		    while((line = in.readLine())!= null){
		    	text += line;
		    }
		    in.close();
		}
		catch (Exception e)
		{
		    System.err.println("Error: " + e.getMessage());
		}finally{
			if(in != null) {
		        try {
		        	in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
		return text;
	}
	
	public void write(String path, String context, boolean append){
		BufferedWriter out = null;
		try  
		{
		    FileWriter fstream = new FileWriter(path, append);
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
					e.printStackTrace();
				}
		    }
		}
	}
	
}
