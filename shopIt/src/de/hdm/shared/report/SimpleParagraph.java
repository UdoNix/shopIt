package de.hdm.shared.report;

import java.io.Serializable; 

/**
 * 
 * @author Larisa
 *
 */
public class SimpleParagraph extends Paragraph implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private String text = ""; 
	
	public SimpleParagraph() {
		
	}
	
	public SimpleParagraph(String value) {
		this.text = value; 
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String toString() {
		return this.text; 
	}

}
