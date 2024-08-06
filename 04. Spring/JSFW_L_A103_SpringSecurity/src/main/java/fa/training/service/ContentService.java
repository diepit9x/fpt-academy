package fa.training.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import fa.training.dto.ContentDTO;
import fa.training.entity.Content;
import fa.training.entity.Member;

public interface ContentService {
    Content create(ContentDTO contentDTO);

    Content update(ContentDTO contentDTO);

    Content findById(Member member, Integer contentId);

    List<Content> findAll(Member member);
    
    Page<Content> findAllByMemberAndKeyword(String keyword, Member member, PageRequest pageRequest);

    void delete(Member member, Integer contentId);
}
