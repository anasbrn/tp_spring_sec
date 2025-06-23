package org.tp_spring_sec.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("admin/products/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "views/product/new_product";
    }

    @PostMapping("admin/products/store")
    public String store(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "views/product/new_product";
        productRepository.save(product);
        return "redirect:/public/products";
    }
}
