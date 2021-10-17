package de.coffee.domain.models.dtos.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeInShopFrontendDTO {

    private String shopName;

    private BigDecimal price;

    private String shopPlace;

    private String coffeeName;

    private String coffeeBrand;

    private String coffeeType;

    private String coffeeFlavour;

}
