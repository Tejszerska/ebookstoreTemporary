package com.portfolio.ebookstore.model.dto;

import com.portfolio.ebookstore.model.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class EbookDto {
    private Long id;
    private String title;
    private String authors;
    private String publisher;
    private String imageName;
    private String description;
    private String genre;
    private Float sellingPrice;
    private Float purchaseCost;
}
