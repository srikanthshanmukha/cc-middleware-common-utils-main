package com.uaic.cc.middleware.common.jdbcdelegate;

public interface TargetInvoker<T> {
	
	public T execute(String payload);

}
