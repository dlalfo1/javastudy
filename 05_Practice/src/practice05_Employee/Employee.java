package practice05_Employee;

// 사원

public abstract class Employee {    // 사원(부모클래스)
 									// 추상 메소드를 가졌기 때문에 추상 클래스로 작성함

	// 필드
	private int empNo;    // 사원번호
	private String name;  // 사명
	
	// 생성자
	public Employee(int empNo, String name) {
		super();
		this.empNo = empNo; 
		this.name = name;
	}

	// 메소드
	public int getEmpNo() {
		return empNo;
	}
	
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void info() {
		System.out.print("[사원번호:" + empNo + ", 사원명:" + name + "]");
	}
	
	public abstract int getPay();  // 모든 사원들의 월급을 반환하는 추상 메소드. 정규/비정규에 따라 계산법이 다르기 때문에 추상 메소드로 작성
								   // 클래스별로 getPay 메소드를 만든 후 본문을 만들어줘야한다. (추상메소드이기 때문에)
	
	// info, getPay 메소드는 Regular, Temporary 클래스에서 다 사용할것임.
}