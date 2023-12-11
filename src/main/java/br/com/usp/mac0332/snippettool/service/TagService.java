package br.com.usp.mac0332.snippettool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usp.mac0332.snippettool.dto.tag.TagCreateDto;
import br.com.usp.mac0332.snippettool.dto.tag.TagResponseDto;
import br.com.usp.mac0332.snippettool.dto.tag.TagUpdateDto;
import br.com.usp.mac0332.snippettool.model.Tag;
import br.com.usp.mac0332.snippettool.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TagService {

	@Autowired
	private TagRepository repository;

	@Transactional
	public TagResponseDto createTag(TagCreateDto tagCreateDto) {
		Tag tag = new Tag(tagCreateDto);
		TagResponseDto tagResponseDto = new TagResponseDto(repository.save(tag));
		return tagResponseDto;
	}

	public List<TagResponseDto> readTags() {
		List<Tag> tags = repository.findAll();
		List<TagResponseDto> tagsResponseDto = tags.stream().map(TagResponseDto::new).toList();
		return tagsResponseDto;
	}

	public TagResponseDto readTag(Integer tagId) {
		Tag tag = repository.findById(tagId).orElseThrow(() -> new EntityNotFoundException("Tag not found with the id: " + tagId));
		TagResponseDto tagResponseDto = new TagResponseDto(tag);
		return tagResponseDto;
	}

	@Transactional
	public TagResponseDto updateTag(Integer tagId, TagUpdateDto updatedTag) {
		Tag existingTag = repository.findById(tagId).orElseThrow(() -> new EntityNotFoundException("Tag not found with the id: " + tagId));
		existingTag.update(updatedTag);
		TagResponseDto tagResponseDto = new TagResponseDto(repository.save(existingTag));
		return tagResponseDto;
	}

	public void deleteTag(Integer tagId) {
		repository.deleteById(tagId);
	}
	
	public Tag findById(Integer tagId) {
		return repository.findById(tagId).orElseThrow(() -> new EntityNotFoundException("Tag not found with the id: " + tagId));
	}

}
