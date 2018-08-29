package com.zhx.gmms.frame;

import java.util.List;

public interface CrudDao<T> {

	public T get(String id);
	
	public List<T> findList(T t);
	
	public int insert(T t);
	
	public int update(T t);
	
	public int delete(String id);
}
