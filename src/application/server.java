package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class server {
	
	public static void main(String[] args) {

	
		//-----------------------instantiate server	
		ServerSocket server = null;
		boolean shutdown = false;
		try
		{
			server = new ServerSocket(1236);
			System.out.println("port bound. Accepting Connections");
			
		} catch (IOException e){
			e.printStackTrace();
			System.exit(-1);
		}	
		
		while(!shutdown) {
			
			Socket client = null;
			InputStream input = null;
			OutputStream output = null;
			String clientInput = null;
			
			try
			{
				client = server.accept();
				input =  client.getInputStream();
				output =  client.getOutputStream();
				
				int n = input.read();
				byte[] data = new byte[n];
				input.read(data);
				
				 clientInput = new String(data, StandardCharsets.UTF_8);
				clientInput.replace("\n", "");
				
				String response1 = "Client said: " + clientInput;
				
			System.out.println("Client said: " + clientInput);
		
			
			String primeResult = Prime(Integer.parseInt(clientInput));
			
			
			String response = "Your input was [" +  clientInput + "]" +" and it is " + primeResult;
			
			
			output.write(response.length());
			
			output.write(response.getBytes());
		
			
			client.close();
			
			
			if (clientInput.equalsIgnoreCase("shutdown"))
			{
				System.out.println("Shutting down...");
			}
			
		} catch (IOException e){
			e.printStackTrace();
			System.exit(-1);
		
	}
	
		}
		
}	
				
			



public static String Prime(Integer number) {
		
		String result = null;
		
		if( number%2 == 0)
			result = "not prime";
			else
				result = "prime";
			
		return result;
		
	
	
}
	

}



