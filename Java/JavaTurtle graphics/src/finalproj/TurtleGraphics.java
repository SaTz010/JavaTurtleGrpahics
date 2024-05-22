package finalproj; 
import java.awt.Color;
import java.io.File;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;


import uk.ac.leedsbeckett.oop.OOPGraphics;

public class TurtleGraphics extends OOPGraphics{ 
	private ArrayList<String> commandList;
    private boolean canExit=false;  
    TurtleGraphics(){
    commandList = new ArrayList<>();	
    JFrame MainFrame = new JFrame("Turtle Graphics Java--Sanskar Tiwari"); 
    MainFrame.setLayout(new FlowLayout()); 
    MainFrame.add(this); 
    MainFrame.setSize(850,450); 
    MainFrame.setVisible(true); 
    displayMessage("Welcome To My Program");
    super.about();  
    clear();
    reset();
    penDown();
    setStroke(4);

    }

public static void main(String[] args) 
{
    new TurtleGraphics();
    
}

public void processCommand(String command) {
	
	commandList.add(command);
    
    BufferedImage bufImg;

// List of valid commands

    List<String> validCommands = Arrays.asList("pendown", "penup", "turnleft", "turnright", "forward", "backward", "blue", "green", "red", 
    "white", "yellow", "reset", "clear", "circle", "about","load","capture","display","square","pencolor","penwidth","triangle","equilateral","rectangle","savecommands","loadcommands","exit");
    command = command.toLowerCase();
    String[] sut = command.split(" ");
    int parameter = 0;
    int parameter1 = 0;
    int parameter2 = 0;
    int parameter3 = 0;
    String cmd = sut[0];

 // Checking for valid commands

    if (!validCommands.contains(cmd)) {
        JOptionPane.showMessageDialog(null, "Looks like the command you entered is invalid. Here is a list of valid commands:\n\n"
                                    + "about: Displays a graphic containing the author name.\n"
						        	+ "penup: Lifts the pen from the canvas, so that movement does not get shown.\n"
						    	    + "pendown: Places the pen down on the canvas so movement gets shown as a drawn line.\n"
						    	    + "turnleft <degrees>: Turn <degrees> to the left\n"
                                    + "turnright <degrees>: Turn <degrees> to the right.\n"
                                    + "forward <distance>: Move forward the specified distance.\n"
                                    + "backward <distance>: Move backwards the specified distance.\n"
                                    + "blue: Sets the output pen colour to blue.\n"       
                                    + "green: Sets the output pen colour to green.\n"  
                                    + "red: Sets the output pen colour to red.\n"  
                                    + "white: Sets the output pen colour to white.\n"  
                                    + "yellow: Sets the output pen colour to yellow.\n"      
                                    + "reset: Resets the canvas to its initial state with turtle pointing down but does not clear the display.\n"   
                                    + "clear: Clears the display.\n" 
                                    + "capture: Captures the current image.\n"
                                    + "display: Displays the last captured image.\n"
                                    + "load: Loads the executed commands.\n"
                                    + "circle <radius>: Draws a circle of readius provided by the user.\n"
                                    + "square <length>: Draws a square of length provided by the user.\n"
                                    + "equilateral <length>: Draws an equilateral triangle of length provided by the user.\n"
                                    + "pencolour <red>,<green>,<blue>: Takes three parameters, one for red, one for green and one for blue to make \nan RGB colour for output pen colour.\n"
                                    + "penwidth <width>: Sets the pen stroke as desired by the user.\n"
                                    + "triangle <side1>,<side2>,<side3>: Draws a triangle taking three different sides provided by the user.\n"
                                    + "rectangle <length>,<breadth>: Draws a rectangle of length and breadth provided by the user.\n"
                                    + "exit: To exit the current system\n\n"
                                    + "NOTE! DO NOT SURROUND THE PARAMETERS WITH <>.\n"
                                    );                                                                            
        return;
    }

// Checks for valid parameters

//for 3 parameters
    if (Arrays.asList("triangle","pencolor").contains(cmd)) {
        if (sut.length != 4) {
            System.out.println("Invalid number of parameters for command " + cmd);
            displayMessage("Invalid number of parameters for command " + cmd);
            return;
        }
        try {
            parameter1 = Integer.parseInt(sut[1]);
            parameter2 = Integer.parseInt(sut[2]);
            parameter3 = Integer.parseInt(sut[3]);
        } catch (NumberFormatException e) {
            System.out.println("Non-numeric parameter for command " + cmd);
            displayMessage("Non-numeric parameter for command " + cmd);
            return;
        }
    }

//for 2 parameters
    if (Arrays.asList("rectangle").contains(cmd)) {
        if (sut.length != 3) {
            System.out.println("Invalid number of parameters for command " + cmd);
            displayMessage("Invalid number of parameters for command " + cmd);
            return;
        }
        try {
            parameter1 = Integer.parseInt(sut[1]);
            parameter2 = Integer.parseInt(sut[2]);
        } catch (NumberFormatException e) {
            System.out.println("Non-numeric parameter for command " + cmd);
            displayMessage("Invalid number of parameters for command " + cmd);
            return;
        }
    }

//for 1 parameter
    if (Arrays.asList("forward", "backward", "circle", "turnleft", "turnright","square","penwidth","equilateral").contains(cmd)) {
        if (sut.length !=2) {
            System.out.println("Invalid number of parameters for command " + cmd);
            displayMessage("Invalid number of parameters for command " + cmd);
            return;
        }
        try {
            parameter = Integer.parseInt(sut[1]);
        } catch (NumberFormatException e) {
            System.out.println("Non-numeric parameter for command " + cmd);
            displayMessage("Non-numeric parameter for command " + cmd);
            return;
        }
    }

//Executes the valid commands
switch (cmd) 
{
    case "pendown":
        if (sut.length > 1) {
            System.out.println("Parameter not required for command: " + cmd);
            displayMessage("Parameter not required for command: " + cmd);
            return;
        }
        displayMessage("Pen is Down"); 
        penDown();
        break;



    case "penup":
        if (sut.length > 1) {
            System.out.println("Parameter not required for command: " + cmd);
            displayMessage("Parameter not required for command: " + cmd);
            return;
        }
        displayMessage("Pen is Up");
        penUp();
        break;


    case "turnleft":
        if (parameter <= 0) {
            System.out.println("Invalid parameter: " + parameter);
            displayMessage("Invalid parameter: " + parameter  );
            return;
        }
        displayMessage("Turtle turned left");
        turnLeft(parameter);
        break;


    case "turnright":
        if (parameter <= 0) {
            System.out.println("Invalid parameter: " + parameter);
            displayMessage("Invalid parameter: " + parameter  );
            return;
        }
        displayMessage("Turtle Turned Right");
        turnRight(parameter);
        break;

    case "forward":
        if (parameter <=0 || parameter >=450) {
            System.out.println("Invalid parameter: Parameter Range is 1 to 450 " );
            displayMessage("Invalid parameter:Parameter Range is 1 to 450 " );
            return;
        }
        displayMessage("Turtle moved Forward ");
        forward(parameter);
        break;

    case "backward":
        if (parameter <= 0 || parameter >=450) {
            System.out.println("Invalid parameter:Parameter Range is 1 to 450 " );
            displayMessage("Invalid parameter:Parameter Range is 1 to 450 " );
            return;
        }
        displayMessage("Turtle moved backward ");
        forward(-parameter);
        break;

    case "blue":
        if (sut.length > 1) {
            System.out.println("Parameter not required for command:" + cmd);
            displayMessage("Parameter not required for command:" + cmd);
            return;
        }
        displayMessage("Pen color is Blue ");
        setPenColour(Color.blue);
        break;

    case "green":
        if (sut.length > 1) {
            System.out.println("Parameter not required for command: " + cmd);
            displayMessage("Parameter not required for command:" + cmd);
            return;
        }
        displayMessage("Pen color is Green ");
        setPenColour(Color.green);
        break;


    case "red":
        if (sut.length > 1) {
            System.out.println("Parameter not required for command:" + cmd);
            displayMessage("Parameter not required for command:" + cmd);
            return;
        }
        displayMessage("Pen color is Red ");
        setPenColour(Color.red);
        break;


    case "white":
        if (sut.length > 1) {
            System.out.println("Parameter not required for command:" + cmd);
            displayMessage("Parameter not required for command: " + cmd);
            return;
        }
        displayMessage("Pen color is White ");
        setPenColour(Color.white);
        break;


    case "yellow":
        if (sut.length > 1) {
            System.out.println("Parameter not required for command: " + cmd);
            displayMessage("Parameter not required for command: " + cmd);
            return;
        }
        displayMessage("Pen color is Yellow ");
        setPenColour(Color.yellow);
        break;

    case "reset":
        if (sut.length > 1) {
            System.out.println("Parameter not required for command:" + cmd);
            displayMessage("Parameter not required for command:" + cmd);
            return;
        }
        displayMessage("Back to its starting postion");
        reset();
        penDown();
        setStroke(4);
        setPenColour(Color.red);
        break;

    case "clear":
        if (sut.length > 1) {
        	setxPos(0);
        	setyPos(0);
            System.out.println("Parameter not required for command:" + cmd);
            displayMessage("Parameter not required for command:" + cmd);
            return;
        }
        displayMessage("Now try some other commands!");
        clear();
        break;


    case "circle":
        if (parameter <= 0 || parameter >=400) {
            System.out.println("Invalid parameter:Parameter Range is 1 to 400 ");
            displayMessage("Invalid parameter:Parameter Range is 1 to 400 ");
            return;
        }
        displayMessage(" Circle drawn successfully");
        circle(parameter);
        break;

    case "about":
        if (sut.length > 1) {
            System.out.println("Parameter not required for command: " + cmd);
            displayMessage("Parameter not required for command: " + cmd);
            return;
        }
        displayMessage(" The Artist ");
        about();
        break;

    case "square":
        if (parameter <= 0 || parameter >=400) {
            System.out.println("Invalid parameter:Parameter Range is 1 to 400");
            displayMessage("Invalid parameter:Parameter Range is 1 to 400");
            return;
        }
        displayMessage(" Square drawn successfully");
        square(parameter);
        break;

    case "rectangle":
        if (parameter1 <= 0 || parameter2 <=0) {
            System.out.println("Invalid parameters");
            return;
        }
        displayMessage(" Rectangle drawn successfully");
        rectangle(parameter1, parameter2);
        break;

    case "pencolor":
        if (parameter1 < 0 || parameter2 <0 || parameter3 <0) {
            System.out.println("Invalid parameters: Range is 0 - 255. ");
            displayMessage("Invalid parameters: Range is 0 - 255. ");
            return;
        }
        try {
            pencolor(parameter1,parameter2,parameter3);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        break; 


    case "penwidth":
        if (parameter <= 0 || parameter >=25) {
            System.out.println("Invalid parameter: Parameter Range is 1 to 25 " );
            displayMessage("Invalid parameter: Parameter Range is 1 to 25 " );
            return;
        }
        penwidth(parameter);
        break;

    case "triangle":
        if (parameter1 <= 0 || parameter2 <=0 || parameter3 <=0) {
            System.out.println("Invalid parameters");
            displayMessage("Invalid parameters");
            return;
        }
        drawTriangle(parameter1,parameter2,parameter3);
        break;

    case "equilateral":
        if (parameter <= 0 || parameter >=450) {
            System.out.println("Invalid parameter: Parameter Range is 1 to 450 ");
            displayMessage("Invalid parameter: Parameter Range is 1 to 450 ");
            return;
        }
        equilateralTriangle(parameter);
        break; 

    case "savecommands":
        String saveCommandsFileName = JOptionPane.showInputDialog("Enter commands file name to save:");
        saveCommands(saveCommandsFileName, commandList);
        break;
    case "loadcommands":
        String loadCommandsFileName = JOptionPane.showInputDialog("Enter commands file name to load:");
        loadCommands(loadCommandsFileName);
        break;

 
    case "capture":
        if (sut.length > 1) {
            System.out.println("Parameter not required for command: " + cmd);
            displayMessage("Parameter not required for command: " + cmd);
            return;
        }
        bufImg = getBufferedImage(); 
        try {
        File outputFile = new File("image.png");
        ImageIO.write(bufImg, "png", outputFile);
        canExit=true;
        displayMessage("Image has been Captured");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error has occured!"+ e.getMessage());
        }
        break;

    case "display":
        if (sut.length > 1) {
            System.out.println("Parameter not required for command: " + cmd);
            displayMessage("Parameter not required for command: " + cmd);
            return;
        }
        
        bufImg = null; // initialize bufImg
        try {
        bufImg = ImageIO.read(new File("image.png")); // load the saved image
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error has occured!");
        }
        if (bufImg != null) {
            setBufferedImage(bufImg); // set the background image to the new image
        } else {
            JOptionPane.showMessageDialog(null, "No image has been captured yet.");
        }
        break;

    case "exit":
        if (sut.length > 1) {
            System.out.println("Parameter not required for command: " + cmd);
            displayMessage("Parameter not required for command: " + cmd);
            return;
        }
        while(!canExit){
            JOptionPane.showMessageDialog(null, "Cannot exit without capturing the image!!");
            return;
        }
        bye();
        System.exit(0);
        break; 

}

}

//method to draw square
public void square(int length)
{
    setStroke(3);
    forward(length);
    turnRight(90);
    forward(length);
    turnRight(90);
    forward(length);
    turnRight(90);
    forward(length);

}

//method to draw equilateral triangle
public void equilateralTriangle(int sideLength)
{
    setStroke(3);
    turnLeft(60);
    forward(sideLength);
    turnLeft(120);
    forward(sideLength);
    turnLeft(120);
    forward(sideLength);

}

//method to change the pen stroke
public void penwidth(int width)
{
    setStroke(width);

}

//method to change the pen color using rgb
public void pencolor(int red, int green, int blue )
{
    setPenColour(new Color(red,green,blue));
}

//method that draws a triangle taking three parameters
public void drawTriangle(int s1, int s2, int s3) {
    // Check if the given side lengths form a valid triangle
    if ((s1 + s2 > s3) && (s1 + s3 > s2) && (s2 + s3 > s1)) {
        // Calculate the angles of the triangle using the law of cosines
        int angle1 = (int) Math.toDegrees(Math.acos((s2 * s2 + s3 * s3 - s1 * s1) / (2.0 * s2 * s3)));
        int angle2 = (int) Math.toDegrees(Math.acos((s1 * s1 + s3 * s3 - s2 * s2) / (2.0 * s1 * s3)));
        int angle3 = 180 - angle1 - angle2;
        //displayMessage(angle1 + " " + angle2 + " " + angle3);

        // Draw the triangle
        //turnLeft(angle2/2);
        turnRight(angle2);
        forward(s1);
        turnLeft(180 - angle3);
        forward(s2);
        turnLeft(180 - angle1); 
        forward(s3);
    }
    else {
        // Show a warning message if the side lengths do not form a valid triangle
        JOptionPane.showMessageDialog(null, "Invalid side lengths: They do not form a valid triangle.\n" +
                "Sum of two  should be greater than another side.\n" 
                  ,"Parameter Error", JOptionPane.WARNING_MESSAGE);
    }
}
    

//method to draw a rectangle
public void rectangle(int length, int breadth)
{

    forward(length);
    turnRight(90);
    forward(breadth);
    turnRight(90);
    forward(length);
    turnRight(90);
    forward(breadth);

}

//method to display the author name overriding about method that displays OOP
public void loadCommands(String fileName) {
    ArrayList<String> loadedCommands = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = reader.readLine()) != null) {
            loadedCommands.add(line);
        }
        // Process all loaded commands
        for (String command : loadedCommands) {
            processCommand(command);
        }
        System.out.println("Commands loaded and executed successfully.");
        displayMessage("Commands loaded and executed successfully.");
    } catch (IOException e) {
        // Print error message if file cannot be read
        System.err.println("Error loading commands: " + e.getMessage());
    }
}
public void saveCommands(String fileName, ArrayList<String> commands) {
    try (PrintWriter writer = new PrintWriter(fileName)) {
        for (String command : commands) {
            writer.println(command);
        }
        System.out.println("Commands saved successfully.");
        displayMessage("Commands saved successfully.");
    } catch (IOException e) {
        System.err.println("Error saving commands: " + e.getMessage());
    }
}

