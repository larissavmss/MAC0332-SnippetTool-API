package br.com.usp.mac0332.snippettool.service;

import br.com.usp.mac0332.snippettool.model.Folder;
import br.com.usp.mac0332.snippettool.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderService {

    @Autowired
    FolderRepository repo;

    public void addFolder(Folder folder) {
        repo.save(folder);
    }

    public Folder findByName(String name) {
        return repo.findByName(name);
    }
}
