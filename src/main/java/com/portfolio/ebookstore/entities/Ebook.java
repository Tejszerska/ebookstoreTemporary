package com.portfolio.ebookstore.entities;

import com.portfolio.ebookstore.model.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NotBlank
    private String title;
//    @NotBlank
    private String authors;
    private String publisher;
    private String imageName;
    private String description;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private Float sellingPrice;
//    @NotBlank
    private Float purchaseCost;
}
