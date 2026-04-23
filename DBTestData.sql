------テスト用データ　ここから下をコピペして(１４行目は終了を１時間後にしている)（）----------------------

INSERT INTO user VALUES('1100003','xxxxxx','情報太郎','東京都','0','0');
INSERT INTO user VALUES('1100015','yyyyyy','情報花子','大阪府','0','0');
INSERT INTO user VALUES('1111','admin','管理者ログイン','管理','1','0');

INSERT INTO room VALUES('0501','大会議室',0);
INSERT INTO room VALUES('0502','５Ａ会議室',0);
INSERT INTO room VALUES('0503','５Ｂ会議室',0);

INSERT INTO reservation VALUES(NULL,'0501','2026-04-18','09:00:00','10:00:00','1100003');
INSERT INTO reservation VALUES(NULL,'0501','2026-04-18','11:00:00','12:00:00','1100003');
INSERT INTO reservation VALUES(NULL,'0502','2026-04-19','09:00:00','10:00:00','1100003');
INSERT INTO reservation VALUES(NULL,'0502','2026-04-19','12:00:00','13:00:00','1100003');
INSERT INTO reservation VALUES (NULL,'0503','2026-04-19','13:00:00',ADDTIME('13:00:00', '01:00:00'),'1100003');
INSERT INTO reservation VALUES(NULL,'0503','2026-04-19','14:00:00','15:00:00','1100015');

------テスト用データ　ここまで----------------------

------テストデータが入ってるか確認用　ここから下----------------------
SELECT * FROM user;
SELECT * FROM room;
SELECT * FROM reservation;

SELECT * FROM reservation, user, room WHERE reservation.roomid = room.id AND reservation.userid = user.id;

SELECT name, address FROM user WHERE id = '1100015' AND password = 'yyyyyy';