DELIMITER //

CREATE TRIGGER trg_sightingPostComment_blind_user
    AFTER UPDATE ON sightingPostComment
    FOR EACH ROW
BEGIN
    DECLARE blindCount INT;
    DECLARE newActivationDate VARCHAR(19);

    -- 게시글 블라인드 처리 시 user_id가 존재하는 경우
    IF OLD.is_blinded = 0 AND NEW.is_blinded = 1 AND NEW.user_id IS NOT NULL THEN

        -- 1. 블라인드 횟수 증가
        UPDATE user
        SET deactivation_times = deactivation_times + 1
        WHERE user_id = NEW.user_id;

        -- 2. 누적 블라인드 횟수 조회
        SELECT deactivation_times INTO blindCount
        FROM user
        WHERE user_id = NEW.user_id;

        -- 3. 정지/블랙 처리
        IF blindCount = 3 THEN
            SET newActivationDate = DATE_FORMAT(DATE_ADD(NOW(), INTERVAL 7 DAY), '%Y-%m-%d %H:%i:%s');
            UPDATE user
            SET user_status = 'BLACK',
                activation_date = newActivationDate
            WHERE user_id = NEW.user_id;

        ELSEIF blindCount = 5 THEN
            SET newActivationDate = DATE_FORMAT(DATE_ADD(NOW(), INTERVAL 15 DAY), '%Y-%m-%d %H:%i:%s');
            UPDATE user
            SET user_status = 'BLACK',
                activation_date = newActivationDate
            WHERE user_id = NEW.user_id;

        ELSEIF blindCount = 7 THEN
            SET newActivationDate = DATE_FORMAT(DATE_ADD(NOW(), INTERVAL 30 DAY), '%Y-%m-%d %H:%i:%s');
            UPDATE user
            SET user_status = 'BLACK',
                activation_date = newActivationDate
            WHERE user_id = NEW.user_id;

        ELSEIF blindCount >= 10 THEN
            SET newActivationDate = DATE_FORMAT(DATE_ADD(NOW(), INTERVAL 100 YEAR), '%Y-%m-%d %H:%i:%s');
            UPDATE user
            SET user_status = 'BLACK',
                activation_date = newActivationDate
            WHERE user_id = NEW.user_id;

        END IF;

    END IF;
END;
//

DELIMITER ;
