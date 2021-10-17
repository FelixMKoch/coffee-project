package de.coffee.controller.review;

import de.coffee.application.dao.CoffeeDAO;
import de.coffee.application.dao.ReviewDAO;
import de.coffee.application.dao.ShopDAO;
import de.coffee.domain.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;

@Controller
public class ReviewTableController {

    @Autowired
    private ReviewDAO reviewDAO;

    @Autowired
    private CoffeeDAO coffeeDAO;

    @Autowired
    private ShopDAO shopDAO;

    @GetMapping("/reviewtable")
    public String reviewTableGet(Model model) {

        var reviewList = reviewDAO.findAll();
        reviewList.sort(Comparator.comparing(Review::getCreateDate).reversed());

        model.addAttribute("reviewList", reviewList);

        model.addAttribute("coffeeIdToStringMap", coffeeDAO.coffeeIdToStringMap());

        model.addAttribute("shopIdToStringMap", shopDAO.shopIdToStringMap());

        return "review/reviewtable";
    }
}
