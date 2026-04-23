package jp.co.seminar.beans;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.seminar.dao.AccessLogDao;
import jp.co.seminar.dao.ImageDao;
import jp.co.seminar.dao.ReservationDao;
import jp.co.seminar.dao.RoomDao;
import jp.co.seminar.dao.UserDao;

public class MeetingRoom implements Serializable {

	private String date;
	private static final int INTERVAL = (60 * 60);
	private static final String[] PERIOD = { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00" };
	private RoomBean[] rooms;
	private static long serialVersionUID = 1L;
	private UserBean user;

	// コンストラクタ　loginサーブレットでログイン成功時インスタンス生成
	public MeetingRoom() {
		// rooms をセット
		RoomDao roD = new RoomDao();
		this.rooms = roD.findAll();
		// date をyyyy-MM-ddでセット
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.date = sdf.format(nowDate);
	}

	// 予約キャンセル処理
	public void cancel(ReservationBean reservation) throws Exception {
		//会議室予約情報で会議室をキャンセルします。
		try {
			ReservationDao reD = new ReservationDao();
			reD.delete(reservation);
		} catch (Exception e) {
			System.err.println("キャンセルエラー");//暫定処理
		}
	}

	//予約情報の生成
	public ReservationBean createReservation(String roomId, String start) {
		//予約日で会議室と時間帯を指定した会議室予約情報を生成します。
		String end;
		if (start.equals("16:00")) {
			end = "17:00";
		} else {
			end = PERIOD[startPeriod(start) + 1];
		}
		ReservationBean reB = new ReservationBean(roomId, date, start, end, user.getId());
		return reB;

	}

	//キャンセル情報の生成
	public ReservationBean createCancel(String roomId, String start) {

		ReservationBean reB = new ReservationDao().findCancel(date, start, roomId);
		return reB;

	}

	public String getDate() {
		return date;
	}

	public static String[] getPeriod() {
		return PERIOD;
	}

	// 一日分の予約状況を二次元配列で返す
	public ReservationBean[][] getReservations() {
		//会議室予約システムの利用日における予約状況を返します。
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

	// 予約状況String
	public String[][] getDisabled() {
		//会議室予約システムの利用日における予約状況を返します。

		// null の2次元配列とDAOを用意
		String[][] disabled = new String[rooms.length][PERIOD.length];
		ReservationDao reD = new ReservationDao();
		// 予約一覧を取得
		List<ReservationBean> reList = reD.findByDate(date);//date文字列のSQLdate型変換はDAOで行う
		// 予約リストの全件
		for (ReservationBean reB : reList) {
			// 部屋番号と時間
			String roomId = reB.getRoomId();
			String start = reB.getStart();

			// 配列の[room添え字][時間添え字]に代入
			disabled[roomIndex(roomId)][startPeriod(start)] = "disabled";

		}

		for (int i = 0; i < disabled.length; i++) {
			for (int j = 0; j < PERIOD.length; j++) {
				// 現在時刻が表の時刻より後なら
				if (!AfterNow(PERIOD[j])) {
					disabled[i][j] = "disabled";
				}
			}
		}
		return disabled;
	}

	// キャンセル可能状況String
	public String[][] getCanCancels() {
		//会議室予約システムの利用日における予約状況を返します。

		// null の2次元配列とDAOを用意
		String[][] cans = new String[rooms.length][PERIOD.length];
		ReservationDao reD = new ReservationDao();
		// 予約一覧を取得
		List<ReservationBean> reList = reD.findByDate(date);//date文字列のSQLdate型変換はDAOで行う
		// 予約リストの全件
		for (ReservationBean reB : reList) {
			// 予約のユーザーIDと自信のユーザーIDが一致かつスタートが現在時刻より後
			if (reB.getUserId().equals(this.user.getId()) && AfterNow(reB.getStart())) {
				String roomId = reB.getRoomId();
				String start = reB.getStart();

				// 配列の[room添え字][時間添え字]に代入
				cans[roomIndex(roomId)][startPeriod(start)] = "can";
			}

		}

		return cans;
	}

	private boolean AfterNow(String time) {
		//現在の時刻を取得
		LocalDateTime nowTime = LocalDateTime.now();
		//予約時刻を取得し比較できる形式に その前にgetDateとStartはStringなのでキャストを挟む
		LocalDate date1 = LocalDate.parse(date);
		LocalTime Time = LocalTime.parse(time);
		// 表の時刻
		LocalDateTime cellTime = LocalDateTime.of(date1, Time);
		// 現在時刻が表の時刻より後なら
		return cellTime.isAfter(nowTime);

	}

	// ルームIDが一致するものを検索
	public RoomBean getRoom(String roomId) {
		//会議室予約システムで利用できるすべての会議室を返します
		for (RoomBean roB : rooms) {
			if (roomId.equals(roB.getId())) {
				return roB;
			}
		}
		return null;
	}

	public RoomBean[] getRooms() {
		return rooms;
	}

	public String[] getRoomsName() {
		String[] roomsName = new String[rooms.length];
		for (int i = 0; i < roomsName.length; i++) {
			roomsName[i] = rooms[i].getName();
		}
		return roomsName;
	}

	public String[] getRoomsId() {
		String[] roomsId = new String[rooms.length];
		for (int i = 0; i < roomsId.length; i++) {
			roomsId[i] = rooms[i].getId();
		}
		return roomsId;
	}

	public UserBean getUser() {
		return user;
	}

	// ログイン処理
	public boolean login(String id, String password, String ip, String agent) {
		//会議室予約システムにログインします。
		UserDao uD = new UserDao();
		UserBean uB = uD.certificate(id, password);
		boolean result;
		if (uB != null) {
			this.user = uB;
			result = true;
		} else {
			result = false;
		}
		// 以下ログ記録
		AccessLogDao ALD = new AccessLogDao();

		ALD.addLog(id, result ? "OK" : "NG", ip, agent);
		return result;
	}

	// 予約の処理
	public void reserve(ReservationBean reservation) throws Exception {
		//予約登録
		//会議室予約情報で会議室Daoを利用し、予約します。
		//現在の時刻を取得
		LocalDateTime nowTime = LocalDateTime.now();
		//予約時刻を取得し比較できる形式に その前にgetDateとStartはStringなのでキャストを挟む
		LocalDate date = LocalDate.parse(reservation.getDate());
		LocalTime time = LocalTime.parse((reservation.getStart()));
		LocalDateTime reservationTime = LocalDateTime.of(date, time);
		ReservationDao reD = new ReservationDao();
		List<ReservationBean> reservationCheckList = reD.findByDate(reservation.getDate());
		//--ここから予約処理判定--
		//時刻を過ぎている場合
		if (nowTime.isAfter(reservationTime)) {//isAfeterで現在時刻が予約時刻を過ぎていないか確認
			throw new Exception("時刻が過ぎているため予約できません");
		}
		//予約済みかどうか判定
		//ここは予約をリスト形式で受け取る　Forで取り出しifで判定
		for (ReservationBean reB : reservationCheckList) {
			if (reB.getRoomId().equals(reservation.getRoomId()) && reB.getStart().equals(reservation.getStart())) {
				throw new Exception("すでに予約されています");
			}
		}
		if (!reD.insert(reservation)) {
			throw new Exception("予約できませんでした");
		}
	}

	// MR内で使用するプライベートメソッド
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

	// 日付変更
	public void setDate(String date) {
		this.date = date;
	}

	// MR内で使用するプライベートメソッド
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

	// 以下追加メソッド
	// ユーザーIDの存在チェック
	public boolean existsByUserId(String userId) {
		UserDao uD = new UserDao();
		return uD.existsByUserId(userId);
	}

	// ユーザー登録
	public void addUser(UserBean user) throws Exception {
		UserDao uD = new UserDao();
		if (!uD.addUser(user)) {
			throw new Exception("ユーザー登録に失敗しました");
		}
	}

	//　予約一覧を取得 セッションにCSV用のデータを格納
	public String getReservationList(String order, String date1, String date2, String room, String user,
			HttpServletRequest request) {
		if ("all".equals(room)) {
			room = null;
		} else if (room != null) {
			room = rooms[Integer.parseInt(room)].getName();
		}
		ReservationDao rD = new ReservationDao();
		List<String[]> list = rD.findAll(order, date1, date2, room, user);
		// セッションにlistをセット
		HttpSession session = request.getSession();
		session.setAttribute("CSVdata", list);
		String result = "";
		for (String[] str : list) {
			result += "<tr>"
					+ "<td>" + str[0] + "</td>"
					+ "<td>" + str[1] + "～" + str[2] + "</td>"
					+ "<td>" + str[3] + "</td>"
					+ "<td>" + str[4] + "</td>"
					+ "</tr>";
		}
		return result;
	}

	//追加要件 会議室の追加
	public void addRoom(String roomId, String roomName) {
		try {
			RoomDao roD = new RoomDao();
			roD.addRoom(roomId, roomName);
			this.rooms = roD.findAll();//表示のためのroomsをここで更新
		} catch (Exception e) {
			System.err.println("MeetingRoom:addRoomエラー");
		}
	}

	//会議室の削除
	public void deleteRoom(String roomId) {
		try {
			RoomDao roD = new RoomDao();
			roD.deleteRoom(roomId);
			this.rooms = roD.findAll();
		} catch (Exception e) {
			System.err.println("MeetingRoom:deleteRoomエラー");
		}
	}

	// UserDaoの削除メソッドを呼ぶ　useDel	
	public boolean deleteUser(String userId) {
		//インスタンス化
		UserDao userdao = new UserDao();

		// UserDaoの削除メソッドを呼び出し、その結果をそのまま返す
		return userdao.deleteUser(userId);
	}

	// アクセスログの取得
	public String getAccessLog() {

		AccessLogDao ALD = new AccessLogDao();
		List<AccessLogBean> list = ALD.findAll();
		String result = "";
		for (AccessLogBean ALB : list) {
			result += "<tr>"
					+ "<td>" + ALB.getId() + "</td>"
					+ "<td>" + ALB.getUserId() + "</td>"
					+ "<td>" + ALB.getResult() + "</td>"
					+ "<td>" + ALB.getWhenTime() + "</td>"
					+ "<td>" + ALB.getIp() + "</td>"
					+ "<td>" + ALB.getAgent() + "</td>"
					+ "</tr>";
		}
		return result;
	}

	//画像の追加
	public boolean addImage(int imageSize, String roomId, String imageName, String imageType,
			byte[] imageContent, Timestamp createdAt) {
		ImageBean image = new ImageBean(
				null,
				roomId,
				imageName,
				imageType,
				imageContent,
				imageSize,
				createdAt);
		ImageDao iD = new ImageDao();
		try {
			return iD.insertImage(image);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("MeetingRoom addImageエラー");
			return false;
		}
	}

	// 画像の表示
	public String getImageSrc(String roomId) {
		String result = null;
		ImageDao imageDao = new ImageDao();
		ImageBean image = imageDao.findById(roomId);
		// 検索結果なし
		if (image == null) {
			return null;
		}

		String imageSrc = null;
		// コンテンツと画像タイプがあるか？
		if (image.getImageContent() != null && image.getImageType() != null) {
			String base64 = Base64.getEncoder().encodeToString(image.getImageContent());
			imageSrc = "data:" + image.getImageType() + ";base64," + base64;
			result = imageSrc;
		}

		return result;
	}

	// CSV出力メソッド
	public void writeDataToCSV(List<String[]> data, HttpServletResponse response, String fileName)
			throws IOException {

		// CSVダウンロード用のレスポンス設定
		response.setContentType("text/csv; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		try (ServletOutputStream out = response.getOutputStream()) {

			// Excel文字化け対策用 BOM
			out.write(0xEF);
			out.write(0xBB);
			out.write(0xBF);

			//
			out.write("予約日,開始,終了,会議室,予約者\n".getBytes(StandardCharsets.UTF_8));

			// 一行ずつ
			for (String[] row : data) {
				StringBuilder csvRow = new StringBuilder();

				// 1情報ずつ
				for (String value : row) {
					String escapedValue = escapeSpecialCharacters(value);
					// エスケープしたものとカンマを入力
					csvRow.append(escapedValue).append(",");
				}

				if (csvRow.length() > 0) {
					csvRow.deleteCharAt(csvRow.length() - 1); // 最後のカンマ削除
				}
				// 改行と書き込み
				csvRow.append("\n");
				out.write(csvRow.toString().getBytes(StandardCharsets.UTF_8));
			}

			// ダウンロード出力
			out.flush();
		}
	}

	// エスケープ処理
	private static String escapeSpecialCharacters(String value) {
		if (value == null) {
			return "";
		}

		// ダブルクォートを二重化
		String escapedValue = value.replace("\"", "\"\"");

		// カンマ、改行、ダブルクォートを含む場合は全体をダブルクォートで囲む
		if (escapedValue.contains(",")
				|| escapedValue.contains("\n")
				|| escapedValue.contains("\r")
				|| escapedValue.contains("\"")) {
			escapedValue = "\"" + escapedValue + "\"";
		}

		return escapedValue;
	}

	public UserBean update(String id, String password, String name, String address, boolean isAdmin) {
		UserBean uB = new UserBean(id, name, address, password, isAdmin);
		UserDao uD = new UserDao();
		return uD.update(uB);
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "利用日:" + this.date + "利用時間:" + INTERVAL;
	}

}
