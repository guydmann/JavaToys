package Phraser;
/**
 * 
 */

/**
 * @author guydmann
 *
 */
public class PhraseTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testPangram();
		//testPalingram();
		//testPalindrome();
	}


	/*
	 * testPalingram
	 * is the testing code for the palingram functions
	 */
	public static void testPalingram () {
		String sTest_text;
		String[] asTexts = {"I Palindrome, I","'son, i am able,' she said 'though you scare me.' 'watch,' said I 'Beloved,' I said 'watch me scare you though.' said she, 'able am i, Son'", "hello world", "test", "The quick brown fox jumps over god", "i am i"};
		for (int i = 0; i < asTexts.length ; i++) {
			sTest_text = asTexts[i];
			if (checkPalingram(sTest_text)) {
				System.out.println("'" + sTest_text + "' is a palingram\n");
				
			} else {
				System.out.println("'" + sTest_text + "' is not a palingram\n");
			}
		}
		
	}
	
	/*
	 * checkPalingram
	 * a palingram is a string that contains the same words in the same order whether it si read forwards or backwards
	 * the palingram test ignores all punctuation.
	 * returns true if the test_string is a palingram and false if it is not
	 * 
	 */
	public static boolean checkPalingram(String sTest) {
		String stripped = sTest.replaceAll("\\p{Punct}+", "");
		String[] asTest_temp = stripped.toLowerCase().split(" ");
		for (int i = 0; i <= asTest_temp.length/2; i++) {
			if(!asTest_temp[i].equals(asTest_temp[asTest_temp.length-1-i])) {
				//System.out.println(asTest_temp[i] + " " + asTest_temp[asTest_temp.length-1-i]);
				return false;
			}
		}
		return true;
	}

	/*
	 * testPalindrome
	 * is the testing code for the palindrome functions
	 */
	public static void testPalindrome () {
		String sTest_text;
		String[] asTexts = {"dammit i'm mad","hello world", "test", "able was i ere i saw elba", "A man, A plan, A canal, panama"};
		for (int i = 0; i < asTexts.length ; i++) {
			sTest_text = asTexts[i];
			if (checkPalindrome(sTest_text)) {
				System.out.println("'" + sTest_text + "' is a palindrome\n");
				
			} else {
				System.out.println("'" + sTest_text + "' is not a palindrome\n");
			}
		}
		
	}
	
	/*
	 * checkPalindrome
	 * a palingram is a string that contains the same letters in the same order forwards and backwards
	 * the palindrome test ignores all punctuation.
	 * returns true if the test_string is a palingram and false if it is not
	 * 
	 */
	public static boolean checkPalindrome(String sTest) {
		String stripped = sTest.replaceAll("\\p{Punct}+", "");
		stripped = stripped.replaceAll("\\s+", "");
		String sTest_temp = stripped.toLowerCase();
		//String sTest_temp = sTest.toLowerCase();
		for (int i = 0; i <= sTest_temp.length() /2; i++) {
			if(sTest_temp.charAt(i) != sTest_temp.charAt(sTest_temp.length()-1-i)) {
				//System.out.println(sTest_temp.charAt(i) + " " + sTest_temp.charAt(sTest_temp.length()-1-i));
				return false;
			}
		}
		return true;
	}
	
	/*
	 * testPangram
	 * is the testing code for the pangram functions
	 */
	public static void testPangram () {
		String sTest_text;
		String[] asTexts = {"The quick brown fox jumps over the laZy dog", "hello world", "test", "The quick brown fox jumps over god"};
		for (int i = 0; i < asTexts.length ; i++) {
			sTest_text = asTexts[i];
			if (checkPangram(sTest_text)) {
				System.out.println("'" + sTest_text + "' is a pangram\n");
				
			} else {
				System.out.println("'" + sTest_text + "' is not a pangram");
				System.out.println(getMissingLetters(sTest_text ) + " are missing\n");
			}
		}
		
	}
	
	
	/*
	 * checkPangram
	 * a pangram is a string that contains at least 1 of each letter in the alphabet {a..z)
	 * only works for the english alphabet
	 * returns true if the test_string is a pangram and false if it is not
	 * 
	 */
	public static boolean checkPangram(String sTest) {
		String sTest_temp = sTest.toLowerCase();
		for (char c = 'a'; c <= 'z'; c++) {
			if(sTest_temp.indexOf(c)==-1) return false;
		}
		return true;
	}
	
	/*
	 * getMissingLetters
	 * determines the missing letters for a pangram
	 * returns a String containing all missing letters
	 */
	public static String getMissingLetters(String sTest) {
		String sMissing_letters="";
		String sTest_temp = sTest.toLowerCase();
		for (char c = 'a'; c <= 'z'; c++) {
			if(sTest_temp.indexOf(c)==-1) sMissing_letters += c;
		}
		return sMissing_letters;
	}
	
	
}
