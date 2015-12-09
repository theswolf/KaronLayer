package core.september.karonlayer.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import core.september.karonlayer.persistence.model.UserSecurity;



//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
@Repository
public interface UserSecurityRepo  extends PagingAndSortingRepository<UserSecurity, Long>{
	//List<Person> findByLastName(@Param("name") String name);
	//User findByUsername(String name);
}
