package com.portfolio.ebookstore.entities;

import com.portfolio.ebookstore.model.enums.Genre;
import jakarta.persistence.*;
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
    private String title;
    private String authors;
    private String publisher;
    private String imageName;
    private String description;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private Float sellingPrice;
    private Float purchaseCost;
}
