/*
 * Decrypt.java
 * Done by: Ahmad Alsabagh
 * Last modified: November 6th, 2016
 * This programs takes an encrypted message and performs transposition cipher and substitution cipher to get the
 * decrypted message.
 * */

//Imported packages
import java.util.*;
import java.io.*;


public class Decrypt
{
  public static StringBuffer ALPHABET = new StringBuffer ("ABCDEFGHIJKLMNOPQRSTUVWXYZ "); //Class varaible of the alphabet
  
  //Main method
  public static void main(String[] args) throws IOException
  {
    
    //Key input
    String key = args[0]; //takes first argument from the terminal
    int hash = key.hashCode();
    
    //File input
    BufferedReader fileInput = new BufferedReader(new FileReader(args[1]));
    
    StringBuffer decryptedAnswer = new StringBuffer(substitution(hash,transposer(hash,removePunctuation(storeFile(hash, fileInput)))));
    decrypter(decryptedAnswer, args);
    System.out.println("Your file has been successfully decrypted! Please check the output text file.");
    
    fileInput.close();
  }
  
  /*
   * Creates a new alphabet given a hashkey
   * @param hashNumber the abritrary number made using the key input
   * @return newAlphabet the new alphabet created using the hash number
   * */
  public static StringBuffer constantSubstitution (int hashNumber)
  {
    StringBuffer CONSTANT = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ ");
    Random random = new Random(hashNumber); //new random object with hashNumber
    StringBuffer newAlphabet = new StringBuffer(); //ALPHABET after it has been scrambled
    
    for (int x = 0; x < 100; x++)
    {
      //Generating two random numbers
      int randomNumberOne = random.nextInt(27);
      int randomNumberTwo = random.nextInt(27);
      
      //Find the letter at the position of the random numbers
      char charOne = CONSTANT.charAt(randomNumberOne);
      char charTwo = CONSTANT.charAt(randomNumberTwo);
      
      //Replace the two letters
      CONSTANT.setCharAt(randomNumberOne, charTwo);
      CONSTANT.setCharAt(randomNumberTwo, charOne);
      
    } 
    newAlphabet = CONSTANT;
    return newAlphabet;
  }
  
  /*
   * Performs substitution cipher on a given StringBuffer
   * @param hashNumber the number produced when given a key input
   * @param line a StringBuffer to perform the substitution cipher on
   * @return line the substitute ciphered StringBuffer
   * */
  public static StringBuffer substitution(int hashNumber, StringBuffer line)
  { 
    StringBuffer newAlphabet = new StringBuffer (constantSubstitution(hashNumber));
    
    //Performs substitution
    for (int x = 0; x < line.length(); x++)
    {
      for(int y = 0; y < newAlphabet.length(); y++)
      {
        if(line.charAt(x) == newAlphabet.charAt(y))
        {
          line.setCharAt(x, ALPHABET.charAt(y));
          break;
        }
        
      }
    }
    return line;
  }
  
  /*
   * scrambles the order of the number 0-7
   * @param hashNumber the number created given the key input
   * @return pattern a scrambled number from 0-7
   * */
  public static int[] order(int hashNumber)
  {
    int[] pattern = {0,1,2,3,4,5,6,7}; //pattern from 0-7 before being scrambled
    int tempStorage; //holds a number temporarily
    
    //new seed
    Random seed = new Random(hashNumber);
    
    for(int y = 0; y < 100; y++)
    {
      int positionOne = seed.nextInt(8);
      int positionTwo = seed.nextInt(8);
      
      tempStorage = pattern[positionOne];
      pattern[positionOne] = pattern[positionTwo];
      pattern[positionTwo] = tempStorage;
      
    }
    return pattern;
  }
  
  /*
   * Stores the text file into a StringBuffer
   * @param hashNumber the number created by the key input
   * @param fileInput the file input from the uer
   * @return fileOutput the file stored in a string
   * */
  public static StringBuffer storeFile(int hashNumber, BufferedReader fileInput) throws IOException
  {
    StringBuffer fileOutput = new StringBuffer(); //Used to store file text
    String line = fileInput.readLine();  //Used to read the file
    
    //Stores the file text into fileOutput
    while(line != null)
    {
      fileOutput.append(line);
      line = fileInput.readLine();
    }
    return fileOutput;
  }
  
  /*
   * Does transposition cipher on a given StringBuffer
   * @param hashNumber the number created by the key input
   * @param fileInput the text file stored in a StringBuffer
   * @return the text after transposition cipher is done on it
   * */
  public static StringBuffer transposer(int hashNumber, StringBuffer fileInput)
  {
    //Variable instantiation 
    char[][] matrix = new char[8][8]; //2D array to make a 8x8 matrix
    int[] arrayOrder = order(hashNumber); //Recalling order method to get the order of columns
    String chunkString = ""; //breaks the text into chunks
    int counter = 0; //counter
    StringBuffer answer = new StringBuffer(); //stores the string after transposition
    String tempString = "";//Temporary String to store the array then after add it to final answer
    int multipleOfSixtyFour = fileInput.length()/64; //determines how many 64 chars there are in the text
    
    for (int x = 0; x < multipleOfSixtyFour; x++)
    {
      chunkString = fileInput.substring(x * 64, (x+1) * 64); //chunks of 64 characters
      
      //writing it column by column according to the user's chosen order                             
      for (int columns = 0; columns < 8; columns++)
      {
        for (int rows = 0; rows < 8; rows++)
        {
          matrix[rows][arrayOrder[columns]] = chunkString.charAt(counter);
          counter++;    
        }
      } 
      
      //converts the array to a string
      for (int z = 0; z < matrix.length; z++)
      {
        for (int y = 0; y < matrix.length; y++)
        {
          tempString = tempString + matrix[z][y];
        }
        answer = answer.append(tempString); // concatenate each 64 chars
        
        //reseting variables
        counter = 0; //reset counter for the next chunkString
        tempString = ""; // reset tempString for the next 64 chars
        chunkString = ""; // reset chunkString for the next 64 chars  
      }
    }
    return answer; 
  }
  
  /*
   * Removes punctuation & capitalizes the letters
   * @param fileInput the file after it has been made into a string
   * @return finalString StringBuffer ready to be transposed and substituted
   * */
  public static StringBuffer removePunctuation (StringBuffer fileInput)
  {
    String noPunctuation = fileInput.toString();
    noPunctuation = noPunctuation.replaceAll("[^a-zA-Z\\s]", ""); //Regex removes everything that is not letters and whitespace
    String upperCase = noPunctuation.toUpperCase();  //Converts everything to uppercase
    StringBuffer finalString = new StringBuffer(upperCase);      //Convert the string into a string buffer and return it
    return finalString;
  }
  
  /*
   * Outputs decyption to a text file
   * @param line the StringBuffer to be outputted to a text file
   * @param args to allow the naming of the output file from the command line
   * */
  public static void decrypter(StringBuffer line, String[] args) throws IOException
  {
    StringBuffer transposedAnswer = new StringBuffer(line); // stores decrypted message
    PrintStream output = new PrintStream(new FileOutputStream(args[2])); //the name of the output text file
    output.print(transposedAnswer);
    
    //closes leak
    output.close();
  }
}