package application;
	
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
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


public class Main extends Application implements EventHandler<ActionEvent> {
	String Tfstring;
	TextField textField1 = new TextField("");
	TextField textField2 = new TextField("Enter your number");

	Button pButton;
	Label label1;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			 //Instantiate a Text object for as title of the page and attributes
		      Text pageTitle = new Text();  
		      pageTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)); 
		       pageTitle.setFill(Color.GOLDENROD);
		      //position of the the x and y portions of the page titles size
		      pageTitle.setX(50); 
		      pageTitle.setY(130);  
		     // pageTitle.setStrokeWidth(2);
		    //  pageTitle.setStroke(Color.GOLDENROD);
		      pageTitle.setUnderline(true);

		      pageTitle.setText("Prime or Not"); 
	
			//Instantiate  label for instructions/confirmation
			
			label1 = new Label("Enter a whole number to find out if it is prime or not");
			label1.setTextFill(Color.DARKGOLDENROD);
		    
			// pButton attributes
			pButton = new Button();
			pButton.setLayoutX(100);
			pButton.setLayoutY(100);
			pButton.setText("Process");
			pButton.setOnAction(this);
			pButton.setTextFill(Color.DARKGOLDENROD);
			
			// GridPane and attributes 
			GridPane gridPane = new GridPane();
			gridPane.setMinSize(400,200);
			gridPane.setPadding(new Insets(10,10,10,10));
			gridPane.setVgap(5);
			gridPane.setHgap(5);
			gridPane.setAlignment(Pos.CENTER);
			gridPane.add(label1, 0, 1);
			gridPane.add(pButton,0,3);
			BackgroundFill background_fill = new BackgroundFill(Color.BLACK,CornerRadii.EMPTY, Insets.EMPTY); 
	        Background background = new Background(background_fill); 
	        gridPane.setBackground(background);
	       // gridPane.add(listview, 0, 2);
	        gridPane.add(pageTitle, 0, 0);
	        gridPane.add(textField1, 0, 2);
	        gridPane.getColumnConstraints().add(new ColumnConstraints(400)); //  400 wide for column 0



			primaryStage.setTitle("Prime or Not");
			Scene scene = new Scene(gridPane);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
//------------------------------------event handler for pbutton-----------------------------------------------------		
	public void handle (ActionEvent event) {
		if(event.getSource()== pButton) {
			
			
			
			//Scanner in = new Scanner(System.in);
			String	userString =  textField1.getText();
				try
				{
						
					
						Socket connection = new Socket("127.0.0.1",1236);
						InputStream input = connection.getInputStream();
						OutputStream output = connection.getOutputStream();
								
						output.write(userString.length());
						output.write(userString.getBytes());
								
						int n = input.read();
						byte[] data = new byte[n];
						input.read(data);	
								
						String serverResponse = new String(data, StandardCharsets.UTF_8);
						
								
					
						textField1.setText("Server said: " + serverResponse);
							System.out.println("Server said: " + serverResponse);
							if(!connection.isClosed())
								connection.close();
								
							} catch (IOException e1){
								e1.printStackTrace();
							
							}
			
	//-----------------------
				
			
			
		 catch(Exception e) {
			e.printStackTrace();
		}
	}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
