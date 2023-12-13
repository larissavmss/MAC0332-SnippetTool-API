package br.com.usp.mac0332.snippettool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usp.mac0332.snippettool.dto.tag.TagCreateDto;
import br.com.usp.mac0332.snippettool.dto.tag.TagResponseDto;
import br.com.usp.mac0332.snippettool.dto.tag.TagUpdateDto;
import br.com.usp.mac0332.snippettool.model.Tag;
import br.com.usp.mac0332.snippettool.model.User;
import br.com.usp.mac0332.snippettool.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TagService {

	@Autowired
	private TagRepository repository;

	@Transactional
	public TagResponseDto createTag(TagCreateDto tagCreateDto, User user) {
		Tag tag = new Tag(tagCreateDto, user);
		TagResponseDto tagResponseDto = new TagResponseDto(repository.save(tag));
		return tagResponseDto;
	}

	public List<TagResponseDto> readTags(Integer userId) {
		List<Tag> tags = repository.findByUser_Id(userId);
		List<TagResponseDto> tagsResponseDto = tags.stream().map(TagResponseDto::new).toList();
		return tagsResponseDto;
	}

	public TagResponseDto readTag(Integer tagId, Integer userId) {
		Tag tag = repository.findByIdAndUser_Id(tagId, userId).orElseThrow(() -> new EntityNotFoundException("Tag not found with the id: " + tagId));
		TagResponseDto tagResponseDto = new TagResponseDto(tag);
		return tagResponseDto;
	}

	@Transactional
	public TagResponseDto updateTag(Integer tagId, TagUpdateDto updatedTag, Integer userId) {
		Tag existingTag = repository.findByIdAndUser_Id(tagId, userId).orElseThrow(() -> new EntityNotFoundException("Tag not found with the id: " + tagId));
		existingTag.update(updatedTag);
		TagResponseDto tagResponseDto = new TagResponseDto(repository.save(existingTag));
		return tagResponseDto;
	}

	@Transactional
	public void deleteTag(Integer tagId, Integer userId) {
		repository.deleteByIdAndUser_Id(tagId, userId);
	}
	
	public Tag findById(Integer tagId) {
		return repository.findById(tagId).orElseThrow(() -> new EntityNotFoundException("Tag not found with the id: " + tagId));
	}

}
