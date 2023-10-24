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

import br.com.usp.mac0332.snippettool.model.Snippet;
import br.com.usp.mac0332.snippettool.service.SnippetService;

@RestController
@RequestMapping("snippet")
public class SnippetController {

	@Autowired
	private SnippetService service;

	@PostMapping
	public ResponseEntity<Snippet> createSnippet(Snippet snippet) {
		Snippet response = service.createSnippet(snippet);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<Snippet>> readSnippets() {
		List<Snippet> response = service.readSnippets();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{snippetId}")
	public ResponseEntity<Snippet> readSnippet(@PathVariable Integer snippetId) {
		Snippet response = service.readSnippet(snippetId);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{snippetId}")
	public ResponseEntity<Snippet> updateSnippet(@PathVariable Integer snippetId, @RequestBody Snippet updatedSnippet) {
		Snippet response = service.updateSnippet(snippetId, updatedSnippet);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{snippetId}")
	public ResponseEntity<Void> deleteSnippet(@PathVariable Integer snippetId) {
		service.deleteSnippet(snippetId);
		return ResponseEntity.noContent().build();
	}

}
