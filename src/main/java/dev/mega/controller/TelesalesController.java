package dev.mega.controller;

import dev.mega.entity.Telesales;
import dev.mega.service.interfaces.TelesalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/telesales")
public class TelesalesController {

    private final TelesalesService telesalesService;

    @PostMapping
    public ResponseEntity<?> createTelesales(@RequestBody Telesales telesales) {
        try {
            return ResponseEntity.ok().body(telesalesService.createTelesales(telesales));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTelesales() {
        try {
            return ResponseEntity.ok().body(telesalesService.getAllTelesales());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleTelesales(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(telesalesService.getSingleTelesales(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTelesales(@PathVariable Long id, @RequestBody Telesales telesales) {
        try {
            return ResponseEntity.ok().body(telesalesService.updateTelesales(id, telesales));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelesales(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(telesalesService.deleteTelesales(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<?> findAllByWorkingPeriodsAndWorkingRegions(
            @RequestParam(value = "period",  required = false) String period,
            @RequestParam(value = "region", required = false) String region
    ) {
        try {
            return ResponseEntity.ok(telesalesService.findAllTelesalesByWorkingPeriodsAndWorkingRegions(period, region));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
