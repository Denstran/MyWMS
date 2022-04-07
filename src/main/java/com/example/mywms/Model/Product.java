package com.example.mywms.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_product")
@Getter
@Setter
public class Product extends BaseEntity{

    private String productName;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private String productDescription;

    private int productPrice;

    private boolean isDelivered;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery productDelivery;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return productPrice == product.productPrice && productName.equals(product.productName) && productType == product.productType && productDescription.equals(product.productDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productType, productDescription, productPrice);
    }
}
