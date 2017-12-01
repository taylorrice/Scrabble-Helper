import java.io.*;
import java.util.*;
/**
 * This class takes in a text file containing a dictionary and a string of
 * random letters and if they are valid, returns all words in the dictionary
 * that can be made with those exact letters.
 * 
 * @author taylorrice
 *
 */
public class ScrabbleHelper {

	public static void main(String[] args) {
		
		try{
			//take args[0] and create a Dictionary object
			File file = new File(args[0]);
			Dictionary dictionary = new Dictionary(file);
			
			//take args[1] and create a Permutations object
			String letters = args[1];
			Permutations inputLetters = new Permutations(letters);
			
			//get all words from the group of letters and print them in alphabetical order
			ArrayList<String> validWords = inputLetters.getAllWords(dictionary);
			Collections.sort(validWords);;
			System.out.printf("Found %d words: \n", validWords.size());
			//use for loop to print array one word at a time
			for (int x=0; x<validWords.size(); x++){
				System.out.println(validWords.get(x));
			}
			
		//catch exception if either argument is missing and print correct error message	
		}catch (ArrayIndexOutOfBoundsException e){
			//if args[0] is missing tell user no file was input as argument
			if (e.getMessage().contains("0")){
				System.err.println("Error: Missing name of the input file.");
			//if args[1] is missing tell user no letters were input as an argument
			} else if(e.getMessage().contains("1")){
				System.err.println("Error: No letters input as an argument.");
			}
			//exit the program
			System.exit(1);	
		
		//if string of letters contains invalid characters tell user and quit the program
		}catch (IllegalArgumentException e){
			System.err.printf("Error: %s contains invalid characters.", args[1]);
			System.exit(1);
		}	

	}

}
