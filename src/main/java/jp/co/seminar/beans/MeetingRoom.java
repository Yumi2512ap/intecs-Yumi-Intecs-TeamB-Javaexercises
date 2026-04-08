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

	public ReservationBean[][] getReservations() {
		return;//帰り値分からん　追記予定
	}

	public RoomBean getRoom(String roomId) {
		return;//帰り値分からん　追記予定
	}

	public RoomBean[] getRooms() {
		return;//帰り値分からん　追記予定
	}

	public UserBean getUser() {
		return;//帰り値分からん　追記予定
	}

	public boolean login(String id, String password) {

	}

	public void reserve(ReservationBean reservation) {

	}

	private int roomIndex(String roomId) throws IndexOutOfBoundsException {
		String[] room = { "0501", "0502", "0503" };
		for (int i = 0; i < room.length; i++) {
			if (room.equals(roomId)) {
				return i;
			}
		}throw new IndexOutOfBoundsException("会議室が存在しません");

	}

	public void setDate(String date) {
		this.date = date;
	}

	private int startPeriod(String start) throws IndexOutOfBoundsException {
		int startTime = 9;
		int endTime = 16;
		int time = Integer.parseInt(start.substring(0, 2));
		if (time < startTime || time > endTime) {
			throw new IndexOutOfBoundsException("利用時間帯の範囲外です");
		}
		return time - startTime;

	}

	@Override
	public String toString() {
		return "利用日:" + this.date + "利用時間:" + INTERVAL;
	}

}
