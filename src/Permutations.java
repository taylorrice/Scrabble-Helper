import java.util.*;
/**
 * This class creates Permutations objects, which are strings of lowercase letters
 * 
 * @author taylorrice
 *
 */
public class Permutations {
	//initialize variables
	String letters;
	private String seq = "";
	private ArrayList<String> allPermutations = new ArrayList<String>();
	private ArrayList<String> allWords = new ArrayList<String>();
	
	/**
	 * This constructor takes the input letters string, checks to
	 * make sure the characters are valid letters, converts them to
	 * lowercase and stores them in the letters variable
	 * 
	 * @param letters string of random letters
	 * @throws IllegalArgumentException thrown when there are characters
	 * other than letters in the letters parameter string
	 */
	Permutations (String letters) throws IllegalArgumentException{
		//get number of letters
		int length = letters.length();
		//initialize empty string to hold reformatted letters
		String validLetters = "";
		
		//go through the characters of the letter string in a for loop
		for (int x=0; x<length; x++){
			char c = letters.charAt(x);
			//if the character is a letter, make it lowercase and
			//add it to the valid letters string
			if(Character.isLetter(c)){
				String letter = Character.toString(c);
				validLetters = validLetters + letter.toLowerCase();
				continue;
			//if character is not a letter throw illegal argument exception
			}else{
				throw new IllegalArgumentException();
			}
		}
		this.letters = validLetters;
		
		
	}
	/**
	 * Helper method that gets all permutations but keeps parameters
	 * of recursive method hidden
	 * 
	 * @return returns ArrayList of all permutations created by recursive
	 * getAllPermutations(letters, seq) method
	 */
	public ArrayList<String> getAllPermutations(){
		return getAllPermutations(letters, seq);
	}
	/**
	 * This is a recursive method that takes in the desired letters as a string
	 * and an empty string, and then creates every permutation of the letters
	 * and adds them to an ArrayList
	 * 
	 * @param letters String containing the letters that are being used to create
	 * the permutations
	 * @param seq an empty string that will store the sequences of letters that are
	 * creating the permutations as the method is called recursively
	 * 
	 * @return an ArrayList containing all Permutations of those letters given in
	 * the parameter
	 */
	private ArrayList<String> getAllPermutations(String letters, String seq){
			//if there are no more letters to use, add sequence to ArrayList
			//base case
			if (letters.length() == 0){
				if (!allPermutations.contains(seq)){
					allPermutations.add(seq);
				}
				
			}else{
				//for each character in the letters string
				for (int x=0; x<letters.length(); x++){
					//create new string of letters excluding the one you just used
					String newLetters = letters.substring(0, x) + letters.substring(x+1);
					//at the current letter to the sequence
					String newSeq = seq + letters.charAt(x);
					//use method again recursively
					getAllPermutations(newLetters, newSeq);	
				}
			}
			//return ArrayList containing all sequences
			return allPermutations;
	}
	/**
	 * Helper method that gets all words in a dictionary that can be created
	 * with the Permutations' letters, while keeping the parameters of the
	 * recursive method hidden
	 * 
	 * @param dictionary an ArrayList of all words in the given dictionary
	 * in alphabetical order
	 * 
	 * @return an ArrayList created by the getAllWords(letters, seq, dictionary)
	 * method
	 *
	 */
	public ArrayList<String> getAllWords(Dictionary dictionary){
		return getAllWords(letters, seq, dictionary);
	}
	/**
	 * A recursive method that takes a string of letters, an empty string, and
	 * a dictionary, and creates permutations of the letters, checking them against
	 * the dictionary each time a new letter is added, if that prefix exists, the recursion
	 * continues, otherwise that prefix is skipped over with backtracking. if all letters have
	 * been added to the sequence and the sequence is a word in the dictionary, the sequence is
	 * added to the ArrayList
	 * 
	 * @param letters String of random letters
	 * @param seq an empty string that holds sequences as they are created
	 * @param dictionary an ArrayList of words in alphabetical order
	 * 
	 * @return an ArrayList of all words in the dictionary that can be created
	 * with the given letters
	 */
	private ArrayList<String> getAllWords(String letters, String seq, Dictionary dictionary){
			//if no letters are left
			//base case
			if (letters.length() == 0){
				//only add word to ArrayList if it isn't already there
				//and it is a valid word in the dictionary
				if (!allWords.contains(seq) && dictionary.isWord(seq)){
						allWords.add(seq);
				}
				
			}else{
				//for each letter in the string
				for (int x=0; x<letters.length(); x++){
					//add that letter to the seq, and remove that letter from
					//letter string to avoid using it again
					String newLetters = letters.substring(0, x) + letters.substring(x+1);
					String newSeq = seq + letters.charAt(x);
					//only use method recursively if that sequence exists
					//as a prefix in the dictionary
					//backtracking
					if (dictionary.isPrefix(newSeq)){
						getAllWords(newLetters, newSeq, dictionary);
					}
					
				}
			}
			return allWords;

	}

}
