package lv.venta.model;
// just comment
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
//@AllArgsConstructor
public class Product {
	@Setter(value = AccessLevel.NONE)
	private int id;

	private String title;
	private float price;
	private String description;
	private int quantity;

	private static int counter = 0;

	public void setId() {
		id = counter;
		counter++;
	}

	public Product(String title, float price, String description, int quantity) {
		setId();
		setTitle(title);
		setPrice(price);
		setDescription(description);
		setQuantity(quantity);
	}

}