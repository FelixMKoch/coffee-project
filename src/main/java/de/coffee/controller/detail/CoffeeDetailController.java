package de.coffee.controller.detail;

import de.coffee.application.dao.CoffeeDAO;
import de.coffee.application.dao.ReviewDAO;
import de.coffee.application.dao.ShopDAO;
import de.coffee.domain.models.Coffee;
import de.coffee.domain.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CoffeeDetailController {

    @Autowired
    private CoffeeDAO coffeeDAO;

    @Autowired
    private ReviewDAO reviewDAO;

    @Autowired
    private ShopDAO shopDAO;

    @GetMapping("/coffee/detail")
    public String coffeeDetailGet(Model model,
                                  @RequestParam (required = false) Long coffeeId) {

        if(coffeeId == null) return "redirect:/coffee";

        Coffee coffee = coffeeDAO.getById(coffeeId);

        if(coffee == null) return "redirect:/coffee";

        List<Review> reviewList = reviewDAO.getAllByCoffeeId(coffee.getId());

        model.addAttribute("coffeeItem", coffee);

        model.addAttribute("reviewList", reviewList);

        model.addAttribute("shopStringMap", shopDAO.shopIdToStringMap());

        model.addAttribute("avgRating",
                Math.round((double) reviewList.stream().map(Review::getRating).reduce(0, (a, b) -> a+b)
                        / reviewList.size()));


        return "detail/coffeedetail";
    }

}
