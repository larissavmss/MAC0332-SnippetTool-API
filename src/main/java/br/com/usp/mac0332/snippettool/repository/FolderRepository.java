package br.com.usp.mac0332.snippettool.repository;

import br.com.usp.mac0332.snippettool.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Integer> {

    Folder findByName(String name);

    Folder save(Folder folder);

}
