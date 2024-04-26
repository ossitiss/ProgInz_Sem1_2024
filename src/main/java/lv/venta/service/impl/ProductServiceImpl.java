package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductCRUDService;
import lv.venta.service.IProductFilteringService;

@Service
public class ProductServiceImpl implements 
			IProductCRUDService, IProductFilteringService{

	@Autowired
	private IProductRepo productRepo;
	
	@Override
	public void create(Product product) {
		
		Product existedProduct = productRepo.findByTitleAndDescriptionAndPrice(product.getTitle(),product.getDescription(),product.getPrice());
		
		if(existedProduct != null) {
			existedProduct.setQuantity(existedProduct.getQuantity() + product.getQuantity());
			
		}
				
		productRepo.save(product);
	}

	@Override
	public Product retrieveById(int id) throws Exception{
		if(id < 0) throw new Exception("Id should be positive");
		
		if(productRepo.existsById(id))
		{
			return productRepo.findById(id).get();
		}
		else
		{
			throw new Exception("Product with this id ("+id+") is not in the system");
		}
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception{
		//TODO izmest izmaiņu, ja ir tukša tabula
		if(productRepo.count() == 0) throw new Exception("There is no product in the system");
			
		// TODO pretējā gadījumāsa ameklt visus ierakstus no repo (DB)
		return (ArrayList<Product>) productRepo.findAll();
	}

	@Override
	public void updateById(int id, Product product) throws Exception{
		Product productForUpdating = retrieveById(id);
		
		productForUpdating.setTitle(product.getTitle());
		productForUpdating.setDescription(product.getDescription());
		productForUpdating.setPrice(product.getPrice());
		productForUpdating.setQuantity(product.getQuantity());

		productRepo.save(productForUpdating);	
	}

	@Override
	public void deleteById(int id) throws Exception {
		Product productForDeleting = retrieveById(id);

		productRepo.delete(productForDeleting);			
	}
	
	@Override
	public ArrayList<Product> filterByPriceLess(float threshold) throws Exception {
		if(threshold <= 0) throw new Exception("Price should be positive");
		
		if(productRepo.count() == 0) throw new Exception("There is no product in the system");
		
		ArrayList<Product> filteredProducts = productRepo.findByPriceLessThan(threshold);
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> filterByQuantityLess(int threshold) throws Exception {
		if(threshold <= 0) throw new Exception("Threshold wrong");
		
		if(productRepo.count() == 0) throw new Exception("There is no product in the system");
		
		ArrayList<Product> filteredProducts = productRepo.findByQuantityLessThan(threshold);
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> filterByTitleOrDescription(String phrase, String phrase2) throws Exception  {
		if(phrase == null) throw new Exception("Product is with null adress");
		
		if(productRepo.count() == 0) throw new Exception("There is no product in system");
		
		ArrayList<Product> filteredProducts = productRepo.findByTitleIgnoreCaseLikeOrDescriptionIgnoreCaseLike(String phrase, String phrase2);
		return filteredProducts;
	}

	@Override
	public float calculateTotalValueOfProducts() throws Exception {
		if(productRepo.count() == 0) throw new Exception("There is no product in system");
		
		float totalValue = productRepo.calculateTotalValueFromRepoProducts();
		
		return totalValue;
	}


}