package com.tsanta.station.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor

public class Product{
    private String id_product;
    private String name;
    private float price;

}
