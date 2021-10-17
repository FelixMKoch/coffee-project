package de.coffee.application.dao.services;

import de.coffee.application.dao.CoffeeDAO;
import de.coffee.application.dao.ReviewDAO;
import de.coffee.application.dao.ShopDAO;
import de.coffee.application.exceptions.review.ReviewItemNotFoundException;
import de.coffee.domain.models.Coffee;
import de.coffee.domain.models.Review;
import de.coffee.domain.models.Shop;
import de.coffee.domain.models.dtos.custom.ReviewDetailFrontendDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    @Autowired
    private ShopDAO shopDAO;

    @Autowired
    private CoffeeDAO coffeeDAO;

    /**
     * Get a list of all the review-scale quantities
     * @return A List of the number of reviews with a rating
     */
    public List<Integer> getQuantityList() {

        List<Integer> reviewList = reviewDAO.getRatingList();

        List<Integer> resultList = new ArrayList<>();

        Map<Integer, CountItem> countMap = new HashMap<>();

        reviewList.forEach(review -> {
            if(!countMap.containsKey(review))
                countMap.put(review, new CountItem());
            countMap.get(review).addOne();
        });

        for(int i = 0; i <=10; i++) {
            resultList.add(countMap.getOrDefault(i, new CountItem()).c);
        }

        return resultList;
    }

    public ReviewDetailFrontendDTO getFrontendItem(Long reviewId)
            throws ReviewItemNotFoundException {

        Review review = reviewDAO.getById(reviewId);

        Shop shop = shopDAO.getById(review.getShopId());

        Coffee coffee = coffeeDAO.getById(review.getCoffeeId());

        if(shop == null || coffee == null) throw new ReviewItemNotFoundException("Item not found");

        return new ReviewDetailFrontendDTO(review, shop, coffee);

    }


    /**
     * Helper Item for a mutable Integer
     */
    private class CountItem{

        int c;

        public CountItem(){
            c = 0;
        }

        void add(int i) {
            c += i;
        }

        void addOne(){
            c = c+1;
        }

    }
}
