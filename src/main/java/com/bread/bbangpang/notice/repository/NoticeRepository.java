package com.bread.bbangpang.notice.repository;

import com.bread.bbangpang.notice.dto.NoticeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeDTO, Integer> {

    @Modifying
    @Query("UPDATE NoticeDTO n SET n.noticeViews = n.noticeViews + 1 where n.noticeNo = :noticeNo")
    int updateViews(@Param("noticeNo") Integer noticeNo);

    Page<NoticeDTO> findByNoticeTitleContaining(String searchKeyword, Pageable pageable);
    Page<NoticeDTO> findByNoticeContentContaining(String searchKeyword, Pageable pageable);

}
