//package dev.mega.controller;
//
//import dev.mega.dto.ReportDTO;
//import dev.mega.service.AppService;
//import dev.mega.service.TelesalesServiceImpl;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@Slf4j
//@RequestMapping("/app")
//public class AppController {
//
//    private final AppService appService;
//    private final TelesalesServiceImpl telesalesServiceImpl;
//
//    @GetMapping("/all-actions")
//    public ResponseEntity<?> getTelesalesActionsOnSpecificDate(@RequestParam("date") String date) {
//        try {
//            return ResponseEntity.ok().body(appService.getTelesalesActionsOnSpecificDate(date));
//        } catch (Exception e) {
//            return ResponseEntity.ok().body(e.getMessage());
//        }
//    }
//
//    @PostMapping("/add-report")
//    public ResponseEntity<?> addReport(@RequestBody ReportDTO reportDTO) {
//        try {
//            return ResponseEntity.ok().body(appService.addReport(reportDTO.telesalesName(), reportDTO.callsReport(), reportDTO.billsDTO()));
//        } catch (Exception e) {
//            return ResponseEntity.ok().body(e.getMessage());
//        }
//    }
//
//    @PostMapping("/add-reports")
//    public ResponseEntity<?> addReports(@RequestBody ReportDTO[] reportDTOs) {
//        try {
//            for (ReportDTO reportDTO : reportDTOs) {
//                appService.addReport(reportDTO.telesalesName(), reportDTO.callsReport(), reportDTO.billsDTO());
//            }
//            return ResponseEntity.ok().body("Reports have added successfully");
//        } catch (Exception e) {
//            return ResponseEntity.ok().body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/get-all-telesales")
//    public ResponseEntity<?> findAllByWorkingPeriods(@RequestParam(value = "period", required = false) String period) {
//        try {
//            if (period != null) {
//                return ResponseEntity.ok().body(telesalesServiceImpl.findAllByWorkingPeriods(period.toUpperCase().trim()));
//            } else {
//                return ResponseEntity.ok().body(telesalesServiceImpl.getAllTelesales());
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(e.getMessage());
//        }
//    }
//}
