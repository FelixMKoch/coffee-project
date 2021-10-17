package de.coffee.controller.items;


import de.coffee.application.dao.BrandDAO;
import de.coffee.application.dao.CoffeeDAO;
import de.coffee.application.dao.services.CoffeeService;
import de.coffee.application.exceptions.ItemAlreadyExistsException;
import de.coffee.domain.models.Coffee;
import de.coffee.domain.models.dtos.CoffeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CoffeeController {

    @Autowired
    private CoffeeDAO coffeeDAO;

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private BrandDAO brandDAO;


    @GetMapping("/coffee")
    public String coffeeAddGet(Model model) {

        modelAdds(model);

        return "items/coffee";

    }


    @PostMapping("/coffee")
    public String coffeePost(Model model, CoffeeDTO coffeedto)
            throws ItemAlreadyExistsException {

        coffeeDAO.save(coffeedto);

        modelAdds(model);

        return "items/coffee";

    }


    private void modelAdds(Model model) {

        model.addAttribute("newCoffee", new Coffee());

        model.addAttribute("coffeeList", coffeeDAO.getCoffeeList());

        model.addAttribute("coffeeRatingMap", coffeeService.getAvgRatingMap());

        model.addAttribute("brandList", brandDAO.findAll());

    }
}
