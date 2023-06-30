package dev.mega.service.implementations;

import dev.mega.dto.CallsResponse;
import dev.mega.entity.CallsReport;
import dev.mega.entity.Telesales;
import dev.mega.repository.CallsReportRepo;
import dev.mega.service.interfaces.CallsReportService;
import dev.mega.service.interfaces.TelesalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static dev.mega.mapper.MapCallsReportToCallsResponse.toCallsResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class CallsReportServiceImpl implements CallsReportService {

    private final TelesalesService telesalesService;
    private final CallsReportRepo callsReportRepo;

    @Override
    public String createCallsReport(Long telesalesId, CallsReport callsReport) {

        Telesales telesales = telesalesService.getSingleTelesales(telesalesId);

        if (
            callsReport.getTotalCalls() == null ||
            callsReport.getPositiveCalls() == null ||
            callsReport.getDate() == null
        ) {
            log.error("Please Provide All data [Total CallsReport Positive CallsReport, date]");
            throw new RuntimeException("""
                من فضلك قم بتزويدنا بجميع المعلوملات اللازمه لاٍتمام هذه العمليه
                المعلومات المراد ادخالها [اجمالي المكالمات, المكالمات الإيجابيه, التاريخ]
            """);
        }

        if (getByTelesalesNameAndDate(telesales.getName(), callsReport.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).isPresent()) {
            log.error("the report you want to add to this telesales in this date is already exist if you aim to edit please go to edit page");
            throw new IllegalStateException("التقارير التي تريد اضافتها للتلي سيلز في هذا التاريخ موجوده بالفعل اذا اردت تعديل هذه البيانات من فضلك توجه الي صفحه التعديلات");
        }

        callsReport.setTelesales(telesales);
        callsReportRepo.save(callsReport);
        log.info("CallsReport Created ....................");
        return "تم إنشاء تقرير بالمكالمات للتلي سيلز المُسمي ب " + "<" + telesales.getName() + ">" + " بنجاح";
    }

    @Override
    public String createCallsReport(String telesalesName, CallsReport callsReport) {

        Telesales telesales = telesalesService.getSingleTelesales(telesalesName);

        if (
                callsReport.getTotalCalls() == null ||
                        callsReport.getPositiveCalls() == null ||
                        callsReport.getDate() == null
        ) {
            log.error("Please Provide All data [Total CallsReport Positive CallsReport, date]");
            throw new RuntimeException("""
                من فضلك قم بتزويدنا بجميع المعلوملات اللازمه لاٍتمام هذه العمليه
                المعلومات المراد ادخالها [اجمالي المكالمات, المكالمات الإيجابيه, التاريخ]
            """);
        }

        if (getByTelesalesNameAndDate(telesales.getName(), callsReport.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).isPresent()) {
            log.error("the report you want to add to this telesales in this date is already exist if you aim to edit please go to edit page");
            throw new IllegalStateException("التقارير التي تريد اضافتها للتلي سيلز في هذا التاريخ موجوده بالفعل اذا اردت تعديل هذه البيانات من فضلك توجه الي صفحه التعديلات");
        }

        callsReport.setTelesales(telesales);
        callsReportRepo.save(callsReport);
        log.info("CallsReport Created ....................");
        return "تم إنشاء تقرير بالمكالمات للتلي سيلز المُسمي ب " + "<" + telesales.getName() + ">" + " بنجاح";
    }

    @Override
    public List<CallsResponse> getAllCallsReports(String date) {
        return toCallsResponse(callsReportRepo.findAllByDate(date));
    }

    @Override
    public String deleteCallsReport(Long id) {
        CallsReport targetCallsReport = getSingleCallsReport(id);
        callsReportRepo.deleteById(targetCallsReport.getId());
        log.info("Bills Report Deleted ....................");
        return "تم حذف تقرير المكالمات بنجاح";
    }

    @Override
    public CallsReport getSingleCallsReport(Long id) {
        CallsReport callsReport = callsReportRepo.findById(id).orElseThrow(() -> {
            log.error("Calls Report with id = " + id + " not found");
            return new IllegalStateException("Calls report with id = " + id + " not found");
        });
        log.info("Calls report fetched successfully ....................");
        return callsReport;
    }

    @Override
    @Transactional
    public String updateSingleCallsReport(String telesalesName, String date, CallsReport callsReport) {

        Optional<CallsReport> callsReport1 = getByTelesalesNameAndDate(telesalesName, date);

        if (callsReport1.isEmpty()) {
            log.error("CallsReport not exist");
            throw new IllegalStateException("تقارير المكالمات المراد تعديلها غير موجوده");
        }

        CallsReport targetCallsReportToUpdate = callsReport1.get();

        if (
            callsReport.getTotalCalls() == null ||
            callsReport.getPositiveCalls() == null
        ) {
            log.error("Please Provide All data [totalCalls, positiveCalls]");
            throw new IllegalStateException("""
                        من فضلك قم بتزويدنا بجميع المعلوملات اللازمه لاٍتمام هذه العمليه
                        المعلومات المراد ادخالها [اجمالي المكالمات, المكالمات الايجابيه]
                    """);
        }

        targetCallsReportToUpdate.setTotalCalls(callsReport.getTotalCalls());
        targetCallsReportToUpdate.setPositiveCalls(callsReport.getPositiveCalls());

        log.info("CallsReport report to telesalesName = " + telesalesName + " on date = " + date + " updated successfully");
        return "تم تحديث تقارير المكالمات للتلي سيلز المُسمي ب " + telesalesName + " في تاريخ " + date +  " بنجاح";
    }

    private Optional<CallsReport> getByTelesalesNameAndDate(String telesalesName, String date) {
        return callsReportRepo.findByTelesalesNameAndDate(telesalesName, date);
    }

}
