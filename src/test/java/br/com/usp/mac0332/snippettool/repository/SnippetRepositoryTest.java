package br.com.usp.mac0332.snippettool.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.usp.mac0332.snippettool.enums.Color;
import br.com.usp.mac0332.snippettool.model.Folder;
import br.com.usp.mac0332.snippettool.model.Snippet;
import br.com.usp.mac0332.snippettool.model.Tag;
import br.com.usp.mac0332.snippettool.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SnippetRepositoryTest {

    @Autowired
    private SnippetRepository repository;
    
    @Autowired
    private FolderRepository folderRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    private User user;
    
    private Folder folder;
    
    private Snippet snippet;
    
    @BeforeEach
    public void init() {
    	this.user = User.builder().username("username test").email("email@test.com").password("password test").build();
    	userRepository.save(this.user);
    	this.folder = Folder.builder().name("folder test").user(this.user).build();
    	folderRepository.save(this.folder);
    	this.snippet = Snippet.builder().name("snippet test").content("content test").folder(this.folder).build();
    	repository.save(this.snippet);
    }
    
    @Test
    public void findByFolder_User_IdAndNameContaing_Should_Find() {
    	List<Snippet> snippets = repository.findByFolder_User_IdAndNameContaining(this.user.getId(), this.snippet.getName());
    	Assertions.assertThat(snippets).isNotNull();
    }

    @Test
    public void findByIdAndFolder_User_Id_Should_Find() {
    	Optional<Snippet> optionalSnippet = repository.findByIdAndFolder_User_Id(this.snippet.getId(), this.user.id);
    	Assertions.assertThat(optionalSnippet).isNotEmpty();
    }

    @Test
    public void deleteByIdAndFolder_User_Id_Should_Delete() {
    	repository.deleteByIdAndFolder_User_Id(this.snippet.getId(), this.user.getId());
    	Optional<Snippet> optionalSnippet = repository.findById(this.snippet.getId());
    	Assertions.assertThat(optionalSnippet).isEmpty();
    }

    @Test
    public void findByFolder_IdAndFolder_User_IdAndNameContaining_Should_Find() {
    	List<Snippet> snippets = repository.findByFolder_IdAndFolder_User_IdAndNameContaining(this.folder.getId(), this.user.getId(), this.snippet.getName());
    	Assertions.assertThat(snippets).isNotNull();
    }
    
    @Test
    public void findByTags_IdAndFolder_User_IdAndNameContaining_Should_Find() {
    	Tag tag = Tag.builder().name("tag test").color(Color.RED).user(this.user).build();
    	Set<Tag> tags = new HashSet<>();
    	tags.add(tag);
    	this.snippet.setTags(tags);
    	repository.save(this.snippet);
    	List<Snippet> snippets = repository.findByTags_IdAndFolder_User_IdAndNameContaining(this.folder.getId(), this.user.getId(), this.snippet.getName());
    	Assertions.assertThat(snippets).isNotNull();
    }

}


