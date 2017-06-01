package demojpa.dao;

import java.util.List;

public interface DaoGenerique<T> {
	
	public void create(T t);
	
	public List<T> readByName(String name);
	
	public T readById(int i);
	
	public void update(T t);
	
	public void delete(int i);
	
	public List<T> getAll();
	
	public int getId(T t);

}