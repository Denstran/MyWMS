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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId().equals(product.getId()) && getProductName().equals(product.getProductName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getProductName().hashCode();
        result = (int) (prime * result + getId());
        return result;
    }
}
