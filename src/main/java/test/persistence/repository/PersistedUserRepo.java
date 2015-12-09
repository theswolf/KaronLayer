package test.persistence.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import test.persistence.model.User;

//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
@Repository
public interface PersistedUserRepo  extends PagingAndSortingRepository<User, Long>{
	//List<Person> findByLastName(@Param("name") String name);
	User findByUsername(String name);
}
