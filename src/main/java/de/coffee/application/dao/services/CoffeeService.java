package de.coffee.application.dao.services;

import de.coffee.application.dao.CoffeeDAO;
import de.coffee.application.dao.CoffeeInShopDAO;
import de.coffee.domain.models.Coffee;
import de.coffee.domain.models.dtos.custom.BrandLeaderBoardsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoffeeService {

    @Autowired
    private CoffeeDAO coffeeDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CoffeeInShopDAO coffeeInShopDAO;

    public boolean alreadySaved(Coffee coffee) {

        if(coffee == null) return false;

        return coffeeDAO.findAll().stream()
                .anyMatch(coffee::equals);


    }

    public List<Coffee> getAllFromShop(Long shopId) {

        List<Long> coffeeFromShop = coffeeInShopDAO.findAllCoffeeIdsByShopId(shopId);

        return coffeeDAO.findAllInIdList(coffeeFromShop);

    }

    public Map<Long, Double> getAvgRatingMap() {

        Map<Long, Double> resultMap = new HashMap<>();

        String query = "SELECT c.id as cid, avg(r.rating) as ratingavg" +
                " FROM coffee c INNER JOIN review r ON c.id = r.coffee_id" +
                " GROUP BY c.id";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);

        while(rowSet.next()) {
            resultMap.put(rowSet.getLong("cid"), rowSet.getDouble("ratingavg"));
        }

        return resultMap;
    }

    public List<BrandLeaderBoardsDTO> getAllBrandLeaderboardDtos() {

        List<BrandLeaderBoardsDTO> result = new ArrayList<>();

        Map<String, Integer> brandSizeSumMap =
                coffeeDAO.getBrandSumSizeMap();

        String query = "SELECT c.brand as cBrand, count(*) as times, avg(r.rating) as avgRating" +
                " FROM coffee c INNER JOIN review r ON c.id = r.coffee_id" +
                " GROUP BY c.brand";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);

        while(rowSet.next()) {
            result.add(new BrandLeaderBoardsDTO(
                    rowSet.getString("cBrand"),
                    rowSet.getInt("times"),
                    brandSizeSumMap.get(rowSet.getString("cBrand")),
                    rowSet.getDouble("avgRating")
            ));
        }

        return result;
    }
}
