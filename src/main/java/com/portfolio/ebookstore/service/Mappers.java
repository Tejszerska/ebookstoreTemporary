//package com.portfolio.ebookstore.service;
//
//import com.portfolio.ebookstore.entities.Ebook;
//import com.portfolio.ebookstore.model.dto.EbookDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Mappers {
//
//    public EbookDto ebookEntityToDto(Ebook ebook){
//        EbookDto ebookDto = new EbookDto();
//
//        ebookDto.builder()
//                .id(ebook.getId())
//                .title(ebook.getTitle())
//                .authors(ebook.getAuthors())
//                .publisher(ebook.getPublisher())
//                .imageName(ebook.getImageName())
//                .description(ebook.getDescription())
//                .genre(ebook.getGenre().toString())
//                .sellingPrice(ebook.getSellingPrice())
//                .purchaseCost(ebook.getPurchaseCost())
//                .isAvailable(ebook.isAvailable());
//        return ebookDto;
//    }
//
//}
