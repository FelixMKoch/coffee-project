package de.coffee.persistence;


import de.coffee.domain.models.CoffeeInShop;
import de.coffee.domain.models.pks.CoffeeInShopPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CoffeeInShopRepository extends JpaRepository<CoffeeInShop, CoffeeInShopPK> {

    @Query(value = "SELECT price FROM coffee_in_shop WHERE shop_id = :shopId AND coffee_id = :coffeeId", nativeQuery = true)
    BigDecimal getPrice(@Param("shopId") Long shopId, @Param("coffeeId") Long coffeeId);

    @Query(value = "SELECT coffee_id FROM coffee_in_shop WHERE shop_id = :shopId", nativeQuery = true)
    List<Long> findAllCoffeeIdByShopId(@Param("shopId") Long shopId);
}
