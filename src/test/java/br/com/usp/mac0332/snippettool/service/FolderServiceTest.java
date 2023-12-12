package br.com.usp.mac0332.snippettool.service;

import br.com.usp.mac0332.snippettool.TestUtils;
import br.com.usp.mac0332.snippettool.repository.FolderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {"spring.profiles.active:test"})
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FolderServiceTest {

    @Autowired
    FolderService folderService;

    @Test
    void testAddFolder(){
        var mockedRepository = Mockito.mock(FolderRepository.class);
        Mockito.when(mockedRepository.save(ArgumentMatchers.any())).thenReturn(null);
        var user = TestUtils.createUser();
        folderService.addFolder("test", user);
    }

    @Test
    void testFindByName(){
        var mockedRepository = Mockito.mock(FolderRepository.class);
        Mockito.when(mockedRepository.save(ArgumentMatchers.any())).thenReturn(null);
        folderService.findByName("test");
    }

    @Test
    void testGetAll(){
        var mockedRepository = Mockito.mock(FolderRepository.class);
        Mockito.when(mockedRepository.findAllByUserId(ArgumentMatchers.any())).thenReturn(null);

        folderService.getAll(1);
    }

//    @Test
//    void testUpdateFolder(){
//        var mockedRepository = Mockito.mock(FolderRepository.class);
//        var folder = TestUtils.createFolder();
//        Mockito.when(mockedRepository.save(ArgumentMatchers.any())).thenReturn(folder);
//        Mockito.when(mockedRepository.findById(ArgumentMatchers.any()));
//        folderService.updateFolder(1, folder);
//    }

    @Test
    void testDeleteFolder(){
        var mockedRepository = Mockito.mock(FolderRepository.class);
        Mockito.when(mockedRepository.save(ArgumentMatchers.any())).thenReturn(null);
        var user = TestUtils.createUser();
        folderService.addFolder("test", user);
    }

}
