import sheffield.*;
public class Model {
	
	public static void main (String [] args)  {
		final int POUNDS_IN_A_STONE = 14;
		final double KILOGRAMS_IN_A_POUND = 0.453592;
		
		//Get the weight in stones and pounds
		EasyWriter screen = new EasyWriter();
		screen.println("Please type in a weight in stones and pounds");
		EasyReader keyboard = new EasyReader();
		int stones = keyboard.readInt("How many stones? ");
		int pounds = keyboard.readInt("How many pounds? ");
		
		//Turn it into kilograms
		double kilograms = 
					(stones*POUNDS_IN_A_STONE + pounds) * KILOGRAMS_IN_A_POUND;
					
		//Print out the weight
		screen.print(stones+" stones and "+pounds+" pounds is ");
		screen.print(kilograms,2);
		screen.println("kg on Earth");
		
		//Set the layout and print out details of Earth
		final String PADDING = "         ";
		final String KG = "kg";
		final int COLUMN_WIDTH=10;
		screen.print(("Earth"+PADDING).substring(0,COLUMN_WIDTH));
		screen.print(kilograms,4,12);
		screen.println(KG);
		
		//Read in the other planet and deal with it
		EasyReader planets = new EasyReader("planets.txt");
		String [] firstLine = planets.readString().split(" ");
		screen.print((firstLine[1]+PADDING).substring(0,COLUMN_WIDTH));
		screen.print( Double.valueOf(
			firstLine[6].substring(0,firstLine[6].length()-1))*kilograms,4,12);
		screen.println(KG);
		
//		Used for the version worth 70%
//		String otherPlanet = firstLine[1];
//		double surfaceGravity = Double.valueOf(
//			firstLine[6].substring(0,firstLine[6].length()-1));
//		screen.print(kilograms,4);
//		screen.print("kg on Earth weighs ");
//		screen.print(surfaceGravity*kilograms,4);
//		screen.println("kg on "+otherPlanet);
		
	}

}