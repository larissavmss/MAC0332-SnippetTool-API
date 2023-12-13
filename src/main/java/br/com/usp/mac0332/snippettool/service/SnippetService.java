package br.com.usp.mac0332.snippettool.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usp.mac0332.snippettool.dto.snippet.SnippetCreateDto;
import br.com.usp.mac0332.snippettool.dto.snippet.SnippetResponseDto;
import br.com.usp.mac0332.snippettool.dto.snippet.SnippetUpdateDto;
import br.com.usp.mac0332.snippettool.dto.tag.TagResponseDto;
import br.com.usp.mac0332.snippettool.model.Folder;
import br.com.usp.mac0332.snippettool.model.Snippet;
import br.com.usp.mac0332.snippettool.model.Tag;
import br.com.usp.mac0332.snippettool.repository.SnippetRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class SnippetService {

	@Autowired
	private SnippetRepository repository;

	@Autowired
	private FolderService folderService;
	
	@Autowired
	private TagService tagService;

	@Transactional
	public SnippetResponseDto createSnippet(SnippetCreateDto snippetCreateDto, Integer userId) {
		Folder folder = Objects.nonNull(snippetCreateDto.folderId()) ? folderService.findByIdAndUserId(snippetCreateDto.folderId(), userId) : folderService.findDefaultFolderByUserId(userId);
		Snippet snippet = new Snippet(snippetCreateDto, folder);
		Snippet snippetSaved = repository.save(snippet);
		SnippetResponseDto response = this.createSnippetResponseDto(snippetSaved);
		return response;
	}

	public List<SnippetResponseDto> readSnippets(Integer userId) {
		List<Snippet> snippets = repository.findByFolder_User_Id(userId);
		List<SnippetResponseDto> snippetsResponseDto = snippets.stream().map(this::createSnippetResponseDto).toList();
		return snippetsResponseDto;
	}

	public SnippetResponseDto readSnippet(Integer snippetId, Integer userId) {
		Snippet snippet = repository.findByIdAndFolder_User_Id(snippetId, userId).orElseThrow(() -> new EntityNotFoundException("Snippet not found with id: " + snippetId));
		SnippetResponseDto snippetResponseDto = this.createSnippetResponseDto(snippet);
		return snippetResponseDto;
	}

	@Transactional
	public SnippetResponseDto updateSnippet(Integer snippetId, SnippetUpdateDto updatedSnippet, Integer userId) {
		Snippet existingSnippet = repository.findByIdAndFolder_User_Id(snippetId, userId).orElseThrow(() -> new EntityNotFoundException("Snippet not found with id: " + snippetId));
		Folder folder = null;
		if(Objects.nonNull(updatedSnippet.folderId())) {			
			folder = folderService.findByIdAndUserId(updatedSnippet.folderId(), userId);
		}
		existingSnippet.update(updatedSnippet, folder);
		SnippetResponseDto snippetResponseDto = this.createSnippetResponseDto(repository.save(existingSnippet));
		return snippetResponseDto;
	}

	@Transactional
	public void deleteSnippet(Integer snippetId, Integer userId) {
		repository.deleteByIdAndFolder_User_Id(snippetId, userId);
	}
	
	public SnippetResponseDto addTagToSnippet(Integer snippetId, Integer tagId, Integer userId) {
		Snippet snippet = repository.findByIdAndFolder_User_Id(snippetId, userId).orElseThrow(() -> new EntityNotFoundException("Snippet not found with id: " + snippetId));
		Tag tag = tagService.findById(tagId);
		snippet.addTag(tag);
		SnippetResponseDto snippetResponseDto = this.createSnippetResponseDto(repository.save(snippet));
		return snippetResponseDto;
	}
	
	public SnippetResponseDto removeTagFromSnippet(Integer snippetId, Integer tagId, Integer userId) {
		Snippet snippet = repository.findByIdAndFolder_User_Id(snippetId, userId).orElseThrow(() -> new EntityNotFoundException("Snippet not found with id: " + snippetId));
		Tag tag = tagService.findById(tagId);
		snippet.removeTag(tag);
		SnippetResponseDto snippetResponseDto = this.createSnippetResponseDto(repository.save(snippet));
		return snippetResponseDto;
	}
	
	public List<SnippetResponseDto> findByFiltro(Integer folderId, Integer userId){
		List<Snippet> snippets = repository.findByFolder_IdAndFolder_User_Id(folderId, userId);
		List<SnippetResponseDto> snippetsResponseDto = snippets.stream().map(this::createSnippetResponseDto).toList();
		return snippetsResponseDto;
	}
	
	public List<TagResponseDto> findTags(Integer snippetId, Integer userId){
		Snippet snippet = repository.findByIdAndFolder_User_Id(snippetId, userId).orElseThrow(() -> new EntityNotFoundException("Snippet not found with id: " + snippetId));
		List<TagResponseDto> tagsResponseDto = snippet.getTags().stream().map(TagResponseDto::new).toList();
		return tagsResponseDto;
	}
	
	private SnippetResponseDto createSnippetResponseDto(Snippet snippet) {
		Set<Tag> tags = snippet.getTags();
		List<TagResponseDto> tagsResponseDto = tags.stream().map(TagResponseDto::new).toList();
		return new SnippetResponseDto(snippet, tagsResponseDto);
	}
}
