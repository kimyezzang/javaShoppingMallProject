package kim.yechan.yesinsa.java1.service;

import java.util.HashMap;
import java.util.Map;

import kim.yechan.yesinsa.java1.model.Order;
import kim.yechan.yesinsa.java1.model.Product;
import kim.yechan.yesinsa.java1.model.Sales;
import kim.yechan.yesinsa.java1.view.MenuImpl;

public class HostImpl implements Host{
	
	Map<Integer,Integer> codeList = new HashMap<Integer,Integer>();
	
	  MenuImpl menu = MenuImpl.getInstance();	
	GuestImpl guestimpl;
	
	private int totalMoney;
	private String guestId;
	
	 private HostImpl() {
	}
	private static HostImpl hostImpl = new HostImpl();
	
	public static HostImpl getInstance() {	// 인스턴스 생성
		if(hostImpl == null) {
			hostImpl = new HostImpl();
		}
		return hostImpl;
	}
	
	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}
	public int getTotalMoney() {
		return totalMoney;
	}
	
	private static final String HOST_ID = "host";	// 관리자 아이디값
	private static final String HOST_PW = "host";
	
	public static String getHostId () {
		return HOST_ID;
	}
	public static String getHostPw () {
		return HOST_PW;
	}
	@Override
	public void productList() {		// 상품 목록 
		System.out.println("====================== 상품 목록 ======================");
		System.out.println("번호"+"\t이름\t브랜드\t가격\t수량");
		System.out.println("----------------------------------------------------");
		
		for(int key : Product.productMap.keySet()) {
			Product value = Product.productMap.get(key);
			System.out.println(value.toSting());
		}
		System.out.println("=====================================================");
	}
	
	@Override
	public void productAdd() {		// 상품 추가
		
		System.out.print("* 상품 이름을 입력하세요 : ");
		String productName = menu.scan.next();
		System.out.print("* 상품 개수를 입력하세요 : ");
		int count = menu.scan.nextInt();
		System.out.print("* 상품 가격을 입력하세요 : ");
		int price = menu.scan.nextInt();
		System.out.print("* 상품 브랜드을 입력하세요 : ");
		String brand = menu.scan.next();
		
		int productNo;
		
		while(true) {
			productNo = (int)(Math.random() *1000) + 1000;
			if((false == codeList.containsKey(productNo))){		// 같은 번호가 나오면 다시 생성
				codeList.put(productNo, productNo);
				break;
			}
		}	// 랜덤으로 1000~2000까지 생성	
		
		Product.productMap.put(productNo, new Product(productNo, price, count, productName, brand));
		// hashMap에 상품추가
		System.out.println(" *** 추가되었습니다. ***");
		
	}
	@Override
	public void productUpdate() {		// 상품 수정
		productList();
		System.out.print("* 수정할 상품 번호를 입력해주세요 : ");
		int updateNo = menu.scan.nextInt();
		System.out.print("* 상품 이름을 입력하세요 : ");
		String productName = menu.scan.next();
		System.out.print("* 상품 개수를 입력하세요 : ");
		int count = menu.scan.nextInt();
		System.out.print("* 상품 가격을 입력하세요 : ");
		int price = menu.scan.nextInt();
		System.out.print("* 상품 브랜드을 입력하세요 : ");
		String brand = menu.scan.next();
		
		Product.productMap.put(updateNo, new Product(updateNo,price,count,productName,brand));
		// 코드키로 값 엎어치기
		System.out.println(" *** 수정되었습니다. ***");
		
	}
	@Override
	public void productRemove() {		// 상품 삭제
		productList();
		System.out.print("* 삭제할 상품 번호를 입력해주세요 : ");
		int deleteNo = menu.scan.nextInt();
		Product.productMap.remove(deleteNo);	// 코드키로 상품 삭제
		System.out.println(" *** 상품이 삭제 되었습니다. ***");
		
	}
	@Override
	public void orderList() {			// 주문 목록
		guestimpl = GuestImpl.getInstance();
		System.out.println("====================== 상품 목록 ======================");
		System.out.println("아이디\t번호"+"\t이름\t브랜드\t가격\t수량");
		System.out.println("----------------------------------------------------");
		
		for(int key : Order.orderList.keySet()) {		// 주문 목록 호출
			Order value =  Order.orderList.get(key);
			System.out.println(value.toSting());}
		}

	@Override
	public void orderConfirm() {		// 결제 승인
		orderList();
		guestimpl = GuestImpl.getInstance();
		System.out.println("----------------------------------------------------");
		System.out.print("* 결제승인할 코드를 입력하세요 : ");
		int confirrmNo = menu.scan.nextInt();
		int count = Order.orderList.get(confirrmNo).getCount();
		int price = Order.orderList.get(confirrmNo).getPrice();
		String productName = Order.orderList.get(confirrmNo).getProductName();
		String brand = Order.orderList.get(confirrmNo).getBrand();
		String guestId = Order.orderList.get(confirrmNo).getGuestId();
		int total = count * price;
		this.totalMoney += total;
		
		System.out.println(" *** 결제를 승인하였습니다. ***");
		Product.productMap.get(confirrmNo).setCount((Product.productMap.get(confirrmNo).getCount()-count));
		// 재고 갯수 조정
		Sales.refundList.put(confirrmNo,new Sales(confirrmNo,price,count,productName,brand,guestId));
		// 결제 승인 상품 refundList (HashMap)에 대입
		Order.orderList.remove(confirrmNo); // 기존 결제 대기 상품 제거
		
	}
	@Override
	public void orderCancel() {		// 결제 취소
		orderList();
		guestimpl = GuestImpl.getInstance();
		System.out.print("* 결제를 취소할 코드를 입력하세요 : ");
		int confirrmNo = menu.scan.nextInt();
		
		Order.orderList.remove(confirrmNo);		// 승인 대기 리스트 상품 제거
		System.out.println(" *** 결제를 취소하였습니다. ***");
	}
	
	@Override
	public void saletotal() {		// 결산
		System.out.println("* 결산 : " + totalMoney);
	}
}