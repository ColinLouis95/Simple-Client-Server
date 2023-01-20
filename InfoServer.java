/*
 * 
 * Colin Kugler
 * CS3750-001
 *
 * Homework 9 revision for resubmission
 * while(!clientReader.readLine().equals("Client ready for message...")){} method for 
 * stalling server message recommended by Anthony Mitcham	
 *
*/

import java.io.*;
import javax.net.ssl.*;

public class InfoServer {
	//System.setProperty("javax.net.ssl.keyStore", "3750keystore");
    //System.setProperty("javax.net.ssl.keyStorePassword", "pass1234");
	
	public static void main(String[] arstring) {
		// create serversocket
		try {
			SSLServerSocketFactory sslSSF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			SSLServerSocket sslSS = (SSLServerSocket) sslSSF.createServerSocket(5120);
			BufferedWriter writeToFile = null;

			while(true){
				SSLSocket socket = (SSLSocket) sslSS.accept();
				System.out.println("Connection created");

			// used to send info to client
				OutputStream os = socket.getOutputStream();
    		    OutputStreamWriter ow = new OutputStreamWriter(os);
				BufferedWriter clientWriter = new BufferedWriter(ow);

			//used to read incoming info from client.
				InputStream is = socket.getInputStream();
                InputStreamReader ir = new InputStreamReader(is);
				BufferedReader clientReader = new BufferedReader(ir);

			// getting session info
				socket.addHandshakeCompletedListener(new HandshakeCompletedListener() {
					public void handshakeCompleted(HandshakeCompletedEvent event){ 
						System.out.println("Peer host is... " + event.getSession().getPeerHost());
                        System.out.println("Cipher suite is... " + event.getSession().getCipherSuite());
                        System.out.println("Protocol is... " + event.getSession().getProtocol());
                        System.out.println("Session ID is... " + event.getSession());
                        System.out.println("Creation time of session... " + event.getSession().getCreationTime());
                        System.out.println("Last accessed time of session... " + event.getSession().getLastAccessedTime());}
                    });
				boolean client_info = true;
				
			// while client_info is true (yes to adding more users), loop through getting info 
			// from client and saving to a file name after the usernames.

				while(client_info){
					clientWriter.write("What is the User Name?: " + '\n');
					clientWriter.flush();
					String answer = null;

					if((answer = clientReader.readLine()) != null){
						String filename = answer;
						System.out.println("Response from Client " + answer);
						writeToFile = new BufferedWriter(new FileWriter(filename + ".txt"));
						writeToFile.write("User Name: "+ answer + '\n');
					}
			// allows server to wait for client to respond to question and acknowledge being ready for next question.		
					while(!clientReader.readLine().equals("Client ready for message...")){}

					clientWriter.write("What is the Client Full Name?: " + '\n');
					clientWriter.flush();
					if((answer = clientReader.readLine()) != null){
						System.out.println("Response from Client " + answer);
						writeToFile.write("Full Name: " + answer + '\n');
					}
					while(!clientReader.readLine().equals("Client ready for message...")){}

					clientWriter.write("What is the Client Address?: " + '\n');
					clientWriter.flush();
					if((answer = clientReader.readLine()) != null){
						System.out.println("Response from Client " + answer);
						writeToFile.write("Address: " + answer + '\n');
					}
					while(!clientReader.readLine().equals("Client ready for message...")){}	

					clientWriter.write("What is the Client Phone Number?: " + '\n');
					clientWriter.flush();
					if((answer = clientReader.readLine()) != null){
						System.out.println("Response from Client " + answer);
						writeToFile.write("Phone Number: " + answer + '\n');
					}
					while(!clientReader.readLine().equals("Client ready for message...")){}

					clientWriter.write("What is the Client Email Address?: " + '\n');
					clientWriter.flush();
					if((answer = clientReader.readLine()) != null){
						System.out.println("Response from Client " + answer);
						writeToFile.write("Email Address: " + answer + '\n');
					}
					while(!clientReader.readLine().equals("Client ready for message...")){}

					clientWriter.write("Add more users? (yes or any for no)" + '\n');
					clientWriter.flush();
					String addUsers = null;
					if((addUsers = clientReader.readLine()) != null){
						System.out.println("Response from Client " + answer);
						if(!addUsers.equals("yes")){
							socket.close();
							client_info = false;
							System.out.println("Connection ended.");
						}
					}
				}
				clientReader.close();
				writeToFile.close();
			}
		} catch(Exception e){e.printStackTrace();}
	}
}