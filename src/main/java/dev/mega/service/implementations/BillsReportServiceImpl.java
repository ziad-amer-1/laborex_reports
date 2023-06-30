package dev.mega.service.implementations;

import dev.mega.dto.BillsDTO;
import dev.mega.dto.BillsResponse;
import dev.mega.entity.BillsReport;
import dev.mega.entity.Telesales;
import dev.mega.mapper.MapBillsReportToBillsResponse;
import dev.mega.repository.BillsReportRepo;
import dev.mega.service.interfaces.BillsReportService;
import dev.mega.service.interfaces.TelesalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static dev.mega.mapper.MapBillsReportToBillsResponse.toBillsResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillsReportServiceImpl implements BillsReportService {

    private final BillsReportRepo billsReportRepo;
    private final TelesalesService telesalesService;


    @Override
    public String createBillsReport(Long telesalesId, BillsDTO billsDTO) {

        Telesales telesales = telesalesService.getSingleTelesales(telesalesId);

        if (
            billsDTO.numberOfBills() == null ||
            billsDTO.totalSale() == null ||
            billsDTO.date() == null ||
            billsDTO.numberOfClients() == null
        ) {
            log.error("Please Provide All data [number of bills, total sale, date, number of clients]");
            throw new IllegalStateException("""
                        من فضلك قم بتزويدنا بجميع المعلوملات اللازمه لاٍتمام هذه العمليه
                        المعلومات المراد ادخالها [عدد الفواتير, اجمالي البيع, التاريخ العمل, عدد العملاء]
                    """);
        }

        if (getByTelesalesNameAndDate(telesales.getName(), billsDTO.date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).isPresent()) {
            log.error("the report you want to add to this telesales in this date is already exist if you aim to edit please go to edit page");
            throw new IllegalStateException("التقارير التي تريد اضافتها للتلي سيلز في هذا التاريخ موجوده بالفعل اذا اردت تعديل هذه البيانات من فضلك توجه الي صفحه التعديلات");
        }

        BillsReport billsReport = new BillsReport();

        billsReport.setTelesales(telesales);
        billsReport.setTotalSale(billsDTO.totalSale());
        billsReport.setNumberOfBills(billsDTO.numberOfBills());
        billsReport.setNumberOfClients(billsDTO.numberOfClients());
        billsReport.setDate(billsDTO.date());

        billsReportRepo.save(billsReport);

        log.info("BillsReport Created ....................");
        return "تم إنشاء تقرير بالفواتير للتلي سيلز المُسمي ب " + "<" + telesales.getName() + ">" + " بنجاح";
    }

    @Override
    public String createBillsReport(String telesalesName, BillsDTO billsDTO) {

        Telesales telesales = telesalesService.getSingleTelesales(telesalesName);

        if (
            billsDTO.numberOfBills() == null ||
            billsDTO.totalSale() == null ||
            billsDTO.date() == null ||
            billsDTO.numberOfClients() == null
        ) {
            log.error("Please Provide All data [number of bills, total sale, date, number of clients]");
            throw new IllegalStateException("""
                        من فضلك قم بتزويدنا بجميع المعلوملات اللازمه لاٍتمام هذه العمليه
                        المعلومات المراد ادخالها [عدد الفواتير, اجمالي البيع, التاريخ العمل, عدد العملاء]
                    """);
        }

        if (getByTelesalesNameAndDate(telesales.getName(), billsDTO.date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).isPresent()) {
            log.error("the report you want to add to this telesales in this date is already exist if you aim to edit please go to edit page");
            throw new IllegalStateException("التقارير التي تريد اضافتها للتلي سيلز في هذا التاريخ موجوده بالفعل اذا اردت تعديل هذه البيانات من فضلك توجه الي صفحه التعديلات");
        }

        BillsReport billsReport = new BillsReport();

        billsReport.setTelesales(telesales);
        billsReport.setTotalSale(billsDTO.totalSale());
        billsReport.setNumberOfBills(billsDTO.numberOfBills());
        billsReport.setNumberOfClients(billsDTO.numberOfClients());
        billsReport.setDate(billsDTO.date());

        billsReportRepo.save(billsReport);

        log.info("BillsReport Created ....................");
        return "تم إنشاء تقرير بالفواتير للتلي سيلز المُسمي ب " + "<" + telesales.getName() + ">" + " بنجاح";
    }

    @Override
    public List<BillsResponse> getAllBillsReports(String date) {
        log.info("All BillsReports Fetched Successfully ....................");
        return toBillsResponse(billsReportRepo.findAllByDate(date));
    }

    @Override
    public String deleteBillsReport(Long billsReportId) {
        BillsReport targetBillsReport = getSingleBillsReport(billsReportId);
        billsReportRepo.deleteById(targetBillsReport.getId());
        log.info("Bills Report Deleted ....................");
        return "تم حذف تقرير الفواتير بنجاح";
    }

    @Override
    public BillsReport getSingleBillsReport(Long billsReportId) {
        BillsReport billsReport = billsReportRepo.findById(billsReportId).orElseThrow(() -> {
            log.error("Bills Report with id = " + billsReportId + " not found");
            return new IllegalStateException("telesales with id = " + billsReportId + " not found");
        });
        log.info("Bills Report fetched successfully ....................");
        return billsReport;
    }

    @Override
    @Transactional
    public String updateSingleBillsReport(String telesalesName, String date, BillsDTO billsDTO) {

        Optional<BillsReport> billsReport = getByTelesalesNameAndDate(telesalesName, date);

        if (billsReport.isEmpty()) {
            log.error("BillsReport not exist");
            throw new IllegalStateException("تقارير الفواتير المراد تعديلها غير موجوده");
        }


        BillsReport targetBillsReportToUpdate = billsReport.get();

        if (
            billsDTO.numberOfBills() == null ||
            billsDTO.totalSale() == null ||
            billsDTO.numberOfClients() == null
        ) {
            log.error("Please Provide All data [numberOfClients, numberOfBills, totalSale]");
            throw new IllegalStateException("""
                        من فضلك قم بتزويدنا بجميع المعلوملات اللازمه لاٍتمام هذه العمليه
                        المعلومات المراد ادخالها [عدد العملاء, عدد الفواتير, اجمالي البيع]
                    """);
        }

        targetBillsReportToUpdate.setNumberOfBills(billsDTO.numberOfBills());
        targetBillsReportToUpdate.setNumberOfClients(billsDTO.numberOfClients());
        targetBillsReportToUpdate.setTotalSale(billsDTO.totalSale());


        log.info("BillsReport with telesalesName = " + telesalesName + " and on a date = " + date + " updated successfully");
        return "تم تحديث بيانات تقارير الفواتير للتلي سيلز المُسمي ب " + telesalesName + " في تاريخ " + date + " بنجاح";

    }

    private Optional<BillsReport> getByTelesalesNameAndDate(String telesalesName, String date) {
        return billsReportRepo.findByTelesalesNameAndDate(telesalesName, date);
    }


}
