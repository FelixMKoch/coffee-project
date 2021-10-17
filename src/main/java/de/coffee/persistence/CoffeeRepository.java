package de.coffee.persistence;


import de.coffee.domain.models.Coffee;
import de.coffee.domain.models.projections.CoffeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

    @Query(value = "SELECT name, brand, type, flavour FROM Coffee" )
    List<CoffeeProjection> findAllProjections();
}
