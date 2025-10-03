package com.backoven.catdogshelter.domain.notice.command.domain.repository;

import com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity.NoticeLikedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeLikedRepository extends JpaRepository<NoticeLikedEntity, Integer> {

    /* USER 좋아요 존재 여부 / 삭제 */
    @Query("""
           select (count(l) > 0)
           from NoticeLikedEntity l
           where id(l.notice) = :noticeId
             and id(l.user)   = :userId
           """)
    boolean existsByNoticeIdAndUserId(@Param("noticeId") Integer noticeId,
                                      @Param("userId")   Integer userId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
           delete from NoticeLikedEntity l
           where id(l.notice) = :noticeId
             and id(l.user)   = :userId
           """)
    int deleteByNoticeIdAndUserId(@Param("noticeId") Integer noticeId,
                                  @Param("userId")   Integer userId);


    /* HEAD(보호소장) 좋아요 존재 여부 / 삭제 */
    @Query("""
           select (count(l) > 0)
           from NoticeLikedEntity l
           where id(l.notice)      = :noticeId
             and id(l.head) = :headId
           """)
    boolean existsByNoticeIdAndHeadId(@Param("noticeId") Integer noticeId,
                                      @Param("headId")   Integer headId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
           delete from NoticeLikedEntity l
           where id(l.notice)      = :noticeId
             and id(l.head) = :headId
           """)
    int deleteByNoticeIdAndHeadId(@Param("noticeId") Integer noticeId,
                                  @Param("headId")   Integer headId);
}
