drop procedure loopInsert;
CREATE PROCEDURE loopInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE base DATE DEFAULT '2022-01-01';
    DECLARE j DATE DEFAULT '2022-01-01';

    WHILE i <= 2000
        DO
            INSERT INTO whereismyhome.rooms
            (lat, lng, comment, user_id, subject, register_time, start_date, end_date, deposit, monthly)
            VALUES
                (CONCAT(34 + RAND() * 4), CONCAT(126 + RAND() * 2),
                 concat('Random comment', i), concat('test', 1 + floor(rand()*10)) , concat('Random subject', i),
                 NOW(), j,
                 DATE_ADD(j, INTERVAL FLOOR(RAND() * 365) DAY),
                 (100 + 100 * floor(RAND() * 20)), FLOOR(20 + 5 * floor(rand() * 20)));
            SET i = i + 1;
            set j = DATE_ADD(base, INTERVAL FLOOR(30 * RAND() * 10) DAY);
        END WHILE;
END;

call loopInsert()