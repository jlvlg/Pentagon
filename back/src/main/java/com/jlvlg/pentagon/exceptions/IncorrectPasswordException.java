package com.jlvlg.pentagon.exceptions;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException() {
        super("Incorrect password");
    }
}
