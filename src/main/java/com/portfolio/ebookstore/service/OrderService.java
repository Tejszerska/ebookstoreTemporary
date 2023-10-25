package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderDto> getOrderDtos() {
        return orderRepository.findAll().stream()
        .map(e -> new OrderDto(e.getId(), e.getUser(), e.getTotalCost(), e.getOrderTime(), e.getEbooks()))
                .toList();
    }
}
