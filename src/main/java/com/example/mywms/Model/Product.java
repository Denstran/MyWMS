package com.example.mywms.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product extends BaseEntity{

    private String productName;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private String productDescription;

    private int productPrice;

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
