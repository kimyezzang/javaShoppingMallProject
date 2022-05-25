package kim.yechan.yesinsa.java1.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import kim.yechan.yesinsa.java1.service.GuestImpl;
import kim.yechan.yesinsa.java1.service.HostImpl;

public class MenuImpl implements Menu, Code {
	
	public static Scanner scan = new Scanner(System.in);
	String id;	// ID값 받음
	Map<String,GuestImpl> join = new HashMap<String,GuestImpl>();	// id,pw 저장
	HostImpl hostImpl;
	GuestImpl guestImpl;
	
	private MenuImpl() {}
	private static MenuImpl menuimpl = new MenuImpl();	// instance
	public static MenuImpl getInstance() {
		if(menuimpl == null)
			{menuimpl = new MenuImpl();}
		return menuimpl;
	}
	
	@Override
	public void loginMenu() {	// 첫 시작 화면
	 
		hostImpl = HostImpl.getInstance();
		guestImpl = GuestImpl.getInstance();
		System.out.println("----------------의류쇼핑몰에 오신것을 환영합니다.--------------");
		System.out.println("  ***** 하기 원하시는 번호를 입력 후 Enter를 클릭 하십시오 ***** ");
		System.out.println("  1.관리자 로그인  2.회원 로그인  3.회원가입  4.비밀번호찾기  5.종료");
		System.out.print(">> 선택 : ");
	
		String menuNo = scan.next();
		
		try {
		switch(Integer.parseInt(menuNo)){								// 공통메뉴를 타서
			case 1 : commonMenu(HOST_MENU_LOGIN);	// 관리자 로그인 화면 이동
				break;
			case 2 : commonMenu(GUEST_MENU_LOGIN);	// 고객 로그인 화면 이동
				break;
			case 3 : commonMenu(GUEST_JOIN);	// 회원가입 화면 이동
				break;
			case 4 : commonMenu(GUEST_SERCH_PASSWORD);		// 비밀번호 찾기
				break ;
			case 5 : 
				System.out.println(" 종료되었습니다.. ");	// 종료
				break;
			default :
				System.out.println(" *****       잘못된 번호입니다. 다시 입력 바랍니다.      *****");	// 다른 번호 입력시 화면 다시 호출
				loginMenu();
				break ;
		}
	} 
	 catch (Exception e){
		System.out.println(" *** 잘못된 입력으로 인한 오류로 인해 다시 입력 바랍니다. ***");
		loginMenu();
	 }
		
	}
// 첫 시작 화면 끝

	@Override	// 시작화면의 공통메뉴
	public void commonMenu(int menuNo) {
		switch (menuNo) {
		case HOST_MENU_LOGIN : hostLogin();
			break;
		case GUEST_MENU_LOGIN :guestLogin();
			break;
		case GUEST_JOIN : guestJoin();
			break;
		case GUEST_SERCH_PASSWORD : guestSerchPassword();
			break;
		default :
			break ;
		}	
	}	// 시작화면의 공통메뉴 끝
	
	@Override
	public void hostLogin() {	// 관리자 로그인
		System.out.println("-----------------------------------------------------");
		System.out.print(" * ID를 입력하세요 : " );
		String hostid = scan.next();
		if(hostid.equals(hostImpl.getHostId())) {			// 입력받은 ID랑 기존 관리자 ID 일치 여부 
			System.out.print(" * Password를 입력하세요 : ");
			String hostpw = scan.next();
			if(hostpw.equals(hostImpl.getHostPw())) {		// ID PW 동일 여부
				System.out.println(" * 로그인 되었습니다.");
				hostView();									// 관리자 화면 이동
			}
			else {
				System.out.println(" * 비밀번호가 일치하지 않습니다.");
				loginMenu();	
			}
	}else {
		System.out.println(" * 존재하는 ID가 없습니다.");
		loginMenu();	
	}
		// loginMenu();						// 첫 시작화면 이동
		}									// 관리자 로그인 끝								
	
