/**
 * 
 */
package com.jlvlg.pentagon.exceptions;

/**
 * Generic name taken exception
 * @author Lucas
 *
 */
public abstract class NameTakenException extends Exception {
	private static final long serialVersionUID = 3737909435137681292L;

	public NameTakenException(String className) {
		super(String.format("Two %ss cannot have the same name", className));
	}
}
