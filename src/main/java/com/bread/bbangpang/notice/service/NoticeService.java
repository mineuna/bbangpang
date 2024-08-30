package com.bread.bbangpang.notice.service;

import com.bread.bbangpang.notice.dto.NoticeDTO;
import com.bread.bbangpang.notice.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public void addNotice(NoticeDTO noticeDTO) {
        noticeRepository.save(noticeDTO);
    }

    public Page<NoticeDTO> noticeList(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }

    public Page<NoticeDTO> searchNoticeTitle(String searchKeyword, Pageable pageable) {
        return noticeRepository.findByNoticeTitleContaining(searchKeyword, pageable);
    }
    public Page<NoticeDTO> searchNoticeContent(String searchKeyword, Pageable pageable) {
        return noticeRepository.findByNoticeContentContaining(searchKeyword, pageable);
    }

    @Transactional
    public int noticeViews(Integer noticeNo) {
        return noticeRepository.updateViews(noticeNo);
    }

    public NoticeDTO notice(Integer noticeNo) {
        return noticeRepository.findById(noticeNo).get();
    }

    public void deleteNotice(Integer noticeNo) {
        noticeRepository.deleteById(noticeNo);
    }
}
