package fa.training.service;

import org.springframework.security.core.Authentication;

import fa.training.dto.UserDTO;
import fa.training.entity.User;

public interface UserService {
	User register(UserDTO userDTO);
	Authentication login(UserDTO userDTO);
}
