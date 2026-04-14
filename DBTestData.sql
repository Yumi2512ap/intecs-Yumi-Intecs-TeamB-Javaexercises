------テスト用データ　ここから下をコピペして----------------------

INSERT INTO user VALUES('1100003','xxxxxx','情報太郎','東京都','0');
INSERT INTO user VALUES('1100015','yyyyyy','情報花子','大阪府','0');

INSERT INTO room VALUES('0501','大会議室','0');
INSERT INTO room VALUES('0502','５Ａ会議室','0');
INSERT INTO room VALUES('0503','５Ｂ会議室','0');

INSERT INTO reservation VALUES(NULL,'0501','テスト実施前日','09:00:00','10:00:00','1100003');
INSERT INTO reservation VALUES(NULL,'0501','テスト実施前日','11:00:00','12:00:00','1100003');
INSERT INTO reservation VALUES(NULL,'0502','テスト実施日','09:00:00','10:00:00','1100003');
INSERT INTO reservation VALUES(NULL,'0502','テスト実施日','12:00:00','13:00:00','1100003');
INSERT INTO reservation VALUES(NULL,'0503','テスト実施日','テスト実施後時間','テスト実施後時間＋１時間','1100003');
INSERT INTO reservation VALUES(NULL,'0503','テスト実施日','13:00:00','14:00:00','1100015');

------テスト用データ　ここまで----------------------

------テストデータが入ってるか確認用　ここから下----------------------
SELECT * FROM user;
SELECT * FROM room;
SELECT * FROM reservation;

SELECT * FROM reservation, user, room WHERE reservation.roomid = room.id AND reservation.userid = user.id;

SELECT name, address FROM user WHERE id = '1100015' AND password = 'yyyyyy';