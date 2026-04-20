package jp.co.seminar.beans;

import java.io.Serializable;

public class SearchBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String order;
	private String date1;
	private String date2;
	private String roomIndex;
	private String user;

	public SearchBean() {
	}

	public SearchBean(String order, String date1, String date2, String room, String user) {
		this.order = order;
		this.date1 = date1;
		this.date2 = date2;
		if (room == null || room.isEmpty()) {
			room = "all";
		}
		this.roomIndex = room;
		this.user = user;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getRoomIndex() {
		return roomIndex;
	}

	public void setRoomIndex(String room) {
		this.roomIndex = room;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getIsASC() {
		return "ASC".equals(this.order) ? "selected" : "";
	}

	public String getIsDESC() {
		return "DESC".equals(this.order) ? "selected" : "";
	}

	public String getIsAll() {
		return "all".equals(this.roomIndex) ? "selected" : "";
	}

	public String isRoom(int num) {
		String number = String.valueOf(num);
		return number.equals(this.roomIndex) ? "selected" : "";
	}
}