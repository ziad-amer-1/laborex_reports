package dev.mega.controller;

import dev.mega.entity.CallsReport;
import dev.mega.service.interfaces.CallsReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/callsReport")
public class CallsController {

    private final CallsReportService callsReportService;

    @PostMapping("/{telesalesId}")
    public ResponseEntity<?> createCalls(@PathVariable Long telesalesId, @RequestBody CallsReport callsReport) {
        try {
            return ResponseEntity.ok().body(callsReportService.createCallsReport(telesalesId, callsReport));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCalls(@RequestParam(value = "date", required = false) String date) {
        try {
            return ResponseEntity.ok().body(callsReportService.getAllCallsReports(date));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateCalls(
            @RequestParam("telesalesName") String telesalesName,
            @RequestParam("date") String date,
            @RequestBody CallsReport callsReport
    ) {
        try {
            return ResponseEntity.ok().body(callsReportService.updateSingleCallsReport(telesalesName, date, callsReport));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCalls(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(callsReportService.deleteCallsReport(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}

