package com.portfolio.ebookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.enums.Genre;
import com.portfolio.ebookstore.repositories.EbookRepository;
import com.portfolio.ebookstore.repositories.OrderRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Optional;

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

    @MockBean
    private OrderRepository orderRepository;

    /**
     * Method under test: {@link EbookService#getEbookDtos()}
     */
    @Test
    void testGetEbookDtos() {
        when(ebookRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(ebookService.getEbookDtos().isEmpty());
        verify(ebookRepository).findAll();
    }

    /**
     * Method under test: {@link EbookService#getEbookDtos()}
     */
    @Test
    void testGetEbookDtos2() {
        Ebook ebook = new Ebook();
        ebook.setAuthors("JaneDoe");
        ebook.setAvailable(true);
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("Image Name");
        ebook.setPublisher("Publisher");
        ebook.setPurchaseCost(new BigDecimal("2.3"));
        ebook.setSellingPrice(new BigDecimal("2.3"));
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
    void testGetEbookDtos3() {
        Ebook ebook = new Ebook();
        ebook.setAuthors("JaneDoe");
        ebook.setAvailable(true);
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("Image Name");
        ebook.setPublisher("Publisher");
        ebook.setPurchaseCost(new BigDecimal("2.3"));
        ebook.setSellingPrice(new BigDecimal("2.3"));
        ebook.setTitle("Dr");

        Ebook ebook2 = new Ebook();
        ebook2.setAuthors("Authors");
        ebook2.setAvailable(false);
        ebook2.setDescription("Description");
        ebook2.setGenre(Genre.NON_FICTION);
        ebook2.setId(2L);
        ebook2.setImageName("42");
        ebook2.setPublisher("42");
        ebook2.setPurchaseCost(new BigDecimal("2.3"));
        ebook2.setSellingPrice(new BigDecimal("2.3"));
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
    void testGetEbookDtos4() {
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
        doNothing().when(ebook).setAvailable(anyBoolean());
        doNothing().when(ebook).setDescription(Mockito.<String>any());
        doNothing().when(ebook).setGenre(Mockito.<Genre>any());
        doNothing().when(ebook).setId(Mockito.<Long>any());
        doNothing().when(ebook).setImageName(Mockito.<String>any());
        doNothing().when(ebook).setPublisher(Mockito.<String>any());
        doNothing().when(ebook).setPurchaseCost(Mockito.<BigDecimal>any());
        doNothing().when(ebook).setSellingPrice(Mockito.<BigDecimal>any());
        doNothing().when(ebook).setTitle(Mockito.<String>any());
        ebook.setAuthors("JaneDoe");
        ebook.setAvailable(true);
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("Image Name");
        ebook.setPublisher("Publisher");
        ebook.setPurchaseCost(new BigDecimal("2.3"));
        ebook.setSellingPrice(new BigDecimal("2.3"));
        ebook.setTitle("Dr");

        ArrayList<Ebook> ebookList = new ArrayList<>();
        ebookList.add(ebook);
        when(ebookRepository.findAll()).thenReturn(ebookList);
        assertThrows(IllegalArgumentException.class, () -> ebookService.getEbookDtos());
        verify(ebookRepository).findAll();
        verify(ebook).getGenre();
        verify(ebook).getId();
        verify(ebook).getAuthors();
        verify(ebook).getDescription();
        verify(ebook).getImageName();
        verify(ebook).getPublisher();
        verify(ebook).getTitle();
        verify(ebook).getSellingPrice();
        verify(ebook).setAuthors(Mockito.<String>any());
        verify(ebook).setAvailable(anyBoolean());
        verify(ebook).setDescription(Mockito.<String>any());
        verify(ebook).setGenre(Mockito.<Genre>any());
        verify(ebook).setId(Mockito.<Long>any());
        verify(ebook).setImageName(Mockito.<String>any());
        verify(ebook).setPublisher(Mockito.<String>any());
        verify(ebook).setPurchaseCost(Mockito.<BigDecimal>any());
        verify(ebook).setSellingPrice(Mockito.<BigDecimal>any());
        verify(ebook).setTitle(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EbookService#getAvailableEbookDtos()}
     */
    @Test
    void testGetAvailableEbookDtos() {
        when(ebookRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(ebookService.getAvailableEbookDtos().isEmpty());
        verify(ebookRepository).findAll();
    }

    /**
     * Method under test: {@link EbookService#getAvailableEbookDtos()}
     */
    @Test
    void testGetAvailableEbookDtos2() {
        Ebook ebook = new Ebook();
        ebook.setAuthors("JaneDoe");
        ebook.setAvailable(true);
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("Image Name");
        ebook.setPublisher("Publisher");
        ebook.setPurchaseCost(new BigDecimal("2.3"));
        ebook.setSellingPrice(new BigDecimal("2.3"));
        ebook.setTitle("Dr");

        ArrayList<Ebook> ebookList = new ArrayList<>();
        ebookList.add(ebook);
        when(ebookRepository.findAll()).thenReturn(ebookList);
        assertEquals(1, ebookService.getAvailableEbookDtos().size());
        verify(ebookRepository).findAll();
    }

    /**
     * Method under test: {@link EbookService#getAvailableEbookDtos()}
     */
    @Test
    void testGetAvailableEbookDtos3() {
        Ebook ebook = new Ebook();
        ebook.setAuthors("JaneDoe");
        ebook.setAvailable(true);
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("Image Name");
        ebook.setPublisher("Publisher");
        ebook.setPurchaseCost(new BigDecimal("2.3"));
        ebook.setSellingPrice(new BigDecimal("2.3"));
        ebook.setTitle("Dr");

        Ebook ebook2 = new Ebook();
        ebook2.setAuthors("Authors");
        ebook2.setAvailable(false);
        ebook2.setDescription("Description");
        ebook2.setGenre(Genre.NON_FICTION);
        ebook2.setId(2L);
        ebook2.setImageName("42");
        ebook2.setPublisher("42");
        ebook2.setPurchaseCost(new BigDecimal("2.3"));
        ebook2.setSellingPrice(new BigDecimal("2.3"));
        ebook2.setTitle("Mr");

        ArrayList<Ebook> ebookList = new ArrayList<>();
        ebookList.add(ebook2);
        ebookList.add(ebook);
        when(ebookRepository.findAll()).thenReturn(ebookList);
        assertEquals(1, ebookService.getAvailableEbookDtos().size());
        verify(ebookRepository).findAll();
    }

    /**
     * Method under test: {@link EbookService#getAvailableEbookDtos()}
     */
    @Test
    void testGetAvailableEbookDtos4() {
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
        doNothing().when(ebook).setAvailable(anyBoolean());
        doNothing().when(ebook).setDescription(Mockito.<String>any());
        doNothing().when(ebook).setGenre(Mockito.<Genre>any());
        doNothing().when(ebook).setId(Mockito.<Long>any());
        doNothing().when(ebook).setImageName(Mockito.<String>any());
        doNothing().when(ebook).setPublisher(Mockito.<String>any());
        doNothing().when(ebook).setPurchaseCost(Mockito.<BigDecimal>any());
        doNothing().when(ebook).setSellingPrice(Mockito.<BigDecimal>any());
        doNothing().when(ebook).setTitle(Mockito.<String>any());
        ebook.setAuthors("JaneDoe");
        ebook.setAvailable(true);
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("Image Name");
        ebook.setPublisher("Publisher");
        ebook.setPurchaseCost(new BigDecimal("2.3"));
        ebook.setSellingPrice(new BigDecimal("2.3"));
        ebook.setTitle("Dr");

        ArrayList<Ebook> ebookList = new ArrayList<>();
        ebookList.add(ebook);
        when(ebookRepository.findAll()).thenReturn(ebookList);
        assertThrows(IllegalArgumentException.class, () -> ebookService.getAvailableEbookDtos());
        verify(ebookRepository).findAll();
        verify(ebook).getGenre();
        verify(ebook).getId();
        verify(ebook).getAuthors();
        verify(ebook).getDescription();
        verify(ebook).getImageName();
        verify(ebook).getPublisher();
        verify(ebook).getTitle();
        verify(ebook).getSellingPrice();
        verify(ebook).setAuthors(Mockito.<String>any());
        verify(ebook).setAvailable(anyBoolean());
        verify(ebook).setDescription(Mockito.<String>any());
        verify(ebook).setGenre(Mockito.<Genre>any());
        verify(ebook).setId(Mockito.<Long>any());
        verify(ebook).setImageName(Mockito.<String>any());
        verify(ebook).setPublisher(Mockito.<String>any());
        verify(ebook).setPurchaseCost(Mockito.<BigDecimal>any());
        verify(ebook).setSellingPrice(Mockito.<BigDecimal>any());
        verify(ebook).setTitle(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EbookService#getEbookDtoById(Long)}
     */
    @Test
    void testGetEbookDtoById() {
        Ebook ebook = new Ebook();
        ebook.setAuthors("JaneDoe");
        ebook.setAvailable(true);
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("Image Name");
        ebook.setPublisher("Publisher");
        ebook.setPurchaseCost(new BigDecimal("2.3"));
        ebook.setSellingPrice(new BigDecimal("2.3"));
        ebook.setTitle("Dr");
        Optional<Ebook> ofResult = Optional.of(ebook);
        when(ebookRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        EbookDto actualEbookDtoById = ebookService.getEbookDtoById(1L);
        assertEquals("JaneDoe", actualEbookDtoById.getAuthors());
        assertTrue(actualEbookDtoById.isAvailable());
        assertEquals("Dr", actualEbookDtoById.getTitle());
        BigDecimal expectedSellingPrice = new BigDecimal("2.3");
        assertEquals(expectedSellingPrice, actualEbookDtoById.getSellingPrice());
        BigDecimal expectedPurchaseCost = new BigDecimal("2.3");
        assertEquals(expectedPurchaseCost, actualEbookDtoById.getPurchaseCost());
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
    void testGetEbookDtoById2() {
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
        doNothing().when(ebook).setAvailable(anyBoolean());
        doNothing().when(ebook).setDescription(Mockito.<String>any());
        doNothing().when(ebook).setGenre(Mockito.<Genre>any());
        doNothing().when(ebook).setId(Mockito.<Long>any());
        doNothing().when(ebook).setImageName(Mockito.<String>any());
        doNothing().when(ebook).setPublisher(Mockito.<String>any());
        doNothing().when(ebook).setPurchaseCost(Mockito.<BigDecimal>any());
        doNothing().when(ebook).setSellingPrice(Mockito.<BigDecimal>any());
        doNothing().when(ebook).setTitle(Mockito.<String>any());
        ebook.setAuthors("JaneDoe");
        ebook.setAvailable(true);
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("Image Name");
        ebook.setPublisher("Publisher");
        ebook.setPurchaseCost(new BigDecimal("2.3"));
        ebook.setSellingPrice(new BigDecimal("2.3"));
        ebook.setTitle("Dr");
        Optional<Ebook> ofResult = Optional.of(ebook);
        when(ebookRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> ebookService.getEbookDtoById(1L));
        verify(ebookRepository).findById(Mockito.<Long>any());
        verify(ebook).getGenre();
        verify(ebook).getId();
        verify(ebook).getAuthors();
        verify(ebook).getDescription();
        verify(ebook).getImageName();
        verify(ebook).getPublisher();
        verify(ebook).getTitle();
        verify(ebook).getSellingPrice();
        verify(ebook).setAuthors(Mockito.<String>any());
        verify(ebook).setAvailable(anyBoolean());
        verify(ebook).setDescription(Mockito.<String>any());
        verify(ebook).setGenre(Mockito.<Genre>any());
        verify(ebook).setId(Mockito.<Long>any());
        verify(ebook).setImageName(Mockito.<String>any());
        verify(ebook).setPublisher(Mockito.<String>any());
        verify(ebook).setPurchaseCost(Mockito.<BigDecimal>any());
        verify(ebook).setSellingPrice(Mockito.<BigDecimal>any());
        verify(ebook).setTitle(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EbookService#getEbookDtoById(Long)}
     */
    @Test
    void testGetEbookDtoById3() {
        Optional<Ebook> emptyResult = Optional.empty();
        when(ebookRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        new IllegalArgumentException("foo");
        new IllegalArgumentException("foo");
        new IllegalArgumentException("foo");
        assertThrows(IllegalArgumentException.class, () -> ebookService.getEbookDtoById(1L));
        verify(ebookRepository).findById(Mockito.<Long>any());
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
            ebook.setAvailable(true);
            ebook.setDescription("The characteristics of someone or something");
            ebook.setGenre(Genre.FICTION);
            ebook.setId(1L);
            ebook.setImageName("Image Name");
            ebook.setPublisher("Publisher");
            ebook.setPurchaseCost(new BigDecimal("2.3"));
            ebook.setSellingPrice(new BigDecimal("2.3"));
            ebook.setTitle("Dr");
            when(ebookRepository.save(Mockito.<Ebook>any())).thenReturn(ebook);
            EbookDto ebookDto = new EbookDto();
            ebookService.addEbook(ebookDto,
                    new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
            mockFiles.verify(() -> Files.exists(Mockito.<Path>any(), isA(LinkOption[].class)));
            mockFiles.verify(() -> Files.copy(Mockito.<InputStream>any(), Mockito.<Path>any(), isA(CopyOption[].class)));
            verify(ebookRepository).save(Mockito.<Ebook>any());
            assertFalse(ebookDto.isAvailable());
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
            ebook.setAvailable(true);
            ebook.setDescription("The characteristics of someone or something");
            ebook.setGenre(Genre.FICTION);
            ebook.setId(1L);
            ebook.setImageName("Image Name");
            ebook.setPublisher("Publisher");
            ebook.setPurchaseCost(new BigDecimal("2.3"));
            ebook.setSellingPrice(new BigDecimal("2.3"));
            ebook.setTitle("Dr");
            when(ebookRepository.save(Mockito.<Ebook>any())).thenReturn(ebook);

            EbookDto ebookDto = new EbookDto();
            ebookDto.setPurchaseCost(new BigDecimal("2.3"));
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
    void testAddEbook3() throws IOException {
        try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
            mockFiles.when(() -> Files.exists(Mockito.<Path>any(), isA(LinkOption[].class))).thenReturn(false);
            mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
                    .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
            mockFiles.when(() -> Files.copy(Mockito.<InputStream>any(), Mockito.<Path>any(), isA(CopyOption[].class)))
                    .thenReturn(1L);

            Ebook ebook = new Ebook();
            ebook.setAuthors("JaneDoe");
            ebook.setAvailable(true);
            ebook.setDescription("The characteristics of someone or something");
            ebook.setGenre(Genre.FICTION);
            ebook.setId(1L);
            ebook.setImageName("Image Name");
            ebook.setPublisher("Publisher");
            ebook.setPurchaseCost(new BigDecimal("2.3"));
            ebook.setSellingPrice(new BigDecimal("2.3"));
            ebook.setTitle("Dr");
            when(ebookRepository.save(Mockito.<Ebook>any())).thenReturn(ebook);

            EbookDto ebookDto = new EbookDto();
            ebookDto.setPurchaseCost(null);
            ebookService.addEbook(ebookDto,
                    new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
            mockFiles.verify(() -> Files.exists(Mockito.<Path>any(), isA(LinkOption[].class)));
            mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
            mockFiles.verify(() -> Files.copy(Mockito.<InputStream>any(), Mockito.<Path>any(), isA(CopyOption[].class)));
            verify(ebookRepository).save(Mockito.<Ebook>any());
            assertFalse(ebookDto.isAvailable());
        }
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

    /**
     * Method under test: {@link EbookService#editEbook(EbookDto, Long)}
     */
    @Test
    void testEditEbook() {
        Optional<Ebook> emptyResult = Optional.empty();
        when(ebookRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(IllegalArgumentException.class, () -> ebookService.editEbook(new EbookDto(), 1L));
        verify(ebookRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EbookService#editEbookWithoutCover(EbookDto, Long)}
     */
    @Test
    void testEditEbookWithoutCover() {
        Optional<Ebook> emptyResult = Optional.empty();
        when(ebookRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(IllegalArgumentException.class, () -> ebookService.editEbookWithoutCover(new EbookDto(), 1L));
        verify(ebookRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EbookService#editEbookWithCover(EbookDto, Long, MultipartFile)}
     */
    @Test
    void testEditEbookWithCover() throws IOException {
        Optional<Ebook> emptyResult = Optional.empty();
        when(ebookRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        EbookDto ebookDto = new EbookDto();
        assertThrows(IllegalArgumentException.class, () -> ebookService.editEbookWithCover(ebookDto, 1L,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(ebookRepository).findById(Mockito.<Long>any());
    }
}

