package br.com.usp.mac0332.snippettool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usp.mac0332.snippettool.model.Snippet;
import br.com.usp.mac0332.snippettool.repository.SnippetRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class SnippetService {

	@Autowired
	private SnippetRepository repository;

	@Transactional
	public Snippet createSnippet(Snippet snippet) {
		return repository.save(snippet);
	}

	public List<Snippet> readSnippets() {
		List<Snippet> snippets = repository.findAll();
		return snippets;
	}

	public Snippet readSnippet(Integer snippetId) {
		Snippet snippet = repository.findById(snippetId)
				.orElseThrow(() -> new EntityNotFoundException("Snippet not found with id: " + snippetId));
		return snippet;
	}

	@Transactional
	public Snippet updateSnippet(Integer snippetId, Snippet updatedSnippet) {
		Snippet existingSnippet = repository.findById(snippetId)
				.orElseThrow(() -> new EntityNotFoundException("Snippet not found with id: " + snippetId));
		existingSnippet.update(updatedSnippet);
		return repository.save(existingSnippet);
	}

	public void deleteSnippet(Integer snippetId) {
		repository.deleteById(snippetId);
	}
}
