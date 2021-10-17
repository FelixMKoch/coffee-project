package de.coffee.application.dao;


import de.coffee.application.dao.services.ShopService;
import de.coffee.application.exceptions.ItemAlreadyExistsException;
import de.coffee.domain.models.Shop;
import de.coffee.domain.models.dtos.ShopDTO;
import de.coffee.persistence.ShopRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopDAO {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopService shopService;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<Shop> getShopList() {
        return shopRepository.findAll();
    }

    public Shop save(ShopDTO shopdto) throws ItemAlreadyExistsException {

        Shop shop = modelMapper.map(shopdto, Shop.class);

        if(shopService.alreadySaved(shop))
            throw new ItemAlreadyExistsException("This shop already exists");

        shopRepository.save(shop);

        return shop;

    }

    public Map<Long, String> shopIdToStringMap() {

        Map<Long, String> result = new HashMap<>();

        shopRepository.findAll().forEach(shop ->
                result.put(shop.getShopId(), shop.getName() + " - " + shop.getPlace()));

        return result;

    }

    public Shop getById(Long shopId) {
        return shopRepository.getById(shopId);
    }
}
