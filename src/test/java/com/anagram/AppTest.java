package com.anagram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Unit test for simple App.
 */
public class AppTest    
{
	@Test
	public void testApplicationCreatesFile(){
		try {
	    	PrintStream fileOut = new PrintStream("anagram_out.txt");
	    	System.setOut(fileOut);
	    	ExecutorService executor =  Executors.newSingleThreadExecutor();
	    	FileProcessor fp = new FileProcessor("anagram.txt");
	        Map<Integer, Set<String>> mapOfWords = fp.getMapOfWords();
	        executor = Executors.newFixedThreadPool(2);
	        for (Set<String> set : mapOfWords.values()){
	        	AnagramTask at = new AnagramTask(set);
	        	executor.execute(at);
	        }
	        executor.shutdown();
	        File checkOutFile = new File("anagram_out.txt");
	    	Assert.assertTrue(checkOutFile.exists());
		}
		catch (Exception e){			
		}
	}
	
	@Test (dependsOnMethods={"testApplicationCreatesFile"})
	public void testApplicationCreatedFileWithOneLine(){
	    try (BufferedReader br = new BufferedReader(new FileReader("cusip_out.txt"))) {
	    	String currentLine;
	    	int lineCount = 0 ;
			while ((currentLine = br.readLine()) != null) {
				lineCount++;
			}
			Assert.assertTrue(lineCount == 1);
	    }
	    catch (Exception e){
	    	
	    }		
	}

}
