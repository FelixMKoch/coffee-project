package de.coffee.controller.insights;

import de.coffee.application.dao.ReviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralInsightController {

    @Autowired
    private ReviewDAO reviewDAO;


    @GetMapping("/generalinsight")
    public String generalInsightGet(Model model) {

        model.addAttribute("costSum", reviewDAO.sumPrices());

        model.addAttribute("avgCost", reviewDAO.getAvgCost());

        model.addAttribute("coffeeSum", reviewDAO.getNoReviews());

        model.addAttribute("coffeeMlSum", reviewDAO.getSumLiterInMl());

        return "insights/generalinsight";
    }
}
