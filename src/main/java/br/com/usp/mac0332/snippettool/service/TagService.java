package br.com.usp.mac0332.snippettool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usp.mac0332.snippettool.model.Tag;
import br.com.usp.mac0332.snippettool.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TagService {

	@Autowired
	private TagRepository repository;

	@Transactional
	public Tag createTag(Tag tag) {
		return repository.save(tag);
	}

	public List<Tag> readTags() {
		List<Tag> tags = repository.findAll();
		return tags;
	}

	public Tag readTag(Integer tagId) {
		Tag tag = repository.findById(tagId)
				.orElseThrow(() -> new EntityNotFoundException("Tag not found with the id: " + tagId));
		return tag;
	}

	@Transactional
	public Tag updateTag(Integer tagId, Tag updatedTag) {
		Tag existingTag = repository.findById(tagId)
				.orElseThrow(() -> new EntityNotFoundException("Tag not found with the id: " + tagId));
		existingTag.update(updatedTag);
		return repository.save(existingTag);
	}

	public void deleteTag(Integer tagId) {
		repository.deleteById(tagId);
	}

}
