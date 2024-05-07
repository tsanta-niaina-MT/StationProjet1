package com.tsanta.station.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode

public class Stores {
    private String id_stores;
    private float quatity_remaining;
    private float initial_quantity;
    private float quantity_evaporated;
}
