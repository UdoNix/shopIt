package de.hdm.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * 
 * @author Larisa
 *
 */
public class CompositeParagraph extends Paragraph implements Serializable {
	private static final long serialVersionUID = 1L;
	/*
	 * Speicherort der Untersbschnitte 
	 */
	
	private Vector<SimpleParagraph> subParagraphs = new Vector<SimpleParagraph>(); 
	
	public void addSubParagraph(SimpleParagraph p) {
		this.subParagraphs.addElement(p); 
	}
	
	public void removeParagraph(SimpleParagraph p) {
		this.subParagraphs.removeElement(p); 
	}
	
	public Vector<SimpleParagraph> getSubParagraphs() {
	    return this.subParagraphs;
	}
	
	/*
	 * Auslesen der Anzahl der Unterabschnitte.
	 */
	public int getNumParagraphs() {
	    return this.subParagraphs.size();
	}
	
	/*
	 * Auslesen eines einzelnen Unterabschnitts und return
	 */
	public SimpleParagraph getParagraphAt(int i) {
	    return this.subParagraphs.elementAt(i);
	}
	
	public String toString() {
		
		StringBuffer result = new StringBuffer();
	
		/*
		 * Schleife �ber alle Unterabschnitte 
		 */
		for (int i = 0; i < this.subParagraphs.size(); i++) {
		      SimpleParagraph p = this.subParagraphs.elementAt(i);
		      
		      result.append(p.toString() + "\n");
	}
	
	return result.toString();
	
	}

}
