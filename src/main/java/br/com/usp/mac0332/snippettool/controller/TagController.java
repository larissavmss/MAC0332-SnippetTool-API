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

import br.com.usp.mac0332.snippettool.model.Tag;
import br.com.usp.mac0332.snippettool.service.TagService;

@RestController
@RequestMapping("tag")
public class TagController {

	@Autowired
	private TagService service;

	@PostMapping
	public ResponseEntity<Tag> createTag(Tag tag) {
		Tag response = service.createTag(tag);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<Tag>> readTags() {
		List<Tag> response = service.readTags();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{tagId}")
	public ResponseEntity<Tag> readTag(@PathVariable Integer tagId) {
		Tag response = service.readTag(tagId);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{tagId}")
	public ResponseEntity<Tag> updatedTag(@PathVariable Integer tagId, @RequestBody Tag updatedTag) {
		Tag response = service.updateTag(tagId, updatedTag);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{tagId}")
	public ResponseEntity<Void> deleteTag(@PathVariable Integer tagId) {
		service.deleteTag(tagId);
		return ResponseEntity.noContent().build();
	}
}
