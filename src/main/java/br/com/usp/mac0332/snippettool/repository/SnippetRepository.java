package br.com.usp.mac0332.snippettool.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.usp.mac0332.snippettool.model.Snippet;

public interface SnippetRepository extends JpaRepository<Snippet, Integer> {

	List<Snippet> findByFolder_User_Id(Integer userId);
	
	Optional<Snippet> findByIdAndFolder_User_Id(Integer id, Integer userId);
	
	void deleteByIdAndFolder_User_Id(Integer id, Integer userId);
	
	List<Snippet> findByFolder_IdAndFolder_User_Id(Integer folderId, Integer userId);
	
}
