package dev.mega.repository;

import dev.mega.entity.CallsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CallsReportRepo extends JpaRepository<CallsReport, Long> {

    @Query("""
        SELECT c FROM CallsReport c WHERE (?1 IS NULL OR TO_CHAR(c.date, 'yyyy-MM-dd') = ?1 )
    """)
    List<CallsReport> findAllByDate(String date);

    @Query("""
        SELECT c FROM CallsReport c WHERE c.telesales.name = ?1 AND TO_CHAR(c.date, 'yyyy-MM-dd') = ?2
    """)
    Optional<CallsReport> findByTelesalesNameAndDate(String telesalesName, String date);

}
