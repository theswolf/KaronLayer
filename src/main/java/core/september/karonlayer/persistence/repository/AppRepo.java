package core.september.karonlayer.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import core.september.karonlayer.persistence.model.App;
import core.september.karonlayer.persistence.model.User;


//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
@Repository
public interface AppRepo  extends PagingAndSortingRepository<App, Long>{
	App findByName(String name);
}
