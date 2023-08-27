package com.ty.webproject.member.dto;

public class MemberDTO {
	private String id;
	private String userpw1; // 비밀번호 입력 필드
	private String userpw2; // 비밀번호 확인 입력 필드
	private String name;
	private String regnum1; // 주민등록 번호 앞 6자리
	private String regnum2; // 주민등록 번호 뒷 7자리
	private String email;
	private String phone;
	private String sex;
	private int year; // 생년 (년도)
	private int month; // 생월 (월)
	private int day; // 생일 (일)
	private String address;
	private String extraAddress;
	private String detailAddress;
	private Long member_id;

	public MemberDTO() {

	}

	public MemberDTO(String id, String userpw1, String userpw2, String name, String regnum1, String regnum2, String email,
			String phone, String sex, int year, int month, int day, String address, String extraAddress,
			String detailAddress, Long member_id) {
		this.id = id;
		this.userpw1 = userpw1;
		this.userpw2 = userpw2;
		this.name = name;
		this.regnum1 = regnum1;
		this.regnum2 = regnum2;
		this.email = email;
		this.phone = phone;
		this.sex = sex;
		this.year = year;
		this.month = month;
		this.day = day;
		this.address = address;
		this.extraAddress = extraAddress;
		this.detailAddress = detailAddress;
		this.member_id = member_id;
	}

	
public Long getMember_id() {
		return member_id;
	}

	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}

public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserpw1() {
		return userpw1;
	}

	public void setUserpw1(String userpw1) {
		this.userpw1 = userpw1;
	}

	public String getUserpw2() {
		return userpw2;
	}

	public void setUserpw2(String userpw2) {
		this.userpw2 = userpw2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegnum1() {
		return regnum1;
	}

	public void setRegnum1(String regnum1) {
		this.regnum1 = regnum1;
	}

	public String getRegnum2() {
		return regnum2;
	}

	public void setRegnum2(String regnum2) {
		this.regnum2 = regnum2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getExtraAddress() {
		return extraAddress;
	}

	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

@Override
public String toString() {
	return "memberDTO (id=" + id +" "+ "userpw1=" + userpw1 +" "+ "userpw2=" + userpw2+" "
	+ "name=" + name + " " +"regnum1=" + regnum1	+" " + "regnum2=" +	 regnum2 +" "+ "email=" + email +" "+ "phone=" + phone
	+" "+ "sex=" + sex +" "+ "year=" + year+" " + "month=" + month +" "+ "day=" + day +" "+ "address=" + address
	+" "+ "extraAddress=" + extraAddress +" "+"detailAddress=" + detailAddress + ")";
}



}
