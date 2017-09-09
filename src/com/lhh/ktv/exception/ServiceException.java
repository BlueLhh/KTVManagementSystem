package com.lhh.ktv.exception;

//自定义的用于业务逻辑层（Service层）抛出的异常
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3587522378869787740L;

	public ServiceException() {
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}

