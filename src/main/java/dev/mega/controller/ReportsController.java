package dev.mega.controller;

import dev.mega.dto.ReportDTO;
import dev.mega.service.interfaces.ReportsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reports")
public class ReportsController {

    private final ReportsService reportsService;

    @PostMapping
    public ResponseEntity<String> addReports(@RequestBody ReportDTO[] reportDTOS) {
        try {
            return ResponseEntity.ok(reportsService.addReports(reportDTOS));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
