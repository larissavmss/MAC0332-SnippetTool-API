package br.com.usp.mac0332.snippettool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.usp.mac0332.snippettool.model.Folder;

public interface FolderRepository extends JpaRepository<Folder, Integer> {

    Folder findByName(String name);

    List<Folder> findAllByUserId(Integer id);
    
}
