package ex07_constructor;

public class Book {
	
	// 필드
	String title; // 책 제목
	String author; // 책 저자
	int price; // 책 가격
	
	// 생성자
	
	Book(String bookTitle){
		title = bookTitle;
		author = "미상";
		// price는 이미 초기화 값이 0이므로 price = 0; 이건 굳이 안 써도 됨
	}
	
	Book(String bookTitle, String bookAuthor){
		title = bookTitle;
		author = bookAuthor;
	}
	
	Book(String bookTitle, String bookAuthor, int bookPrice) {
		
		title = bookTitle;
		author = bookAuthor;
		price = bookPrice;		
		
	}
	
	// 메소드
	void info() {
		System.out.println("제목 : " + title);
		System.out.println("저자 : " + author);
		System.out.println("가격 : " + price);
		System.out.println();
	}
	
	

}
