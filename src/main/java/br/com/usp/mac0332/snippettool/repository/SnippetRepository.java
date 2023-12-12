package br.com.usp.mac0332.snippettool.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.usp.mac0332.snippettool.model.Snippet;

public interface SnippetRepository extends JpaRepository<Snippet, Integer> {

	List<Snippet> findByUserId(Integer userId);
	
	Optional<Snippet> findByIdAndUserId(Integer id, Integer userId);
	
	void deleteByIdAndUserId(Integer id, Integer userId);
	
	List<Snippet> findByTag_IdOrNameAndFolder_Id(Integer tagId, String name, Integer userId);
	
}
