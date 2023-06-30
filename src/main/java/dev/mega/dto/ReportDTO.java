package dev.mega.dto;

import dev.mega.entity.CallsReport;

public record ReportDTO(String telesalesName, CallsReport callsReport, BillsDTO billsDTO) {
}
