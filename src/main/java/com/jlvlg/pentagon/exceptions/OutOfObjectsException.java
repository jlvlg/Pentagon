package com.jlvlg.pentagon.exceptions;

public abstract class OutOfObjectsException extends Exception {
    public OutOfObjectsException(String className) {
        super(String.format("No more %s left", className));
    }
}