public void bye() {
	//letter B
	setPenColour(Color.white);
	clear();
	penDown();
	setStroke(10);
	setxPos(190);
    setyPos(150);
    forward(150);
    penUp();
    setxPos(190);
    setyPos(150);
    turnLeft(90);
    penDown();
    forward(75);
    turnRight(90);
    forward(75);
    turnRight(90);
    forward(75);
    forward(-75);
    turnLeft(90);
    forward(75);
    turnRight(90);
    forward(75);
    
    //letter Y
    setPenColour(Color.cyan);
    setStroke(10);
	setxPos(320);
    setyPos(150);
    turnLeft(90);
    forward(75);
    turnLeft(90);
    forward(75);
    turnLeft(90);
    forward(75);
    setxPos(355);
    setyPos(225);
    turnLeft(180);
    forward(75);
    
    //letter E
    
    setPenColour(Color.yellow);
    setStroke(10);
	setxPos(450);
    setyPos(150);
    forward(150);
    turnLeft(90);
    forward(75);
    setxPos(450);
    setyPos(150);
    forward(75);
    setxPos(450);
    setyPos(150+75);
    forward(75);
    penUp();
    setxPos(450+175);
    setyPos(150+75);
    circle(1);circle(1);circle(1);	
	
}
@Override
public void about()
{
	clear();
	
	penUp();
	setStroke(10);
	setxPos(90);
    setyPos(150);

	 //Drawing letter S
    setPenColour(Color.red);
    turnRight(90);
    penDown();
	forward(60);
	turnLeft(90);
	forward(50);
	turnLeft(90);
	forward(60);
	turnRight(90);
	forward(50);
	turnRight(90);
	forward(60);
	

	//  letter A 
	penUp();
	setPenColour(Color.yellow);
	setxPos(150);
    setyPos(150);
	turnLeft(70); 
	penDown();
	forward(110);
	penUp();
	setxPos(150);
    setyPos(150);
    penDown();
    turnLeft(40);
    penDown();
    forward(110);
	penUp();
	
	//letter N
	setPenColour(Color.blue);
	setxPos(210);
    setyPos(150);
    penDown();	
    turnRight(20);
    forward(110);
    penUp();
    setxPos(210);
    setyPos(150);
    turnLeft(30);
    penDown();
    forward(110);
    turnLeft(150);
    forward(100);
    
  //letter S
    setxPos(300);
    setyPos(250); 
    setPenColour(Color.magenta);
    turnRight(90);
    penDown();
	forward(60);
	turnLeft(90);
	forward(50);
	turnLeft(90);
	forward(60);
	turnRight(90);
	forward(50);
	turnRight(90);
	forward(60);
	penUp();
	
  // letter K 
	setxPos(400);
    setyPos(150); 
    setPenColour(Color.white);
    turnRight(90);
    penDown();
    forward(100);
    penUp();
    setxPos(400);
    setyPos(200); 
    turnLeft(45);
    penDown();
    forward(80);
    penUp();
    setxPos(400);
    setyPos(200); 
    turnLeft(90);
    penDown();
    forward(80);
    penUp();
    
    
    //Letter A
    setPenColour(Color.cyan);
    setxPos(520);
    setyPos(150);
    turnRight(150); 
	penDown();
	forward(110);
	penUp();
	setxPos(520);
    setyPos(150);
    penDown();
    turnLeft(35);
    penDown();
    forward(110);
    penUp();
    
   //letter R 
    setPenColour(Color.green);
    setxPos(590);
    setyPos(150);
    penDown();
    turnRight(20);
    forward(110);
    penUp();
    setxPos(590);
    setyPos(150);
    penDown();
    turnLeft(90);
    forward(50);
    turnRight(90);
    forward(50);
    turnRight(90);
    forward(50);
    turnLeft(90+45);
    forward(90);
    penUp();
    setxPos(730);
    setyPos(200); 
    
    circle(1);
    
}

}




















