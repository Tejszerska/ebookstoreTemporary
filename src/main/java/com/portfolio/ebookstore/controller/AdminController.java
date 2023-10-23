package com.portfolio.ebookstore.controller;

import com.portfolio.ebookstore.service.EbookService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.portfolio.ebookstore.model.enums.Genre;
import com.portfolio.ebookstore.model.dto.EbookDto;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final EbookService ebookService;

    @PostMapping
    @RequestMapping("/add")
    public String addEbook(EbookDto ebookDto, @RequestParam("image") MultipartFile file) {
        ebookDto.setImageName(file.getOriginalFilename());
        ebookService.addEbook(ebookDto, file);
        return "redirect:/admin/add-view";
    }

    @GetMapping
    @RequestMapping("/add-view")
    public String getAddEbookView(Model model) {
        model.addAttribute("newEbook", new EbookDto());
        List<String> genreList = Arrays.stream(Genre.values()).map(Enum::name).toList();
        model.addAttribute("genres", genreList);
        return "admin/add-ebook";
    }
    @GetMapping
    public String getAdminPanel(){ return "admin/admin";}
}
