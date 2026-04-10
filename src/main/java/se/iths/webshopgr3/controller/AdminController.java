package se.iths.webshopgr3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.webshopgr3.model.Product;
import se.iths.webshopgr3.service.ProductService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;

    @GetMapping
    public String printAllProducts(Model model) {
        model.addAttribute("allProducts", productService.listAllProducts()); //Attributnamn? Loopa i frontend?
        return "admin"; //skapa adminsida
    }

    @PostMapping("/add") // egen add sida eller allt på samma?
    public String addProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/admin";
    }

//    @PutMapping("/update")
//    public String updateProduct(){
//    }
}
