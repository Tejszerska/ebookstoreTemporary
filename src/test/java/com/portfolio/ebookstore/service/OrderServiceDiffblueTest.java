package com.portfolio.ebookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderService.class})
@ExtendWith(SpringExtension.class)
class OrderServiceDiffblueTest {
    @MockBean
    private EbookRepository ebookRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @MockBean
    private ShoppingCart shoppingCart;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link OrderService#getOrderDtos()}
     */
    @Test
    void testGetOrderDtos() {
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(orderService.getOrderDtos().isEmpty());
        verify(orderRepository).findAll();
    }

    /**
     * Method under test: {@link OrderService#getOrderDtos()}
     */
    @Test
    void testGetOrderDtos2() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setName("Name");
        address.setStreet("Street");
        address.setSurname("Doe");
        address.setZipCode("21654");

        User user = new User();
        user.setAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setPastPurchases(new ArrayList<>());
        user.setRole(Role.ADMIN);

        Order order = new Order();
        order.setEbooks(new ArrayList<>());
        order.setId(1L);
        order.setOrderTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setTotalCost(new BigDecimal("2.3"));
        order.setUser(user);

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order);
        when(orderRepository.findAll()).thenReturn(orderList);
        List<OrderDto> actualOrderDtos = orderService.getOrderDtos();
        assertEquals(1, actualOrderDtos.size());
        OrderDto getResult = actualOrderDtos.get(0);
        assertTrue(getResult.getEbooks().isEmpty());
        assertSame(user, getResult.getUser());
        BigDecimal expectedTotalCost = new BigDecimal("2.3");
        assertEquals(expectedTotalCost, getResult.getTotalCost());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals("1970-01-01 00:00:00", getResult.getOrderTime());
        verify(orderRepository).findAll();
    }

    /**
     * Method under test: {@link OrderService#getOrderDtos()}
     */
    @Test
    void testGetOrderDtos3() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setName("Name");
        address.setStreet("Street");
        address.setSurname("Doe");
        address.setZipCode("21654");

        User user = new User();
        user.setAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setPastPurchases(new ArrayList<>());
        user.setRole(Role.ADMIN);

        Order order = new Order();
        order.setEbooks(new ArrayList<>());
        order.setId(1L);
        order.setOrderTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setTotalCost(new BigDecimal("2.3"));
        order.setUser(user);

        Address address2 = new Address();
        address2.setCity("London");
        address2.setName("Name");
        address2.setStreet("Street");
        address2.setSurname("yyyy-MM-dd HH:mm:ss");
        address2.setZipCode("OX1 1PT");

        User user2 = new User();
        user2.setAddress(address2);
        user2.setEmail("john.smith@example.org");
        user2.setId(2L);
        user2.setPassword("yyyy-MM-dd HH:mm:ss");
        user2.setPastPurchases(new ArrayList<>());
        user2.setRole(Role.USER);

        Order order2 = new Order();
        order2.setEbooks(new ArrayList<>());
        order2.setId(2L);
        order2.setOrderTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        order2.setTotalCost(new BigDecimal("2.3"));
        order2.setUser(user2);

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order2);
        orderList.add(order);
        when(orderRepository.findAll()).thenReturn(orderList);
        List<OrderDto> actualOrderDtos = orderService.getOrderDtos();
        assertEquals(2, actualOrderDtos.size());
        OrderDto getResult = actualOrderDtos.get(0);
        assertSame(user2, getResult.getUser());
        OrderDto getResult2 = actualOrderDtos.get(1);
        assertSame(user, getResult2.getUser());
        List<Ebook> ebooks = getResult.getEbooks();
        assertTrue(ebooks.isEmpty());
        assertEquals("1970-01-01 00:00:00", getResult.getOrderTime());
        assertEquals("1970-01-01 00:00:00", getResult2.getOrderTime());
        BigDecimal expectedTotalCost = new BigDecimal("2.3");
        assertEquals(expectedTotalCost, getResult2.getTotalCost());
        BigDecimal expectedTotalCost2 = new BigDecimal("2.3");
        assertEquals(expectedTotalCost2, getResult.getTotalCost());
        assertEquals(2L, getResult.getId().longValue());
        assertEquals(ebooks, getResult2.getEbooks());
        assertEquals(1L, getResult2.getId().longValue());
        verify(orderRepository).findAll();
    }

    /**
     * Method under test: {@link OrderService#getEbooksFromPastOrders(Long)}
     */
    @Test
    void testGetEbooksFromPastOrders() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setName("Name");
        address.setStreet("Street");
        address.setSurname("Doe");
        address.setZipCode("21654");

        User user = new User();
        user.setAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setPastPurchases(new ArrayList<>());
        user.setRole(Role.ADMIN);

        Order order = new Order();
        order.setEbooks(new ArrayList<>());
        order.setId(1L);
        order.setOrderTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setTotalCost(new BigDecimal("2.3"));
        order.setUser(user);
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertTrue(orderService.getEbooksFromPastOrders(1L).isEmpty());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OrderService#placeOrder(UserOrderDto)}
     */
    @Test
    void testPlaceOrder() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setName("Name");
        address.setStreet("Street");
        address.setSurname("Doe");
        address.setZipCode("21654");

        User user = new User();
        user.setAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setPastPurchases(new ArrayList<>());
        user.setRole(Role.ADMIN);

        Order order = new Order();
        order.setEbooks(new ArrayList<>());
        order.setId(1L);
        order.setOrderTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setTotalCost(new BigDecimal("2.3"));
        order.setUser(user);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order);

        Address address2 = new Address();
        address2.setCity("Oxford");
        address2.setName("Name");
        address2.setStreet("Street");
        address2.setSurname("Doe");
        address2.setZipCode("21654");

        User user2 = new User();
        user2.setAddress(address2);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setPassword("iloveyou");
        user2.setPastPurchases(new ArrayList<>());
        user2.setRole(Role.ADMIN);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(shoppingCart.getTotalCost()).thenReturn(new BigDecimal("2.3"));
        when(shoppingCart.getCartItems()).thenReturn(new ArrayList<>());
        orderService.placeOrder(new UserOrderDto());
        verify(orderRepository).save(Mockito.<Order>any());
        verify(userRepository).save(Mockito.<User>any());
        verify(shoppingCart).getTotalCost();
        verify(shoppingCart).getCartItems();
    }

    /**
     * Method under test: {@link OrderService#getOrderById(Long)}
     */
    @Test
    void testGetOrderById() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setName("Name");
        address.setStreet("Street");
        address.setSurname("Doe");
        address.setZipCode("21654");

        User user = new User();
        user.setAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setPastPurchases(new ArrayList<>());
        user.setRole(Role.ADMIN);

        Order order = new Order();
        order.setEbooks(new ArrayList<>());
        order.setId(1L);
        order.setOrderTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setTotalCost(new BigDecimal("2.3"));
        order.setUser(user);
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        OrderDto actualOrderById = orderService.getOrderById(1L);
        assertTrue(actualOrderById.getEbooks().isEmpty());
        assertSame(user, actualOrderById.getUser());
        BigDecimal expectedTotalCost = new BigDecimal("2.3");
        assertEquals(expectedTotalCost, actualOrderById.getTotalCost());
        assertEquals(1L, actualOrderById.getId().longValue());
        assertEquals("1970-01-01T00:00", actualOrderById.getOrderTime());
        verify(orderRepository).findById(Mockito.<Long>any());
    }
}

