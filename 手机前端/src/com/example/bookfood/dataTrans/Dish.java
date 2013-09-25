package com.example.bookfood.dataTrans;

/**
 * 构建Dish类方便接收
 * 
 * @Project BookFood
 * @Package com.example.bookfood.dataTrans
 * @Class Menu
 * @Date 2012-11-9 下午6:38:31
 * @author upaman
 * @version
 * @since
 */

public class Dish {
	private String name;
	private String type;
	private double price;
	private String pic;

	public Dish() {
		super();
	}

	public Dish(String name, String type, double price, String pic) {
		super();
		this.name = name;
		this.type = type;
		this.price =price;
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.name = pic;
	}

	@Override
	public String toString() {
		return "Dish [name=" + name + ", type=" + type + ", price=" + price
				+",pic="+pic+ "]";
	}
}
