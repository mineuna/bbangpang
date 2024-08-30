package com.bread.bbangpang.report.service;

import com.bread.bbangpang.report.dto.ReportDTO;
import com.bread.bbangpang.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public void addReport(ReportDTO reportDTO) {
        reportRepository.save(reportDTO);
    }

    public Page<ReportDTO> reportList(Pageable pageable) {
        return reportRepository.findAll(pageable);
    }

}
