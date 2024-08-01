package fa.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.model.Fresher;
import fa.training.repository.FresherRepository;

@Service
public class RegisterService {
	@Autowired
	private FresherRepository fresherRepository;
	
	public boolean register(Fresher fresher) {
		return fresherRepository.register(fresher);
	}
}
