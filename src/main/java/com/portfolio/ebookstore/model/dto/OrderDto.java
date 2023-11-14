package com.portfolio.ebookstore.model.dto;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private String userEmail;
    private BigDecimal totalCost;
    private String orderTime;
    private List<EbookDto> ebooks;

    @Override
    public String toString() {
        return "PurchaseDto{" +
                "id=" + id +
                ", customer=" + userEmail +
                ", totalCost=" + totalCost +
                ", orderDate=" + orderTime +
                ", purchasedEbooks=" + ebooks +
                '}';
    }
}
