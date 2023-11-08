package com.portfolio.ebookstore.entities;

import com.portfolio.ebookstore.model.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Ebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String authors;
    @NotBlank
    private String publisher;
    @NotBlank
    private String imageName;
    @NotBlank
    private String description;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @NonNull
    private BigDecimal sellingPrice;
    @NonNull
    private BigDecimal purchaseCost;
    @NonNull
    private boolean isAvailable;


}
