package de.coffee.application.dao;


import de.coffee.application.dao.services.CoffeeInShopService;
import de.coffee.application.exceptions.ItemAlreadyExistsException;
import de.coffee.domain.models.CoffeeInShop;
import de.coffee.persistence.CoffeeInShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CoffeeInShopDAO {

    @Autowired
    private CoffeeInShopRepository coffeeInShopRepository;

    @Autowired
    private CoffeeInShopService coffeeInShopService;

    public CoffeeInShop save(CoffeeInShop cis) throws ItemAlreadyExistsException {

        if(coffeeInShopService.alreadySaved(cis))
            throw new ItemAlreadyExistsException("Kombination existiert bereits!");

        coffeeInShopRepository.save(cis);

        return cis;

    }


    public List<CoffeeInShop> findAll() {
        return coffeeInShopRepository.findAll();
    }

    public BigDecimal getPrice(Long shopId, Long coffeeId){
        return coffeeInShopRepository.getPrice(shopId, coffeeId);
    }

    public List<Long> findAllCoffeeIdsByShopId(Long shopId) {

        return coffeeInShopRepository.findAllCoffeeIdByShopId(shopId);

    }
}
