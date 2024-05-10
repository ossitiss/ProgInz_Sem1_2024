package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Product;
import lv.venta.service.IProductCRUDService;

@Controller
@RequestMapping("/product/crud/")
public class ProductCRUDController {
	
	@Autowired
	private IProductCRUDService crudService;
	
	@GetMapping("/all") //localhost:8080/product/crud/all
	public String getProductCRUDAll(Model model) {
		try {
			//ArrayList<Product> allProducts = crudService.retrieveById();
			model.addAttribute("mydata", crudService.retrieveAll());
			return "product-show-all-page";
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/one") //localhost:8080/product/crud/one?id=1
	public String getProductCRUDOne(@RequestParam("id") int id, Model model) {
		try {
			Product foundProduct = crudService.retrieveById(id);
			model.addAttribute("mydata", foundProduct);
			return "product-show-one-page";
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/all/{id}") //localhost:8080/product/crud/all/1
	public String getProductCRUDAllById(@PathVariable("id") int id, Model model) {
		try {
			//ArrayList<Product> allProducts = crudService.retrieveById();
			model.addAttribute("mydata", crudService.retrieveAll());
			return "product-show-all-page";
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/insert") //localhost:8080/product/crud/insert
	public String getProductCRUDInsert(Model model) {
		model.addAttribute("product", new Product());
		return "product-insert-page";
	}
	
	@PostMapping("/insert")
	public String postProductCRUDInsert(@Valid Product product, BindingResult result) {
		// vai ir kādi validācijas pārkāpumi?
		if(result.hasErrors()) {
			return "product-insert-page"; // turpinām palikt product-insert-page.html lapā
		}
		else 
		{
			crudService.create(product);
			return "redirect:/product/crud/all";
		}
		
		//System.out.println(product);
		
	}
	
	
	
}
