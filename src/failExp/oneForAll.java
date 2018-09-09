package failExp;

import java.util.List;

public interface oneForAll {
	public <T> T findById(T type,int id);
	public <T> List<T> findByField(T type, String field, String value);
	public <T>  void deleteById(T type,int id);
	public <T>  void deleteByField(T type, String field, String value);
	public <T>  T findByObj(T type, T obj);
	public <T>  void updateByField(T type, String field, String value,String fieldv, String valuev,T obj);

}
