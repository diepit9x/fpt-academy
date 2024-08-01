package fa.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.model.Fresher;
import fa.training.repository.FresherRepository;

@Service
public class LoginService {
	@Autowired
	private FresherRepository fresherRepository;
	
	public boolean login(Fresher fresher) {
		return fresherRepository.authenticate(fresher);
	}
}
