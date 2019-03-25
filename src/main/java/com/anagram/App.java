package com.anagram;

import java.io.PrintStream;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App 
{
	private static String inFileName;
	private static String outFileName;
	private static ExecutorService executor;

	public static void main( String[] args )
    {
		try {
	    	if (!checkArgs(args)) {
	    		printUsage();
	    		return;
	    	}
	    	PrintStream fileOut = new PrintStream(outFileName);
	    	System.setOut(fileOut);
	        FileProcessor fp = new FileProcessor(inFileName);
	        Map<Integer, Set<String>> mapOfWords = fp.getMapOfWords();
	        executor = Executors.newFixedThreadPool(2);
	        for (Set<String> set : mapOfWords.values()){
	        	AnagramTask at = new AnagramTask(set);
	        	executor.execute(at);
	        }
	        executor.shutdown();
		}
		catch (Exception e){
			
		}
    }
    public static boolean checkArgs(String[] args){
    	if (args==null || args.length!=2){
    		return false;
    	}
    	else {
    		inFileName = args[0];
    		outFileName = args[1];
    		return true;
    	}
    }
    
    public static void printUsage(){
    	System.out.println("Usage : java -jar anagram.jar <InFileName> <OutFileName>");
    }

}
