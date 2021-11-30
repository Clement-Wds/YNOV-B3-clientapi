package com.ynovb3.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ynovb3.client.model.Product;
import com.ynovb3.client.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	//Utiliser Model model pour envoyer dans la page html
	public String productsPage(Model model) {
		List<Product> products = productService.getProducts();
		//Envoyer dans la page html
		model.addAttribute("products", products);
		
		return "products";
	}
	
	@GetMapping("/product/{id}")
	//Utiliser Model model pour envoyer dans la page html
	public String productPage(@PathVariable(name = "id") Integer id, Model model) {
		Product product = productService.getProductById(id);
		//Envoyer dans la page html
		model.addAttribute("product", product);
		
		return "product";
	}
	
}
