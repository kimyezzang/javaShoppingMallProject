package kim.yechan.yesinsa.java1.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sales {	// 판매 완료 상품들
	public static Map<Integer, Sales> refundList = new HashMap<Integer,Sales>();	// 구매 완료된 상품
	
	private Sales() {}
	
	private static Sales sales = new Sales();
	
	public static Sales getInstance() {
		if(sales == null) {
			sales = new Sales();
		}
		return sales;
	}
	
	private int productNo;
	private int price;
	private int count;
	private String productName;
	private String brand;
	private String guestId;
	
	public Sales(int productNo,int price,int count,String productName,String brand,String guestId) {
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
