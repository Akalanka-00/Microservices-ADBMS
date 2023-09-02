package com.microservice.inventory_management.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private long unitPrice;
    private int quantity;
    @Temporal(TemporalType.DATE)
    private Date date;


}
