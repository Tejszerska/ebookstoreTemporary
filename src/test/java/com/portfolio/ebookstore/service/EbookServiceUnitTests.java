package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.enums.Genre;
import com.portfolio.ebookstore.repositories.EbookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@ExtendWith(MockitoExtension.class)
public class EbookServiceUnitTests {
    private static final Long EBOOK_ID = 1L;
    private static EbookDto TEST_EBOOKDTO1 = new EbookDto(1L, "Testing", "Abc Def", "GHIJK", "cover.jpg", "Qwerty", Genre.FANTASY.toString(), 30F, 10.99F);
    private static EbookDto TEST_EBOOKDTO2 = new EbookDto(2L, "Testing", "Abc Def", "GHIJK", "cover.jpg", "Qwerty", Genre.FANTASY.toString(), 30F, 10.99F);
    private static Ebook TEST_EBOOK1 = new Ebook(1L, "Testing", "Abc Def", "GHIJK", "cover.jpg", "Qwerty", Genre.FANTASY, 30F, 10.99F);
    private static Ebook TEST_EBOOK2 = new Ebook(2L, "Testing", "Abc Def", "GHIJK", "cover.jpg", "Qwerty", Genre.FANTASY, 30F, 10.99F);

    @Mock
    private Logger log;
    @Mock
    private EbookRepository ebookRepository;

    @InjectMocks
    private EbookService ebookService;
    @Test
    public void shouldReturnListOfEbookDtos(){
        //Arrange
        Mockito.when(ebookRepository.findAll()).thenReturn(List.of(TEST_EBOOK1, TEST_EBOOK2));
        List<EbookDto> expected = List.of(TEST_EBOOKDTO1, TEST_EBOOKDTO2);
        //Act
        List<EbookDto> result = ebookService.getEbookDtos();
        //Assert
        Assertions.assertThat(result).isEqualTo(expected)
                .hasSize(2);
        Assertions.assertThat(result.get(0)).isEqualTo(expected.get(0));
        Mockito.verify(ebookRepository).findAll();
    }

    @Test
    public void shouldGetEbookDtoById() {
        //    Arrange
        Mockito.when(ebookRepository.findById(EBOOK_ID)).thenReturn(Optional.ofNullable(TEST_EBOOK1));
        //    Act
        EbookDto result = ebookService.getEbookDtoById(EBOOK_ID);
        //    Assert
        Assertions.assertThat(result).isEqualTo(TEST_EBOOKDTO1);

        Mockito.verify(ebookRepository).findById(EBOOK_ID);

    }

    @Test
public void shouldCoverUpload()throws IOException {
        // Arrange
        MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test image".getBytes());
        Path imgDirectory = Path.of("src/.../img");

        // Mock the Files class to simulate directory creation
        Mockito.when(Files.exists(imgDirectory)).thenReturn(false); // Directory doesn't exist
        Mockito.doNothing().when(Files.class);
        Files.createDirectories(imgDirectory);

        // Mock the Logger to do nothing when errors are logged
        Mockito.doNothing().when(log).error(Mockito.anyString());

        // Act
        ebookService.coverUpload(mockFile);

        // Assert
        // Verify that the createDirectories method was called
        Mockito.verify(Files, Mockito.times(1)).createDirectories(imgDirectory);

        // Verify that the copy method was called
        Mockito.verify(Files, Mockito.times(1)).copy(
                mockFile.getInputStream(), imgDirectory.resolve(mockFile.getOriginalFilename())
        );

        // Verify that no errors were logged
        Mockito.verify(log, Mockito.never()).error(Mockito.anyString());
    }


}

