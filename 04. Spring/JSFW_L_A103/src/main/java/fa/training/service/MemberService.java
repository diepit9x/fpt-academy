package fa.training.service;

import fa.training.dto.LoginDTO;
import fa.training.dto.RegisterDTO;
import fa.training.dto.UpdateProFileDTO;
import fa.training.entity.Member;

public interface MemberService {
    Member login(LoginDTO loginDTO);

    boolean register(RegisterDTO registerDTO);

    Member update(Integer memberId, UpdateProFileDTO updateProFileDTO);
}
