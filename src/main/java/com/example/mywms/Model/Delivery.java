package com.example.mywms.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "delivery")
@Getter
@Setter
public class Delivery extends BaseEntity {

    private String address;

    private Date deliveryDate;

    private int productsAmountInDelivery;

    @Transient
    @ManyToMany(mappedBy = "deliveriesProductIn")
    private Set<Product> productsForDelivery = new HashSet<>();

    private boolean isFinished;
}
