package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.model.Product;

@Controller
public class FirstController {

	@GetMapping("/hello") // localhost:8080/hello
	public String getHello() {
		System.out.println("First controller works");
		return "hello-page"; // tiks parādīta hello-page.html lapa
	}
	// TODO uztaisīt plāšaku html lapu
	// TODO jaunu kontrolieru funkciju, kas parāda citu html lapu

	Random rand = new Random();

	@GetMapping("/hello/msg") // localhost:8080/hello/msg
	public String getHelloMsg(Model model) {
		model.addAttribute("mydata", "Ziņa no Karinas:" + rand.nextInt(0, 100));
		return "hello-msg-page";// tiks parādīta hello-msg-page.html, ar padotajiem datiem
	}

	// TODO izveidot kontroliera funkciju,
	// kura caur model pados Jūsu izveidoto produktu
	// (izveidojam šajā fukcijā jebkādu produktu

	@GetMapping("/product/test") // localhost:8080/product/test
	public String getProductTest(Model model) {
		Product product = new Product("Abols", 0.99f, "Sarkans un garšīgs", 4);
		model.addAttribute("mydata", product);
		return "product-show-one-page";// tiks parādīta product-show-one-page.html lapa ar ābola produktu
	}

	// TODO izveidot kontroliera dunkciju, kura ieksienē uztaisīt 3 produktus
	// un salikt tos sarakstā. Kā mydata iedot šo sarakstu un parādīt
	// hmtl lapā

	@GetMapping("/product/test/all") // localhost:8080/product/test/all
	public String getProductTestAll(Model model) {
		ArrayList<Product> allProducts = new ArrayList<>(
				Arrays.asList(new Product("Abols", 0.99f, "Sarkans un garšīgs", 4),
						new Product("Tomats", 1.99f, "Dzeltens un garšīgs", 2),
						new Product("Gurkis", 2.99f, "Zaļš un garšīgs", 1)));

		model.addAttribute("mydata", allProducts);
		return "product-show-all-page";// tiks parādīta product-show-all-page.html lapa ar visiem produktiem

	}

}