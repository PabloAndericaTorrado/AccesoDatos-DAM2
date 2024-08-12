package Repository;

import java.util.List;

public interface Repository<T> {
	
	
	  List<T> findAll();
	  T findOneById(Long i);
	  void add(T t);
	  void updateById(Long id);
	  void updateById(Long id, T t);
	  void deleteById(Long id);
	  void delete(T t);
	  void update(T t);

}
