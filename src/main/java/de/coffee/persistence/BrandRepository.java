package de.coffee.persistence;

import de.coffee.domain.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
    List<Brand> findAll();
}
