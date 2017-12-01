import java.io.*;
import java.util.*;
/**
 * This class creates dictionary objects which have an ArrayList of
 * words
 * 
 * @author taylorrice
 *
 */
@SuppressWarnings("serial")
public class Dictionary extends ArrayList<String> {
	ArrayList<String> dictionary = new ArrayList<String>();
	
	/**
	 * Constructor which takes a text file and if it exists creates
	 * an ArrayList of all the words in lowercase
	 * 
	 * @param f Text file containing dictionary words
	 * 
	 * @throws IllegalArgumentException if something is wrong with the file
	 * entered as a parameter
	 */
	public Dictionary (File f) throws IllegalArgumentException{
		//initiate scanner
		Scanner dictionaryReader = null;
		
		try{
			File file = f;
			dictionaryReader = new Scanner(file);
			
			//as long as the dictionary file has a next line
			while (dictionaryReader.hasNextLine()){
				//add line to the dictionary as a word in lowercase
				String word = dictionaryReader.nextLine();
				dictionary.add(word.toLowerCase());
			}
		}catch (FileNotFoundException e){
			//if the file path doesn't lead to anything display error message
			System.err.printf("Error: File %s does not exist.", f);
			System.exit(1);
		}
		//close scanner
		dictionaryReader.close();
	}
	/**
	 * Helper method which uses the isWord(str, begin, end) while keeping
	 * the indices hidden in the main class
	 * 
	 * @param str string that is being tested
	 * 
	 * @return true if the word is in the dictionary, and false if the word
	 * is not in the dictionary
	 * 	
	 */
	public boolean isWord(String str){
		return isWord(str, 0, dictionary.size()-1);
	}
	/**
	 * Method that implements a recursive binary search and checks whether
	 * or not a given word is in the dictionary
	 * 
	 * @param str word being tested
	 * @param begin the starting index of the dictionary ArrayList
	 * @param end the last index of the dictionary ArrayList
	 * @return
	 */
	private boolean isWord(String str, int begin, int end){
		//get the middle index
		int mid = (end + begin)/2;
		//base case -- if begin index is larger than end index return false
		if (begin > end){
			return false; 
		}
		String testWord = dictionary.get(mid);
		//if the word at the index is the same as the input string return true
		if (testWord.equals(str)){
			return true;
		//string testing comes before middle index word alphabetically,
		//run method recursively using the indices for first half of the list only
		}else if (testWord.compareTo(str) > 0){
			return isWord(str, begin, mid-1);
		//input word comes later alphabetically, run method again on 
		//latter half of the dictionary
		}else{
			return isWord(str, mid+1, end);
		}
		
	}
	/**
	 * Helper method that uses isPrefix(str, begin, end) method
	 * while keeping the indices parameters hidden in the main class
	 * 
	 * @param str a prefix being tested against the dictionary
	 * @return true if prefix exists in dictionary false otherwise
	 */
	public boolean isPrefix(String str){
		return isPrefix(str, 0, dictionary.size()-1);
	}
	/**
	 * Method that implements a recursive binary search of the dictionary
	 * ArrayList to determine whether an input string is a prefix of any words
	 * in the dictionary
	 * 
	 * @param str prefix being tested
	 * @param begin the starting index of the dictionary ArrayList
	 * @param end the last index of the dictionary ArrayList
	 * 
	 * @return true if a word exists in the dictionary with the given prefix,
	 * false otherwise
	 */
	private boolean isPrefix(String str, int begin, int end){
		//get middle index of dictionary
		int mid = (end + begin)/2;
		//base case -- if begin index is bigger than end index return false
		if (begin > end){
			return false; 
		}
		String testWord = dictionary.get(mid);
		//if the string is the prefix of the word at middle index return true
		if (testWord.startsWith(str)){
			return true;
		//if the prefix comes before the middle word alphabetically
		//test again on first half of the dictionary
		}else if (testWord.compareTo(str) > 0){
			return isPrefix(str, begin, mid-1);
		//if the prefix comes after the middle word alphabetically
		//test again on the latter half of the dictionary
		}else{
			return isPrefix(str, mid+1, end);
		}
		
	}
	/**
	 * Returns the dictionary ArrayList as a string
	 * 
	 * @return string of dictionary ArrayList 
	 */
	public String toString(){
		//return the dictionary ArrayList as a string
		return dictionary.toString();
	}
}
