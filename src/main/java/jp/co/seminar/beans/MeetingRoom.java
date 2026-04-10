package jp.co.seminar.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import bean.UserBean;

public class MeetingRoom implements Serializable {

	private String date;
	private static final int INTERVAL = (60 * 60);
	private static final String[] PERIOD = { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00" };
	private RoomBean[] rooms;
	private static long serialVersionUID = 1L;
	private UserBean user;

	public MeetingRoom() {
		// rooms をセット
		RoomDao roD = new RoomDao();
		this.rooms = roD.findAll();
		// date をyyyy-MM-ddでセット
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.date = sdf.format(nowDate);
	}

	public void cancel(ResevationBean reservation) throws Exception {
		try {
			ReservationDao reD = new ReservationDao();
			reD.delete(reservation);
		} catch (Exception e) {
			System.err.println("キャンセルエラー");//暫定処理
		}
	}

	public ReservationBean createReservation(String roomId, String start) {
		// しおり46
		// end情報→スタートの次
		String end = PERIOD[startPeriod(start) + 1];
		ReservationBean reB = new ReservationBean(roomId, date, start, end, user.getId());
		return reB;

	}

	public String getDate() {
		return date;
	}

	public static String[] getPeriod() {
		return PERIOD;
	}

	public ReservationBean[][] getReservations() {

		// null の2次元配列とDAOを用意
		ReservationBean[][] reBs = new ReservationBean[rooms.length][PERIOD.length];
		ReservationDao reD = new ReservationDao();
		// 予約一覧を取得
		List<ReservationBean> reList = reD.findByDate(date);//date文字列のSQLdate型変換はDAOで行う
		// 予約リストの全件
		for (ReservationBean reB : reList) {
			// 部屋番号と時間
			String roomId = reB.getRoomId();
			String start = reB.getStart();

			// 配列の[room添え字][時間添え字]に代入
			reBs[roomIndex(roomId)][startPeriod(start)] = reB;

		}
		return reBs;
	}

	public RoomBean getRoom(String roomId) {
		for (RoomBean roB : rooms) {
			if (roomId.equals(roB.getId)) {
				return roB;
			}
		}
	}

	public RoomBean[] getRooms() {
		return rooms;
	}

	public UserBean getUser() {
		return user;
	}

	public boolean login(String id, String password) {
		// しおり26 , 47
		UserDao uD = new UserDao();
		UserBean uB = uD.certificate(id, password);
		return uB != null;
	}

	public void reserve(ReservationBean reservation) throws Exception {
		//予約登録
		//会議室予約情報で会議室Daoを利用し、予約します。
		LocalDateTime nowTime = LocalDateTime.now();
		LocalDateTime reservationTime = LocalDateTime.of(reservation.getDate(), reservation.getStart());
		ReservationDao reD = new ReservationDao();
		List<ReservetionBean> reservationCheck = reD.findByDate(reservation.getDate);
		//時刻を過ぎている場合
		if (nowTime.isAfter(reservationTime)) {
			throw new Exception("時刻が過ぎているため予約できません");
		}
		//予約済みかどうか判定
		//ここは予約をリスト形式で受け取る　Forで取り出しifで判定
		for (String reC : reservationCheck) {
			if (reC.getRoomId.equals(reservation.getRoomId) && reC.getStart.equals(reservation.getStart)) {
				throw new Exception("すでに予約されています");
			}
		}
		if (!reD.insert(reservation)) {
			throw new Exception("予約できませんでした");
		}
	}

	private int roomIndex(String roomId) throws IndexOutOfBoundsException {
		//roomIdが配列にあった場合その添え字を返すメソッド
		//		RoomDao roD = new RoomDao();
		//		RoomBean[] Rooms = roD.findAll();
		for (int i = 0; i < this.rooms.length; i++) {
			if (this.rooms[i].getId().equals(roomId)) {
				return i;
			}
		}
		throw new IndexOutOfBoundsException("会議室が存在しません");
	}

	public void setDate(String date) {
		this.date = date;
	}

	private int startPeriod(String start) throws IndexOutOfBoundsException {
		//受け取った入力時間を添え字で返す
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
