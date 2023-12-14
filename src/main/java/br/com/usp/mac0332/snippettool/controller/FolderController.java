package br.com.usp.mac0332.snippettool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.usp.mac0332.snippettool.dto.folder.FolderCreateDto;
import br.com.usp.mac0332.snippettool.dto.folder.FolderResponseDto;
import br.com.usp.mac0332.snippettool.dto.folder.FolderUpdateDto;
import br.com.usp.mac0332.snippettool.service.FolderService;
import br.com.usp.mac0332.snippettool.service.MyUserDetails;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("folder")
public class FolderController {

	@Autowired
	FolderService folderService;
	
	@PostMapping
	public ResponseEntity<FolderResponseDto> addFolder(@Valid @RequestBody FolderCreateDto folderCreateDto, @AuthenticationPrincipal UserDetails userDetails) {
		FolderResponseDto response = folderService.addFolder(folderCreateDto, ((MyUserDetails) userDetails).user);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<FolderResponseDto >> getAll(@AuthenticationPrincipal UserDetails userDetails) {
		List<FolderResponseDto> response = folderService.getAll(((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/filtro/{name}")
	public ResponseEntity<List<FolderResponseDto>> findByName(@PathVariable String name, @AuthenticationPrincipal UserDetails userDetails) {
		List<FolderResponseDto> response = folderService.findByName(name, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{folderId}")
	public ResponseEntity<FolderResponseDto> findById(@PathVariable Integer folderId, @AuthenticationPrincipal UserDetails userDetails) {
		FolderResponseDto response = folderService.findByIdAndUserIdToDto(folderId, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{folderId}")
	public ResponseEntity<FolderResponseDto> updateFolder(@PathVariable Integer folderId, @Valid @RequestBody FolderUpdateDto updatedFolder, @AuthenticationPrincipal UserDetails userDetails) {
		FolderResponseDto response = folderService.updateFolder(folderId, updatedFolder, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{folderId}")
	public ResponseEntity<Void> deleteFolder(@PathVariable Integer folderId, @AuthenticationPrincipal UserDetails userDetails) {
		folderService.deleteFolder(folderId, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.noContent().build();
	}

}
