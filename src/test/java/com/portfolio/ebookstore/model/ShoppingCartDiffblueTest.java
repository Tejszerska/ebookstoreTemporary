package com.portfolio.ebookstore.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.model.dto.CartItemDto;
import com.portfolio.ebookstore.model.dto.EbookDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ShoppingCartDiffblueTest {
    /**
     * Method under test: {@link ShoppingCart#addItem(EbookDto)}
     */
    @Test
    void testAddItem() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.portfolio.ebookstore.components.ShoppingCart
        //   See https://diff.blue/R027 to resolve this issue.

        ShoppingCart shoppingCart = new ShoppingCart();
        EbookDto.EbookDtoBuilder publisherResult = EbookDto.builder()
                .authors("JaneDoe")
                .description("The characteristics of someone or something")
                .genre("Genre")
                .id(1L)
                .imageName("Image Name")
                .publisher("Publisher");
        EbookDto.EbookDtoBuilder purchaseCostResult = publisherResult.purchaseCost(new BigDecimal("2.3"));
        BigDecimal sellingPrice = new BigDecimal("2.3");
        EbookDto ebookDto = purchaseCostResult.sellingPrice(sellingPrice).title("Dr").build();
        shoppingCart.addItem(ebookDto);
        List<CartItemDto> cartItems = shoppingCart.getCartItems();
        assertEquals(1, cartItems.size());
        BigDecimal expectedTotalCost = new BigDecimal("2.3");
        assertEquals(expectedTotalCost, shoppingCart.getTotalCost());
        CartItemDto getResult = cartItems.get(0);
        assertSame(ebookDto, getResult.getEbookDto());
        assertSame(sellingPrice, getResult.getTotalCost());
        assertEquals(1, getResult.getQuantity());
    }

    /**
     * Method under test: {@link ShoppingCart#addItem(EbookDto)}
     */
    @Test
    void testAddItem2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.portfolio.ebookstore.components.ShoppingCart
        //   See https://diff.blue/R027 to resolve this issue.

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartItems(null);
        shoppingCart.setTotalCost(new BigDecimal("2.3"));
        EbookDto.EbookDtoBuilder publisherResult = EbookDto.builder()
                .authors("JaneDoe")
                .description("The characteristics of someone or something")
                .genre("Genre")
                .id(1L)
                .imageName("Image Name")
                .publisher("Publisher");
        EbookDto.EbookDtoBuilder purchaseCostResult = publisherResult.purchaseCost(new BigDecimal("2.3"));
        BigDecimal sellingPrice = new BigDecimal("2.3");
        EbookDto ebookDto = purchaseCostResult.sellingPrice(sellingPrice).title("Dr").build();
        shoppingCart.addItem(ebookDto);
        List<CartItemDto> cartItems = shoppingCart.getCartItems();
        assertEquals(1, cartItems.size());
        BigDecimal expectedTotalCost = new BigDecimal("4.6");
        assertEquals(expectedTotalCost, shoppingCart.getTotalCost());
        CartItemDto getResult = cartItems.get(0);
        assertSame(ebookDto, getResult.getEbookDto());
        assertSame(sellingPrice, getResult.getTotalCost());
        assertEquals(1, getResult.getQuantity());
    }

    /**
     * Method under test: {@link ShoppingCart#addItem(EbookDto)}
     */
    @Test
    void testAddItem3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.portfolio.ebookstore.components.ShoppingCart
        //   See https://diff.blue/R027 to resolve this issue.

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartItems(new ArrayList<>());
        shoppingCart.setTotalCost(null);
        EbookDto.EbookDtoBuilder publisherResult = EbookDto.builder()
                .authors("JaneDoe")
                .description("The characteristics of someone or something")
                .genre("Genre")
                .id(1L)
                .imageName("Image Name")
                .publisher("Publisher");
        EbookDto.EbookDtoBuilder purchaseCostResult = publisherResult.purchaseCost(new BigDecimal("2.3"));
        BigDecimal sellingPrice = new BigDecimal("2.3");
        EbookDto ebookDto = purchaseCostResult.sellingPrice(sellingPrice).title("Dr").build();
        shoppingCart.addItem(ebookDto);
        List<CartItemDto> cartItems = shoppingCart.getCartItems();
        assertEquals(1, cartItems.size());
        BigDecimal expectedTotalCost = new BigDecimal("2.3");
        assertEquals(expectedTotalCost, shoppingCart.getTotalCost());
        CartItemDto getResult = cartItems.get(0);
        assertSame(ebookDto, getResult.getEbookDto());
        assertSame(sellingPrice, getResult.getTotalCost());
        assertEquals(1, getResult.getQuantity());
    }

    /**
     * Method under test: {@link ShoppingCart#addItem(EbookDto)}
     */
    @Test
    void testAddItem4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.portfolio.ebookstore.components.ShoppingCart
        //   See https://diff.blue/R027 to resolve this issue.

        ShoppingCart shoppingCart = new ShoppingCart();
        EbookDto ebookDto = mock(EbookDto.class);
        when(ebookDto.getSellingPrice()).thenReturn(new BigDecimal("2.3"));
        shoppingCart.addItem(ebookDto);
        verify(ebookDto, atLeast(1)).getSellingPrice();
        List<CartItemDto> cartItems = shoppingCart.getCartItems();
        assertEquals(1, cartItems.size());
        BigDecimal expectedTotalCost = new BigDecimal("2.3");
        assertEquals(expectedTotalCost, shoppingCart.getTotalCost());
        CartItemDto getResult = cartItems.get(0);
        assertSame(ebookDto, getResult.getEbookDto());
        BigDecimal expectedTotalCost2 = new BigDecimal("2.3");
        assertEquals(expectedTotalCost2, getResult.getTotalCost());
        assertEquals(1, getResult.getQuantity());
    }

    /**
     * Method under test: {@link ShoppingCart#addItem(EbookDto)}
     */
    @Test
    void testAddItem5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.portfolio.ebookstore.components.ShoppingCart
        //   See https://diff.blue/R027 to resolve this issue.

        ShoppingCart shoppingCart = new ShoppingCart();
        EbookDto.EbookDtoBuilder publisherResult = EbookDto.builder()
                .authors("JaneDoe")
                .description("The characteristics of someone or something")
                .genre("Genre")
                .id(1L)
                .imageName("Image Name")
                .publisher("Publisher");
        EbookDto.EbookDtoBuilder purchaseCostResult = publisherResult.purchaseCost(new BigDecimal("2.3"));
        shoppingCart.addItem(purchaseCostResult.sellingPrice(new BigDecimal("2.3")).title("Dr").build());
        EbookDto ebookDto = mock(EbookDto.class);
        when(ebookDto.getId()).thenReturn(1L);
        when(ebookDto.getSellingPrice()).thenReturn(new BigDecimal("2.3"));
        shoppingCart.addItem(ebookDto);
        verify(ebookDto).getId();
        verify(ebookDto).getSellingPrice();
        BigDecimal expectedTotalCost = new BigDecimal("4.6");
        assertEquals(expectedTotalCost, shoppingCart.getTotalCost());
        BigDecimal expectedTotalCost2 = new BigDecimal("4.6");
        CartItemDto getResult = shoppingCart.getCartItems().get(0);
        assertEquals(expectedTotalCost2, getResult.getTotalCost());
        assertEquals(2, getResult.getQuantity());
    }

    /**
     * Method under test: {@link ShoppingCart#getCartSize()}
     */
    @Test
    void testGetCartSize() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.portfolio.ebookstore.components.ShoppingCart
        //   See https://diff.blue/R027 to resolve this issue.

        assertEquals(0, (new ShoppingCart()).getCartSize());
    }

    /**
     * Method under test: {@link ShoppingCart#getCartSize()}
     */
    @Test
    void testGetCartSize2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.portfolio.ebookstore.components.ShoppingCart
        //   See https://diff.blue/R027 to resolve this issue.

        ArrayList<CartItemDto> cartItems = new ArrayList<>();
        cartItems.add(new CartItemDto());

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartItems(cartItems);
        assertEquals(0, shoppingCart.getCartSize());
    }

    /**
     * Method under test: {@link ShoppingCart#getCartSize()}
     */
    @Test
    void testGetCartSize3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.portfolio.ebookstore.components.ShoppingCart
        //   See https://diff.blue/R027 to resolve this issue.

        CartItemDto cartItemDto = mock(CartItemDto.class);
        when(cartItemDto.getQuantity()).thenReturn(1);

        ArrayList<CartItemDto> cartItems = new ArrayList<>();
        cartItems.add(cartItemDto);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartItems(cartItems);
        assertEquals(1, shoppingCart.getCartSize());
        verify(cartItemDto).getQuantity();
    }
}

