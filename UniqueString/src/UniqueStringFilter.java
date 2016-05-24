import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UniqueStringFilter {
	// 1) A method that takes an input of List of Strings and returns a list of
	// unique strings
	// Input: “ Is it Sunny”, “ Sunny it is”, “Hello World”, “Hello World”,
	// “hello world”
	// Output: “ Sunny it is”, “hello world”,
	//
	// Rules:
	// a) two strings are considered the same if the only difference between the
	// two is the uppercase and lowercase characters.
	// b) Two strings are considered the same as long as they have the same
	// words in them. In the above example " Is it Sunny" and " Sunny it is" are
	// considered the same.
	// c) return the last seen string if multiple strings are the same. In the
	// above example “Hello World”, “Hello World”, “hello world” return “hello
	// world”
	// d) The input order needs to be maintained in the output order.

	public List <String> returnUniqueStrings (List <String> inputString)
	{
		if (inputString.size() == 0) {
			return inputString;
		}
		
		// Simple method
//	    return removeDupByLoopAndCompare(inputString);
	    
	    // A.Normalize each string: convert to lower case and sort words.
	    // B.CCompare string to prev and keep prev is string changed
	    // C.Sort non dup pair back to original order
	    // D. Return the string from original input. 
//	    return removeDupBySortAndCompare(inputString);
	    
	    // Instead of B & C, we can use a HashMap to filter out duplicates after normalization.
		return removeDupWithHashMap(inputString);
	    
	}

	/**
	 * @param inputString
	 * @return
	 */
	private List<String> removeDupBySortAndCompare(List<String> inputString) {
		List<Pair> normalizedPair = new ArrayList<Pair>();
	    for (int idx=0; idx<inputString.size(); idx++) {
	    	String str = normalizeString(inputString.get(idx));
	    	normalizedPair.add(new Pair(str, idx));
	    }
	    
	    Collections.sort(normalizedPair, new Comparator<Pair>() {
	        public int compare(Pair o1, Pair o2) {
	           int result = o1.str.compareTo(o2.str);
	           if (result != 0) {
	               return result;
	           }
	           return Integer.compare(o1.index, o2.index);    
	        }

	    });

	    // loop through list to remove duplicates
	    List<Pair> nonDupResult = new ArrayList<Pair>();
	    
	    Iterator<Pair> it = normalizedPair.iterator();
	    Pair prevPair = normalizedPair.get(0);
	    
	    Pair currentPair = null;
	    while ( it.hasNext()) {
	    	currentPair = it.next();
	        
	        // If a new string is found, store the previous str.
	        if (!currentPair.str.equals(prevPair.str)) {
	            nonDupResult.add(prevPair);
	        }
            prevPair = currentPair;
	            
	    }

	    // Last element.
	    if (currentPair != null) {
            nonDupResult.add(currentPair);
	    }
	    // Put the string back according to original order
	    Collections.sort(nonDupResult, new Comparator<Pair>() {
	        public int compare(Pair o1, Pair o2) {
	           // Only sort by index
	           return Integer.compare(o1.index, o2.index);    
	        }
	    });
	    
	    // Final result;
	    List<String> result = new ArrayList<String>();
	    
	    for (Pair pair: nonDupResult) {
	        result.add(inputString.get(pair.index)); // get original string
	    }

	    return result;
	}

	/**
	 * @param inputString
	 * @return
	 */
	private List<String> removeDupWithHashMap(List<String> inputString) {
		Map<String, Integer> map = new HashMap<String, Integer>();
	    for (int idx=0; idx<inputString.size(); idx++) {
	    	map.put(normalizeString(inputString.get(idx)), idx); // Keep later index for duplicate string.
	    }
	    
	    List<Integer> uniqIndices = new ArrayList<Integer>();
	    uniqIndices.addAll(map.values());
	    Collections.sort(uniqIndices);
	    
	    List<String> result = new ArrayList<String>();
	    
	    for (Integer idx: uniqIndices) {
	        result.add(inputString.get(idx)); // get original string
	    }

	    	return result;
	}

	static class Pair {
		final String str;
		int index;

		Pair(String str, Integer index) {
			this.str = str;
			this.index = index;
		}

	}

	String normalizeString(String str) {
		String[] words = str.trim().toLowerCase().split(" ");
		Arrays.sort(words);
		return String.join(" ", words);
	}

	/**
	 * @param inputString
	 * @return 
	 */
	private List<String> removeDupByLoopAndCompare(List<String> inputString) {
		List<String> result = new LinkedList<String>();
		for (String currentString: inputString) {
			insertOrReplace(result, currentString);
		}
		return result;
	}

	void insertOrReplace(List<String> result, String str) {
		int idx = findStringInList(result, str);
		if (idx >= 0) {
			result.remove(idx);
			result.add(idx, str); // Rule d: Override with a later duplicate to
									// keep the last dup in original order
									// "hello world" "sunny it is" "Hello world"
									// => "Hello world" "sunny it is"
		} else {
			result.add(str); // Append the string to the list of results.
		}
	}

	int findStringInList(List<String> list, String str) {
		for (int i = 0; i < list.size(); i++) {
			String currentStr = list.get(i);
			if (isDuplicate(currentStr, str)) {
				return i;
			}
		}
		return -1;
	}

	boolean isDuplicate(String str1, String str2) {

	    String[] words1 = str1.trim().split(" ");
	    String[] words2 = str2.trim().split(" ");
	    
	    // If the rules desire, we can filter out the duplicate words before compare the length, we assume duplicate words are still counted.
	    
	    if (words1.length != words2.length) return false; // "is it sunny" != "is it sunny sunny"
	    
	    List<String> words2Array = new ArrayList<String>();
	    words2Array.addAll(Arrays.asList(words2)); // asList is not modifiable, put the elements into new list.
	    for (int i=0; i<words1.length; i++ ) {
	        int foundIdx = -1;
	        // Loop through second list
	        for (int j=0; j<words2Array.size(); j++) {
	            // rule c
	            if (words1[i].equalsIgnoreCase(words2Array.get(j))) {
	                foundIdx = j;
	                break;
	            }
	        }
	        if (foundIdx < 0) {
	            return false;
	        }
	        words2Array.remove(foundIdx);
	    }
	    
	    return words2Array.size() == 0;
	    
	}
}
