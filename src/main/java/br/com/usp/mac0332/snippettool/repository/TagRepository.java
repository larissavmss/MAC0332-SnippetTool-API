package br.com.usp.mac0332.snippettool.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.usp.mac0332.snippettool.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {
	
	List<Tag> findByUser_IdAndNameContaining(Integer userId, String filtro);

	Optional<Tag> findByIdAndUser_Id(Integer tagId, Integer userId);
	
	void deleteByIdAndUser_Id(Integer tagId, Integer userId);
	
}
