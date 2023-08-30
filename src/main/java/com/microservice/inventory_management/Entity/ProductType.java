package com.microservice.inventory_management.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "ProductTypes")
public class ProductType {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private int quantity;

}
