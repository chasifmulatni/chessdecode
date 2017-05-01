/**
 * 
 */
package chessdecode;

import java.util.Arrays;
import java.util.List;

/**
 * @author choudhry.m.nisar
 * 
 * This class decode the encoded chess position
 *
 */
public class DecodePosition {
	
	public static final String WRONG_NUMBER_OF_ROWS = "Wrong number of rows";
	public static final String WRONG_NUMBER_OF_SQUARES_ON_ROW  = " Wrong number of squares on row";
	public static final String UNEXPECTED_CHARACHETR = "Unexpected character";
	public static final String DELIMITER = "\\/";
	
	 
	List<Character> pieces = Arrays.asList(new Character[] { 'r', 'n', 'b', 'q', 'k', 'p', 'R', 'N', 'B', 'Q', 'K', 'P'});
	private String encodedRows;
	private String[] rows;
	
	public DecodePosition(String encodedRows) {
		this.encodedRows = encodedRows;
	}
	
	/**
	 * decode the position and return the result , if could not encoded it return erorrs
	 * @return The result.
	 */
	public String decodePosition() {	
		String result = null;
		if (null != this.encodedRows) {
			rows = this.encodedRows.split(DELIMITER);
			if (8 == rows.length) {
				result = parseRows(rows);
			} else {
				result = WRONG_NUMBER_OF_ROWS;
			}
		}
		return result;
	}
	
	/**
	 * parse the rows
	 * @param rows
	 * @return The decoded rows. 
	 */
	private String parseRows(String[] rows) {
		String result = null;
		StringBuffer  decodedRows = new StringBuffer();
		for (int i = 0; i < rows.length; i++) {
			result = parseRow(rows[i]);
			 if (result.equals(UNEXPECTED_CHARACHETR) || result.equals(WRONG_NUMBER_OF_SQUARES_ON_ROW) || result.equals(WRONG_NUMBER_OF_SQUARES_ON_ROW)) {
				 decodedRows = null;
				 break;
			 } else {
				 decodedRows.append(result).append("\n");
			 }
		}
		
		if (null != decodedRows && decodedRows.length() != 0) {
			result = decodedRows.toString();
		}
		return result;
	}
	
	/**
	 * parse the specific row
	 * @param encodedRow
	 * @return the decoded row
	 */
	private String parseRow(String encodedRow) {
		String row = null;
		StringBuffer  decodedRow = new StringBuffer();
		if (!encodedRow.equals("")) {
			for(int i = 0; i < encodedRow.length(); i++) {
				//check if it is unexpected char
			    if(!Character.isDigit((encodedRow.charAt(i))) && !pieces.contains(encodedRow.charAt(i))) { 
			    	row = UNEXPECTED_CHARACHETR;
			    	break;
			    } else {
			    	if(Character.isLetter(encodedRow.charAt(i))) {
			    		decodedRow.append(encodedRow.charAt(i));
				    } else if(Character.isDigit((encodedRow.charAt(i)))) {//if number
				    	decodedRow.append(printEmptySquare(Character.getNumericValue(encodedRow.charAt(i))));
				    }
			    }
			}
		}
		if (null == row){
			if (decodedRow.length() != 8) {
				row = WRONG_NUMBER_OF_SQUARES_ON_ROW;
			} else {
				row = decodedRow.toString();
			}
		}
		return row;
	}
	
	/**
	 * generate the empty squares for a row
	 * @param spaces
	 * @return
	 */
	private String printEmptySquare(int spaces) {
		StringBuffer  dots = new StringBuffer();
		for (int i = 0; i < spaces; i++) {
			dots.append(".");
		}
		return dots.toString();
	}
	
	/**
	 * 
	 * @return The encoded rows
	 */
	public String getEncodedPosition() {
		return encodedRows;
	}
	
	/**
	 * Sets the encoded rows
	 * @param encodedRows
	 */
	public void setEncodedPosition(String encodedRows) {
		this.encodedRows = encodedRows;
	}
}
