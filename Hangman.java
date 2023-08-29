import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Hangman {

    public static Scanner scanner = new Scanner(System.in);

    public static Random random = new Random();
    
    public static boolean playing = true;

    public static int missmatch = 0;

    public static char[] placeholders;

    public static char[] chars;

    public static String mistakes = "";
    
    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    public static void main(String[] args) {

        System.out.println("Welcome in Hangman III - The Game");
        
        String word = randomWord();

        chars = word.toCharArray();

        placeholders = new char[word.length()];
        for (int i = 0; i < placeholders.length; i++) {
            placeholders[i] = '_';
        }                
        
        while(playing){
            printGallows(missmatch, gallows);

            printPlaceholders(placeholders);

            String guess = scanner.nextLine();

            String result = checkGuess(word, guess);

            if (result.equals("miss")){
               mistakes += (" " + guess.charAt(0));
            }

            updatePlaceholders(result, word);

            System.out.println("Mistakes: " + mistakes);
          if (Arrays.toString(placeholders).equals(Arrays.toString(chars))){
                System.out.println("You win, congrats!!!!");
                System.out.println(word);
                playing = false;
            }
            if (missmatch == 7){
                System.out.println("You lose :(");
                System.out.println(word);
                System.exit(0);
            }    
        } 
        scanner.close();
    }

        /**
     * Function name: randomWord
     * 
     * Inside the function
     *  1. Returns random word.
     * @return (String)
     */
    public static String randomWord(){
        return words[random.nextInt(words.length)];
    }

    /**
     * Function name: printPlaceholders
     * @param word (String)
     * 
     * Inside the function:
     *   1. converts word to "-" and prints it.
     */
    public static void printPlaceholders(char[] placeholders){
      for (char c : placeholders) {
        System.out.print(c);
      }
      System.out.println();
    }

    /**
     * Fkunction name: updatePlaceholders
     * @param result (String)
     * @param word (String)
     * Inside the function:
     *  1. Updates char[] placeholders field with guest chars
     */

    public static void updatePlaceholders(String result, String word){
        String[] outcome = result.split("-");
        for (int i = 0; i < outcome.length; i++) {
            if(result.equals("miss")){
                break;
            }
            int resultIndex = Integer.parseInt(outcome[i]);
            for (int j = 0; j < chars.length; j++) {
                if (resultIndex == j){
                    placeholders[j] = chars[j];
                }
            }
        }
       
    }

    /**
    * Function name: printGallows
    * @param mismatch (int)
    * @param gallows (String[])
    *
    * Inside the function:
    * 1. Prints correct gallow by user mistakes.
    */
    public static void printGallows(int missmatch, String[] gallows){
        System.out.println(gallows[missmatch]);
        System.out.println("\n");
    }
    

    /**
     * Function name: checkGuess
     * @param word
     * @param guess
     * @return (int[])
     * 
     * Inside the function:
     * 1. Checks how many leters from guess are correct 
     * 2. Checks how many leters from guess are incorrect
     * 2. End game if user hit correct word - status win :)
     * 3. End game if user hit to many miss letters
     * 4. Returns int[] same lenght as word filed with:
     *  a) 1 if letter is correct
     *  b) 0 if not
     */
    public static String checkGuess(String word, String guess){
        String result = "";
        boolean isCorrect = false;
        if (guess.equalsIgnoreCase(word)){
            System.out.println("You win, congrats!!!!");
            System.exit(0);
        }
        for (int i = 0; i < chars.length; i++) {
            if(guess.charAt(0) == chars[i]){
                isCorrect = true;
                result += Integer.toString(i).charAt(0) + "-";
            }
        }
        if(!isCorrect){
            missmatch++;
            return "miss";
        } 
        return result;
    }
}