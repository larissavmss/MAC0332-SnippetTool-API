package br.com.usp.mac0332.snippettool.model;

import static jakarta.persistence.CascadeType.REFRESH;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import br.com.usp.mac0332.snippettool.dto.snippet.SnippetCreateDto;
import br.com.usp.mac0332.snippettool.dto.snippet.SnippetUpdateDto;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "snippet", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Snippet {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	private String name;

	private String content;

	@CreationTimestamp
	private Date creationDate;

	@ManyToOne
	@JoinColumn(name = "folder_id")
	private Folder folder;

	@ManyToMany(mappedBy = "snippets", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, REFRESH})
	private Set<Tag> tags = new HashSet<>();

	public Snippet(SnippetCreateDto snippetDto, Folder folder) {
		this.name = snippetDto.name();
		this.content = snippetDto.content();
		this.folder = folder;
	}
	
	public void addTag(Tag tag) {
		tag.addSnippet(this);
		this.tags.add(tag);
	}

	public void removeTag(Tag tag) {
		tag.removeSnippet(this);
		this.tags.remove(tag);
	}
	
	public void update(SnippetUpdateDto updatedSnippet, Folder folder) {
		if (StringUtils.isNotBlank(updatedSnippet.name())) {
			this.name = updatedSnippet.name();
		}
		if (StringUtils.isNotBlank(updatedSnippet.content())) {
			this.content = updatedSnippet.content();
		}
		if (Objects.nonNull(folder)) {
			this.folder = folder;
		}
	}

}