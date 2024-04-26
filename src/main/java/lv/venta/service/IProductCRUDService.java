package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface IProductCRUDService {
	// CRUD = create - retrieve - update - delete
	public abstract void create(Product product);

	public abstract Product retrieveById (int id) throws Exception;
	
	public abstract ArrayList<Product> retrieveAll() throws Exception;
	
	public abstract void updateById(int id, Product product) throws Exception;
	
	public abstract void deleteById (int id)  throws Exception;

}
