/**
 * 
 */
package com.jlvlg.pentagon.exceptions;

/**
 * Generic not found exception
 * @author Lucas
 *
 */
public abstract class ObjectNotFoundException extends Exception {
	private static final long serialVersionUID = 828773794349982185L;
	
	public ObjectNotFoundException(String className) {
		super(String.format("%s not found", className));
	}
}
