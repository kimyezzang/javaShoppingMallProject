package kim.yechan.yesinsa.java1.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {	// 구매대기중인 상품 목록
	public static Map<Integer, Order> orderList = new HashMap<Integer,Order>();	// 구매 신청한 상품
	private Order() {}
	
	private static Order order = new Order();
	
	public static Order getInstance() {
		return order;
	}
	
	private int productNo;
	private int price;
	private int count;
	private String productName;
	private String brand;
	private String guestId;
	
	public Order(int productNo,int price,int count,String productName,String brand,String guestId) {
		this.productNo = productNo;
		this.price = price;
		this.count = count;
		this.productName = productName;
		this.brand = brand;
		this.guestId = guestId;
	}
	
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductName() {
		return productName;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBrand() {
		return brand;
	}
	public String getGuestId() {
		return guestId;
	}
	
	public String toSting() {	// 아이디 번호 이름 브랜드 가격 수량
		return guestId+"\t"+productNo + "\t" + productName + "\t" + brand + "\t" + price + "\t" + String.valueOf(count); 
	}
	
}
