package com.example.mywms.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "t_delivery")
@Getter
@Setter
public class Delivery extends BaseEntity {

    private String address;
    private Date deliveryDate;

    @OneToMany(mappedBy = "delivery",
                cascade = CascadeType.ALL)
    private Set<Product> productsForDelivery;


    private boolean isFinished;
}
