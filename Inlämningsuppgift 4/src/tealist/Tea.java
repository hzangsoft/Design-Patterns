package tealist;

/**
 * Information regarding tea.
 * 
 * @author Thomas Ejnefjäll 
 */
public class Tea {
	/* Constants regarding tea */
	public static final String TEA = "tea",
							   TEALIST = "tealist",	
							   CATEGORY = "category",
							   NAME = "name",
							   PRICE = "price",
							   DESCRIPTION = "description";	
	
	private String category;
	private String name;
	private int price;
	private String description;
	

	/**
	 * Constructs a tea object.
	 */
	public Tea() {
		category = name = description = "";
	}
	/**
	 * Constructs a tea object.
	 * 
	 * @param category the tea category (Black, red, herb tea etc)
	 * @param name the name of the tea
	 * @param price the price
	 * @param description a description containing flavors, how it is done etc
	 */
	public Tea(String category, String name, int price, String description) {
		this.category = category;
		this.name = name;
		this.price = price;
		this.description = description;
	}
	
	/* Getters and setters for the members of the Tea class. */
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

}