package fa.training.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fa.training.dto.ContentDTO;
import fa.training.entity.Content;
import fa.training.entity.Member;
import fa.training.exception.AuthenticatedException;
import fa.training.exception.DataNotFoundException;
import fa.training.repository.ContentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContentServiceImp implements ContentService {
    private final ContentRepository contentRepository;

    @Override
    public Content create(ContentDTO contentDTO) {
	if (contentDTO.getMember() == null) {
	    throw new AuthenticatedException();
	}
	Content newContent = Content.builder().title(contentDTO.getTitle()).brief(contentDTO.getBrief())
		.content(contentDTO.getContent()).member(contentDTO.getMember()).build();
	return contentRepository.save(newContent);
    }

    @Override
    public Content update(ContentDTO contentDTO) {
	if (contentDTO.getMember() == null) {
	    throw new AuthenticatedException();
	}
	Content existingContent = findById(contentDTO.getMember(), contentDTO.getContentId());
	Integer authorId = existingContent.getMember().getId();
	Integer memberId = contentDTO.getMember().getId();
	if (!authorId.equals(memberId)) {
	    throw new DataNotFoundException("Access Denied");
	}
	existingContent.setTitle(contentDTO.getTitle());
	existingContent.setBrief(contentDTO.getBrief());
	existingContent.setContent(contentDTO.getContent());

	return contentRepository.save(existingContent);
    }

    @Override
    public Content findById(Member member, Integer contentId) {
	if (member == null) {
	    throw new AuthenticatedException();
	}
	Content content = contentRepository.findByMemberAndId(member, contentId)
		.orElseThrow(() -> new DataNotFoundException("Content not found"));
	return content;
    }

    @Override
    public void delete(Member member, Integer contentId) {
	if (member == null) {
	    throw new AuthenticatedException();
	}
	Content existingContent = findById(member, contentId);
	contentRepository.delete(existingContent);
    }

    @Override
    public List<Content> findAll(Member member) {
	if (member == null) {
	    throw new AuthenticatedException();
	}
	return contentRepository.findAllByMember(member);
    }

    @Override
    public Page<Content> findAllByMemberAndKeyword(String keyword, Member member, PageRequest pageRequest) {
	if (member == null) {
	    throw new AuthenticatedException();
	}
	return contentRepository.findAllByMemberAndKeyword(keyword, member, pageRequest);
    }

}
