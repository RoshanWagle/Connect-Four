/*	
 * ConnectFour.java
 * 
 * A file for game called Connect Four. Here 'R' stands for Red and 'Y' stands for yellow which simply indicate two players as Red and Yellow.
  This program works in every condition either that's vertical , horizontal, forward or backward . A GUi based game made using JavaFX.
 *  
       Roshan Wagle
 */

//Importing all the JavaFX libraries
import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//Creating a class called ConnectFour 
public class ConnectFour extends Application 
{
	public static void main(String[] args) 
	{
		launch(args);
    }
	//Initializing the player Yellow as 'Y'
	public char Turn = 'Y';
	//Calling the cell class to make GUI for a game
	private Cell[][] cell =  new Cell[6][7];

	@Override
	public void start(Stage primaryStage) 
	{	
		//Making a new GridPane called grid
		GridPane grid = new GridPane();
		//Making a new stackPane which create a stack of HBOX at the top
		final StackPane stack = new StackPane();
		//Making a new BorderPane which divide the whole PrimaryStage to Top,Center AND Bottom
		BorderPane borderPane = new BorderPane();
		
		//Making the i,j number for Cell Object
		for (int i = 0; i < 6; i++)
		      for (int j = 0; j < 7; j++)
		    	  grid.add(cell[i][j] = new Cell(primaryStage), j, i);
		
		//New button to start a new game
		Button button1 = new Button("NEW GAME");
		//Header for the Game
		Text t = new Text("CONNECT FOUR");
		//Setting Header FONT to 'Fira Code'
		t.setFont(Font.font ("Fira Code", 32));
		//Setting the text color to Black
		t.setFill(Color.BLACK);
		//Creating a new dropShadow for Text
		DropShadow ds = new DropShadow();
		ds.setOffsetY(4.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
		//Setting effects to Text object
		t.setEffect(ds);
		//Making a new HBox for the top
		HBox hboxTop = new HBox(t);
		//Setting padding around the box
		hboxTop.setPadding(new Insets(23, 0, 0,185));
		//Making a new HBox for the bottom
		HBox hboxBottom = new HBox(button1);
		//Setting padding around the box
		hboxBottom.setPadding(new Insets(26, 0, 0, 550));
		
		//Adding hbox2 and HBox to the stack
		stack.getChildren().addAll(hboxTop,hboxBottom);
		
		//Setting stack to the Top of the borderPane
		borderPane.setTop(stack);
		//Setting grid i.e. the board to the Bottom of borderPane
		borderPane.setBottom(grid);
		
		button1.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override 
	        public void handle(MouseEvent event) //Event handler for NEW GAME button
			{        			  
				Alert alert = new Alert(AlertType.CONFIRMATION);//Making a new Confirmation AlertBox
	        	alert.setTitle("Connect Four");//Setting a Title to Connect Four
	        	alert.setHeaderText("A NEW GAME HAS BEEN REQUESTED");//Requesting user to play a new game
	        	alert.setContentText("DO YOU WANT TO START A NEW GAME ?");

	        	Optional<ButtonType> result = alert.showAndWait();//Show the AlertBox to the user
	        	
	        	if (result.get() == ButtonType.OK)//creating OK button for the user
	        	{
	        		for(int i=0;i<7;i++) 
	        		{
	        			for(int j=0;j<6;j++) 
	        			{
	        				cell[j][i].setToken(' ');//Clear the 2D array
	        				cell[j][i].ClearBoard();//Clear all nodes from the board
	        				Turn = 'Y';//Initialize the current player to Y
	        			}
	        		}
	        	} 
	        	
	        	else 
	        	{
	        		alert.close();//Closes the alert box
	        		primaryStage.close();//Closes the primary stage
	        	}
			}
		});
		    
