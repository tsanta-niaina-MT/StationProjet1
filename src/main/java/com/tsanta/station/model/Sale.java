package com.tsanta.station.model;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class Sale {
    private String id_sale;
    private Instant date_sale;
    private Float quantity_to_sell;
    private boolean in_litre;
}
