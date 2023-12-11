package br.com.usp.mac0332.snippettool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usp.mac0332.snippettool.dto.folder.FolderCreateDto;
import br.com.usp.mac0332.snippettool.dto.folder.FolderResponseDto;
import br.com.usp.mac0332.snippettool.dto.folder.FolderUpdateDto;
import br.com.usp.mac0332.snippettool.model.Folder;
import br.com.usp.mac0332.snippettool.model.User;
import br.com.usp.mac0332.snippettool.repository.FolderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class FolderService {

	@Autowired
	FolderRepository repository;

	@Transactional
	public FolderResponseDto addFolder(FolderCreateDto folderCreateDto, User user) {
		Folder folder = new Folder(folderCreateDto, user);
		FolderResponseDto folderResponseDto = new FolderResponseDto(repository.save(folder));
		return folderResponseDto;
	}

	public FolderResponseDto findByName(String name, Integer userId) {
		Folder folder = repository.findByNameAndUserId(name, userId).orElseThrow(() -> new EntityNotFoundException("Folder not found with name: " + name));
		return new FolderResponseDto(folder);
	}

	public List<FolderResponseDto> getAll(Integer id) {
		List<Folder> folders = repository.findByUserId(id);
		List<FolderResponseDto> foldersResponseDto = folders.stream().map(FolderResponseDto::new).toList();
		return foldersResponseDto;
	}

	@Transactional
	public FolderResponseDto updateFolder(Integer folderId, FolderUpdateDto updatedFolder, Integer userId) {
		Folder existingFolder = repository.findByIdAndUserId(folderId, userId).orElseThrow(() -> new EntityNotFoundException("Folder not found with id: " + folderId));
		existingFolder.update(updatedFolder);
		return new FolderResponseDto(repository.save(existingFolder));
	}

	public void deleteFolder(Integer snippetId, Integer userId) {
		repository.deleteByIdAndUserId(snippetId, userId);
	}
	
	public Folder findDefaultFolderByUserId(Integer userId) {
		return repository.findByNameAndUserId("Default", userId).get();
	}
	
	public Folder findByIdAndUserId(Integer id, Integer userId) {
		return repository.findByIdAndUserId(id, userId).orElseThrow(() -> new EntityNotFoundException("Folder not fond with id: " + id));
	}
}
