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
	public ObjectNotFoundException(String className) {
		super(String.format("%s not found", className));
	}
}
