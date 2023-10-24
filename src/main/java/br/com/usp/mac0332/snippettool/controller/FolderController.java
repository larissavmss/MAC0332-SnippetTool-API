package br.com.usp.mac0332.snippettool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.usp.mac0332.snippettool.model.Folder;
import br.com.usp.mac0332.snippettool.service.FolderService;

@RestController
@RequestMapping("folder")
public class FolderController {

	@Autowired
	FolderService folderService;

	@PostMapping
	public ResponseEntity<Folder> addFolder(@RequestBody Folder folder) {
		Folder response = folderService.addFolder(folder);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<Folder>> getAll() {
		List<Folder> response = folderService.getAll();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{folderId}")
	public ResponseEntity<Folder> findByName(@PathVariable("name") String name) {
		Folder response = folderService.findByName(name);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{folderId}")
	public ResponseEntity<Folder> updateFolder(@PathVariable Integer folderId, @RequestBody Folder updatedFolder) {
		Folder response = folderService.updateFolder(folderId, updatedFolder);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{folderId}")
	public ResponseEntity<Void> deleteFolder(@PathVariable Integer folderId) {
		folderService.deleteFolder(folderId);
		return ResponseEntity.noContent().build();
	}
}
