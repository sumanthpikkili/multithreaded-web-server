# multithreaded-web-server
Simple Web Client and Multithreaded Web Server

##DESCRIPTION
    -The program can run on any available port of the system.
    -Multiple requests can be handled by the server at the same time. Client requests are handled in separate threads.
    -If the file exists on the server, the server responds with "HTTP/1.1 200 OK” along with the contents of the file. If the      file is not found, server responds with a "HTTP/1.0 404 Not Found” message.
    -Messages exchanged between the server and the client are written into a log file.
    
##TECHNICAL SPECIFICATIONS
     Operating System: MAC OSX
     Browsers : Safari V8.0, Internet Explorer v9, Mozilla Firefox v38.0.5
     Development Platform: Java Standard Edition  (JDK 1.8)
     Tool : MAC OSX Terminal
     Server Source Code:
      - WebServer.java
      - HttpRequestGet.java
     Client Source Code: WebClient.java
     Log File: log.txt
     Test Files: adventure.txt, gameofthrones.html
     
##PROGRAM EXECUTION INSTRUCTIONS
     -Install JDK 1.8.
     -Compile the three java files using the following commands: 
      javac WebServer.java
      javac WebClient.java
      javac HttpRequestGet.java
     -Run the server code(eg: ‘java WebServer 2000’). The port number should be given as an argument. The server starts            waiting for a client.
     -If “def” is given as an argument, the server takes the default port 8080. Open a new terminal window (CMD in windows         and navigate to the project directory. 
      Compile all the three Java Files with the commands mentioned above.
      Run the Client program “WebClient.java”. The program takes the URL as an argument. 
      Eg: java WebClient  http://localhost:2000/gameofthrones.html . The contents of the file are displayed on the terminal.
     -Request a text file from the Web browser and validate that the file gets downloaded automatically.                           (http://localhost:2000/adventure.txt)
     -Validate the contents of the log file.
     
##CLIENT AND SERVER CHARACTERISTICS
    -Client requests for a file given the IP Address and port number of the server.
    -Client establishes a connection with the server using sockets.
    -If the connection is successful, client gets the “HTTP/1.1 200 OK” message along with the content of the file.
    -If the connection fails, the client gets the “HTTP/1.0 404 Not Found” error message.
    -All Server side requests are stored in the log file.
     
