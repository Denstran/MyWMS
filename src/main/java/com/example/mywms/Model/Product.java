package com.example.mywms.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product extends BaseEntity{

    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 1, max = 40, message = "Название товара должно быть от 5 до 40 символов")
    private String productName;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 10, max = 255, message = "Описание товара должно быть от 10 до 255 символов")
    private String productDescription;

    @Positive(message = "Цена товара не может быть меньше 0 или быть равной 0")
    private double productPrice;

    @PositiveOrZero(message = "Количесвто товара не может быть меньше 0")
    private int productStock;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @ManyToMany
    @JoinTable(
        name = "product_delivery",
        joinColumns = {
                @JoinColumn(name = "product_id"),
        },
        inverseJoinColumns = @JoinColumn(name = "delivery_id")
    )
    private Set<Delivery> deliveriesProductIn = new HashSet<>();
}