		Scene scene = new Scene(borderPane, 650, 670); //makes a new scene of 650x670 and then adds borderPane to it
	    primaryStage.setTitle("Connect Four"); //Set the Title for the PrimaryStage
	    primaryStage.setScene(scene);	//Set whole scene to the PrimaryStage
	    primaryStage.setResizable(false);//To stop the user from changing the scene size because the functioning of the app depends on fixed co-ordinates
	    primaryStage.show();//Show the Primary Stage 	    
	}	
	
	public boolean BoardisFull() 
	{
		//Checking  whether the whole board is full or not
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
				if (cell[i][j].getToken() == ' ')
					return false;
	    return true;//Returns true if none of the element in the array is ' '.
	}
	
	public boolean ColumnIsFull(int col) 
	{
		//checks whether the user-input column is full or not
		  for(int i=0;i<6;i++) 
			if (cell[i][col].getToken() == ' ') 
				return false;
		return true;//Returns true if the specified column does not have a empty spot for the disk
	}
	
	public boolean isWinner() 
	{
		//Declaring the winner if user matches the qualification to win horizontally,vertically or diagonally
		//For horizontal
		for(int j=0;j<4;j++)  
		{
			for(int i=0; i<6; i++) 
			{
				if(cell[i][j].getToken()!=' ') 
				{
					if((cell[i][j].getToken()== cell[i][j+1].getToken()&&(cell[i][j].getToken()==cell[i][j+2].getToken())&&cell[i][j].getToken()==cell[i][j+3].getToken())) 
					{
						
						if (cell[i][j].getToken() == 'Y') 
						{
							cell[i][j].ClearBoard();//Clear the node at cell[i][j] and replace with the flashing one
							cell[i][j].YellowFlash();//Add the Yellow Flashing Disk
							cell[i][j+1].ClearBoard();
							cell[i][j+1].YellowFlash();
							cell[i][j+2].ClearBoard();
							cell[i][j+2].YellowFlash();
							cell[i][j+3].ClearBoard();
							cell[i][j+3].YellowFlash();
						}
						if (cell[i][j].getToken() == 'R') 
						{		
							cell[i][j].ClearBoard();
							cell[i][j].RedFlash();//Add the Red Flashing Disk
							cell[i][j+1].ClearBoard();
							cell[i][j+1].RedFlash();
							cell[i][j+2].ClearBoard();
							cell[i][j+2].RedFlash();
							cell[i][j+3].ClearBoard();
							cell[i][j+3].RedFlash();
						}
						return true;
					}
				}
			}
		}
		//For Vertical
		for(int j=0; j<7; j++) 
		{
			for(int i=0;i<3;i++) 
			{
				if(cell[i][j].getToken()!=' ') 
				{
					if((cell[i][j].getToken()== cell[i+1][j].getToken()&&(cell[i][j].getToken()==cell[i+2][j].getToken())&&cell[i][j].getToken()==cell[i+3][j].getToken())) 
					{
						if (cell[i][j].getToken() == 'R') 
						{		
							cell[i][j].ClearBoard();
							cell[i][j].RedFlash();
							cell[i+1][j].ClearBoard();
							cell[i+1][j].RedFlash();
							cell[i+2][j].ClearBoard();
							cell[i+2][j].RedFlash();
							cell[i+3][j].ClearBoard();
							cell[i+3][j].RedFlash();
						}
						
						if (cell[i][j].getToken() == 'Y') 
						{
							cell[i][j].ClearBoard();
							cell[i][j].YellowFlash();
							cell[i+1][j].ClearBoard();
							cell[i+1][j].YellowFlash();
							cell[i+2][j].ClearBoard();
							cell[i+2][j].YellowFlash();
							cell[i+3][j].ClearBoard();
							cell[i+3][j].YellowFlash();
						}	
						return true;
					}
				}
			}
		}
		//For Diagonal(left to right up)
		for(int j=0;j<4;j++) 
		{
			for(int i=0; i<3; i++) 
			{
				if(cell[i][j].getToken()!=' ') 
				{
					if((cell[i][j].getToken()== cell[i+1][j+1].getToken()&&(cell[i][j].getToken()==cell[i+2][j+2].getToken())&&cell[i][j].getToken()==cell[i+3][j+3].getToken())) 
					{
						if (cell[i][j].getToken() == 'R') 
						{		
							cell[i][j].ClearBoard();
							cell[i][j].RedFlash();
							cell[i+1][j+1].ClearBoard();
							cell[i+1][j+1].RedFlash();
							cell[i+2][j+2].ClearBoard();
							cell[i+2][j+2].RedFlash();
							cell[i+3][j+3].ClearBoard();
							cell[i+3][j+3].RedFlash();
						}
						
						if (cell[i][j].getToken() == 'Y') 
						{
							cell[i][j].ClearBoard();
							cell[i][j].YellowFlash();
							cell[i+1][j+1].ClearBoard();
							cell[i+1][j+1].YellowFlash();
							cell[i+2][j+2].ClearBoard();
							cell[i+2][j+2].YellowFlash();
							cell[i+3][j+3].ClearBoard();
							cell[i+3][j+3].YellowFlash();
						}
						return true;
					}
				}
			}
		}
		//For diagonal(left to right down)
		for(int i=0;i<3; i++) 
		{
			for(int j=3;j<7;j++) 
			{
				if(cell[i][j].getToken()!=' ') 
				{
					if((cell[i][j].getToken()== cell[i+1][j-1].getToken()&&(cell[i][j].getToken()==cell[i+2][j-2].getToken())&&cell[i][j].getToken()==cell[i+3][j-3].getToken())) 
					{
						if (cell[i][j].getToken() == 'R') 
						{		
							cell[i][j].ClearBoard();
							cell[i][j].RedFlash();
							cell[i+1][j-1].ClearBoard();
							cell[i+1][j-1].RedFlash();
							cell[i+2][j-2].ClearBoard();
							cell[i+2][j-2].RedFlash();
							cell[i+3][j-3].ClearBoard();
							cell[i+3][j-3].RedFlash();
						}
						
						if (cell[i][j].getToken() == 'Y') 
						{
							cell[i][j].ClearBoard();
							cell[i][j].YellowFlash();
							cell[i+1][j-1].ClearBoard();
							cell[i+1][j-1].YellowFlash();
							cell[i+2][j-2].ClearBoard();
							cell[i+2][j-2].YellowFlash();
							cell[i+3][j-3].ClearBoard();
							cell[i+3][j-3].YellowFlash();
						}
						return true;
					}
				}	
			}
		}		
		
		return false; //Returns false if condition satisfied earlier
	}

	public class Cell extends Pane 
	{
		private char token = ' ';//Setting the initial private token to ' '
		public Cell(Stage primaryStage) 
		{
			DropShadow dropShadow = new DropShadow();//Creating a DropShadow object for the circle
		   	dropShadow.setRadius(10.0);
		    dropShadow.setOffsetX(3.0);
		    dropShadow.setOffsetY(3.0);
		    dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
		    	 
			this.setPrefSize(100, 100);//Setting the preferred size of a single box
			
			Circle circle = new Circle(50,50,40, Color.WHITE);//making a white circle
			circle.setStroke(Color.BLACK);//Setting the border of the circle to Black
			
			Rectangle rectangle = new Rectangle(100,100,Color.BLUE);//making a square in GUI
		 	circle.setEffect(dropShadow);//Adding the drop shadow into the circle
		 	
		 	getChildren().add(rectangle);//This adds the blue Rectangle to the GUI
		 	getChildren().add(circle);//This adds the White Circle to the GUI where Disk drop
		   	      
			this.setOnMouseMoved(new EventHandler<MouseEvent>() 
			{
				//Used to tack the movement of mouse
			   	@Override
			   	public void handle(MouseEvent event) 
			   	{
			   		if (Turn == 'Y') 
			   		{
			   			circle.setFill(Color.YELLOW);//Fills the current instance of circle object to Yellow if the player is Y
			   		}
		           
			   		if (Turn == 'R') 
			   		{
			   			circle.setFill(Color.RED);//Fills the current instance of circle object to Red if the player is R
			   		}
			   	  }
			});
		      
			this.setOnMouseExited(new EventHandler<MouseEvent>() 
			{
				@Override 
			    public void handle(MouseEvent event) 
			    {
			      	  circle.setFill(Color.WHITE);//Fills the space to white
			    }
			});
			      
			this.setOnMouseClicked(new EventHandler<MouseEvent>()
			{
		    	@Override
	            public void handle(MouseEvent t) 
		    	{
			         if (Turn == 'Y') 
			         {
			        	 circle.setFill(Color.RED);//Fills the space in RED
			         }
				         
			         if (Turn == 'R')
				     {
				       	 circle.setFill(Color.YELLOW);//FIlls the space in Yellow
			         }
		            	
		           	int column = (((int)t.getSceneX())/100)%100;//Getting the current mouse position in  the scene
		            	
		          	if (Turn != ' ') //Turn is set to ' ' if the game is over so this condition is checking the status if the game is still running
		           	{
			    		if (ColumnIsFull(column)) //Runs if the column if full
			    		{	
			    			Alert alert = new Alert(AlertType.INFORMATION);//Creating a new INFORMATION AlertBox
				    		alert.setTitle("Connect Four");//Title for the Alert
				    		alert.setHeaderText("THE SELECTED COLUMN IS FULL");
				   			alert.setContentText("PLEASE SELECT ANOTHER COLUMN");
				   			alert.showAndWait();//Shows the AlertBox
				   			Turn = (Turn == 'Y') ? 'R' : 'Y';//Change the current player to avoid error while checking
			     		}
				    		
			    		else 
			    		{
			    			dropDisk(Turn,column);//Drop the current disk if the game is still in progress				  		
			    		}
		            }
				    	
		          	if (BoardisFull()) //Calls the BoardisFull to check whether the board is full or not
		          	{			
				    	Alert alert = new Alert(AlertType.CONFIRMATION);//Making a new CONFIRMATION alert box
		        		alert.setTitle("Connect Four");
		        		alert.setHeaderText("THE BOARD IS FULL");
		        		alert.setContentText("DO YOU WANT TO START A NEW GAME?");

		        		Optional<ButtonType> result = alert.showAndWait();
		        			
		        		if (result.get() == ButtonType.OK)//If user pressed the OK Button
		       			{
		       				  Turn = ' ';
		       				  for(int i=0;i<7;i++) 
		       				  {
		       	        		  for(int j=0;j<6;j++) 
		       	        		  {
		       	        			  cell[j][i].setToken(' ');//Set current token to ' '
		       	        			  cell[j][i].ClearBoard();//Clears the board
	        	        			  Turn = 'Y';//Set the player to Yellow
	        	        		  }
		       				  }
		        		} 
		        	
		        		else //If user Pressed any button except than OK
		        		{
		        			  Turn = ' ';//Indicates to end the game
		        		      alert.close();//Closes the alert
		       			      primaryStage.close();//Closes the whole GUI
		       			}
				    } 
				    	
		          	if (isWinner()) //Checks if someone has won the game
				    {
				   		Alert alert = new Alert(AlertType.CONFIRMATION);//Making a new CONFIRMATION AlertBox
		        		alert.setTitle("Connect Four");
		       			alert.setHeaderText(Turn + " HAS WON THE GAME");
		       			alert.setContentText("DO YOU WANT TO START A NEW GAME?");
		        			  
		       			Optional<ButtonType> result = alert.showAndWait();
		        			  
		       			if (result.get() == ButtonType.OK)//If the player pressed OK
		       			{
		       				  Turn = 'Y';//Set the Player to Yellow
		       				  for(int i=0;i<7;i++) 
		       				  {
	        	        		  for(int j=0;j<6;j++) 
	        	        		  {
		       	        			  cell[j][i].setToken(' ');//Clears the 2D array to start again
		       	        			  cell[j][i].ClearBoard();//Clears the board to start a new game
		       	        		  }
	        				  }		  
		        		}
		        			
		       			else 
		        		{
		        			Turn = ' ';//Taking Turn to ' ' to end game
		        			alert.close();//Close the alert
		       				primaryStage.close();//Closes the whole GUI
		       			}
				   }
				    	
		       		else
		       		{
			    		Turn = (Turn == 'Y') ? 'R' : 'Y';//If nothing is satisfied, game is still running so change player
			   		}
		    	}
			}); 
		}
		    
		public char getToken() 
		{
	    	return token;
		}
		    
		public void setToken(char current) 
		{
	    	token = current;
		}
		    
		public void dropDisk(char current, int column) 
		{
		   	int row=5; //This is the bottom row
	    	if (current=='R') 
	    	{
	    		while(cell[row][column].getToken() != ' ') //If the cell is empty
	    		{
		    		row--;//If the bottom row is full, go one step up and so on and store value in row
		    	}
	    		cell[row][column].setToken('R');//Adding R in the 2D array
	    		cell[row][column].addRedDisk();//Dropping the Red disk to the row and column
	    	}
		   	if (current=='Y') 
		   	{
		   		while(cell[row][column].getToken() != ' ') 
		   		{
		    		row--;//If the bottom row is full, go one step up and so on and store value in row
		    	}		
		   		cell[row][column].setToken('Y');//Add Y in the 2D array
		   		cell[row][column].addYellowDisk();//Dropping the yellow disk to the row and column
		   	}
		}
		
		Circle YellowCircle = new Circle(50,50,40, Color.YELLOW); //Yellow Disk	
		Circle RedCircle = new Circle(50,50,40, Color.RED); //Red Disk
		Circle YellowCircleFlash = new Circle(50,50,40, Color.YELLOW); //Flashing Yellow Disk
		Circle RedCircleFlash = new Circle(50,50,40, Color.RED); //Flashing Red Disk
		    
	    public void addRedDisk() 
		{
	    	//Red Disk to the selected Node
			getChildren().add(RedCircle); 
		}
		    
		public void addYellowDisk() 
		{	
			//Yellow Disk to the selected Node
			getChildren().add(YellowCircle);
		}
			
		public void ClearBoard() 
		{
			//Responsible to removing all Disks from the selected Node
			getChildren().remove(RedCircle);
			getChildren().remove(YellowCircle);
			getChildren().remove(YellowCircleFlash);
			getChildren().remove(RedCircleFlash);
		}
			
		public void RedFlash() 
		{
			//Responsible to adding Red Flashing Disk to the selected Node
			getChildren().add(RedCircleFlash);
			FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.4), RedCircleFlash); //Making the animation
		    fadeTransition.setFromValue(1.0); //Peak value
		    fadeTransition.setToValue(0.4); //End value
		    fadeTransition.setCycleCount(Animation.INDEFINITE); //Run animation
		    fadeTransition.play(); //Starts animation
		}
			
		public void YellowFlash() 
		{
			//Responsible to adding Yellow Flashing Disk to the selected Node(Comments are similar to above..)
			getChildren().add(YellowCircleFlash);
			FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.4), YellowCircleFlash);//Making Animation
		    fadeTransition.setFromValue(1.0);//Peak Value
		    fadeTransition.setToValue(0.4);//End Value
		    fadeTransition.setCycleCount(Animation.INDEFINITE);//Run Animation
		    fadeTransition.play();//Start Animation
		}
	}
}
//End
