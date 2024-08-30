package com.bread.bbangpang.inquiry.repository;

import com.bread.bbangpang.inquiry.dto.InquiryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<InquiryDTO, Integer> {

    @Modifying
    @Query("UPDATE InquiryDTO i SET i.inquiryViews = i.inquiryViews + 1 where i.inquiryNo = :inquiryNo")
    int updateViews(@Param("inquiryNo") Integer inquiryNo);

    Page<InquiryDTO> findByInquiryTypeContaining(String searchKeyword, Pageable pageable);
    Page<InquiryDTO> findByInquiryTitleContaining(String searchKeyword, Pageable pageable);
    Page<InquiryDTO> findByInquiryWriterContaining(String searchKeyword, Pageable pageable);

}
