package com.example.storeapp.controller;

import com.example.storeapp.global.GlobalData;
import com.example.storeapp.mailSender.EmailSender;
import com.example.storeapp.model.Product;
import com.example.storeapp.model.User;
import com.example.storeapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CartController {
    @Autowired
    ProductService productService;

    @Autowired
    private EmailSender emailSender;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id) {
        GlobalData.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }


    @GetMapping("/cart")
    public String cartGet(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);

        return "cart";
    }


    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index) {
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }


    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());


        return "checkout";
    }

    @PostMapping("/payNow")
    public String payNow(@RequestParam("firstname") String firstname,
                         @RequestParam("lastname") String lastname,
                         @RequestParam("address1") String address1,
                         @RequestParam("address2") String address2,
                         @RequestParam("postcode") String postcode,
                         @RequestParam("city") String city,
                         @RequestParam("phone") String phone,
                         @RequestParam("emailSend") String emailSend,
                         @RequestParam("information") String information
                         ){

        System.out.println(emailSend);
        emailSender.sendEmail(emailSend, firstname+" "+lastname+" " + address1, address2+" "+postcode+" "+ city, phone+" " + information);




        return "payNow";
    }

}
