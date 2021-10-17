package de.coffee.application.dao.services;

import de.coffee.application.dao.ShopDAO;
import de.coffee.domain.models.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ShopService {

    @Autowired
    private ShopDAO shopDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean alreadySaved(Shop shop) {

        return shopDAO.getShopList().contains(shop);

    }

    public Map<Long, Double> getAvgRatingMap() {

        Map<Long, Double> resultMap = new HashMap<>();

        String query = "SELECT s.shop_id as sid, avg(r.rating) as ratingavg" +
                " FROM shop s INNER JOIN review r ON s.shop_id = r.shop_id" +
                " GROUP BY s.shop_id";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);

        while(rowSet.next()) {
            resultMap.put(rowSet.getLong("sid"), rowSet.getDouble("ratingavg"));
        }

        return resultMap;
    }
}
