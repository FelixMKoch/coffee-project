package de.coffee.domain.models.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeDTO {

    private String name;

    private String brand;

    private String type;

    private String flavour;

    private Integer size;

    private boolean plastic;

}
