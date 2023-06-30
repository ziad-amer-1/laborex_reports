package dev.mega.mapper;

import dev.mega.dto.BillsDTO;
import dev.mega.dto.BillsResponse;
import dev.mega.entity.BillsReport;

import java.util.List;
import java.util.stream.Collectors;

public class MapBillsReportToBillsResponse {

    public static List<BillsResponse> toBillsResponse(List<BillsReport> billsReports) {
        return billsReports
                .stream()
                .map(MapBillsReportToBillsResponse::toBillsResponse)
                .collect(Collectors.toList());
    }

    public static BillsResponse toBillsResponse(BillsReport billsReport) {
        return BillsResponse
                .builder()
                .numberOfBills(billsReport.getNumberOfBills())
                .date(billsReport.getDate())
                .totalSale(billsReport.getTotalSale())
                .numberOfClients(billsReport.getNumberOfClients())
                .telesalesName(billsReport.getTelesales().getName())
                .build();
    }

}
