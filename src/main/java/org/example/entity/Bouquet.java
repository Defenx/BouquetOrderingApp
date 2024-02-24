package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bouquet {
    private int id;
    private String name;
    private BigDecimal price;
    private String photoUrl;
    private String description;
}
