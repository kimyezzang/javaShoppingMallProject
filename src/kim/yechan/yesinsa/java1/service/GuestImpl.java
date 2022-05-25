package kim.yechan.yesinsa.java1.service;

import java.util.HashMap;
import java.util.Map;

import kim.yechan.yesinsa.java1.model.GuestProduct;
import kim.yechan.yesinsa.java1.model.Order;
import kim.yechan.yesinsa.java1.model.Product;
import kim.yechan.yesinsa.java1.model.Sales;
import kim.yechan.yesinsa.java1.view.MenuImpl;

public class GuestImpl implements Guest{	// 
	
	MenuImpl menu = MenuImpl.getInstance();
	HostImpl hostimpl = HostImpl.getInstance();
	Order order = Order.getInstance();
	Sales sales = Sales.getInstance();
	
	String guestId ;
	String guestPw = "";
	String guestPwHint = "";
	
	private GuestImpl() {}
	
	public GuestImpl(String guestId, String guestPw,String guestPwHint) {
		this.guestId = guestId;
		this.guestPw = guestPw;
		this.guestPwHint = guestPwHint;
	}
	
	private static GuestImpl guestImpl = new GuestImpl();

	public static GuestImpl getInstance() {
		if(guestImpl == null)
			guestImpl = new GuestImpl();
		return guestImpl;
	}
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	public String getGuestId() {
		return guestId;
	}
	public void setGuestPw(String guestPw) {
		this.guestPw = guestPw;
	}
	public String getGuestPw() {
		return guestPw;
	}
	public void setGuestPwHint(String guestPwHint) {
		this.guestPwHint = guestPwHint;
	}
	public String getGuestPwHint() {
		return guestPwHint;
	}
	
	public void cartList() {	// 장바구니 목록
		System.out.println("====================== 상품 목록 ======================");
		System.out.println("번호"+"\t이름\t브랜드\t가격\t수량");
		System.out.println("----------------------------------------------------");
		for(int key : GuestProduct.guestMap.keySet()) {		// 장바구니 안에 상품들 호출
			if(this.guestId.equals(GuestProduct.guestMap.get(key).getGuestId())) {		// 내가 담은 상품들만 호출
			GuestProduct value = GuestProduct.guestMap.get(key);
			System.out.println(value.toSting());}
		}
	}
	
	public void cartAdd(String guestId) {	// 장바구니 추가
		System.out.println(" * 장바구니에 담을 상품 번호를 입력해 주십시오.");
		System.out.print("상품 번호 : ");
		int cartNo = menu.scan.nextInt();
		System.out.println(" * 장바구니에 담을 상품 갯수를 입력해 주십시오.");
		System.out.print("상품 갯수 : ");
		int cartProductNo = menu.scan.nextInt();
		
		String brand = Product.productMap.get(cartNo).getBrand();	// 상품 정보들을 가져옴
		String productName = Product.productMap.get(cartNo).getProductName();
		int price = Product.productMap.get(cartNo).getPrice();
		this.guestId = guestId;		// 아이디를 매개변수에 대입
		
		GuestProduct.guestMap.put(cartNo, new GuestProduct(cartNo, price,cartProductNo,productName,brand,guestId));	// 장바구니에 추가
		System.out.println(" *** 상품이 카트에 추가 되었습니다. ***");
	}
	public void cartRemove() {		// 장바구니 삭제
		cartList();
		System.out.print(" * 삭제할 상품 번호를 입력해주세요 : ");
		int deleteNo = menu.scan.nextInt();
		GuestProduct.guestMap.remove(deleteNo);		// 코드번호로 상품 삭제
		System.out.println(" *** 상품이 삭제 되었습니다. ***");
		
	}
	public void cartBuy() {		// 장바구니에서 구매
		System.out.println("=================== 장바구니 목록 ======================");
		System.out.println("번호"+"\t이름\t브랜드\t가격\t수량");
		System.out.println("----------------------------------------------------");
		for(int key : GuestProduct.guestMap.keySet()) {
			if(this.guestId.equals(GuestProduct.guestMap.get(key).getGuestId())) {	// 지금 아이디의 장바구니 목록 호출
			GuestProduct value = GuestProduct.guestMap.get(key);
			System.out.println(value.toSting());}
		}
		System.out.print(" * 구매할 상품의 코드를 입력하세요 : ");
		int code = menu.scan.nextInt();
		
		int no = GuestProduct.guestMap.get(code).getProductNo();				// 장바구니에 상품 내역 저장
		int price = GuestProduct.guestMap.get(code).getPrice();
		int count = GuestProduct.guestMap.get(code).getCount();
		String productName = GuestProduct.guestMap.get(code).getProductName();
		String brand =  GuestProduct.guestMap.get(code).getBrand();
		String guestId =  GuestProduct.guestMap.get(code).getGuestId();
		
		Order.orderList.put(code,new Order(no,price,count,productName,brand,guestId));	// 구매 승인 HashMap에 코드, 상품 내역 저장
		GuestProduct.guestMap.remove(code);			// 장바구니에서는 제거
		System.out.println(" *** 구매 승인 요청하였습니다. ***");
	}
	
	public void nowBuy(String guestId) {	// 장바구니 거치지 않고 바로 구매
			
		System.out.print(" * 구매할 상품의 코드를 입력하세요 :");
		int cartNo = menu.scan.nextInt();
		System.out.print(" * 구매할 상품 갯수를 입력해 주십시오 : ");
		int cartProductNo = menu.scan.nextInt();
		
		String brand = Product.productMap.get(cartNo).getBrand();		// 구매할 상품의 코드키로 데이터 가져옴
		String productName = Product.productMap.get(cartNo).getProductName();
		int price = Product.productMap.get(cartNo).getPrice();
		
		String productGuestId = this.guestId; 
		
		System.out.println(" *** 구매 승인 요청하였습니다. ***");
		Order.orderList.put(cartNo,new Order(cartNo,price,cartProductNo,productName,brand,guestId));	// 구매 승인 요청목록으로 데이터 넘김
	}
	public void refund(String id) {	// 구매한 상품 환불
		System.out.println("=================== 구매    목록 ======================");
		System.out.println("ID\t번호"+"\t이름\t브랜드\t가격\t수량");
		System.out.println("----------------------------------------------------");
		this.guestId = id;
		for(int key : Sales.refundList.keySet()) {					
			if(this.guestId.equals(Sales.refundList.get(key).getGuestId())) {		// 지금 아이디가 구매한 상품 호출
			Sales value = Sales.refundList.get(key);
			System.out.println(value.toSting());}
		}
		
		System.out.print(" * 환불하실 상품 코드를 입력하여 주십시오 : ");
		int refundNo = menu.scan.nextInt();
		
		int count = Sales.refundList.get(refundNo).getCount();
		int price = Sales.refundList.get(refundNo).getPrice();
		int countPrice = count*price;
		
		int originTotalMoney = hostimpl.getTotalMoney();
		int renewTotalMoney = (originTotalMoney -countPrice);
		hostimpl.setTotalMoney(renewTotalMoney);		// 결산에서 돈 마이너스
		
		Product.productMap.get(refundNo).setCount(Product.productMap.get(refundNo).getCount()+count);
		// 재고 갯수 추가하여 원복
		Sales.refundList.remove(refundNo);			// 구매 목록에서 데이터 삭제
		System.out.println(" *** 환불이 완료되었습니다. ***");
		
	}
		}