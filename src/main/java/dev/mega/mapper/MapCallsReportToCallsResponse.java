package dev.mega.mapper;

import dev.mega.dto.CallsResponse;
import dev.mega.entity.CallsReport;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MapCallsReportToCallsResponse {

    public static List<CallsResponse> toCallsResponse(List<CallsReport> callsReports) {
        return callsReports
                .stream()
                .map(MapCallsReportToCallsResponse::toCallsResponse)
                .collect(Collectors.toList());
    }

    public static CallsResponse toCallsResponse(CallsReport callsReport) {
        return CallsResponse
                .builder()
                .positiveCalls(callsReport.getPositiveCalls())
                .totalCalls(callsReport.getTotalCalls())
                .date(callsReport.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .telesalesName(callsReport.getTelesales().getName())
                .build();
    }

}
