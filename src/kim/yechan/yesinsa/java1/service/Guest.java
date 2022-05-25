package kim.yechan.yesinsa.java1.service;

public interface Guest {	// GuestImpl 의 설계도
	
	void cartList();	// 장바구니 목록
	void cartAdd(String str);	// 장바구니 추가
	void cartRemove();	// 장바구니 삭제
	void cartBuy();		// 장바구니에서 구매
	
	void nowBuy(String str);		// 바로 구매
	void refund(String str);		// 구매한 상품 환불
	
}
