package com.portfolio.ebookstore.controller;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.service.EbookService;
import com.portfolio.ebookstore.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    private final OrderService orderService;
    @GetMapping
    @RequestMapping("/orders/{orderId}")
    public String purchaseDetails(Model model, @PathVariable Long orderId){
        OrderDto orderById = orderService.getOrderDtos().get(Math.toIntExact(orderId));
        model.addAttribute("orderById", orderById);
        List<Ebook> orderedEbooks = orderService.getEbooksFromPastOrders(orderById.getId());
        model.addAttribute("orderedEbooks", orderedEbooks);
        return "/admin/order-details";
    }

    @GetMapping
    @RequestMapping("/orders")
    public String getOrdersList(Model model){
        List<OrderDto> orders = orderService.getOrderDtos();
        model.addAttribute("orders", orders);
        return "/admin/orders";
    }
    // Adding ebook
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

    // Main page
    @GetMapping
    public String getAdminPanel(){ return "admin/admin";}
}
