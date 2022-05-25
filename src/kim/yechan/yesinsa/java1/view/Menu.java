package kim.yechan.yesinsa.java1.view;

public interface Menu {
	
	public abstract void loginMenu();	// 첫 시작화면
	public void commonMenu(int menuNo);	// 공통화면 1
	public void guestCommonMenu(int menuNo);		// 고객 공통화면1
	public void hostProductCommomMenu(int menuNo);			// 관리자 재고관리 상세 메뉴
	public void hostOrderCommonMenu(int menuNo);						// 관리자 주문관리 상세 메뉴
	
	public void guestJoin();	// 고객 회원가입
	public void guestLogin();	// 고객 로그인
	public void guestView();	// 고객 메뉴
	
	public void hostLogin();	// 관리자 로그인
	public void hostView();		// 관리자 
	public void commonHostMenu(int hostMenuNo);	// 관리자 공통 메뉴
	public void hostProductMenu();		// 재고관리 메뉴
	public void hostOrderMenu();		// 주문관리 메뉴
	
}
