/**
 *@author Sumanth Pikkili
 *UTA ID - 1001100941
 */

import java.io.* ;
import java.net.* ;
import java.util.* ;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public final class WebServer 
{
    
	public static void main(String args[]) 
	{
	
		try
		{
		int thread = 0;
		// Fetching the command line argument
		int PortServer;
                if(args[0].equals("def"))
		{
		PortServer = 8080;
		}
		else
		{
			PortServer = Integer.parseInt(args[0]);
		}
		
		// Creates the Server Socket 
		ServerSocket skt = new ServerSocket(PortServer);
		
		while (true) 
		{
			System.out.println("Waiting for the client");
			// Waiting for a TCP connection request
			Socket connection = skt.accept();
			
			
			// creating the request object to the HTTP request message
			HttpRequestGet req = new HttpRequestGet (connection);
			
			// Create a new Thread
			Thread PortServer_Thread = new Thread(req);
			
			// Start the server thread
			PortServer_Thread.start();
			System.out.println("Thread Number:"+thread+"\n");
			thread = thread+1;
		}
		}

	catch (Exception e)
        {
        	System.err.println("Serv: Exception in main: " + e); //to catch the exceptions if there are any
        }
		
    }
}
