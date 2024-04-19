package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;

@SpringBootApplication
public class ProgInzSeminar1Application {

	public static void main(String[] args) {
		SpringApplication.run(ProgInzSeminar1Application.class, args);
	}
	
	@Bean //funkcija tiks izsaukta automātiski, līdz ko palaižas sistēma
	public CommandLineRunner testDatabase(IProductRepo productRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				//TODO izveidot 3 produktus
				// ar save funkciju saglabāt repozitorijā
				Product p1 = new Product("Abols", 0.99f, "Sarkans un garšīgs", 4);
				Product p2 = new Product("Tomats", 1.99f, "Dzeltens un garšīgs", 2);
				Product p3 = new Product("Gurkis", 2.99f, "Zaļš un garšīgs", 1);
				productRepo.save(p1);
				productRepo.save(p2);
				productRepo.save(p3);
				
				System.out.println("How many products: " + productRepo.count());
				System.out.println("Get product by id (2): " + productRepo.findById(2).get());
				System.out.println("Find all products: " + productRepo.findAll());
				
				Product productForDeleting = productRepo.findById(2).get();
				productRepo.delete(productForDeleting);
				
				Product productForUpdating = productRepo.findById(1).get();
				productForUpdating.setPrice(0.55f);
				productRepo.save(productForUpdating);
				
			}
		};
	}

}
