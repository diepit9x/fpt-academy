package services;

import java.time.LocalDateTime;

import daos.MemberDAO;
import daos.MemberDAOImpl;
import dtos.Register;
import dtos.UpdateMember;
import entities.Member;

public class MemberServiceImpl implements MemberService {
	private  MemberDAO memberDAO;
	
	public MemberServiceImpl() {
		super();
		this.memberDAO = new MemberDAOImpl();
	}

	@Override
	public Member login(String email, String password) throws Exception {
		return memberDAO.login(email, password);
	}

	@Override
	public Boolean register(Register register) throws Exception {
		Member existingMember = null;
		existingMember = memberDAO.findByUsername(register.getUsername());
		if (existingMember != null) {
			throw new Exception("Username already exist");
		}
		existingMember = memberDAO.findByEmail(register.getEmail());
		if (existingMember != null) {
			throw new Exception("Email already exist");
		}
		if (!register.getPassword().equals(register.getRePassword())) {
			throw new Exception("Password doesn't match");
		}
		LocalDateTime timeNow = LocalDateTime.now();
		Member newMember = Member.builder()
				.username(register.getUsername())
				.email(register.getEmail())
				.password(register.getPassword())
				.description("")
				.firstName("")
				.lastName("")
				.phone("")
				.createDate(timeNow)
				.updateTime(timeNow)
				.build();
		memberDAO.register(newMember);
		
		return true;
	}

	@Override
	public Boolean update(UpdateMember updateMember) throws Exception {
		Member existingMember = null;
		existingMember = memberDAO.findById(updateMember.getId());
		if (existingMember == null) {
			throw new Exception("Member doesn't exist");
		}
		existingMember.setFirstName(updateMember.getFirstName());
		existingMember.setLastName(updateMember.getLastName());
		existingMember.setPhone(updateMember.getPhone());
		existingMember.setDescription(updateMember.getDescription());
		existingMember.setUpdateTime(LocalDateTime.now());
		
		memberDAO.update(existingMember);
		return true;
	}

}
