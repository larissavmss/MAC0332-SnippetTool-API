package br.com.usp.mac0332.snippettool.controller;

import br.com.usp.mac0332.snippettool.enums.Color;
import br.com.usp.mac0332.snippettool.model.ColorName;
import br.com.usp.mac0332.snippettool.model.Tag;
import br.com.usp.mac0332.snippettool.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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

	@GetMapping("getCollorOptions")
	public ResponseEntity getCollorOptions() {
		var response = Arrays.stream(Color.values()).map(it -> new ColorName(it));
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
