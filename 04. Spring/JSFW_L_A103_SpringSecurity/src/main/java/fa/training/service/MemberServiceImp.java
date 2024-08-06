package fa.training.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fa.training.dto.LoginDTO;
import fa.training.dto.RegisterDTO;
import fa.training.dto.UpdateProFileDTO;
import fa.training.entity.Member;
import fa.training.exception.DataNotFoundException;
import fa.training.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	@Override
	public Member login(LoginDTO loginDTO) {

		Member existingMember = memberRepository.findByEmail(loginDTO.getEmail())
				.orElseThrow(() -> new DataNotFoundException("header:Wrong email/password 1"));

		if (!passwordEncoder.matches(loginDTO.getPassword(), existingMember.getPassword())) {
			throw new BadCredentialsException("header:Wrong email/password 2");
		}

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginDTO.getEmail(),
				loginDTO.getPassword(),
				existingMember.getAuthorities()
				);
		Authentication authentication = authenticationManager.authenticate(authenticationToken);

		if (authentication != null && authentication.getPrincipal() instanceof Member) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
			existingMember = (Member) authentication.getPrincipal();
	    }
		
		return existingMember;
	}

	@Override
	public boolean register(RegisterDTO registerDTO) {
		String username = registerDTO.getUsername();
		if (memberRepository.existsByUsername(username)) {
			throw new DataIntegrityViolationException("username:Username already exist");
		}
		String email = registerDTO.getEmail();
		if (memberRepository.existsByEmail(email)) {
			throw new DataIntegrityViolationException("email:Email already exist");
		}
		String password = passwordEncoder.encode(registerDTO.getPassword());
		if (!registerDTO.getPassword().equals(registerDTO.getRePassword())) {
			throw new DataIntegrityViolationException("rePassword:Repassword does not match");
		}
		Member newMember = Member.builder().username(username).email(email).password(password).build();
		memberRepository.save(newMember);
		return true;
	}

	@Override
	public Member update(Integer memberId, UpdateProFileDTO updateProFileDTO) {
		Member existingMember = memberRepository.findById(memberId)
				.orElseThrow(() -> new DataNotFoundException("Member not found"));
		existingMember.setFirstName(updateProFileDTO.getFirstName());
		existingMember.setLastName(updateProFileDTO.getLastName());
		existingMember.setPhone(updateProFileDTO.getPhone());
		existingMember.setDescription(updateProFileDTO.getDescription());
		return memberRepository.save(existingMember);
	}

}
