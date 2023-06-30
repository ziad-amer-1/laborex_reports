package dev.mega.repository;

import dev.mega.entity.BillsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BillsReportRepo extends JpaRepository<BillsReport, Long> {

    @Query("""
        SELECT b FROM BillsReport b WHERE (?1 IS NULL OR TO_CHAR(b.date, 'yyyy-MM-dd') = ?1 )
    """)
    List<BillsReport> findAllByDate(String date);

    @Query("""
        SELECT b FROM BillsReport b WHERE b.telesales.name = ?1 AND TO_CHAR(b.date, 'yyyy-MM-dd') = ?2
    """)
    Optional<BillsReport> findByTelesalesNameAndDate(String telesalesName, String date);

}
