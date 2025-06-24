package org.tp_spring_sec.controllers;

import jakarta.validation.Valid;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tp_spring_sec.entities.Product;
import org.tp_spring_sec.repositories.ProductRepository;

import java.util.List;

@Controller
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String home() {
        return "/index";
    }

    @GetMapping("/public/products")
    public String index(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        model.addAttribute("products", products);
        return "views/product/products_view";
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/products/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/public/products";
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/products/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "views/product/new_product";
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/products/store")
    public String store(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "views/product/new_product";
        productRepository.save(product);
        return "redirect:/public/products";
    }

    // Login form
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    // Login form with error
    @RequestMapping("/error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "auth/login";
    }
}
