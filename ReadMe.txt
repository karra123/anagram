Anagrams Problem
	The following assumptions are made.
	
		1) Since its only a block of text (as mentioned in problem statement), assuming it to fit into memory. So that we have complete set of words 
			available to find anagrams.
		
		2) The program outputs to file, but can be easily changed to write to console, by just commenting couple of lines code. This is done for faster 
			processing and facilitate adding test cases.

	To Build and run the program.

		1) This is a maven project, using Java 8.

		2) Build it as follows:
			mvn clean install

		3) Run the program as follows: (Usage : java -jar anagram.jar <InFileName> <OutFileName>)
			java -jar  anagram-1.0-SNAPSHOT.jar cusip.txt out.txt

		4) A sample "anagram.txt" file is included for testing the program as part of maven build. The build out to a file "anagram_out.txt".
