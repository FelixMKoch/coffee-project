package de.coffee.controller.insights;

import de.coffee.application.dao.services.CoffeeService;
import de.coffee.domain.models.dtos.custom.BrandLeaderBoardsDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class LeaderboardController {

    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/leaderboards")
    public String getLeaderboards(Model model) {

        List<BrandLeaderBoardsDTO> itemList =
                coffeeService.getAllBrandLeaderboardDtos();

        itemList.sort(Comparator.comparing(BrandLeaderBoardsDTO::getLiterSum));

        model.addAttribute("itemList", itemList);

        List<String> xList = new ArrayList<>();
        List<String> xListnew = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();

        itemList.forEach(i -> {
            xList.add(i.getBrand());
            yList.add(i.getLiterSum());
        });

        xList.forEach(i -> xListnew.add("'" + i + "'"));

        model.addAttribute("xAxis", StringUtils.join(xListnew, ',').replace(",", ", "));
        model.addAttribute("yAxis", StringUtils.join(yList, ','));

        return "/insights/leaderboard";
    }
}
