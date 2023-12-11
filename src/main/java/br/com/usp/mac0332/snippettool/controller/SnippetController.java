package br.com.usp.mac0332.snippettool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.usp.mac0332.snippettool.dto.snippet.SnippetCreateDto;
import br.com.usp.mac0332.snippettool.dto.snippet.SnippetResponseDto;
import br.com.usp.mac0332.snippettool.dto.snippet.SnippetUpdateDto;
import br.com.usp.mac0332.snippettool.service.MyUserDetails;
import br.com.usp.mac0332.snippettool.service.SnippetService;

@RestController
@RequestMapping("snippet")
public class SnippetController {

	@Autowired
	private SnippetService service;

	@PostMapping
	public ResponseEntity<SnippetResponseDto> createSnippet(SnippetCreateDto snippetCreateDto, @AuthenticationPrincipal UserDetails userDetails) {
		SnippetResponseDto response = service.createSnippet(snippetCreateDto, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<SnippetResponseDto>> readSnippets(@AuthenticationPrincipal UserDetails userDetails) {
		List<SnippetResponseDto> response = service.readSnippets(((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{snippetId}")
	public ResponseEntity<SnippetResponseDto> readSnippet(@PathVariable Integer snippetId, @AuthenticationPrincipal UserDetails userDetails) {
		SnippetResponseDto response = service.readSnippet(snippetId, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{snippetId}")
	public ResponseEntity<SnippetResponseDto> updateSnippet(@PathVariable Integer snippetId, @RequestBody SnippetUpdateDto updatedSnippet, @AuthenticationPrincipal UserDetails userDetails) {
		SnippetResponseDto response = service.updateSnippet(snippetId, updatedSnippet,
				((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{snippetId}")
	public ResponseEntity<Void> deleteSnippet(@PathVariable Integer snippetId, @AuthenticationPrincipal UserDetails userDetails) {
		service.deleteSnippet(snippetId, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{snippetId}/addTag/{tagId}")
	public ResponseEntity<SnippetResponseDto> addTagToSnippet(@PathVariable Integer snippetId, @PathVariable Integer tagId, @AuthenticationPrincipal UserDetails userDetails) {
		SnippetResponseDto response = service.addTagToSnippet(snippetId, tagId, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{snippetId}/removeTag/{tagId}")
	public ResponseEntity<SnippetResponseDto> removeTagFromSnippet(@PathVariable Integer snippetId, @PathVariable Integer tagId, @AuthenticationPrincipal UserDetails userDetails) {
		SnippetResponseDto response = service.removeTagFromSnippet(snippetId, tagId, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

}
