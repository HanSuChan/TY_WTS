package com.ty.webproject.letter.dto;

public class LetterDTO {

	private String to_id;
	private String from_id;
	private String title;
	private String content;
	private String toCheck;
	private String fromCheck;
	private int	letter_date;
	
	
	public LetterDTO(String to_id, String from_id, String title, String content, String toCheck, String fromCheck,
			int letter_date) {
		super();
		this.to_id = to_id;
		this.from_id = from_id;
		this.title = title;
		this.content = content;
		this.toCheck = toCheck;
		this.fromCheck = fromCheck;
		this.letter_date = letter_date;
	}
	public String getTo_id() {
		return to_id;
	}
	public void setTo_id(String to_id) {
		this.to_id = to_id;
	}
	public String getFrom_id() {
		return from_id;
	}
	public void setFrom_id(String from_id) {
		this.from_id = from_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getToCheck() {
		return toCheck;
	}
	public void setToCheck(String toCheck) {
		this.toCheck = toCheck;
	}
	public String getFromCheck() {
		return fromCheck;
	}
	public void setFromCheck(String fromCheck) {
		this.fromCheck = fromCheck;
	}
	public int getLetter_date() {
		return letter_date;
	}
	public void setLetter_date(int letter_date) {
		this.letter_date = letter_date;
	}
	
	

	
}
