package br.com.usp.mac0332.snippettool.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.usp.mac0332.snippettool.model.Snippet;

public interface SnippetRepository extends JpaRepository<Snippet, Integer> {

	List<Snippet> findByFolder_User_IdAndNameContaining(Integer userId, String filtro);
	
	Optional<Snippet> findByIdAndFolder_User_Id(Integer id, Integer userId);
	
	void deleteByIdAndFolder_User_Id(Integer id, Integer userId);
	
	List<Snippet> findByFolder_IdAndFolder_User_IdAndNameContaining(Integer folderId, Integer userId, String filtro);

	List<Snippet> findByTags_IdAndFolder_User_IdAndNameContaining(Integer tagId, Integer userId, String filtro);

}
