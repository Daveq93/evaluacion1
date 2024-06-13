package com.uce.edu.evaluacion1.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 16)
    private String isbn;

    @Column(length = 128)
    private String title;

    @Column(length = 64)
    private String author;

    @Column
    @Digits(integer=6,fraction=2)
    private BigDecimal price;
}
