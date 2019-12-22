/*
By: Jason You
Ms.Costin
November 23, 2017
The "SlotMachineGame" Program
*/
// Import section
import hsa.Console; // Import the hsa.Console package
import java.awt.Polygon; // Import the java.awt.Polygon package
import java.awt.Color; // Import the java.awt.Color package
import java.awt.*; // Import the java.awt.* package
import java.io.*; // Import java.io.* package
import javax.sound.sampled.AudioInputStream; // Import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.Clip; // Import javax.sound.sampled.Clip 
import sun.audio.AudioData; // Import sun.audio.AudioData
import sun.audio.AudioPlayer; // Import sun.audio.AudioPlayer
import sun.audio.AudioStream; // Import sun.audio.AudioStream
import sun.audio.ContinuousAudioDataStream; // Import sun.audio.ContinuousAudioDataStream
import javax.sound.sampled.Clip; // Import javax.sound.sampled.Clip
import javax.sound.sampled.AudioFormat; // Import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioInputStream; // Import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem; // Import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine; // Import javax.sound.sampled.DataLine
import javax.sound.sampled.LineUnavailableException; // Import javax.sound.sampled.LineUnavailableException
import javax.sound.sampled.SourceDataLine; // Import javax.sound.sampled.SourceDataLine 
/*
This program is responsible for simulating a slot machine program. It gives the user the option of entering a bet amount between $0 and $45. If the user inputs a value
that is outside of this range, then the user will be prompted to reenter a value that is within the range. After each run, depending on the shape and how many of the shapes
are the same, the user will be awarded a certain amount of money. The user will be then prompted to enter any key to continue and 1 to exit the program. At the end of the program, 
the user will be told whether or not he/she has won a certain amount of money or lost money. 

The SlotMachineGame program.
This class is responsible for running the main program of the game. It contains the title of the program and intro of the program.

Variable Declaration 
Variable Name                Type              Description 
c                            Console           This variable is responsible for creating an object of the Console class so that the output screen will be shown. 
*/
public class SlotMachineGame
{
    // Creating an object of the console class.
    static Console c;

    /*
    The title () method. This method is responsible for printing out the title of the screen and clearing the screen when called.
    */
    void title ()
    {
	c.clear (); // Clear the screen
	c.setCursor (1, 32); // Set the text location
	c.print ("Slot Machine Program"); // Title of the program.
    }


    /*
    The printAmount (int betAmount method)
    This method is responsible for printing out the amount that the user has in the game.
    */
    void printAmount (int betAmount)
    {
	c.setCursor (16, 32); // Set text position
	c.print ("Amount in Game:               ");
	c.setCursor (16, 32); //Set text position
	c.print ("Amount in Game: " + betAmount); // Prints the amount that is in the game
    }


    /*
    The intro () method.
    This method is responsible for printing out the introduction of the game.
    It explains some of the details of the program including the amount of money that players win.
    */
    void intro ()
    {
	title ();
	c.println ();
	c.println ("Welcome to the Slot Machine Program!");
	c.println ("This program is responsible for simulating a slot machine. You can place your ");
	c.println ("bets and either earn or lose money depending on the outcome of the program. You can only obtain money if you get all the shapes.");
	c.println ("All triangles earn you $5, all rectangles get you $10, and all circles earn you $15. Enter '1' to exit the program.");
	c.println ("It exits after 10 runs.");

    }


