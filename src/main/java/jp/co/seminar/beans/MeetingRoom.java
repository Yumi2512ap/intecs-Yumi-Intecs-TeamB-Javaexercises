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

	public void cancel(resevationBean reservation) {

	}

	public ReservationBean createReservation(String roomId, String start) {

	}

	public String getDate() {
		return date;
	}

	public static String[] getPeriod() {
		return PERIOD;
	}

	public static ReservationBean[][] getReservations() {
		return;//帰り値分からん　追記予定
	}

	public static RoomBean getRoom(String roomId) {
		return;//帰り値分からん　追記予定
	}

	public RoomBean[] getRooms() {
		return;//帰り値分からん　追記予定
	}

	public UserBean getUser() {
		return ;//帰り値分からん　追記予定
	}

	public boolean login(String id, String password) {

	}

	public void reserve(ReservationBean reservation) {

	}

	private static int roomIndex(String roomId) throws IndexOutOfBoundsException {

	}

	public void setDate(String date) {
		this.date = date;
	}

	private static int startPeriod(String start) {

	}
	@Override
	public String toString() {
		return "";
	}

}
