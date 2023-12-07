package com.portfolio.ebookstore.components;

import com.portfolio.ebookstore.entities.Authority;
import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.model.Address;
import com.portfolio.ebookstore.model.dto.AuthorityDto;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.model.enums.Genre;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mappers {

    public UserDto userEntityToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .authorities(authorityListEntityToDto(user.getAuthorities()))
                .accountNonExpired(user.getAccountNonExpired())
                .accountNonLocked(user.getAccountNonLocked())
                .credentialsNonExpired(user.getCredentialsNonExpired())
                .enabled(user.getEnabled())
                .name(user.getAddress().getName())
                .surname(user.getAddress().getSurname())
                .city(user.getAddress().getCity())
                .street(user.getAddress().getStreet())
                .zipCode(user.getAddress().getZipCode())
                .pastPurchases(user.getPastPurchases().stream().map(this::mapOrderToDto).collect(Collectors.toList()))
                .build();
    }

    public List<UserDto> userListEntityToDto(List<User> users) {
        return users.stream()
                .map(this::userEntityToDto)
                .toList();
    }


    public User userDtoToEntity(UserDto userDto, String password) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(password)
                .authorities(authorityListDtoToEntity(userDto.getAuthorities()))
                .accountNonExpired(userDto.getAccountNonExpired())
                .accountNonLocked(userDto.getAccountNonLocked())
                .credentialsNonExpired(userDto.getCredentialsNonExpired())
                .enabled(userDto.getEnabled())
                .address(new Address(userDto.getName(), userDto.getSurname(), userDto.getStreet(), userDto.getZipCode(), userDto.getCity()))
                .pastPurchases(userDto.getPastPurchases().stream().map(this::mapDtoToOrderWithoutUser).collect(Collectors.toList()))
                .build();
    }

    public List<User> userListDtoToEntity(List<UserDto> users, String password) {
        return users.stream()
                .map(user -> userDtoToEntity(user, password))
                .toList();
    }

    public AuthorityDto authorityEntityToDto(Authority authority) {
        return AuthorityDto.builder()
                .id(authority.getId())
                .authority(authority.getAuthority())
                .build();
    }

    public List<AuthorityDto> authorityListEntityToDto(List<Authority> authorities) {
        return authorities.stream()
                .map(this::authorityEntityToDto)
                .toList();
    }

    public Authority authorityDtoToEntity(AuthorityDto authority) {
        return Authority.builder()
                .id(authority.getId())
                .authority(authority.getAuthority())
                .build();
    }

    public List<Authority> authorityListDtoToEntity(List<AuthorityDto> authorities) {
        return authorities.stream()
                .map(this::authorityDtoToEntity)
                .toList();
    }

    public OrderDto mapOrderToDto(Order order) {
        return OrderDto.builder().id(order
                        .getId())
                .userEmail(order.getUser().getUsername())
                .totalCost(order.getTotalCost())
                .orderTime(order.getOrderTime().toString())
                .ebooks(order.getEbooks().stream().map(this::mapEbookToDto).collect(Collectors.toList()))
                .build();
    }

    private Order mapDtoToOrderWithoutUser(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
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

    public Ebook mapDtoToEbook(EbookDto ebookDto) {
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

