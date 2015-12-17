package core.september.karonlayer.service.data;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import core.september.karonlayer.config.security.UserService;

public abstract class DataService<R extends PagingAndSortingRepository<T, ID>,T,ID extends Serializable> {
	
	protected abstract R getRepo();
	
	@Autowired
	@Qualifier("UserService")
	protected  UserService userService;

	<S extends T> S save(S entity) {
		return getRepo().save(entity);
	}

	//<S extends T> Iterable<S> save(Iterable<S> entities);

	T findOne(ID id) {
		return getRepo().findOne(id);
	}

	//boolean exists(ID id);

	//Iterable<T> findAll();

	//Iterable<T> findAll(Iterable<ID> ids);

	//long count();

	void delete(ID id) {
		getRepo().delete(id);
	}

	void delete(T entity) {
		getRepo().delete(entity);
	}

	//void delete(Iterable<? extends T> entities);

	void deleteAll() {
		getRepo().deleteAll();
	}
}
