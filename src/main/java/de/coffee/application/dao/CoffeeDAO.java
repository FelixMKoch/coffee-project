package de.coffee.application.dao;


import de.coffee.application.dao.services.CoffeeService;
import de.coffee.application.exceptions.ItemAlreadyExistsException;
import de.coffee.domain.models.Coffee;
import de.coffee.domain.models.dtos.CoffeeDTO;
import de.coffee.domain.models.projections.CoffeeProjection;
import de.coffee.persistence.CoffeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CoffeeDAO {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ModelMapper modelMapper = new ModelMapper();

    private final ModelMapper mapper = new ModelMapper();

    public Map<String, Integer> getBrandSumSizeMap() {
        Map<String, Integer> brandSizeSumMap = new HashMap<>();

        Map<String, CountItem> countItemMap = new HashMap<>();

        String query = "SELECT c.brand as cBrand, c.size as cSize" +
                " FROM coffee c INNER JOIN review r ON c.id = r.coffee_id";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);

        while(rowSet.next()) {
            String brand = rowSet.getString("cBrand");
            if(countItemMap.containsKey(brand))
                countItemMap.get(brand).add(rowSet.getInt("cSize"));
            else
                countItemMap.put(brand, new CountItem(rowSet.getInt("cSize")));
        }

        countItemMap.forEach((k, v) -> brandSizeSumMap.put(k, v.get()));

        return brandSizeSumMap;
    }

    public Coffee save(CoffeeDTO coffeeDto) throws ItemAlreadyExistsException {

        Coffee coffee = mapper.map(coffeeDto, Coffee.class);

        if(coffeeService.alreadySaved(coffee))
            throw new ItemAlreadyExistsException("Coffee already exists in the Database");

        coffeeRepository.save(coffee);

        return coffee;

    }


    public Coffee save(Coffee coffee) throws ItemAlreadyExistsException {

        if(coffeeService.alreadySaved(coffee))
            throw new ItemAlreadyExistsException("Coffee already exists in the Database");

        coffeeRepository.save(coffee);

        return coffee;

    }

    public List<Coffee> findAll() {

        return coffeeRepository.findAll();

    }

    public List<CoffeeProjection> getCoffeeProjectionList() {

        return coffeeRepository.findAllProjections();

    }

    public List<CoffeeDTO> getCoffeeDTOList() {

        return coffeeRepository.findAll().stream()
                .map(c -> modelMapper.map(c, CoffeeDTO.class))
                .collect(Collectors.toList());

    }

    public Map<Long, String> coffeeIdToStringMap() {

        Map<Long, String> result = new HashMap<>();

        coffeeRepository.findAll().forEach(coffee ->
                result.put(coffee.getId(), coffee.getBrand() + " - " + coffee.getType()));

        return result;
    }

    public Coffee getById(Long coffeeId) {
        return coffeeRepository.getById(coffeeId);
    }

    public List<Coffee> getCoffeeList() {

        return coffeeRepository.findAll();

    }

    public List<Coffee> findAllInIdList(List<Long> coffeeFromShop) {

        return coffeeRepository.findAllById(coffeeFromShop);

    }



    static class CountItem {
        int i;

        public CountItem() {}

        public CountItem(int i) {this.i = i;};

        void add(int i) {this.i += i;}

        int get(){return this.i;}
    }

}
