/*
 *@author Sumanth Pikkili
 *UTA ID - 1001100941
 */

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;

public class WebClient
{
	public static String File_Name;
	public static int Server_Port;
	public static String filepath;	
  
  public static void main(String[] args)
  {
	try{
	  
	  // Get the port number from the commandline.
	  char[] array = new char[4]; 
	  
	  args[0].getChars(17, 21, array, 0);
	  
	  // Get the File_Name from the commandline URL
	  int index = args[0].lastIndexOf('/');
	  if(args[0] != null)
	  {
	  filepath = args[0].substring(index+1);
	  System.out.println(args[0]);
	  }
	 else
	{
	filepath = "index.html";
	}

	if(args[0].equals("http://localhost:8080"))
	{
	System.out.println("Default Page /n");
	filepath = "index.html";
	}

	 
	Server_Port = Integer.parseInt(new String(array));
	  
	  //Initialize the file path.
	  File_Name = filepath;
	  
	  new WebClient();
	  
	  }
	catch (Exception e)
	 {
		  System.out.println("Kindly enter a valid URL of the format - http://localhost:<Server_Port_Number>/<file_name> \r\n");
		  e.getMessage();
	 }  
    
  }
  
  public WebClient()
  {
		String check_Server_Name = "localhost";
		int port = Server_Port;
		
		try
		{
		  // calling the socket 
		  Socket SocketWeb = Socketopen(check_Server_Name, port);
		   
		  String Result = toClientReadFromSocket(SocketWeb, "GET " + File_Name + " HTTP/1.1\n\n");
		  
		  
		  // Displaying the message from the server 
		  System.out.println(Result);
		  SocketWeb.close();
		}
		
		catch (Exception e)
		{
		  e.printStackTrace();
		}
  }
  
	  private String toClientReadFromSocket(Socket socket, String toClient) throws Exception
	  {
		try 
		{
		  // InputStream filters.
		  BufferedWriter Bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		  Bw.write(toClient);
		  Bw.flush();
		  
		 // OutputStream filters.
		  BufferedReader Br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		  StringBuilder StringBuilder = new StringBuilder();
		  String getString;
		  while ((getString = Br.readLine()) != null)
		  {
			  StringBuilder.append(getString + "\n");
		  }
		  
		  Br.close();
		  return StringBuilder.toString();
		} 
		catch (IOException inputexc) 
		{
		  inputexc.printStackTrace();
		  throw inputexc;
		}
	  }
  
	  private Socket Socketopen(String get_Server, int port) throws Exception
	  {
		Socket Client_Socket;
		
		try
		{
		  InetAddress Inet_Address = InetAddress.getByName(get_Server);
		  SocketAddress Socket_Address = new InetSocketAddress(Inet_Address, port);
	  
		  
		  Client_Socket = new Socket();
	  
		  
		  int TimeOut_in_msec = 10*1000;   // Seconds
		  Client_Socket.connect(Socket_Address, TimeOut_in_msec);
		  
		  return Client_Socket;
		} 
		catch (SocketTimeoutException sockettimexc) 
		{
		  System.err.println("A timeout has occured - Lost Connection with the localhost ");
		  sockettimexc.printStackTrace();
		  throw sockettimexc;
		}
	  }

}
