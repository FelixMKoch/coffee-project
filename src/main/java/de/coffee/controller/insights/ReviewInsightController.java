package de.coffee.controller.insights;

import de.coffee.application.dao.ReviewDAO;
import de.coffee.application.dao.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ReviewInsightController {

    @Autowired
    private ReviewDAO reviewDAO;

    @Autowired
    private ReviewService reviewService;



    @GetMapping("/reviewinsight")
    public String reviewInsightGet(Model model) {

        //List of size 11: Integer indicate the number of reviews with
        model.addAttribute("yAxis", StringUtils.join(reviewService.getQuantityList(), ","));

        modelAdds(model);

        return "insights/reviewinsight";
    }



    private void modelAdds(Model model) {

        model.addAttribute("xAxis",
                StringUtils.join(IntStream.range(0, 11).boxed().collect(Collectors.toList()), ","));

    }



}
