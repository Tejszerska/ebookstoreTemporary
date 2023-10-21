package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.enums.Genre;
import com.portfolio.ebookstore.repositories.EbookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EbookServiceTest {
    @MockBean
    private EbookRepository ebookRepository;

    @Test
    void getEbooksShouldReturnEmptyListIfDatabaseIsEmpty() {
        // Arrange
        when(ebookRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        EbookService ebookService = new EbookService(ebookRepository);
        List<EbookDto> ebookDtos = ebookService.getEbooks();

        // Assert
        assertEquals(0, ebookDtos.size());
    }
    @Test
    void getEbooksShouldReturnListOfEbookDtos() {
        // Arrange
        List<Ebook> ebooks = List.of(
                new Ebook(1L, "Title 1", "Author 1", "Publisher", "Image Name", "Description", Genre.FANTASY, 100F, 50F)
        );
        when(ebookRepository.findAll()).thenReturn(ebooks);

        // Act
        EbookService ebookService = new EbookService(ebookRepository);
        List<EbookDto> ebookDtos = ebookService.getEbooks();

        // Assert
        assertEquals(1, ebookDtos.size());
        assertEquals(1L, ebookDtos.get(0).getId());
        assertEquals("Title 1", ebookDtos.get(0).getTitle());
        assertEquals("Author 1", ebookDtos.get(0).getAuthors());
        assertEquals("Publisher", ebookDtos.get(0).getPublisher());
        assertEquals("Image Name", ebookDtos.get(0).getImageName());
        assertEquals("Description", ebookDtos.get(0).getDescription());
        assertEquals(Genre.FANTASY.toString(), ebookDtos.get(0).getGenre());
        assertEquals(100, ebookDtos.get(0).getSellingPrice());
        assertEquals(50, ebookDtos.get(0).getPurchaseCost());
    }
}
