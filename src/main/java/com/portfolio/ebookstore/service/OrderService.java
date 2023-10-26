package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderDto> getOrderDtos() {
        return orderRepository.findAll().stream()
        .map(e -> new OrderDto(e.getId(), e.getUser(), e.getTotalCost(), e.getOrderTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), e.getEbooks()))
                .toList();
    }

    public List<Ebook> getEbooksFromPastOrders(Long id) {
        return orderRepository.findById(id).map(Order::getEbooks).get().stream().toList();
    }
}
