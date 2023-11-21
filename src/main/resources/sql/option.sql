drop procedure loopInsert;

SET @beginId = 0;
SET @endId = 0;

CREATE PROCEDURE getBeginEnd(INOUT beginId INT, INOUT endId INT)
BEGIN
    SELECT min(id) INTO beginId FROM rooms;
    SELECT max(id) INTO endId FROM rooms;
END;

CALL getBeginEnd(@beginId, @endId);

SELECT @beginId, @endId;

CREATE PROCEDURE loopInsert()
BEGIN
    DECLARE i INT DEFAULT @beginId;
    DECLARE j int DEFAULT 1;
    declare t int default 1;
    WHILE i <= @endId
        DO
            while t <= j
                do
                    insert into options(room_id, opt)
                    values (i, t);
                    set t = t + 1;
                end while;
        SET i = i + 1;
        set t = 1;
        set j = floor(rand()*8);
    END WHILE;
END;

call loopInsert();

select * from options;