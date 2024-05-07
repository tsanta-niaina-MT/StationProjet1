package com.tsanta.station.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class Type_movement {
    private String id_type;
    private boolean between;
    private boolean exit;
}
