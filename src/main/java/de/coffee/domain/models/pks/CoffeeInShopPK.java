package de.coffee.domain.models.pks;


import lombok.Data;

import java.io.Serializable;

/**
 * Primary Key for the CoffeeInShop-Entity
 */
@Data
public class CoffeeInShopPK implements Serializable {

    private Long shopId;

    private Long coffeeId;


}