	@Override
	public void guestLogin() {		// 고객 로그인 창
		
		System.out.print(" ID를 입력해주세요 : ");
		String idTest = scan.next();
		if(join.containsKey(idTest)) {					// ID 존재 여부 확인
			System.out.print(" * Password를 입력해주세요 : ");
			String pwTest = scan.next();
			if(join.get(idTest).getGuestPw().equals(pwTest)) {			// ID PW 동일 여부 확인
				System.out.println(" * 로그인 성공하였습니다. 고객페이지로 이동합니다.");
				this.id = idTest;						// 로그인시 매개변수 ID값에 현재 ID값 대입
				guestView();		// 고객페이지 이동
			}
			else {
				System.out.println("*****           비밀번호가 일치하지 않습니다.            *****");
				System.out.println("*****              처음화면으로 이동합니다.             *****");
				loginMenu();	
			}
		}
			else {
				System.out.println("*****           입력하신 ID가 존재하지 않습니다.          *****");
				System.out.println("*****              처음화면으로 이동합니다.             *****");
				loginMenu();	
			}
													// 첫 시작화면 이동
}	//	고객 로그인창 끝
	@Override
	public void guestJoin() {	// 회원가입 화면
		
		System.out.print(" * ID를 입력해 주세요 : ");
		String idTest = scan.next();
		
		if(idTest.length() <= 4 || idTest.length() >= 21)	// 5~20자 ID 요청
		{
			System.out.println(" *** ID는 5글자 ~ 20글자로 해주세요 ***");
			guestJoin();
		}
		
		else {if(join.containsKey(idTest)) {
			System.out.print(" * 이미 존재하는 ID입니다. 다른");			// 존재 ID일 경우 다른 ID 요구
			guestJoin();
		}
		
		System.out.print(" * Password를 입력해주세요 : ");
		String pwTest = scan.next();
		System.out.println(" * 하기 비밀번호 힌트를 입력해주세요");
		System.out.print(" * 출신 초등학교 : ");
		String pwTestHint = scan.next();
		join.put(idTest,new GuestImpl(idTest,pwTest,pwTestHint));	// HashMap에 아이디 , 비밀번호 추가
		
		System.out.println(" *** 회원가입이 완료 되었습니다. *** ");
		loginMenu();}	// 로그인 화면 호출
	}							// 회원가입 화면 끝
	
	
	public void guestSerchPassword() {	// 비밀번호 찾기 메뉴
		System.out.print("ID를 입력해주세요 : ");
		String idTest = scan.next();
		if(join.containsKey(idTest)) {	// ID가 존재 할 경우
			System.out.println(" * 하기 질문의 답을 입력해주세요.");
			System.out.print(" * 출신 초등학교 : ");
			String pwHint = scan.next();
			if(join.get(idTest).getGuestPwHint().equals(pwHint)) {	// 해당ID의 힌트랑 일치할 경우
				System.out.print(" * 해당 ID의 비밀번호 입니다 : ");
				String answer = join.get(idTest).getGuestPw();	// 해당 ID의 비밀번호 값 가져온다
				System.out.println(answer);
				loginMenu();
			}
			else {
				System.out.println(" * 답이 일치하지 않습니다.");
				loginMenu();
			}
		}
		else {
			System.out.println(" * ID가 존재하지 않습니다.");
			loginMenu();
		}
	}
	
	@Override
	public void guestView() {	// 고객 메뉴 화면
		
		System.out.println("-----------------------고객 메뉴------------------------");
		System.out.println("     1.장바구니  2.장바구니구매   3.바로구매   4.환불   5.로그아웃  ");						
		System.out.println("-----------------------------------------------------");
		
		String guestTry = scan.next();
		try {
		switch(Integer.parseInt(guestTry)) {								// 고객메뉴 공통화면 이동
			case 1 : guestCommonMenu(GUEST_CART_LIST);	// 장바구니
				break;
			case 2 : guestCommonMenu(GUEST_CART_BUY);				// 장바구니 구매
				break;
			case 3 : guestCommonMenu(GUEST_NOW_BUY);					// 바로 구매
				break;
			case 4 : guestCommonMenu(GUEST_REFUND);						// 환불
				break;
			case 5 : loginMenu();										// 첫 시작화면 이동
				break;
			default :
				System.out.println("-------잘못된 번호를 입력하였습니다. 다시 입력 하십시오-------");
				guestView();
				break;}
		}
		 catch (Exception e){
				System.out.println(" *** 잘못된 입력으로 인한 오류로 인해 다시 입력 바랍니다. ***");
				guestView();
			 }
		}
		// 고객 메뉴화면 끝

	@Override
	public void guestCommonMenu(int menuNo) {		// 고객메뉴 공통화면
		switch (menuNo) {
		case GUEST_CART_LIST : 								// 장바구니
			hostImpl.productList();							// 상품리스트 호출
			guestImpl.cartAdd(id);			// 현재 ID 값 전달
			guestView(); 					// 고객메뉴 화면 이동
			break;
		case GUEST_CART_BUY :								// 장바구니 구매
			guestImpl.cartBuy();
			guestView();
			break;
		case GUEST_NOW_BUY : 								// 바로 구매
			hostImpl.productList(); 						
			guestImpl.nowBuy(id);
			guestView();
			break;
		case GUEST_REFUND : 									// 환불
			guestImpl.refund(id);
			guestView();
			break ;
		default :
			break ;
		}	
	}

	@Override
	public void hostView() {	// 관리자 메뉴
		
		System.out.println("-----------------------관리자 메뉴----------------------");
		System.out.println("      1.재고관리      2.주문관리      3.로그아웃     ");						
		System.out.println("-----------------------------------------------------");
		String hostKey = scan.next();
		
		try {
		switch(Integer.parseInt(hostKey)){	// 관리자 공통메뉴 이동
		case 1:	 commonHostMenu(HOST_STOCK_MENU);	// 재고관리
			break;
		case 2:  commonHostMenu(HOST_ORDER_MENU);	// 주문관리
			break;
		case 3:	 loginMenu();						// 첫 시작화면 이동
			break;
		default :	System.out.println("-------잘못된 번호를 입력하였습니다. 다시 입력 하십시오-------");
			hostView();
			break;
		}	
		} 
		catch (Exception e){
				System.out.println(" *** 잘못된 입력으로 인한 오류로 인해 다시 입력 바랍니다. ***");
				hostView();
			 }
		}	// 관리자 메뉴 끝
	
