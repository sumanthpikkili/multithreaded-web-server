/*
 *@author Sumanth Pikkili
 *UTA ID - 1001100941
 */

import java.io.* ;
import java.net.* ;
import java.util.* ;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


final class HttpRequestGet implements Runnable {
    
    Socket ServerSkt;
    public HttpRequestGet(Socket connectSkt) throws Exception 
	{
	this.ServerSkt = connectSkt;
    }
    
    // Implementation of the run() method of the Runnable interface.
    public void run() 
	{
	try {
		//Processing the incoming request from the server.
	    processRequest();
		} 
	catch (Exception runTimeexception) 
		{
	    System.out.println(runTimeexception);
		}
    }

    private void processRequest() throws Exception 
	{
		PrintWriter writer; 
		
		//Creating the logfile log.txt 
		writer = new PrintWriter(new FileWriter("log.txt", true)); 
		
		DateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		
		Calendar calender = Calendar.getInstance();
		
		// inputstream initialization
		InputStream io = ServerSkt.getInputStream();
		
		// outputstream initialization
		DataOutputStream out = new DataOutputStream(ServerSkt.getOutputStream());
		
		//InputStream filters.
		BufferedReader br = new BufferedReader(new InputStreamReader(io));

		// Reading the HTTP Request from the client
		String httpLineRequest = br.readLine();
		
		//GET the requested file name from given URL
		StringTokenizer Str_Token = new StringTokenizer(httpLineRequest);
		Str_Token.nextToken(); 
		String StrFileName = Str_Token.nextToken();
		int index = StrFileName.lastIndexOf('/');
		String filepath = StrFileName.substring(index+1);
		StrFileName = filepath;
		
		
			//to open the file  request.
			FileInputStream Fis = null ;
			boolean fileExistence = true ;
			try 
			{
				Fis = new FileInputStream(StrFileName);
			} 
			catch (FileNotFoundException e) 
			{
				fileExistence = false ;
			}
			
			writer.println("-------------------------------------");
			writer.println("*************************************");
			writer.println("-------------------------------------");

			System.out.println("Requesting from client side...");
			System.out.println(httpLineRequest);
			writer.println(httpLineRequest);
			String header_Line = null;
		
			while ((header_Line = br.readLine()).length() != 0) 
			{
				System.out.println(header_Line);
			}
		
			String Http_Status_Code = null;
			String Http_Content_Type = null;
			String Http_Content_Body = null;
			
			if (fileExistence) 
			{
				Http_Status_Code = "HTTP/1.1 200 OK \r\n";
				Http_Content_Type = "Content-Type: " +	Str_Content_Type(StrFileName) + "\r\n";
				writer.println("\r\nHTTP/1.1 200 OK \r\n");
				writer.println(date.format(calender.getTime()));
				writer.println("-------------------------------------");
				writer.println("*************************************");
				writer.println("-------------------------------------");
			} 
			
			else 
			{
				Http_Status_Code = "HTTP/1.0 404 Not Found \r\n";
				Http_Content_Type = "Content-Type: text/html \r\n" ;
				writer.println("HTTP/1.0 404 Not Found \r\n");
				Http_Content_Body = "File Not Found";
				writer.println(date.format(calender.getTime()));
				writer.println("-------------------------------------");
				writer.println("*************************************");
				writer.println("-------------------------------------");
			}
			
			
			out.writeBytes(Http_Status_Code);
			
			
			out.writeBytes(Http_Content_Type);
			
			out.writeBytes("\r\n");
			
			
			if (fileExistence) 
			{
				Stream_Bytes(Fis, out);
				Fis.close();
			} 
			else 
			{
				out.writeBytes(Http_Content_Body) ;
			}
			
			//Close all connections
			out.close();
			br.close();
			ServerSkt.close();
			writer.close();
    }

    private static void Stream_Bytes(FileInputStream File_Input_Stream, 
				  OutputStream out) throws Exception 
	{
			byte[] buffer = new byte[1024];
			int bytes = 0;
		
			// Put requested file into the socket's output stream.
			while ((bytes = File_Input_Stream.read(buffer)) != -1) 
			{
				out.write(buffer, 0, bytes);
			}
    }
	
	// Contents of the requested file are displayed on the command prompt
	
	private static String Str_Content_Type(String St_File_Name) 
	{
		//Check to see if the file exists
		if(St_File_Name.endsWith(".htm") || St_File_Name.endsWith(".html")) 
		{
			return "text/html";
		}
		
		return "application/octet-stream" ;
    }
	
    
}
