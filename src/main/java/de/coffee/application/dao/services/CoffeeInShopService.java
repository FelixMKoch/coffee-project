package de.coffee.application.dao.services;

import de.coffee.application.dao.CoffeeInShopDAO;
import de.coffee.domain.models.CoffeeInShop;
import de.coffee.domain.models.dtos.custom.CoffeeInShopFrontendDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeInShopService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CoffeeInShopDAO coffeeInShopDAO;

    public List<CoffeeInShopFrontendDTO> getFrontendList() {
        List<CoffeeInShopFrontendDTO> resultList = new ArrayList<>();

        String query = "SELECT s.name as shopName, cis.price as price, s.place as place, c.name as coffeeName," +
                " c.brand as coffeeBrand, c.type as coffeeType, c.flavour as coffeeFlavour" +
                " FROM coffee_in_shop cis" +
                " LEFT JOIN coffee c ON cis.coffee_id = c.id" +
                " INNER JOIN shop s ON s.shop_id = cis.shop_id" +
                " ";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);

        while(rowSet.next()) {

            CoffeeInShopFrontendDTO newItem = new CoffeeInShopFrontendDTO(
                    rowSet.getString("shopName"),
                    rowSet.getBigDecimal("price"),
                    rowSet.getString("place"),
                    rowSet.getString("coffeeName"),
                    rowSet.getString("coffeeBrand"),
                    rowSet.getString("coffeeType"),
                    rowSet.getString("coffeeFlavour")
            );

            resultList.add(newItem);

        }

        return resultList;

    }


    public boolean alreadySaved(CoffeeInShop cis) {

        return coffeeInShopDAO.findAll().stream()
                .anyMatch(e -> cis.getCoffeeId().equals(e.getCoffeeId())
                        && cis.getShopId().equals(e.getShopId()));

    }
}
