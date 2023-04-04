package com.example.demoaddcartspringmvc.controller;

import com.example.demoaddcartspringmvc.model.Cart;
import com.example.demoaddcartspringmvc.model.Product;
import com.example.demoaddcartspringmvc.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ProductController {
    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/product/create")
    public ModelAndView createNewProductForm() {
        ModelAndView mv = new ModelAndView("/createProd");
        mv.addObject("products", new Product());
        return mv;
    }

    @PostMapping("/product/create")
    public ModelAndView createProduct(@ModelAttribute("products") Product product) {
        productService.save(product);
        ModelAndView mav = new ModelAndView("/createProd");
        mav.addObject("products", new Product());
        mav.addObject("message","New product created successfully. \nBack to list after 3s.");
        return mav;
    }


    @GetMapping("/shop")
    public ModelAndView showShop() {
        ModelAndView modelAndView = new ModelAndView("/shop");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, @ModelAttribute Cart cart, @RequestParam("action") String action) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error.404";
        }
        if (action.equals("show")) {
            cart.addProduct(productOptional.get());
            return "redirect:/shopping-cart";
        }
        cart.addProduct(productOptional.get());
        return "redirect:/shop";
    }

    @GetMapping("/minus/{id}")
    public String minus(@PathVariable Long id, @ModelAttribute Cart cart, @RequestParam("action") String action) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error.404";
        }
        if (action.equals("show")) {
            cart.minusProduct(productOptional.get());
            return "redirect:/shopping-cart";
        }
        cart.minusProduct(productOptional.get());
        return "redirect:/shop";
    }
}
