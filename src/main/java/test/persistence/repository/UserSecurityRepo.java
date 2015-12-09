package test.persistence.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import test.persistence.model.User;
import test.persistence.model.UserSecurity;

//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
@Repository
public interface UserSecurityRepo  extends PagingAndSortingRepository<UserSecurity, Long>{
	//List<Person> findByLastName(@Param("name") String name);
	//User findByUsername(String name);
}
