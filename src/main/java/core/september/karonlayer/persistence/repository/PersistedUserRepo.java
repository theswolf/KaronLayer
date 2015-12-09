package core.september.karonlayer.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import core.september.karonlayer.persistence.model.User;


//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
@Repository
public interface PersistedUserRepo  extends PagingAndSortingRepository<User, Long>{
	//List<Person> findByLastName(@Param("name") String name);
	User findByUsername(String name);
}
