package de.coffee.application.dao;

import de.coffee.application.exceptions.ItemAlreadyExistsException;
import de.coffee.domain.models.Brand;
import de.coffee.persistence.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandDAO {

    @Autowired
    private BrandRepository brandRepository;


    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public Brand save(Brand brand) throws ItemAlreadyExistsException {

        if(brand == null) return brand;

        if(brandRepository.existsById(brand.getName())) {
            throw new ItemAlreadyExistsException("Marke '" + brand.getName() + "' existiert bereits");
        }

        return brandRepository.save(brand);

    }

}
