package com.sheryian.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sheryian.major.global.GlobalData;
import com.sheryian.major.service.CategoryService;
import com.sheryian.major.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
	@GetMapping({"/" , "/home"})
	public String home(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model,@PathVariable int id) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("products", productService.getAllProductsByCategoryId(id));
		return "shop";  // products on the basis of category,so we have to create method get product by category id
	}
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model,@PathVariable int id) {
		model.addAttribute("product", productService.getProductById((long) id).get()); // used .get() beacuse we used optional
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "viewProduct";  
	}
	
	
	
	
}
