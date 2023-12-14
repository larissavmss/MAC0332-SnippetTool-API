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

import br.com.usp.mac0332.snippettool.enums.Color;
import br.com.usp.mac0332.snippettool.model.Tag;
import br.com.usp.mac0332.snippettool.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TagRepositoryTest {

	@Autowired
	private TagRepository repository;

	@Autowired
	private UserRepository userRepository;

	private Tag tag;

	private User user;

	@BeforeEach
	public void init() {
		this.user = User.builder().username("username test").email("email@test.com").password("password test").build();
		userRepository.save(this.user);
		this.tag = Tag.builder().name("tag test").color(Color.BLUE).user(user).build();
		repository.save(this.tag);
	}

	@Test
	public void findByUser_IdAndNameContaining_Should_Find() {
		List<Tag> tags = repository.findByUser_IdAndNameContaining(this.user.getId(), this.tag.getName());
		Assertions.assertThat(tags).isNotNull();
	}

	@Test
	public void findByIdAndUser_Id_Should_Find() {
		Optional<Tag> optionalTag = repository.findByIdAndUser_Id(this.tag.getId(), this.user.getId());
		Assertions.assertThat(optionalTag).isNotEmpty();
	}

	@Test
	public void deleteByIdAndUser_Id_Should_Delete() {
		repository.deleteByIdAndUser_Id(this.tag.getId(), this.user.getId());
		Optional<Tag> optionalTag = repository.findById(this.tag.getId());
		Assertions.assertThat(optionalTag).isEmpty();
	}

}
