package dev.mega.service.interfaces;

import dev.mega.dto.CallsResponse;
import dev.mega.entity.CallsReport;

import java.util.List;

public interface CallsReportService {

    String createCallsReport(Long telesalesId, CallsReport callsReport);

    String createCallsReport(String telesalesName, CallsReport callsReport);

    List<CallsResponse> getAllCallsReports(String date);

    String deleteCallsReport(Long id);

    CallsReport getSingleCallsReport(Long id);

    String updateSingleCallsReport(String telesalesName, String date, CallsReport callsReport);


}
