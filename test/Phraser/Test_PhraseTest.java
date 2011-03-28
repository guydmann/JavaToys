package Phraser;

import junit.framework.TestCase;


public class Test_PhraseTest extends TestCase {

	public Test_PhraseTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//"I Palindrome, I"
	//"'son, i am able,' she said 'though you scare me.' 'watch,' said I 'Beloved,' I said 'watch me scare you though.' said she, 'able am i, Son'"
	//"hello world"
	//"test"
	//"The quick brown fox jumps over god"
	//"i am i"
	public final void testCheckPalingram_01() {
		assertTrue(PhraseTest.checkPalingram("I Palindrome, I"));
	}

	public final void testCheckPalingram_02() {
		assertTrue(PhraseTest.checkPalingram("'son, i am able,' she said 'though you scare me.' 'watch,' said I 'Beloved,' I said 'watch me scare you though.' said she, 'able am i, Son'"));
	}
	
	public final void testCheckPalingram_03() {
		assertFalse(PhraseTest.checkPalingram("hello world"));
	}
	
	public final void testCheckPalingram_04() {
		assertTrue(PhraseTest.checkPalingram("test"));
	}
	
	public final void testCheckPalingram_05() {
		assertFalse(PhraseTest.checkPalingram("The quick brown fox jumps over god"));
	}
	
	public final void testCheckPalingram_06() {
		assertTrue(PhraseTest.checkPalingram("i am i"));
	}
	
	
	//"dammit i'm mad","hello world", "test", "able was i ere i saw elba", "A man, A plan, A canal, panama"
	public final void testCheckPalindrome_01() {
		assertTrue(PhraseTest.checkPalindrome("dammit i'm mad"));
	}

	public final void testCheckPalindrome_02() {
		assertFalse(PhraseTest.checkPalindrome("test"));
	}
	public final void testCheckPalindrome_03() {
		assertFalse(PhraseTest.checkPalindrome("hello world"));
	}
	public final void testCheckPalindrome_04() {
		assertTrue(PhraseTest.checkPalindrome("able was i ere i saw elba"));
	}
	public final void testCheckPalindrome_05() {
		assertTrue(PhraseTest.checkPalindrome("A man, A plan, A canal, panama"));
	}
	
	
	
	//"The quick brown fox jumps over the laZy dog", "hello world", "test", "The quick brown fox jumps over god"
	public final void testCheckPangram_01() {
		assertTrue(PhraseTest.checkPangram("The quick brown fox jumps over the laZy dog"));
	}
	public final void testCheckPangram_02() {
		assertFalse(PhraseTest.checkPangram("test"));
	}
	public final void testCheckPangram_03() {
		assertFalse(PhraseTest.checkPangram("hello world"));
	}
	public final void testCheckPangram_04() {
		assertFalse(PhraseTest.checkPangram("The quick brown fox jumps over god"));
	}
	
	//"The quick brown fox jumps over the laZy dog", "hello world", "test", "The quick brown fox jumps over god"
	public final void testGetMissingLetters_01() {
		assertEquals(PhraseTest.getMissingLetters("The quick brown fox jumps over the laZy dog"),"");
	}
	public final void testGetMissingLetters_02() {
		assertEquals(PhraseTest.getMissingLetters("test"),"abcdfghijklmnopqruvwxyz");
	}
	public final void testGetMissingLetters_03() {
		assertEquals(PhraseTest.getMissingLetters("hello world"),"abcfgijkmnpqstuvxyz");
	}
	public final void testGetMissingLetters_04() {
		assertEquals(PhraseTest.getMissingLetters("The quick brown fox jumps over god"),"alyz");
	}
	
}
