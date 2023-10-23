package com.portfolio.ebookstore.controller;

import com.portfolio.ebookstore.entities.Ebook;
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
    public String ebook(Model model){
        List<EbookDto> ebooksFromDb = ebookService.getEbooks();
        model.addAttribute("ebooks", ebooksFromDb);
        model.addAttribute("newEbook", new Ebook());
//        ODKOMENTOWAĆ PO IMPLEMENTACJI WÓZKA
//        int cartSize = shoppingCart.getCartS+ize();
//        model.addAttribute("cartSize", cartSize);
        return "main/ebookstore";
    }

    @RequestMapping("/details/{ebookId}")
    public String ebookDetails (Model model, @PathVariable Long ebookId){
        EbookDto ebookById = ebookService.getEbookById(ebookId);
        model.addAttribute("ebookById", ebookById);
//        int cartSize = shoppingCart.getCartSize();
//        model.addAttribute("cartSize", cartSize);
        return "main/ebook-details";
    }
}
