package de.coffee.controller.review;

import de.coffee.application.dao.CoffeeDAO;
import de.coffee.application.dao.CoffeeInShopDAO;
import de.coffee.application.dao.ReviewDAO;
import de.coffee.application.dao.ShopDAO;
import de.coffee.application.exceptions.NoCombinationFoundException;
import de.coffee.domain.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {

    @Autowired
    private CoffeeDAO coffeeDAO;

    @Autowired
    private ShopDAO shopDAO;

    @Autowired
    private CoffeeInShopDAO coffeeInShopDAO;

    @Autowired
    private ReviewDAO reviewDAO;

    @GetMapping("/review")
    public String reviewGet(Model model) {

        modelAdds(model);

        return "review/review";
    }


    @PostMapping("/review")
    public String reviewPost(Model model, Review review) {

        modelAdds(model);

        try {
            reviewDAO.save(review);
            model.addAttribute("successmessage", "Review wurde gespeichert");
        } catch(NoCombinationFoundException e) {
            model.addAttribute("errormessage", e.getMessage());
        }

        return "review/review";

    }

    private void modelAdds(Model model) {

        model.addAttribute("newReview", new Review());

        model.addAttribute("coffeeList", coffeeDAO.findAll());

        model.addAttribute("shopList", shopDAO.getShopList());

        model.addAttribute("cisList", coffeeInShopDAO.findAll());

    }

}
