package com.example.mywms.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "delivery")
@Getter
@Setter
public class Delivery extends BaseEntity {

    @NotNull
    private String address;

    private Date deliveryDate;

    @Positive(message = "Количесвто товаро не может быть меньше 0 или равным 0")
    private int productsAmountInDelivery;

    @Transient
    @ManyToMany(mappedBy = "deliveriesProductIn")
    private Set<Product> productsForDelivery = new HashSet<>();

    private boolean isFinished;
}
