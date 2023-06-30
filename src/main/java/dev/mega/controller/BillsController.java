package dev.mega.controller;

import dev.mega.dto.BillsDTO;
import dev.mega.service.interfaces.BillsReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/bills")
public class BillsController {

    private final BillsReportService billsReportService;

    @PostMapping("/{telesalesId}")
    public ResponseEntity<?> createBills(@PathVariable Long telesalesId, @RequestBody BillsDTO billsDTO) {
        try {
            return ResponseEntity.ok().body(billsReportService.createBillsReport(telesalesId, billsDTO));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllBills(@RequestParam(value = "date", required = false) String date) {
        try {
            return ResponseEntity.ok().body(billsReportService.getAllBillsReports(date));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateBills(
            @RequestParam("telesalesName") String telesalesName,
            @RequestParam("date") String date,
            @RequestBody BillsDTO billsDTO
    ) {
        try {
            return ResponseEntity.ok().body(billsReportService.updateSingleBillsReport(telesalesName, date, billsDTO));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBills(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(billsReportService.deleteBillsReport(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}

