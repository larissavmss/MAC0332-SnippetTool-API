package br.com.usp.mac0332.snippettool.service;

import br.com.usp.mac0332.snippettool.model.Folder;
import br.com.usp.mac0332.snippettool.model.User;
import br.com.usp.mac0332.snippettool.repository.FolderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService {

	@Autowired
	FolderRepository repository;

	@Transactional
	public Folder addFolder(String folderName, User user) {
		var folder = new Folder(
				folderName, user
		);
		return repository.save(folder);
	}

	public Folder findByName(String name) {
		return repository.findByName(name);
	}

	public List<Folder> getAll(Integer id) {
		return repository.findAllByUserId(id);
	}

	@Transactional
	public Folder updateFolder(Integer folderId, Folder updatedFolder) {
		Folder existingFolder = repository.findById(folderId)
				.orElseThrow(() -> new EntityNotFoundException("Folder not found with id: " + folderId));
		existingFolder.update(updatedFolder);
		return repository.save(existingFolder);
	}

	public void deleteFolder(Integer snippetId) {
		repository.deleteById(snippetId);
	}
}
