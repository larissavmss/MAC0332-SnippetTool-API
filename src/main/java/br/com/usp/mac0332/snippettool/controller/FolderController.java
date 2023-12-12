package br.com.usp.mac0332.snippettool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.usp.mac0332.snippettool.dto.folder.FolderCreateDto;
import br.com.usp.mac0332.snippettool.dto.folder.FolderResponseDto;
import br.com.usp.mac0332.snippettool.dto.folder.FolderUpdateDto;
import br.com.usp.mac0332.snippettool.dto.snippet.SnippetResponseDto;
import br.com.usp.mac0332.snippettool.service.FolderService;
import br.com.usp.mac0332.snippettool.service.MyUserDetails;
import br.com.usp.mac0332.snippettool.service.SnippetService;

@RestController
@RequestMapping("folder")
public class FolderController {

	@Autowired
	FolderService folderService;
	
	@Autowired
	SnippetService snippetService;

	@PostMapping
	public ResponseEntity<FolderResponseDto> addFolder(@RequestBody FolderCreateDto folderCreateDto, @AuthenticationPrincipal UserDetails userDetails) {
		FolderResponseDto response = folderService.addFolder(folderCreateDto, ((MyUserDetails) userDetails).user);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<FolderResponseDto >> getAll(@AuthenticationPrincipal UserDetails userDetails) {
		List<FolderResponseDto> response = folderService.getAll(((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{name}")
	public ResponseEntity<FolderResponseDto> findByName(@PathVariable String name, @AuthenticationPrincipal UserDetails userDetails) {
		FolderResponseDto response = folderService.findByName(name, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{folderId}")
	public ResponseEntity<FolderResponseDto> updateFolder(@PathVariable Integer folderId, @RequestBody FolderUpdateDto updatedFolder, @AuthenticationPrincipal UserDetails userDetails) {
		FolderResponseDto response = folderService.updateFolder(folderId, updatedFolder, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{folderId}")
	public ResponseEntity<Void> deleteFolder(@PathVariable Integer folderId, @AuthenticationPrincipal UserDetails userDetails) {
		folderService.deleteFolder(folderId, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{folderId}/snippets")
	public ResponseEntity<List<SnippetResponseDto>> getSnippetsByFiltro(@PathVariable Integer folderId, @RequestParam Integer tagId, @RequestParam String name, @AuthenticationPrincipal UserDetails userDetails){
		List<SnippetResponseDto> response = snippetService.findByFiltro(tagId, name, folderId, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

}
