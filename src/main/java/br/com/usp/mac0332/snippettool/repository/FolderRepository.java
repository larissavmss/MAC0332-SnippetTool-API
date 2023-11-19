package br.com.usp.mac0332.snippettool.repository;

import br.com.usp.mac0332.snippettool.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Integer> {

    Folder findByName(String name);

    Folder save(Folder folder);

    List<Folder> findAllByUserId(Integer id);
}
