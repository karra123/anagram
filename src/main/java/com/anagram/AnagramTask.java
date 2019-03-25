package com.anagram;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AnagramTask implements Runnable {
	
	private Set<String> setOfWords;
	private Set<String> anagrams = new HashSet<>();
	
	public AnagramTask(Set<String> setOfWords){
		this.setOfWords = setOfWords;
	}
	
	public static boolean areAnagrams(String value1, String value2) {
		//the incoming data is assumed to be of same length and in lower case
        HashMap<Character, Integer> chars = new HashMap<Character, Integer>(value1.length());
        Integer count;
        for (char c : value1.toCharArray()) {
            count = chars.get(c);
            count = Integer.valueOf(count != null ? count + 1 : 1);
            chars.put(c, count);
        }
        for (char c : value2.toCharArray()) {
            count = chars.get(c);
            if (count == null) {
                return false;
            } else {
                count--;
                chars.put(c, count);
            }
        }
        for (Integer i : chars.values()) {
            if (i != 0) {
                return false;
            }
        }
        return true;
	}

	@Override
	public void run() {
		if (setOfWords!=null && !setOfWords.isEmpty() && setOfWords.size()>=2){			
			String [] words = setOfWords.toArray(new String[0]);
			for (int i=0; i<words.length ; i++){
				for (int j=0; j<words.length ; j++){
					if (i!=j){
						if (areAnagrams(words[i], words[j])){
							anagrams.add(words[j]);
							anagrams.add(words[i]);
						}
					}
				}
			}
			printAnagrams();
		}
	}
	
	private void printAnagrams(){
		if (!anagrams.isEmpty()){
			System.out.print("Annagrams found : " );
			anagrams.forEach(
			    (ele)->{
			               System.out.print(ele+",");
			           }
			);
			System.out.println("");
		}
	}

}
