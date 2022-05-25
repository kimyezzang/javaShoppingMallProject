package kim.yechan.yesinsa.java1.service;

public interface Host {		// HostImpl의 설계도
	
	// 관리자 - 재고관리
	public void productList();	// 상품목록
	public void productAdd();	// 상품추가
	public void productUpdate();	// 상품수정
	public void productRemove();	// 상품삭제
	
	// 관리자 - 주문관리
	public void orderList();	// 주문목록
	public void orderConfirm();	// 결제승인
	public void orderCancel();	// 결제취소
	
	// 관리자 - 결산
	public void saletotal();	// 결산
}
