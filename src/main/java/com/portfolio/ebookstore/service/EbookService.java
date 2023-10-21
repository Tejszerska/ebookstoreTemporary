package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.repositories.EbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EbookService {
    private final EbookRepository ebookRepository;


    public List<EbookDto> getEbooks() {
        return ebookRepository.findAll().stream()
                .map(e -> new EbookDto(e.getId(), e.getTitle(), e.getAuthors(), e.getPublisher(), e.getImageName(), e.getDescription(), e.getGenre().toString(), e.getSellingPrice(), e.getPurchaseCost()))
                .toList();
    }
}
