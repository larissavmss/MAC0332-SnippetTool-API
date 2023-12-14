package br.com.usp.mac0332.snippettool.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.usp.mac0332.snippettool.model.Folder;
import br.com.usp.mac0332.snippettool.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FolderRepositoryTest {

    @Autowired
    private FolderRepository repository;
    
    private User user;
    
    private Folder folder;
    
    @BeforeEach
    public void init() {
    	this.user = User.builder().username("username test").email("email@test.com").password("password test").build();
    	this.folder = Folder.builder().name("folder test").user(this.user).build();
    	repository.save(this.folder);
    }
    
    @Test
    public void findByNameAndUserId_Should_Find() {
    	Optional<Folder> optionalFolder = repository.findByNameAndUserId(this.folder.getName(), this.user.getId());
    	Assertions.assertThat(optionalFolder).isNotEmpty();
    }

    @Test
    public void findByUserIdAndNameContaining_Should_Find() {
    	List<Folder> folder = repository.findByUserIdAndNameContaining(this.user.id, this.folder.getName());
    	Assertions.assertThat(folder).isNotNull();
    }

    @Test
    public void findByIdAndUserId_Should_Find() {
    	Optional<Folder> optionalFolder = repository.findByIdAndUserId(this.folder.getId(), this.user.getId());
    	Assertions.assertThat(optionalFolder).isNotEmpty();
    }

    @Test
    public void deleteByIdAndUserId_Should_Delete() {
    	repository.deleteByIdAndUserId(this.folder.getId(), this.user.getId());
    	Optional<Folder> optionalFolder = repository.findById(this.folder.getId());
    	Assertions.assertThat(optionalFolder).isEmpty();
    }

}

