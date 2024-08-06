package fa.training.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fa.training.dto.UserDTO;
import fa.training.entity.Role;
import fa.training.entity.User;
import fa.training.exception.DataNotFoundException;
import fa.training.repository.RoleRepository;
import fa.training.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	
	@Override
	public User register(UserDTO userDTO) {
		String username = userDTO.getUsername();
		if (userRepository.existsByUsername(username)) {
			throw new DataIntegrityViolationException("Username already exist");
		}
		 Role role = roleRepository.findById(1).orElse(null);
		
		 User newUser = new User();
		 newUser.setUsername(username);
		 newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		 newUser.setRole(role);

		return userRepository.save(newUser);
	}

	@Override
	public Authentication login(UserDTO userDTO) {
		
		User existingUser = userRepository.findByUsername(userDTO.getUsername())
				.orElseThrow(() -> new DataNotFoundException("Username/Password wrong 1"));
		
		if(!passwordEncoder.matches(userDTO.getPassword(), existingUser.getPassword())) {
            throw new BadCredentialsException("Username/Password wrong 2");
        }
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				userDTO.getUsername(),
				userDTO.getPassword(),
				existingUser.getAuthorities()
        );
		Authentication authentication =  authenticationManager.authenticate(authenticationToken);
		
		return authentication;
	}

}
