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
