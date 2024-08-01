package fa.training.dao;

import java.util.List;

import fa.training.entities.Candidate;

public interface CandidateDAO {
    boolean addCandidate(Candidate candidate) throws Exception;
    boolean updateCandidate(Candidate candidate) throws Exception;
    List<Candidate> getAllCandidate() throws Exception;
    Candidate getCandidateByCandidateId(int candidateId) throws Exception;
}