package org.tp_spring_sec.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.tp_spring_sec.entities.Product;
import org.tp_spring_sec.repositories.ProductRepository;

import java.util.List;

@Controller
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/public/products")
    public String index(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "views/product/products_view";
    }

    @PostMapping("/admin/products/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/public/products";
    }
}
