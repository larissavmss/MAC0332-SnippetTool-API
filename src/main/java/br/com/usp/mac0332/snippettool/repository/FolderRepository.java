package br.com.usp.mac0332.snippettool.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.usp.mac0332.snippettool.model.Folder;

public interface FolderRepository extends JpaRepository<Folder, Integer> {

    Optional<Folder> findByNameAndUserId(String name, Integer userId);
    
    List<Folder> findByUserIdAndNameContaining(Integer id, String filtro);
    
    Optional<Folder> findByIdAndUserId(Integer id, Integer userId);
    
    void deleteByIdAndUserId(Integer id, Integer userId);
    
}
