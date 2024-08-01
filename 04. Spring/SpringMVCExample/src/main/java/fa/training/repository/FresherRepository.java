package fa.training.repository;

import org.springframework.stereotype.Repository;

import fa.training.model.Fresher;

@Repository
public class FresherRepository {
	
	public boolean authenticate(Fresher fresher) {
		return fresher.getUsername().equals("admin") && fresher.getPassword().equals("123");
	}
	
	public boolean register(Fresher fresher) {
		return fresher.getUsername().equals("admin") && fresher.getPassword().equals("123");
	}

}
