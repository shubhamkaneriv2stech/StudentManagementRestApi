package com.studentmanagement.studentmanagement.exception;


//User Defined Exception Class
public class DataNotFoundException extends RuntimeException {

	  private static final long serialVersionUID = -1871142390742648323L;

	  public DataNotFoundException(String studentId) {
	    super(studentId);
	  }

	  public DataNotFoundException(Throwable cause) {
	    super(cause);
	  }

	  public DataNotFoundException(String message, Throwable cause) {
	    super(message, cause);
	  }
	}