package de.coffee.application.dao;

import de.coffee.application.dao.services.CoffeeInShopService;
import de.coffee.application.exceptions.NoCombinationFoundException;
import de.coffee.application.exceptions.review.ReviewItemNotFoundException;
import de.coffee.domain.models.CoffeeInShop;
import de.coffee.domain.models.Review;
import de.coffee.persistence.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewDAO {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CoffeeInShopDAO coffeeInShopDAO;

    @Autowired
    private CoffeeInShopService coffeeInShopService;

    public List<Review> findAll(){
        return reviewRepository.findAll();
    }

    public Review save(Review review) throws NoCombinationFoundException {

        if(!coffeeInShopService.alreadySaved(new CoffeeInShop(review.getCoffeeId(), review.getShopId(), BigDecimal.ZERO)))
            throw new NoCombinationFoundException("No Shop-Coffee-Combination was found");


        review.setCreateDate(new Date());

        // If no price is set: Set default
        if(review.getPrice() == null || review.getPrice().compareTo(BigDecimal.ZERO) == 0)
            review.setPrice(coffeeInShopDAO.getPrice(review.getShopId(), review.getCoffeeId()));

        return reviewRepository.save(review);

    }

    public List<Integer> getRatingList() {
        return reviewRepository.getRatingList();
    }

    public BigDecimal sumPrices() {
        return reviewRepository.getSumPrices();
    }

    public BigDecimal getAvgCost() {

        BigDecimal prices = reviewRepository.getSumPrices();

        Integer numReviews = reviewRepository.countReviews();
        if(numReviews == 0 || prices == null || prices.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;

        return prices
                .divide(BigDecimal.valueOf((double) numReviews), 2, RoundingMode.HALF_UP);
    }

    public Integer getNoReviews() {
        return reviewRepository.countReviews();
    }

    public Long getSumLiterInMl() {
        Long result = reviewRepository.getSumLiterInMl();

        if(result == null) return 0L;

        return result;
    }

    public Review getById(Long reviewId) throws ReviewItemNotFoundException {
        Optional<Review> opt = reviewRepository.findById(reviewId);

        if(!opt.isPresent())
            throw new ReviewItemNotFoundException("Kein Review mit ID " + reviewId + " gefunden");

        return opt.get();
    }

    public List<Review> getAllByCoffeeId(Long id) {

        return reviewRepository.findAllByCoffeeId(id);

    }
}
