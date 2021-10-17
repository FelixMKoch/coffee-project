package de.coffee.persistence;

import de.coffee.domain.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT rating FROM Review", nativeQuery = true)
    List<Integer> getRatingList();

    @Query(value = "SELECT SUM(cis.price) FROM Review r INNER JOIN coffee_in_shop cis ON " +
            "r.shop_id = cis.shop_id AND r.coffee_id = cis.coffee_id", nativeQuery = true)
    BigDecimal getSumPrices();

    @Query(value = "SELECT COUNT(*) FROM Review", nativeQuery = true)
    Integer countReviews();


    @Query(value = "SELECT SUM(c.size) FROM Review r INNER JOIN coffee c ON r.coffee_id = c.id", nativeQuery = true)
    Long getSumLiterInMl();

    @Query(value = "SELECT * FROM Review r WHERE r.coffee_id = :id", nativeQuery = true)
    List<Review> findAllByCoffeeId(@Param("id") Long id);
}
