package de.coffee.controller.items;

import de.coffee.application.dao.CoffeeDAO;
import de.coffee.application.dao.CoffeeInShopDAO;
import de.coffee.application.dao.ShopDAO;
import de.coffee.application.dao.services.CoffeeInShopService;
import de.coffee.application.exceptions.ItemAlreadyExistsException;
import de.coffee.domain.models.CoffeeInShop;
import de.coffee.domain.models.dtos.CoffeeDTO;
import de.coffee.domain.models.dtos.ShopDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CoffeeInShopController {

    @Autowired
    private CoffeeInShopService coffeeInShopService;

    @Autowired
    private CoffeeInShopDAO coffeeInShopDAO;

    @Autowired
    private ShopDAO shopDAO;

    @Autowired
    private CoffeeDAO coffeeDAO;

    @GetMapping("/coffeeinshop")
    public String coffeeinShopget(Model model) {

        modelAdds(model);

        return "items/coffeeinshop";
    }


    @PostMapping("/coffeeinshop")
    public String coffeeInShopPost(Model model, CoffeeInShop cis)
            throws ItemAlreadyExistsException {

        coffeeInShopDAO.save(cis);

        modelAdds(model);

        return "items/coffeeinshop";

    }



    private void modelAdds(Model model) {

        model.addAttribute("newCis", new CoffeeInShop());

        model.addAttribute("shopList", shopDAO.getShopList());

        model.addAttribute("coffeeList", coffeeDAO.findAll());

        model.addAttribute("cisList", coffeeInShopService.getFrontendList());

    }

}
