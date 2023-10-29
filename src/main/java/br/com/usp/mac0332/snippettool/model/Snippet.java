package br.com.usp.mac0332.snippettool.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "snippet", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Snippet {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	private String name;

	private String content;

	private Date creationDate;

	@ManyToOne
	@JoinColumn(name = "folder_id")
	private Folder folder;

	@ManyToMany(mappedBy = "snippets")
	private Set<Tag> tags;

	public void update(Snippet updatedSnippet) {
		if (updatedSnippet.getName() != null) {
			this.name = updatedSnippet.getName();
		}
		if (updatedSnippet.getContent() != null) {
			this.content = updatedSnippet.getContent();
		}
		if (updatedSnippet.getCreationDate() != null) {
			this.creationDate = updatedSnippet.getCreationDate();
		}
	}

	@PrePersist
	public void onCreate() {
		this.creationDate = Date.valueOf(LocalDate.now());
	}

}