    /*
    The main method. The main method is responsible for running the program.
    */
    public static void main (String[] args) throws Exception
    {
	c = new Console (); // Creates an object of the Console () class.
	String choice = "2";
	//0 is rectangle, 1 is circle, 2 is triangle
	int shape1 = 0; //left most shape
	int shape2 = 0; //middle shape
	int shape3 = 0; //right most shape
	int betAmount = 0; //Variable for keeping track of amount of money won or lost
	int bet = -1; // Setting bet amount
	int count = 1;
	// Creating an object of the SlotMachineGame class.
	SlotMachineGame s = new SlotMachineGame ();
	//AudioDemo a = new AudioDemo ();
	Wheel w = new Wheel (); // Creating an object of the Wheel class.

	String filename = "Fly_me_to_the_Moon.wav";
	InputStream in = new FileInputStream (new File (filename));
	AudioStream audioStream = new AudioStream (in);
	AudioPlayer.player.start (audioStream);

	s.intro (); // Calling the intro() method.
	// The while loop that runs the main program.
	while (true)
	{
	    //a.music ();
	    c.setCursor (10, 1); // Sets location of the text to line 10.
	    c.println ("Enter any key to continue: (Press 1 to exit program) "); // Prompts user to enter any key. (Press 1 to exit the program
	    choice = c.readLine (); // Asks the user for the choice
	    s.title (); // Prints the title of the screen and clears the screen.

	    // If statement to see if the program will continue or not.
	    if (choice.equals ("1") || (count == 11))
	    {
		c.setCursor (8, 1); // Sets cursor
		c.println ("You are now exiting the Slot Machine program."); // Informing the usere that they are exiting the program. 
		if (betAmount < 0)
		{
		    c.println ("Sorry! You did not win any money. Hopefully you'll win next time!!"); // If the user has a lower value, then they will be told that they have lost. 
		}
		else
		{
		    c.println ("Congratulations! You won $" + betAmount + "."); // If the user wins money. 
		}
		c.println ("Enter any key to continue: "); // Prompts user to enter any value to continue. 
		AudioPlayer.player.stop (audioStream); // Stops the playing of the music. 
		c.getChar ();
		c.close (); // Closes the console. 
		break;
	    }
	    else
	    {
		s.title (); // Clears screen 
		c.setCursor (2, 23);
		bet = -1; // Sets an initial value of the bet amount. 
		while ((bet > 45) || (bet < 0)) // Errortraps to make sure that the bet amount is within the range. 
		{
		    s.title ();
		    c.setCursor (2, 23); 
		    c.println ("Enter bet amount (Between $0 and $45): "); // Tells user to input value between 0 and 45. 
		    c.setCursor (3, 40);
		    bet = c.readInt (); // Reads in bet amount. 
		}

		betAmount = betAmount - bet; // Sets betAmount 
		s.title ();
		s.printAmount (betAmount); // Sends betAmount to printAmount. 
		w.spin (); // Calls the spin method in the Wheel class. 
		shape1 = w.valsOne (); // Stores value of shape1 (what type of shape it is)
		shape2 = w.valsTwo (); // Stores value of shape2 (what type of shape it is)
		shape3 = w.valsThree (); // Stores value of shape3 (what type of shape it is)

		//All Rectangles
		if ((shape1 == 0) && (shape2 == 0) && (shape3 == 0)) // Checks for all rectangles
		{
		    betAmount = betAmount - (int) (1.2 * betAmount); // Subtract because betAmount is negative value
		}
		//All Circles
		else if ((shape1 == 1) && (shape2 == 1) && (shape3 == 1)) // Checks for all circles
		
		{
		    betAmount = betAmount - (int) (1.15 * betAmount);
		}
		//All Triangles
		else if ((shape1 == 2) && (shape2 == 2) && (shape3 == 2)) // Checks for all triangles 
		{
		    betAmount = betAmount - (int) (1.1 * betAmount);
		}
		//Two shapes out of three are equal (Add 20 dollars)
		else if (((shape1 == shape2) || (shape2 == shape3) || (shape1 == shape3)))
		{
		    betAmount = betAmount + 20; // Add 20 dollars
		}
		//All shapes are different (Add 5 dollars)
		else
		{
		    betAmount = betAmount + 5; // Add 5 dollars
		}
	    }
	    s.printAmount (betAmount); // Prints out the total cash amount that the user now has.
	    count++; // Increments the count 
	}
    }
}

/*
The Shape class.
This class is the parent class of the shapes that are created in the program. 
Variable Declaration 
Variable Names               Type                 Description
x                            int                  Stores the x coordinate
y                            int                  Stores the y coordinate
width                        int                  Stores the width of the shape
height                       int                  Stores the height of the shape      
*/
abstract class Shape
{
    int x, y, width, height; // Variable Declaration 
    Color col; 
    // Constructor of the Shape 
    /*
    It's responsible for initiailizing the variables 
    */
    protected Shape (int x, int y, int width, int height, Color col) 
    {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.col = col;
    }

    /*
    The draw(Console c) method. 
    Sets the colour of the shape. 
    */
    public void draw (Console c)
    {
	c.setColor (col);
    }

    /*
    The setColor method.
    This is a setter method that sets the colour of the shape. 
    */
    public void setColor (Color c)
    {
	this.col = col;
    }
}

/*
The Rect class.
This class is responsible for creating a rectangle. It inherits the Shape class. 
*/
class Rect extends Shape
{
    /*
    The Rect constructor.
    This constructor is responsible for initializing the variables in the parent class. 
    */
    public Rect (int x, int y, int width, int height, Color col)
    {
	super (x, y, width, height, col);
    }

