package dev.mega.service.implementations;

import dev.mega.dto.ReportDTO;
import dev.mega.service.interfaces.BillsReportService;
import dev.mega.service.interfaces.CallsReportService;
import dev.mega.service.interfaces.ReportsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportsServiceImpl implements ReportsService {

    private final BillsReportService billsReportService;
    private final CallsReportService callsReportService;

    @Override
    public String addReports(ReportDTO[] reportDTOS) {
        for (ReportDTO reportDTO : reportDTOS) {
            addReport(reportDTO);
        }
        log.info("All Reports to all telesales added successfully");
        return "تم اضافة التقارير لجميع التلي سيلز بنجاح";
    }

    public void addReport(ReportDTO reportDTO) {
        billsReportService.createBillsReport(reportDTO.telesalesName(), reportDTO.billsDTO());
        callsReportService.createCallsReport(reportDTO.telesalesName(), reportDTO.callsReport());
        log.info("report added Successfully to " + reportDTO.telesalesName());
    }
}
