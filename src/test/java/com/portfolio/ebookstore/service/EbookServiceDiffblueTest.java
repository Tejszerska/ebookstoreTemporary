package com.portfolio.ebookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.enums.Genre;
import com.portfolio.ebookstore.repositories.EbookRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {EbookService.class})
@ExtendWith(SpringExtension.class)
class EbookServiceDiffblueTest {
    @MockBean
    private EbookRepository ebookRepository;

    @Autowired
    private EbookService ebookService;

    /**
     * Method under test: {@link EbookService#getEbookDtos()}
     */
    @Test
    void getEbookDtosShouldReturnEmpty() {
        when(ebookRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(ebookService.getEbookDtos().isEmpty());
        verify(ebookRepository).findAll();
    }

    /**
     * Method under test: {@link EbookService#getEbookDtos()}
     */
    @Test
    void getEbookDtosShouldReturnOne() {
        Ebook ebook = new Ebook();
        ebook.setAuthors("JaneDoe");
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("Image Name");
        ebook.setPublisher("Publisher");
        ebook.setPurchaseCost(10.0f);
        ebook.setSellingPrice(10.0f);
        ebook.setTitle("Dr");

        ArrayList<Ebook> ebookList = new ArrayList<>();
        ebookList.add(ebook);
        when(ebookRepository.findAll()).thenReturn(ebookList);
        assertEquals(1, ebookService.getEbookDtos().size());
        verify(ebookRepository).findAll();
    }

    /**
     * Method under test: {@link EbookService#getEbookDtos()}
     */
    @Test
    void getEbookDtosShouldReturnTwo() {
        Ebook ebook = new Ebook();
        ebook.setAuthors("JaneDoe");
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("Image Name");
        ebook.setPublisher("Publisher");
        ebook.setPurchaseCost(10.0f);
        ebook.setSellingPrice(10.0f);
        ebook.setTitle("Dr");

        Ebook ebook2 = new Ebook();
        ebook2.setAuthors("Authors");
        ebook2.setDescription("Description");
        ebook2.setGenre(Genre.NON_FICTION);
        ebook2.setId(2L);
        ebook2.setImageName("42");
        ebook2.setPublisher("42");
        ebook2.setPurchaseCost(0.5f);
        ebook2.setSellingPrice(0.5f);
        ebook2.setTitle("Mr");

        ArrayList<Ebook> ebookList = new ArrayList<>();
        ebookList.add(ebook2);
        ebookList.add(ebook);
        when(ebookRepository.findAll()).thenReturn(ebookList);
        assertEquals(2, ebookService.getEbookDtos().size());
        verify(ebookRepository).findAll();
    }

    /**
     * Method under test: {@link EbookService#getEbookDtos()}
     */
    @Test
    void getEbookDtosShouldThrowException() {
        when(ebookRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> ebookService.getEbookDtos());
        verify(ebookRepository).findAll();
    }

    /**
     * Method under test: {@link EbookService#getEbookDtos()}
     */
    @Test
    void testGetEbookDtosWithMock() {
        Ebook ebook = mock(Ebook.class);
        when(ebook.getSellingPrice()).thenThrow(new IllegalArgumentException("foo"));
        when(ebook.getGenre()).thenReturn(Genre.FICTION);
        when(ebook.getId()).thenReturn(1L);
        when(ebook.getAuthors()).thenReturn("JaneDoe");
        when(ebook.getDescription()).thenReturn("The characteristics of someone or something");
        when(ebook.getImageName()).thenReturn("Image Name");
        when(ebook.getPublisher()).thenReturn("Publisher");
        when(ebook.getTitle()).thenReturn("Dr");
        doNothing().when(ebook).setAuthors(Mockito.<String>any());
        doNothing().when(ebook).setDescription(Mockito.<String>any());
        doNothing().when(ebook).setGenre(Mockito.<Genre>any());
        doNothing().when(ebook).setId(Mockito.<Long>any());
        doNothing().when(ebook).setImageName(Mockito.<String>any());
        doNothing().when(ebook).setPublisher(Mockito.<String>any());
        doNothing().when(ebook).setPurchaseCost(Mockito.<Float>any());
        doNothing().when(ebook).setSellingPrice(Mockito.<Float>any());
        doNothing().when(ebook).setTitle(Mockito.<String>any());
        ebook.setAuthors("JaneDoe");
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("Image Name");
        ebook.setPublisher("Publisher");
        ebook.setPurchaseCost(10.0f);
        ebook.setSellingPrice(10.0f);
        ebook.setTitle("Dr");

        ArrayList<Ebook> ebookList = new ArrayList<>();
        ebookList.add(ebook);
        when(ebookRepository.findAll()).thenReturn(ebookList);
        assertThrows(IllegalArgumentException.class, () -> ebookService.getEbookDtos());
        verify(ebookRepository).findAll();
        verify(ebook).getGenre();
        verify(ebook).getSellingPrice();
        verify(ebook).getId();
        verify(ebook).getAuthors();
        verify(ebook).getDescription();
        verify(ebook).getImageName();
        verify(ebook).getPublisher();
        verify(ebook).getTitle();
        verify(ebook).setAuthors(Mockito.<String>any());
        verify(ebook).setDescription(Mockito.<String>any());
        verify(ebook).setGenre(Mockito.<Genre>any());
        verify(ebook).setId(Mockito.<Long>any());
        verify(ebook).setImageName(Mockito.<String>any());
        verify(ebook).setPublisher(Mockito.<String>any());
        verify(ebook).setPurchaseCost(Mockito.<Float>any());
        verify(ebook).setSellingPrice(Mockito.<Float>any());
        verify(ebook).setTitle(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EbookService#addEbook(EbookDto, MultipartFile)}
     */
    @Test
    void testAddEbook() throws IOException {
        try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
            mockFiles.when(() -> Files.exists(Mockito.<Path>any(), isA(LinkOption[].class))).thenReturn(true);
            mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
                    .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
            mockFiles.when(() -> Files.copy(Mockito.<InputStream>any(), Mockito.<Path>any(), isA(CopyOption[].class)))
                    .thenReturn(1L);

            Ebook ebook = new Ebook();
            ebook.setAuthors("JaneDoe");
            ebook.setDescription("The characteristics of someone or something");
            ebook.setGenre(Genre.FICTION);
            ebook.setId(1L);
            ebook.setImageName("Image Name");
            ebook.setPublisher("Publisher");
            ebook.setPurchaseCost(10.0f);
            ebook.setSellingPrice(10.0f);
            ebook.setTitle("Dr");
            when(ebookRepository.save(Mockito.<Ebook>any())).thenReturn(ebook);
            EbookDto ebookDto = new EbookDto();
            ebookService.addEbook(ebookDto,
                    new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
            mockFiles.verify(() -> Files.exists(Mockito.<Path>any(), isA(LinkOption[].class)));
            mockFiles.verify(() -> Files.copy(Mockito.<InputStream>any(), Mockito.<Path>any(), isA(CopyOption[].class)));
            verify(ebookRepository).save(Mockito.<Ebook>any());
        }
    }

    /**
     * Method under test: {@link EbookService#addEbook(EbookDto, MultipartFile)}
     */
    @Test
    void testAddEbook2() throws IOException {
        try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
            mockFiles.when(() -> Files.exists(Mockito.<Path>any(), isA(LinkOption[].class))).thenReturn(true);
            mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
                    .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
            mockFiles.when(() -> Files.copy(Mockito.<InputStream>any(), Mockito.<Path>any(), isA(CopyOption[].class)))
                    .thenReturn(1L);

            Ebook ebook = new Ebook();
            ebook.setAuthors("JaneDoe");
            ebook.setDescription("The characteristics of someone or something");
            ebook.setGenre(Genre.FICTION);
            ebook.setId(1L);
            ebook.setImageName("Image Name");
            ebook.setPublisher("Publisher");
            ebook.setPurchaseCost(10.0f);
            ebook.setSellingPrice(10.0f);
            ebook.setTitle("Dr");
            when(ebookRepository.save(Mockito.<Ebook>any())).thenReturn(ebook);
            ebookService.addEbook(null,
                    new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
            mockFiles.verify(() -> Files.exists(Mockito.<Path>any(), isA(LinkOption[].class)));
            mockFiles.verify(() -> Files.copy(Mockito.<InputStream>any(), Mockito.<Path>any(), isA(CopyOption[].class)));
            verify(ebookRepository).save(Mockito.<Ebook>any());
        }
    }

    /**
     * Method under test: {@link EbookService#addEbook(EbookDto, MultipartFile)}
     */
    @Test
    void testAddEbook3() throws IOException {
        try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
            mockFiles.when(() -> Files.exists(Mockito.<Path>any(), isA(LinkOption[].class))).thenReturn(true);
            mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
                    .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
            mockFiles.when(() -> Files.copy(Mockito.<InputStream>any(), Mockito.<Path>any(), isA(CopyOption[].class)))
                    .thenReturn(1L);
            EbookDto ebookDto = mock(EbookDto.class);
            when(ebookDto.getPurchaseCost()).thenThrow(new IllegalArgumentException("foo"));
            assertThrows(IllegalArgumentException.class, () -> ebookService.addEbook(ebookDto,
                    new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
            verify(ebookDto).getPurchaseCost();
        }
    }

    /**
     * Method under test: {@link EbookService#getEbookDtoById(Long)}
     */
    @Test
    void shouldTestEbookDtoById() {
        Ebook ebook = new Ebook();
        ebook.setAuthors("JaneDoe");
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("Image Name");
        ebook.setPublisher("Publisher");
        ebook.setPurchaseCost(10.0f);
        ebook.setSellingPrice(10.0f);
        ebook.setTitle("Dr");
        Optional<Ebook> ofResult = Optional.of(ebook);
        when(ebookRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        EbookDto actualEbookDtoById = ebookService.getEbookDtoById(1L);
        assertEquals("JaneDoe", actualEbookDtoById.getAuthors());
        assertEquals("Dr", actualEbookDtoById.getTitle());
        assertEquals(10.0f, actualEbookDtoById.getSellingPrice().floatValue());
        assertEquals(10.0f, actualEbookDtoById.getPurchaseCost().floatValue());
        assertEquals("Publisher", actualEbookDtoById.getPublisher());
        assertEquals("Image Name", actualEbookDtoById.getImageName());
        assertEquals(1L, actualEbookDtoById.getId().longValue());
        assertEquals("FICTION", actualEbookDtoById.getGenre());
        assertEquals("The characteristics of someone or something", actualEbookDtoById.getDescription());
        verify(ebookRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EbookService#getEbookDtoById(Long)}
     */
    @Test
    void shouldTestEbookDtoByIdWithMock() {
        Ebook ebook = mock(Ebook.class);
        when(ebook.getSellingPrice()).thenThrow(new IllegalArgumentException("foo"));
        when(ebook.getGenre()).thenReturn(Genre.FICTION);
        when(ebook.getId()).thenReturn(1L);
        when(ebook.getAuthors()).thenReturn("JaneDoe");
        when(ebook.getDescription()).thenReturn("The characteristics of someone or something");
        when(ebook.getImageName()).thenReturn("Image Name");
        when(ebook.getPublisher()).thenReturn("Publisher");
        when(ebook.getTitle()).thenReturn("Dr");
        doNothing().when(ebook).setAuthors(Mockito.<String>any());
        doNothing().when(ebook).setDescription(Mockito.<String>any());
        doNothing().when(ebook).setGenre(Mockito.<Genre>any());
        doNothing().when(ebook).setId(Mockito.<Long>any());
        doNothing().when(ebook).setImageName(Mockito.<String>any());
        doNothing().when(ebook).setPublisher(Mockito.<String>any());
        doNothing().when(ebook).setPurchaseCost(Mockito.<Float>any());
        doNothing().when(ebook).setSellingPrice(Mockito.<Float>any());
        doNothing().when(ebook).setTitle(Mockito.<String>any());
        ebook.setAuthors("JaneDoe");
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("Image Name");
        ebook.setPublisher("Publisher");
        ebook.setPurchaseCost(10.0f);
        ebook.setSellingPrice(10.0f);
        ebook.setTitle("Dr");
        Optional<Ebook> ofResult = Optional.of(ebook);
        when(ebookRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> ebookService.getEbookDtoById(1L));
        verify(ebookRepository).findById(Mockito.<Long>any());
        verify(ebook).getGenre();
        verify(ebook).getSellingPrice();
        verify(ebook).getId();
        verify(ebook).getAuthors();
        verify(ebook).getDescription();
        verify(ebook).getImageName();
        verify(ebook).getPublisher();
        verify(ebook).getTitle();
        verify(ebook).setAuthors(Mockito.<String>any());
        verify(ebook).setDescription(Mockito.<String>any());
        verify(ebook).setGenre(Mockito.<Genre>any());
        verify(ebook).setId(Mockito.<Long>any());
        verify(ebook).setImageName(Mockito.<String>any());
        verify(ebook).setPublisher(Mockito.<String>any());
        verify(ebook).setPurchaseCost(Mockito.<Float>any());
        verify(ebook).setSellingPrice(Mockito.<Float>any());
        verify(ebook).setTitle(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EbookService#getEbookDtoById(Long)}
     */
    @Test
    void ebookDtoByIdShouldReturnException() {
        Optional<Ebook> emptyResult = Optional.empty();
        when(ebookRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        new IllegalArgumentException("foo");
        new IllegalArgumentException("foo");
        assertThrows(IllegalArgumentException.class, () -> ebookService.getEbookDtoById(1L));
        verify(ebookRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EbookService#coverUpload(MultipartFile)}
     */
    @Test
    void testCoverUpload() throws IOException {
        try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
            mockFiles.when(() -> Files.exists(Mockito.<Path>any(), isA(LinkOption[].class))).thenReturn(true);
            mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
                    .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
            mockFiles.when(() -> Files.copy(Mockito.<InputStream>any(), Mockito.<Path>any(), isA(CopyOption[].class)))
                    .thenReturn(1L);
            ebookService.coverUpload(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
            mockFiles.verify(() -> Files.exists(Mockito.<Path>any(), isA(LinkOption[].class)));
            mockFiles.verify(() -> Files.copy(Mockito.<InputStream>any(), Mockito.<Path>any(), isA(CopyOption[].class)));
        }
    }

    /**
     * Method under test: {@link EbookService#coverUpload(MultipartFile)}
     */
    @Test
    void testCoverUpload2() throws IOException {
        try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
            mockFiles.when(() -> Files.exists(Mockito.<Path>any(), isA(LinkOption[].class))).thenReturn(false);
            mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
                    .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
            mockFiles.when(() -> Files.copy(Mockito.<InputStream>any(), Mockito.<Path>any(), isA(CopyOption[].class)))
                    .thenReturn(1L);
            ebookService.coverUpload(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
            mockFiles.verify(() -> Files.exists(Mockito.<Path>any(), isA(LinkOption[].class)));
            mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
            mockFiles.verify(() -> Files.copy(Mockito.<InputStream>any(), Mockito.<Path>any(), isA(CopyOption[].class)));
        }
    }
}

