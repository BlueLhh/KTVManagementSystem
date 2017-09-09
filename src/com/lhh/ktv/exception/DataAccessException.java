package com.lhh.ktv.exception;

//自定义的用于数据访问层（DAO层）抛出的异常
public class DataAccessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854517474188619680L;

	public DataAccessException() {
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
