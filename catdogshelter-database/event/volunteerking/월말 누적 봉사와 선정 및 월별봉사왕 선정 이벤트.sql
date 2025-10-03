-- 이벤트를 활성화하기 위한 설정, 이걸 키기 위해서는 특정 권한의 계정이 필요
-- 현재는 서버부팅마다 새로 해줘야 함-> 컴퓨터 부팅마다 새로 적용
-- 나중에 mariaDB의 설정 파일에서 서버 부팅시 자동 적용하는 걸로 변경하는 것이 좋아 보임
SET GLOBAL event_scheduler = ON;

DELIMITER //

CREATE EVENT ev_test_volunteer_king
    ON SCHEDULE
        EVERY 1 MONTH
        STARTS (LAST_DAY(CURRENT_DATE) + INTERVAL '23:59:59' HOUR_SECOND)
    DO
    BEGIN
        DECLARE top_user_id_total INT;
        DECLARE top_time_total INT;
        DECLARE top_user_id_month INT;
        DECLARE top_time_month INT;

        SELECT user_id, cumulative_volunteer_time
        INTO top_user_id_total, top_time_total
        FROM user
        ORDER BY cumulative_volunteer_time DESC
        LIMIT 1;

        UPDATE volunteerKing
        SET user_id = top_user_id_total,
            volunteer_time = top_time_total
        WHERE category = 0;

        SELECT user_id, month_volunteer_time
        INTO top_user_id_month, top_time_month
        FROM user
        ORDER BY month_volunteer_time DESC
        LIMIT 1;

        INSERT INTO volunteerKing (category, year, month, volunteer_time, user_id)
        VALUES (1, YEAR(CURDATE()), MONTH(CURDATE()), top_time_month, top_user_id_month);

        UPDATE user
        SET month_volunteer_time = 0;
    END//

DELIMITER ;

