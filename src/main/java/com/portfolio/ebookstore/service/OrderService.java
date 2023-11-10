package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.model.Address;
import com.portfolio.ebookstore.model.ShoppingCart;
import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.model.dto.UserOrderDto;
import com.portfolio.ebookstore.model.enums.Role;
import com.portfolio.ebookstore.repositories.EbookRepository;
import com.portfolio.ebookstore.repositories.OrderRepository;
import com.portfolio.ebookstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final EbookRepository ebookRepository;
    private final ShoppingCart shoppingCart;


    public List<OrderDto> getOrderDtos() {
        return orderRepository.findAll().stream()
                .map(e -> new OrderDto(e.getId(), e.getUser(), e.getTotalCost(), e.getOrderTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), e.getEbooks()))
                .collect(Collectors.toList());
    }

    public List<Ebook> getEbooksFromPastOrders(Long id) {
        return orderRepository.findById(id).map(Order::getEbooks).get().stream().toList();
    }

    // dodać przeładowaną metodę (orderdto, userdto)

    public void placeOrder(UserOrderDto userOrderDto) {
        User user = new User();
        user.setEmail(userOrderDto.getEmail());
        user.setRole(Role.GUEST);

        user.setAddress(new Address(
                userOrderDto.getName(), userOrderDto.getSurname(), userOrderDto.getStreet(), userOrderDto.getCity(), userOrderDto.getZipCode()
        ));

        userRepository.save(user);

        Order order = new Order();
        order.setUser(user);
        order.setTotalCost(shoppingCart.getTotalCost());
        order.setOrderTime(LocalDateTime.now());
        order.setEbooks(getEbooksFromCart(shoppingCart));

        orderRepository.save(order);
    }

    private List<Ebook> getEbooksFromCart(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems().stream().map(c -> ebookRepository.findById(c.getEbookDto().getId()).get()).collect(Collectors.toList());

    }

    public OrderDto getOrderById(Long orderId) {
      return  orderRepository.findById(orderId).map( o-> new OrderDto(o.getId(), o.getUser(), o.getTotalCost(), o.getOrderTime().toString(), o.getEbooks())).get();
    }
}
