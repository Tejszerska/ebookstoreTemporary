package com.portfolio.ebookstore.controller;

import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.service.EbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/ebookstore")
public class MainViewController {
    private final EbookService ebookService;

    @GetMapping
    public String ebook(Model model) {
        List<EbookDto> availableEbookDtosFromDb = ebookService.getAvailableEbookDtos();
        model.addAttribute("ebooks", availableEbookDtosFromDb);
//        model.addAttribute("newEbook", new Ebook());  <- a co to? :D
//        ODKOMENTOWAĆ PO IMPLEMENTACJI WÓZKA
//        int cartSize = shoppingCart.getCartS+ize();
//        model.addAttribute("cartSize", cartSize);
        return "main/ebookstore";
    }

    @RequestMapping("/details/{ebookId}")
    public String ebookDetails(Model model, @PathVariable Long ebookId) throws IllegalArgumentException {
        EbookDto ebookById = ebookService.getEbookDtoById(ebookId);
        model.addAttribute("ebookById", ebookById);
//        int cartSize = shoppingCart.getCartSize();
//        model.addAttribute("cartSize", cartSize);
        return "main/ebook-details";
    }
}
