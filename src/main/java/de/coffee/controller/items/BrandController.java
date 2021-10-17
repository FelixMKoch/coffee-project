package de.coffee.controller.items;

import de.coffee.application.dao.BrandDAO;
import de.coffee.application.exceptions.ItemAlreadyExistsException;
import de.coffee.domain.models.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BrandController {

    @Autowired
    private BrandDAO brandDAO;


    @GetMapping("/brands")
    public String brandGet(Model model) {

        modelAdds(model);

        return "items/brand";
    }


    @PostMapping("/brands")
    public String brandPost(Model model, Brand brand) {

        modelAdds(model);
        try {
            brandDAO.save(brand);
        } catch(ItemAlreadyExistsException e) {
            model.addAttribute("errormessage", e.getMessage());
        }

        return "items/brand";
    }


    private void modelAdds(Model model) {
        model.addAttribute("newBrand", new Brand());

        model.addAttribute("brandList", brandDAO.findAll());
    }
}
