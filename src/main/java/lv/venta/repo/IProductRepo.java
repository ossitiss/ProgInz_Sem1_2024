package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;

public interface IProductRepo extends CrudRepository<Product, Integer> {

	// public abstract jau pēc noklusējuma
	// SELECT * FROM product_table WHERE title= 1. arguments AND description= 2. arguments AND price= 3. arguments
	Product findByTitleAndDescriptionAndPrice(String title, String description, float price);

	// public abstract jau pēc noklusējuma
	// SELECT * FROM product_table WHERE price < 1.  argumentu
	ArrayList<Product> findByPriceLessThan(float threshold);

	ArrayList<Product> findByQuantityLessThan(int threshold);
	
}