    /*
    The draw method.
    This method is responsible for drawing the rectangle. 
    */
    public void draw (Console c)
    {
	super.draw (c);
	c.fillRect (x, y, width, height);
    }
}

/*
The Circle class.
This class is responsible for creating the circle. (Inherits the Shape class)
*/
class Circle extends Shape
{
    /*
    The Circle constructor.
    This constructor is responsible for intiailizing the variables in the parent class. 
    */
    public Circle (int x, int y, int width, int height, Color col)

    {
	super (x, y, width, height, col);

    }
    
    /*
    The draw method.
    This method is responsible for drawing the circle
    */
    public void draw (Console c)
    {
	super.draw (c);
	c.fillOval (x, y, width, width);
    }
}

/*
The Triangle class.
This class is responsible for creating the triangle (Inherits the Shape class)
*/
class Triangle extends Shape
{
    /*
    The Triangle constructor. This constructor is repsonsible for initializing the variables of the parent class. 
    */
    public Triangle (int x, int y, int width, int height, Color col)
    {
	super (x, y, width, height, col);

    }
    
    /*
    The draw method.
    This method is responsible for drawing the triangle
    */
    public void draw (Console c)
    {
	super.draw (c);
	Polygon pol = new Polygon (); // Creates an object of the Polygon() class. 
	int[] xc = {x, x + width / 2, x + width};
	int yc[] = {y + height, y, y + height};
	c.fillPolygon (xc, yc, 3); // Draws the triangle. 
    }
}

/*
The Wheel class. This class is responsible for randomizing the shapes and then drawing them on the screen. 
Variable Description 
Variable Name              Type                  Description
shapeTrack                 int                   Tracks the type of shapes
shapes                     Shapes[]              Stores the three shapes
delay                      int                   Delays
X                                                Position of x
Y                                                 Position of y
W                                                Width
H                                                Height
PAD                                              Delay time
*/
class Wheel
{
    //Variable Declaration 
    int[] shapeTrack = new int [3];
    Shape[] shapes = new Shape [3];
    int delay;
    public static final int X = 30, Y = 30, W = 60, H = 80, PAD = 100;
    
    /*
    The Wheel() Constructor
    */
    public Wheel ()
    {

    }
    
    /*
    The valsOne() method. Returns the type of the first shape
    */
    public int valsOne ()
    {
	return shapeTrack [0];
    }

     /*
    The valsTwo() method. Returns the type of the second shape
    */
    public int valsTwo ()
    {
	return shapeTrack [1];
    }

     /*
    The valsThree() method. Returns the type of the third shape
    */
    public int valsThree ()
    {
	return shapeTrack [2];
    }

    /*
    The spin () method. This method is responsible for spinning the shapes. 
    */
    public void spin ()
    {
	// Variable Declaration 
	Color col = new Color ((int) (Math.random () * 255), (int) (Math.random () * 255), (int) (Math.random () * 255));
	Color[] check = new Color [3];
	shapes = new Shape [3];
	shapes [0] = new Rect (X, Y, W, H, col);
	col = new Color ((int) (Math.random () * 255), (int) (Math.random () * 255), (int) (Math.random () * 255));
	shapes [1] = new Circle (X + PAD, Y, W, H, col);
	col = new Color ((int) (Math.random () * 255), (int) (Math.random () * 255), (int) (Math.random () * 255));
	shapes [2] = new Triangle (X + 2 * PAD, Y, W, H, col);
	
	// Runs the loop to simulate a slot machine. 
	for (delay = 10 ; delay < 400 ; delay += (int) (delay * 0.1))
	{
	    SlotMachineGame.c.clearRect (X, Y, (W + PAD) * 3, H + 1);
	    for (int i = 0 ; i < shapes.length ; i++)
	    {

		col = new Color ((int) (Math.random () * 255), (int) (Math.random () * 255), (int) (Math.random () * 255)); // Randomizes colour. 
		check [i] = col;
		shapes [i].setColor (col);
	    }

	    for (int n = 0 ; n < 3 ; n++)  // Stores the shapes 
	    {
		int rand = (int) (Math.random () * 3);
		shapeTrack [n] = rand;
		shapes [rand].x = n * PAD + X;
		shapes [rand].draw (SlotMachineGame.c);
	    }
	    try
	    {
		Thread.sleep (delay); // Delays 
	    }
	    catch (Exception e)
	    {
		System.err.println ("Error"); // Catches error. 
	    }
	}
    }




}
