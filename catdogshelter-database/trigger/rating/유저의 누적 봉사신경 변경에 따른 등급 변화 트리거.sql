DELIMITER //

CREATE TRIGGER trg_user_before_update_rating
    BEFORE UPDATE ON user
    FOR EACH ROW
BEGIN
    DECLARE new_rating_id INT;

    -- 기존 사용자가 일반회원(0~3)일 경우에만 실행
    IF OLD.rating_id BETWEEN 0 AND 3 THEN

        -- 누적 봉사시간이 변경된 경우만 실행
        IF NEW.cumulative_volunteer_time <> OLD.cumulative_volunteer_time THEN

            -- 해당 누적 봉사시간에 맞는 등급 찾기 (일반회원 등급만)
            SELECT id
            INTO new_rating_id
            FROM rating
            WHERE standard <= NEW.cumulative_volunteer_time
              AND id BETWEEN 0 AND 3   -- 일반회원 등급만
            ORDER BY standard DESC
            LIMIT 1;

            -- 등급 변경 필요 시 반영
            IF new_rating_id IS NOT NULL AND new_rating_id <> OLD.rating_id THEN
                SET NEW.rating_id = new_rating_id;
            END IF;

        END IF;
    END IF;
END//

DELIMITER ;