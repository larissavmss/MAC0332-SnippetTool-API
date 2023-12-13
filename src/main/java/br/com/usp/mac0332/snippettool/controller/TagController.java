package br.com.usp.mac0332.snippettool.controller;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RestController;

import br.com.usp.mac0332.snippettool.dto.tag.TagColorResponseDto;
import br.com.usp.mac0332.snippettool.dto.tag.TagCreateDto;
import br.com.usp.mac0332.snippettool.dto.tag.TagResponseDto;
import br.com.usp.mac0332.snippettool.dto.tag.TagUpdateDto;
import br.com.usp.mac0332.snippettool.enums.Color;
import br.com.usp.mac0332.snippettool.service.MyUserDetails;
import br.com.usp.mac0332.snippettool.service.TagService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("tag")
public class TagController {

	@Autowired
	private TagService service;

	@PostMapping
	public ResponseEntity<TagResponseDto> createTag(@Valid @RequestBody TagCreateDto tag, @AuthenticationPrincipal UserDetails userDetails) {
		TagResponseDto response = service.createTag(tag, ((MyUserDetails) userDetails).user);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<TagResponseDto>> readTags(@AuthenticationPrincipal UserDetails userDetails) {
		List<TagResponseDto> response = service.readTags(((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getCollorOptions")
	public ResponseEntity<List<TagColorResponseDto>> getCollorOptions() {
		List<TagColorResponseDto> response = Arrays.asList(Color.values()).stream().map(color -> new TagColorResponseDto(color.toString())).toList();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{tagId}")
	public ResponseEntity<TagResponseDto> readTag(@PathVariable Integer tagId, @AuthenticationPrincipal UserDetails userDetails) {
		TagResponseDto response = service.readTag(tagId, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{tagId}")
	public ResponseEntity<TagResponseDto> updatedTag(@PathVariable Integer tagId, @Valid @RequestBody TagUpdateDto updatedTag, @AuthenticationPrincipal UserDetails userDetails) {
		TagResponseDto response = service.updateTag(tagId, updatedTag, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{tagId}")
	public ResponseEntity<Void> deleteTag(@PathVariable Integer tagId, @AuthenticationPrincipal UserDetails userDetails) {
		service.deleteTag(tagId, ((MyUserDetails) userDetails).user.id);
		return ResponseEntity.noContent().build();
	}
}
