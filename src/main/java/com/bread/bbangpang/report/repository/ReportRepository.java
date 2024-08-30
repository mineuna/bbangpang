package com.bread.bbangpang.report.repository;

import com.bread.bbangpang.report.dto.ReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<ReportDTO, Integer> {
}
