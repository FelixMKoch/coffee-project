package de.coffee.domain.models;


import de.coffee.domain.models.pks.CoffeeInShopPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CoffeeInShopPK.class)
@Table(name="coffee_in_shop")
public class CoffeeInShop {

    @Id
    @Column(name="coffee_id")
    private Long coffeeId;

    @Id
    @Column(name="shop_id")
    private Long shopId;

    @Column(name="price", nullable = true)
    private BigDecimal price;

}
