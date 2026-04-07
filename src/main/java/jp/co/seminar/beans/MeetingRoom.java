package jp.co.seminar.beans;

import java.io.Serializable;

public class MeetingRoom implements Serializable {

	private String date;
	private static final int INTERVAL = (60 * 60);
	private static final String[] PERIOD = { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00" };
	private RoomBean[] rooms;
	private static long serialVersionUID = 1L;
	private UserBean user;

	public MeetingRoom() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public static String[] getPeriod() {
		return PERIOD;
	}

	public RoomBean[] getRooms() {
		return rooms;
	}

	public UserBean getUser() {
		return user;
	}
}
