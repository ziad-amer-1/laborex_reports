package dev.mega.service.interfaces;

import dev.mega.dto.BillsDTO;
import dev.mega.dto.BillsResponse;
import dev.mega.entity.BillsReport;

import java.util.List;

public interface BillsReportService {

    String createBillsReport(Long telesalesId, BillsDTO billsDTO);

    String createBillsReport(String telesalesName, BillsDTO billsDTO);

    List<BillsResponse> getAllBillsReports(String date);

    String deleteBillsReport(Long billsReportId);

    BillsReport getSingleBillsReport(Long billsReportId);

    String updateSingleBillsReport(String telesalesName, String date, BillsDTO billsDTO);

}
