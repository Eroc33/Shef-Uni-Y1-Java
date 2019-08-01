import sheffield.*;
import java.lang.Math;
/**
 * Class to implement Assignment1 for COM1003
 * @author Euan Rochester (ACA15ER)
 */
public class Assignment
{
  static final int POUNDS_PER_STONE = 14;
  static final double KG_PER_POUND = 0.45359237;
  
  static final String PLANET_PREFIX = "On ";
  static final String PLANET_SUFFIX = " ";
  
  static final String GRAVITY_PREFIX = "is ";
  static final String GRAVITY_SUFFIX = "g";
  
  static final String EARTH = "Earth";
  public static void main(String[] args)
  {
    //setup IO
    EasyWriter out = new EasyWriter();
    EasyReader in = new EasyReader();
    EasyReader dataFile = new EasyReader("planets.txt");
    
    //get the user's weight
    out.println("What is your weight in stone and pounds?");
    int stone = in.readInt("stone:");
    int pounds = in.readInt("pounds:");
    
    //do the conversion
    double kilograms = ((stone*POUNDS_PER_STONE)+pounds)*KG_PER_POUND;
    
    //output the converted weight
    out.print(stone);
    out.print(" stones and ");
    out.print(pounds);
    out.print(" pounds is ");
    out.print(kilograms,2);
    out.println("kg on Earth");
    
    //extract the line from the planet file
    String line = dataFile.readString();
    
    //ends at a space following the start of the planet name
    int planetStartIndex = line.indexOf(PLANET_PREFIX)+PLANET_PREFIX.length();
    int planetEndIndex = line.indexOf(PLANET_SUFFIX,planetStartIndex);
    String planet = line.substring(planetStartIndex,planetEndIndex);
    
    //find the string that prefixes the gravity value and advance by the prefix length
    int gravityStartIndex = line.indexOf(GRAVITY_PREFIX)+GRAVITY_PREFIX.length();
    int gravityEndIndex = line.indexOf(GRAVITY_SUFFIX,gravityStartIndex);
    double gravity = Double.parseDouble(line.substring(gravityStartIndex,gravityEndIndex));
    
    //weight on whatever the planet is
    double weightOnPlanet = kilograms * gravity;
    
    //work out formatting
    int planetFieldLength = Math.max(EARTH.length(),planet.length());
    String planetFieldFormatString = "%-"+planetFieldLength+"s\t";//left justified length planetFieldLength string followed by a tab
    
    //output, using printf as tabs alone don't work with planet names which differ in length too much
    //(could also be done using a loop and StringBuilder)
    out.printf(planetFieldFormatString, EARTH);
    out.print(kilograms,4,8);
    out.println("kg");
    
    out.printf(planetFieldFormatString, planet);
    out.print(weightOnPlanet,4,8);
    out.println("kg");
    
  }
}