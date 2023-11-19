package br.com.usp.mac0332.snippettool.controller;

import br.com.usp.mac0332.snippettool.model.Folder;
import br.com.usp.mac0332.snippettool.model.FolderName;
import br.com.usp.mac0332.snippettool.service.FolderService;
import br.com.usp.mac0332.snippettool.service.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("folder")
public class FolderController {

	@Autowired
	FolderService folderService;

	@PostMapping
	public ResponseEntity<Folder> addFolder(@RequestBody FolderName name, @AuthenticationPrincipal UserDetails userDetails) {
		Folder response = folderService.addFolder(name.name, ((MyUserDetails) userDetails).user);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<Folder>> getAll(@AuthenticationPrincipal UserDetails userDetails) {
		List<Folder> response = folderService.getAll(((MyUserDetails) userDetails).user.id);
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
	public ResponseEntity<Void> deleteFolder(@PathVariable Integer folderId, @AuthenticationPrincipal UserDetails userDetails) {
		folderService.deleteFolder(folderId);
		return ResponseEntity.noContent().build();
	}

}
