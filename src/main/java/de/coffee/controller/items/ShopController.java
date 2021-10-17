package de.coffee.controller.items;

import de.coffee.application.dao.ShopDAO;
import de.coffee.application.dao.services.ShopService;
import de.coffee.application.exceptions.ItemAlreadyExistsException;
import de.coffee.domain.models.dtos.ShopDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShopController {

    @Autowired
    private ShopDAO shopDAO;

    @Autowired
    private ShopService shopService;


    @GetMapping("/shop")
    public String shopAddGet(Model model) {

        modelAdds(model);

        return "items/shops";

    }


    @PostMapping("/shop")
    public String shopPost(Model model, ShopDTO shopdto)
            throws ItemAlreadyExistsException {

        shopDAO.save(shopdto);

        modelAdds(model);

        return "items/shops";

    }


    private void modelAdds(Model model) {

        model.addAttribute("newShop", new ShopDTO());

        model.addAttribute("shopList", shopDAO.getShopList());

        model.addAttribute("shopRatingMap", shopService.getAvgRatingMap());

    }
}
