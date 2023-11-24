package generic;

import java.io.PrintWriter;

public class Statistics {
	
	// TODO add your statistics here
	static int numberOfInstructions=0;
	static int numberOfCycles=0;
	 static int noOfwrongbranches=0;
	static int noOfHaults=0;
	public static void printStatistics(String statFile,String msg)
	{
		try
		{
				//Printing statistics in the statFile
			PrintWriter writer = new PrintWriter(statFile);
			writer.println(msg);
			writer.println("Number of instructions executed = " + numberOfInstructions);
			writer.println("Number of cycles taken = " + numberOfCycles);
			writer.println("Number of wrong branches taken :"+noOfwrongbranches);
			writer.println("Number of wrong instructions entered pipeline for wrong branches :"+noOfwrongbranches*2);
			writer.println("Number of haults : "+noOfHaults);
			// TODO add code here to print statistics in the output file
			
			writer.close();
		}
		catch(Exception e)
		{
			Misc.printErrorAndExit(e.getMessage());
		}
	}
	
	// TODO write functions to update statistics
	public static void setNumberOfInstructions(int numberOfInstructions) {
		Statistics.numberOfInstructions = numberOfInstructions;
	}

	public static void setNumberOfCycles(int numberOfCycles) {
		Statistics.numberOfCycles = numberOfCycles;
	}
	public static int getNumberOfInstructions(){
		return numberOfInstructions;
	}
	public static  int getNumberOfCycles(){
		return numberOfCycles;
	}
	public static int getnoOfWrongbranches(){
		return noOfwrongbranches;
	}
	public static void setnowrongbranches(int x){
		Statistics.noOfwrongbranches=x;
	}
	public static int getNoHaults(){
		return noOfHaults;
	}
	public static void setNoHaults(int x){
		Statistics.noOfHaults=x;
	}
}
