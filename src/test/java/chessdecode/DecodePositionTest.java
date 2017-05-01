package chessdecode;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DecodePositionTest {

	private  String encodedPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
	private  DecodePosition decodePosition;
	
	@Before
	public  void setUp() throws Exception {
		decodePosition = new DecodePosition(encodedPosition);
	}

	@Test
	public void testCorrectEncodedRows1() {
		
		StringBuffer expected = new StringBuffer();
		expected
		.append("rnbqkbnr").append("\n")
		.append("pppppppp").append("\n")
		.append("........").append("\n")
		.append("........").append("\n")
		.append("........").append("\n")
		.append("........").append("\n")
		.append("PPPPPPPP").append("\n")
		.append("RNBQKBNR").append("\n");
		//SUT
		String actual = decodePosition.decodePosition();
		
		//assert
		assertEquals(expected.toString(), actual);
	}
	
	@Test
	public void testCorrectEncodedRows2() {
		
		//init
		String encodedPosition = "r1bk3r/p2pBpNp/n4n2/1p1NP2P/6P1/3P4/P1P1K3/q5b1";
		decodePosition.setEncodedPosition(encodedPosition);
		StringBuffer expected = new StringBuffer();
		expected
		.append("r.bk...r").append("\n")
		.append("p..pBpNp").append("\n")
		.append("n....n..").append("\n")
		.append(".p.NP..P").append("\n")
		.append("......P.").append("\n")
		.append("...P....").append("\n")
		.append("P.P.K...").append("\n")
		.append("q.....b.").append("\n");
		
		//SUT
		String actual = decodePosition.decodePosition();
		
		//assert
		assertEquals(expected.toString(), actual);
	}
	
	@Test
	public void testWrongNoOfSquearesOnRow1() {
		
		//init
		String encodedPosition = "/pppppppp/8/8/8/8/8/RNBQKBN";
		decodePosition.setEncodedPosition(encodedPosition);
		
		//SUT
		String result = decodePosition.decodePosition();
		
		//assert
		assertEquals(DecodePosition.WRONG_NUMBER_OF_SQUARES_ON_ROW, result);
	}
	
	@Test
	public void testWrongNoOfSquearesOnRow2() {
		
		//init
		String encodedPosition = "n7/pppp/10/8/8/8/11/RNBQKBN";
		decodePosition.setEncodedPosition(encodedPosition);
		
		//SUT
		String result = decodePosition.decodePosition();
		
		//assert
		assertEquals(DecodePosition.WRONG_NUMBER_OF_SQUARES_ON_ROW, result);
	}
	
	@Test
	public void testUnexpecetdCharaterEncodedRow2() {
		
		//init
		String encodedPosition = "3w4/7p/7p/7p/8/8/8/8";
		decodePosition.setEncodedPosition(encodedPosition);
		
		//SUT
		String result = decodePosition.decodePosition();
		
		//assert
		assertEquals(DecodePosition.UNEXPECTED_CHARACHETR, result);
	}
	
	@Test
	public void testNullEncodedRows() {
		
		//init
		String encodedPosition = null;
		decodePosition.setEncodedPosition(encodedPosition);
		
		//SUT
		String result = decodePosition.decodePosition();
		
		//assert
		assertNull(result);
	}
	
	@After
	public  void tearDownClass() {
		decodePosition = null;
		assertNull(decodePosition);
	}
}
