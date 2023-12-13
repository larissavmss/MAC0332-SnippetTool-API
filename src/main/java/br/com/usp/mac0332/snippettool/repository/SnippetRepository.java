package br.com.usp.mac0332.snippettool.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.usp.mac0332.snippettool.model.Snippet;

public interface SnippetRepository extends JpaRepository<Snippet, Integer> {

	List<Snippet> findByFolder_User_Id(Integer userId);
	
	Optional<Snippet> findByIdAndFolder_User_Id(Integer id, Integer userId);
	
	void deleteByIdAndFolder_User_Id(Integer id, Integer userId);
	
	@Query(value = "SELECT * FROM tag_snippet INNER JOIN tag INNER JOIN snippet WHERE tag_snippet.tag_id == ?1 OR tag.name == ?2 AND snippet.folder_id == ?3 ", nativeQuery = true)
	List<Snippet> findByTag_IdOrNameAndFolder_Id(Integer tagId, String name, Integer folderId);
	
}
