package fa.training.service;

import org.springframework.dao.DataIntegrityViolationException;
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

    @Override
    public Member login(LoginDTO loginDTO) {
	Member member = memberRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword())
		.orElseThrow(() -> new DataNotFoundException("header:Email or Password doesn.t match"));
	return member;
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
	if (!registerDTO.getPassword().equals(registerDTO.getRePassword())) {
	    throw new DataIntegrityViolationException("rePassword:Repassword does not match");
	}
	Member newMember = Member.builder().username(username).email(email).password(registerDTO.getPassword()).build();
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
