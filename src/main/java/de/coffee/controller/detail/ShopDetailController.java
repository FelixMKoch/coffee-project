package de.coffee.controller.detail;

import de.coffee.application.dao.ShopDAO;
import de.coffee.application.dao.services.CoffeeService;
import de.coffee.domain.models.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShopDetailController {

    @Autowired
    private ShopDAO shopDAO;

    @Autowired
    private CoffeeService coffeeService;


    @GetMapping("/shop/detail")
    public String shopDetailget(Model model,
                                @RequestParam (required = false) Long shopId){

        if(shopId == null) return "redirect:/shop";

        Shop shop = shopDAO.getById(shopId);

        if(shop == null) return "redirect:/shop";

        model.addAttribute("shopItem", shop);

        model.addAttribute("coffeeList", coffeeService.getAllFromShop(shop.getShopId()));


        return "detail/shopdetail";
    }

}
