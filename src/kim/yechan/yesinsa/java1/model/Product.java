package kim.yechan.yesinsa.java1.model;

import java.util.HashMap;
import java.util.Map;

public class Product {	// 관리자가 추가한 상품 재고 목록
	public static Map<Integer,Product> productMap = new HashMap<Integer,Product>();	// 관리자가 추가한 상품 목록
	
	private int productNo;	//	상품번호 -> Random() 함수 사용
	private int price;	// 가격
	private int count;	// 수량
	private String productName;	// 상품이름
	private String brand;	// 브랜드
	
	private Product() {}
	
	public Product(int productNo, int price, int count, String productName, String brand) {
		this.productNo = productNo;
		this.productName = productName;
		this.count = count;
		this.price = price;
		this.brand = brand;
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
	
	public String toSting() {	// 번호 이름 브랜드 가격 수량
		return productNo + "\t" + productName + "\t" + brand + "\t" + price + "\t" + String.valueOf(count); 
	}
}