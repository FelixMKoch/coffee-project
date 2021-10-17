package de.coffee.controller.review;

import de.coffee.application.dao.services.ReviewService;
import de.coffee.domain.models.dtos.custom.ReviewDetailFrontendDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewInfoController {

    @Autowired
    private ReviewService reviewService;


    @GetMapping("/reviewinfo")
    public String reviewInfoGet(Model model,
        @RequestParam(name = "reviewId", required=false) Long reviewId){

        if(reviewId == null) return "redirect:/reviewtable";

        ReviewDetailFrontendDTO frontendItem;

        try {
            frontendItem = reviewService.getFrontendItem(reviewId);
        } catch (Exception e) {return "redirect:/reviewtable";}

        model.addAttribute("reviewItem", frontendItem);

        return "review/reviewinfo";
    }
}
