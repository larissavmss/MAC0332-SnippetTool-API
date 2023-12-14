package br.com.usp.mac0332.snippettool.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.usp.mac0332.snippettool.dto.folder.FolderCreateDto;
import br.com.usp.mac0332.snippettool.dto.folder.FolderResponseDto;
import br.com.usp.mac0332.snippettool.dto.folder.FolderUpdateDto;
import br.com.usp.mac0332.snippettool.model.Folder;
import br.com.usp.mac0332.snippettool.model.User;
import br.com.usp.mac0332.snippettool.repository.FolderRepository;

@ExtendWith(MockitoExtension.class)
public class FolderServiceTest {

	@InjectMocks
	private FolderService service;

	@Mock
	private FolderRepository repository;

	private User user;

	@BeforeEach
	public void init() {
		User user = User.builder().username("username test").email("email test").password("password test").build();
	}

	@Test
	public void addFolder_Should_Return_Dto() {
		Folder folder = Folder.builder().name("folder test").build();
		FolderCreateDto folderCreateDto = FolderCreateDto.builder().name("folder test").build();
		when(repository.save(Mockito.any(Folder.class))).thenReturn(folder);
		FolderResponseDto folderResponseDto = service.addFolder(folderCreateDto, this.user);
		Assertions.assertThat(folderResponseDto).isNotNull();
	}

	@Test
	public void gettAll_Should_Return_Dto() {
		int userId = 1;
		String filtro = "filtro test";
		Folder folder = Folder.builder().name("test folder").build();
		when(repository.findByUserIdAndNameContaining(Mockito.anyInt(), Mockito.anyString()))
				.thenReturn(Arrays.asList(folder));
		List<FolderResponseDto> folderResponseDto = service.getAll(userId, filtro);
		Assertions.assertThat(folderResponseDto).isNotNull();
	}

	@Test
	public void updateFolder_Should_Return_Dto() {
		int folderId = 1;
		int userId = 1;
		FolderUpdateDto folderUpdateDto = FolderUpdateDto.builder().name("folder test").build();
		Folder folder = Folder.builder().name("folder test").build();
		Optional<Folder> optionalFolder = Optional.of(folder);
		when(repository.findByIdAndUserId(Mockito.anyInt(), Mockito.anyInt())).thenReturn(optionalFolder);
		when(repository.save(Mockito.any(Folder.class))).thenReturn(folder);
		FolderResponseDto folderResponseDto = service.updateFolder(folderId, folderUpdateDto, userId);
		Assertions.assertThat(folderResponseDto).isNotNull();
	}

	@Test
	public void deleteFolder_Should_Return_Nothing() {
		int snippetId = 1;
		int userId = 1;
		doNothing().when(repository).deleteByIdAndUserId(Mockito.anyInt(), Mockito.anyInt());
		assertAll(() -> service.deleteFolder(snippetId, userId));
	}

	@Test
	public void findDefaultFolderByUserId_Should_Return() {
		int userId = 1;
		Folder folder = Folder.builder().name("folder test").build();
		Optional<Folder> optionalFolder = Optional.of(folder);
		when(repository.findByNameAndUserId(Mockito.anyString(), Mockito.anyInt())).thenReturn(optionalFolder);
		Folder folderResponse = service.findDefaultFolderByUserId(userId);
		Assertions.assertThat(folderResponse).isNotNull();
	}

	@Test
	public void findByIdAndUserIdToDto_Should_Return_Dto() {
		int folderId = 1;
		int userId = 1;
		Folder folder = Folder.builder().name("folder test").build();
		Optional<Folder> optionalFolder = Optional.of(folder);
		when(repository.findByIdAndUserId(Mockito.anyInt(), Mockito.anyInt())).thenReturn(optionalFolder);
		FolderResponseDto folderResponseDto = service.findByIdAndUserIdToDto(folderId, userId);
		Assertions.assertThat(folderResponseDto).isNotNull();
	}

	@Test
	public void findByIdAndUserId_Should_Return_Nothing() {
		int folderId = 1;
		int userId = 1;
		Folder folder = Folder.builder().name("folder test").build();
		Optional<Folder> optionalFolder = Optional.of(folder);
		when(repository.findByIdAndUserId(Mockito.anyInt(), Mockito.anyInt())).thenReturn(optionalFolder);
		Folder folderResponse = service.findByIdAndUserId(folderId, userId);
		Assertions.assertThat(folderResponse).isNotNull();
	}
}
