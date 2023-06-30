package dev.mega.repository;

import dev.mega.entity.Telesales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TelesalesRepo extends JpaRepository<Telesales, Long> {

    Optional<Telesales> findByNameOrCode(String name, String code);
    Optional<Telesales> findByName(String name);
    Optional<Telesales> findByCode(String code);

    @Query("""
        SELECT
        new Map(
            t.name AS name,
            t.code AS code
        )
        FROM
        Telesales t
        WHERE
        (?1 IS NULL OR ?1 MEMBER OF t.workingPeriods)
        AND
        (?2 IS NULL OR ?2 MEMBER OF t.workingRegions)
    """)
    List<Map<String, String>> findAllByWorkingPeriodsAndWorkingRegions(String period, String region);

//    @Query("""
//        SELECT
//        new Map(
//            t.id AS telesalesId,
//            t.name AS telesalesName,
//            t.code AS telesalesCode,
//            c.totalCalls AS totalCalls,
//            c.positiveCalls AS positiveCalls,
//            b.date AS date,
//            b.numberOfClients AS numberOfClients,
//            b.numberOfBills AS numberOfBills,
//            b.totalSale AS totalSale,
//            b.averageOfEachBill AS average
//        )
//        FROM
//        Telesales t JOIN t.callsReport c JOIN t.billsReport b
//        WHERE
//        TO_CHAR(c.date, 'yyyy-MM-dd') = ?1 AND TO_CHAR(b.date, 'yyyy-MM-dd') = ?1
//    """)
//    List<Map<String, Object>> getTelesalesActionsOnSpecificDate(String date);

}
