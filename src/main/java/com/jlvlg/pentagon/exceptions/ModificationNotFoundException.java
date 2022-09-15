/**
 * 
 */
package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Modification;

/**
 * Thrown when trying to access a non-existing modification
 * @author Lucas
 *
 */
public class ModificationNotFoundException extends ObjectNotFoundException {
	private static final long serialVersionUID = 3249536314260932162L;
	private Modification modification;
	
	public ModificationNotFoundException() {
		super("Modification");
	}

	public ModificationNotFoundException(Modification modification) {
		this();
		this.modification = modification;
	}

	public Modification getModification() {
		return modification;
	}
}