	@Override
	public void commonHostMenu(int hostMenuNo) {	// 관리자 공통메뉴 
		switch (hostMenuNo) {
		case HOST_STOCK_MENU : hostProductMenu();	// 재고관리
			break;
		case HOST_ORDER_MENU : hostOrderMenu();		// 주문관리
			break;
		default :
			break ;
		}	
	}	// 관리자 메뉴 화면 끝

	@Override	// 관리자 재고관리 메뉴
	public void hostProductMenu() {
		
		System.out.println("----------------------재고관리 메뉴----------------------");
		System.out.println("      1.목록    2.추가     3.수정     4.삭제     5.이전");						
		System.out.println("-----------------------------------------------------");
		
		String hostProductMenuNo = scan.next();
		
		try {
		switch(Integer.parseInt(hostProductMenuNo)){
		case 1 : hostProductCommomMenu(HOST_GOODS_LIST);	// 목록
			break ;
		case 2 : hostProductCommomMenu(HOST_GOODS_ADD);		// 추가
			break;
		case 3 : hostProductCommomMenu(HOST_GOODS_UPDATE);	// 수정
			break;
		case 4 : hostProductCommomMenu(HOST_GOODS_DEL);		// 삭제
			break;
		case 5 : hostView();			// 관리자 메뉴 이동
			break;
		default : System.out.println("잘못 입력하였습니다.");
			break;
		}}
		catch(Exception e) {
			System.out.println(" *** 잘못된 입력으로 인한 오류로 인해 다시 입력 바랍니다. ***");
			hostProductMenu();
		}
}	// 재고관리 메뉴 끝
	
	@Override
	public void hostProductCommomMenu(int menuNo) {	// 재고관리 공통 메뉴
		switch (menuNo) {
		case HOST_GOODS_LIST : 						// 상품 목록
			hostImpl.productList();					// 등록된 상품 목록 
			hostProductMenu();				// 재고관리 메뉴
			break;
		case HOST_GOODS_ADD : 						
			hostImpl.productAdd();					// 상품 추가
			hostProductMenu();
			break;
		case HOST_GOODS_UPDATE :
			hostImpl.productUpdate();				// 상품 수정
			hostProductMenu();
			break;
		case HOST_GOODS_DEL :	
			hostImpl.productRemove();				// 상품 삭제
			hostProductMenu();
			break;
		default :
			break ;
		}	
	}	// 재고관리 공통메뉴 끝

	@Override
	public void hostOrderMenu() {	// 관리자 주문관리 메뉴
		System.out.println("----------------------주문관리 메뉴----------------------");
		System.out.println("      1.주문목록    2.결제승인    3.결제취소   4.결산   5.이전");						
		System.out.println("-----------------------------------------------------");
		
		String hostOrderMenuNo = scan.next();
		try {
		switch(Integer.parseInt(hostOrderMenuNo)){
			
		case 1 : hostOrderCommonMenu(HOST_ORDER_LIST);	// 주문 목록
			break ;
		case 2 : hostOrderCommonMenu(HOST_ORDER_CONFIRM);	// 결제 승인
			break;
		case 3 : hostOrderCommonMenu(HOST_ORDER_CANCEL);	// 결제 취소
			break;
		case 4 : hostOrderCommonMenu(HOST_ORDER_TOTAL);		// 결산
			break;
		case 5 : hostView();
			break;
		default : System.out.println("잘못 입력하였습니다.");
			break;
		}}
		catch (Exception e) {
			System.out.println(" *** 잘못된 입력으로 인한 오류로 인해 다시 입력 바랍니다. ***");
			hostOrderMenu();
		}
	}

	@Override
	public void hostOrderCommonMenu(int menuNo) {	// 주문관리 공통메뉴
		switch (menuNo) {
		case HOST_ORDER_LIST : 						// 주문 목록
			hostImpl.orderList();			// 고객 주문 목록 호출
			hostOrderMenu();			
			break;
		case HOST_ORDER_CONFIRM : 					// 결제 승인			
			hostImpl.orderConfirm();		// 고객 주문 목록 호출 및 결제 승인
			hostOrderMenu();
			break;
		case HOST_ORDER_CANCEL :					// 결제 취소
			hostImpl.orderCancel();			// 고객 주문 목록 호출 및 결제 취소
			hostOrderMenu();
			break;		
		case HOST_ORDER_TOTAL :						// 결산
			hostImpl.saletotal();			
			hostOrderMenu();
			break;
		default :
			break ;
		}	
	}
}
