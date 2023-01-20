/*
 * 
 * Colin Kugler
 * CS3750-001
 *
 * Homework 9 revision for resubmission
 * switch logic for handling messages sent to server recommended by Anthony Mitcham	
 *
*/ 

import javax.net.ssl.*;
import java.io.*;
import java.util.Scanner;

public class InfoClient {

	private static Scanner input = new Scanner(System.in);
	public static void main(String[] arstring){
		//System.setProperty("javax.net.ssl.trustStore", "3750truststore");
        //System.setProperty("javax.net.ssl.trustStorePassword", "pass1234");
        try{
    // creating the socket, buffered reader/writer that will talk to server.    	
        	SSLSocketFactory sslSF = (SSLSocketFactory) SSLSocketFactory.getDefault();
        	SSLSocket socket = (SSLSocket) sslSF.createSocket("cs3750a.msudenver.edu", 5120);
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter streamWriter = new OutputStreamWriter(os);
            BufferedWriter writeServer = new BufferedWriter(streamWriter);

            InputStream is = socket.getInputStream();
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader readServer = new BufferedReader(ir);

            String fromServer, inToServer = null; 

            while((fromServer = readServer.readLine()) != null){

            	switch(fromServer){

	            	case "What is the User Name?: ":
	            		System.out.println("What is the User Name?: ");
	                        String userName = input.nextLine();
	                        inToServer = "User Name: " + userName;
	                        writeServer.write(inToServer + '\n');
	                        writeServer.flush();
	                        writeServer.write("Client ready for message..." + '\n');
	                        writeServer.flush();
	                        break;

	                case "What is the Client Full Name?: ":
	            		System.out.println("What is the Client Full Name?: ");
	                        String fullName = input.nextLine();
	                        inToServer = "Full Name: " + fullName;
	                        writeServer.write(inToServer + '\n');
	                        writeServer.flush();
	                        writeServer.write("Client ready for message..." + '\n');
	                        writeServer.flush();
	                        break;

	                case "What is the Client Address?: ":
	            		System.out.println("What is the Client Address?: ");
	                        String address = input.nextLine();
	                        inToServer = "Addess: " + address;
	                        writeServer.write(inToServer + '\n');
	                        writeServer.flush();
	                        writeServer.write("Client ready for message..." + '\n');
	                        writeServer.flush();
	                        break;

	                case "What is the Client Phone Number?: " :
	            		System.out.println("What is the Client Phone Number?: " );
	                        String phoneNum = input.nextLine();
	                        inToServer = "Phone Number: " + phoneNum;
	                        writeServer.write(inToServer + '\n');
	                        writeServer.flush();
	                        writeServer.write("Client ready for message..." + '\n');
	                        writeServer.flush();
	                        break;

	                case "What is the Client Email Address?: ":
	            		System.out.println("What is the Client Email Address?: ");
	                        String eaddress = input.nextLine();
	                        inToServer = "Email Address: " + eaddress;
	                        writeServer.write(inToServer + '\n');
	                        writeServer.flush();
	                        writeServer.write("Client ready for message..." + '\n');
	                        writeServer.flush();
	                        break;

	                case "Add more users? (yes or any for no)":
	                    System.out.println("Add more users? (yes or any for no)");
	                        String response = input.nextLine();
	                        writeServer.write(response + '\n');
	                        writeServer.flush();
	                        break;
	            }

	            if (fromServer.equals("Bye!")) break;

	        }

		} catch(Exception e){e.printStackTrace();} 


	}

}

