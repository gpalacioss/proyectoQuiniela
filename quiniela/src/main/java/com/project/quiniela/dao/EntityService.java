package com.project.quiniela.dao;

import java.util.List;

public interface EntityService{
	
	<T> T merge(T object);
	
	
	<T> List<T> findWithQuery(String var1, Object... var2);
	
}
