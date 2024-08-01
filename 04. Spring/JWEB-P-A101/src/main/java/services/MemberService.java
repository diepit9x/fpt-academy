package services;


import dtos.Register;
import dtos.UpdateMember;
import entities.Member;

public interface MemberService {
	Member login(String email, String password) throws Exception;
	Boolean register(Register register) throws Exception;
	Boolean update(UpdateMember updateMember) throws Exception;
}
