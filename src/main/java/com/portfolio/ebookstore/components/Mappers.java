package com.portfolio.ebookstore.components;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.model.Address;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.model.enums.Genre;
import com.portfolio.ebookstore.model.enums.Role;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Mappers {
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
private Set<User> mappedUsers = new HashSet<>();


    public UserDto mapUserToDto(User user) {

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getAddress().getName())
                .surname(user.getAddress().getSurname())
                .city(user.getAddress().getCity())
                .street(user.getAddress().getStreet())
                .zipCode(user.getAddress().getZipCode())
                .role(user.getRole().toString())
                .pastPurchases(user.getPastPurchases().stream().map(this::mapOrderToDto).collect(Collectors.toList()))
                .build();
    }
    public User mapDtoToUser(UserDto userDto) {
        return User.builder().email(userDto.getEmail())
                .password(userDto.getPassword())
                .address(new Address(userDto.getName(), userDto.getCity(), userDto.getSurname(), userDto.getStreet(), userDto.getZipCode()))
                .role(Role.valueOf(userDto.getRole()))
                .pastPurchases(userDto.getPastPurchases().stream().map(this::mapDtoToOrder).collect(Collectors.toList()))
                .build();
    }

    public OrderDto mapOrderToDto(Order order){
       return OrderDto.builder().id(order
                .getId())
                .userDto(mapUserToDto(order.getUser()))
                .totalCost(order.getTotalCost())
                .orderTime(order.getOrderTime().toString())
                .ebooks(order.getEbooks().stream().map(this::mapEbookToDto).collect(Collectors.toList()))
                .build();
    }
    private Order mapDtoToOrder(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .user(mapDtoToUser(orderDto.getUserDto()))
                .totalCost(orderDto.getTotalCost())
                .orderTime(LocalDateTime.parse(orderDto.getOrderTime()))
                .ebooks(orderDto.getEbooks().stream().map(this::mapDtoToEbook).collect(Collectors.toList()))
                .build();
    }
    public EbookDto mapEbookToDto(Ebook ebook) {
        return EbookDto.builder()
                .id(ebook.getId())
                .title(ebook.getTitle())
                .authors(ebook.getAuthors())
                .publisher(ebook.getPublisher())
                .imageName(ebook.getImageName())
                .description(ebook.getDescription())
                .genre(ebook.getGenre().toString())
                .sellingPrice(ebook.getSellingPrice())
                .purchaseCost(ebook.getPurchaseCost())
                .isAvailable(ebook.isAvailable())
                .build();
    }
public Ebook mapDtoToEbook(EbookDto ebookDto){
return Ebook.builder()
        .id(ebookDto.getId())
        .title(ebookDto.getTitle())
        .authors(ebookDto.getAuthors())
        .publisher(ebookDto.getPublisher())
        .imageName(ebookDto.getImageName())
        .description(ebookDto.getDescription())
        .genre(Genre.valueOf(ebookDto.getGenre()))
        .sellingPrice(ebookDto.getSellingPrice())
        .purchaseCost(ebookDto.getPurchaseCost())
        .isAvailable(ebookDto.isAvailable())
        .build();
}

}

