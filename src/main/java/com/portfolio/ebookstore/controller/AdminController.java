package com.portfolio.ebookstore.controller;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.model.enums.Genre;
import com.portfolio.ebookstore.service.EbookService;
import com.portfolio.ebookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final EbookService ebookService;
    private final OrderService orderService;


    //Viewing orders
    @GetMapping
    @RequestMapping("/orders/{orderId}")
    public String purchaseDetails(Model model, @PathVariable Long orderId) {
        OrderDto orderById = orderService.getOrderById(orderId);
        model.addAttribute("orderById", orderById);
        List<Ebook> orderedEbooks = orderService.getEbooksFromPastOrders(orderById.getId());
        model.addAttribute("orderedEbooks", orderedEbooks);
        return "/admin/order-details";
    }

    @GetMapping
    @RequestMapping("/orders")
    public String getOrdersList(Model model) {
        List<OrderDto> orders = orderService.getOrderDtos();
        model.addAttribute("orders", orders);
        return "/admin/orders";
    }

    //Viewing list of ebooks with edit and delete
    @GetMapping
    @RequestMapping("/ebooks")
    public String getEbooksList(Model model) {
        List<EbookDto> ebooksDtos = ebookService.getEbookDtos();
        model.addAttribute("ebooksDtos", ebooksDtos);
        return "/admin/ebooks";
    }

    @PostMapping
    @RequestMapping("/ebooks/edit/{ebookId}")
    public String editEbook(EbookDto ebookDto, @RequestParam("cover") MultipartFile file, @PathVariable Long ebookId) {

        if(file.isEmpty()){
            ebookService.editEbookWithoutCover(ebookDto, ebookId);
        } else {
            ebookDto.setImageName(file.getOriginalFilename());
            ebookService.editEbookWithCover(ebookDto, ebookId, file);
        }
        return "redirect:/admin/ebooks";
    }

    @GetMapping
    @RequestMapping("/ebooks/edit-view/{ebookId}")
    public String getEditEbookView(Model model, @PathVariable Long ebookId) {
        EbookDto editedEbook = ebookService.getEbookDtoById(ebookId);
        model.addAttribute("editedEbook", editedEbook);
        List<String> genreList = Arrays.stream(Genre.values()).map(Enum::name).toList();
        model.addAttribute("genres", genreList);
        
        return "/admin/edit-ebook";
    }

    // Adding ebook
    @PostMapping
    @RequestMapping("/ebooks/add")
    public String addEbook(EbookDto ebookDto, @RequestParam("image") MultipartFile file) {
        ebookDto.setImageName(file.getOriginalFilename());
        ebookService.addEbook(ebookDto, file);
        return "redirect:/admin/ebooks/add-view";
    }

    @GetMapping
    @RequestMapping("/ebooks/add-view")
    public String getAddEbookView(Model model) {
        model.addAttribute("newEbook", new EbookDto());
        List<String> genreList = Arrays.stream(Genre.values()).map(Enum::name).toList();
        model.addAttribute("genres", genreList);
        return "admin/add-ebook";
    }

    // Main page
    @GetMapping
    public String getAdminPanel() {
        return "admin/admin";
    }
}
