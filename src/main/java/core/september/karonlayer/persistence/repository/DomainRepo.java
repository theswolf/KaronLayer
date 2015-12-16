package core.september.karonlayer.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import core.september.karonlayer.persistence.model.Domain;
import core.september.karonlayer.persistence.model.User;


//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
@Repository
public interface DomainRepo  extends PagingAndSortingRepository<Domain, Long>{
	//List<Person> findByLastName(@Param("name") String name);
	List<Domain> findByUser(User user);
	Domain findByAlias(String alias);
	
	@Query("select d from Domain d inner join d.user user where user.username = ?1")
	List<Domain> findByUsername(String username);
}
