package core.september.karonlayer.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import core.september.karonlayer.config.security.UserService;
import core.september.karonlayer.persistence.model.App;
import core.september.karonlayer.persistence.repository.AppRepo;

@Service
public class AppDataService extends DataService<AppRepo,App, Long>{
	
	
	
	@Autowired
	private AppRepo appRepo;


	@Override
	protected AppRepo getRepo() {
		return appRepo;
	}

}
