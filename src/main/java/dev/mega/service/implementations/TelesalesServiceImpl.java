package dev.mega.service.implementations;

import dev.mega.entity.Telesales;
import dev.mega.repository.TelesalesRepo;
import dev.mega.service.interfaces.TelesalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class TelesalesServiceImpl implements TelesalesService {

    private final TelesalesRepo telesalesRepo;

    @Override
    public String createTelesales(Telesales telesales) {

        log.info("creating new telesales...");

        if (
            telesales.getName() == null ||
            telesales.getCode() == null ||
            telesales.getWorkingPeriods() == null ||
            telesales.getWorkingPeriods().size() == 0 ||
            telesales.getWorkingRegions() == null ||
            telesales.getWorkingRegions().size() == 0
        ) {
            log.error("Please Provide All data [name, code, workingPeriods, workingRegions]");
            throw new IllegalStateException("""
                        من فضلك قم بتزويدنا بجميع المعلوملات اللازمه لاٍتمام هذه العمليه
                        المعلومات المراد ادخالها [الاسم, الكود, فترات العمل, مواقع العمل]
                    """);
        }

        if (isTelesalesExist(telesales.getName().trim(), telesales.getCode().trim())) {
            log.error("This name or code already exist");
            throw new IllegalStateException("""
                        هذا الاسم او الكود موجد مسبقاً
                        من فضلك ادخل اسم جديد وكود جديد لإتمام هذه العمليه
                    """);
        }

        log.info("telesales with name = " + telesales.getName() + " created successfully");
        telesalesRepo.save(telesales);

        return "تمت اضافه تيلي سيلز جديد ب اسم " + telesales.getName() + " وكود " + telesales.getCode() + " بنجاح";
    }

    @Override
    public List<Telesales> getAllTelesales() {
        log.info("All Telesales Fetched Successfully ....................");
        return telesalesRepo.findAll();
    }

    @Override
    public List<Map<String, String>> findAllTelesalesByWorkingPeriodsAndWorkingRegions(String period, String region) {
        if (period == null && region == null) {
            log.error("you should provide at least one searching param");
            throw new IllegalStateException("you should provide at least one searching param");
        }
        return telesalesRepo.findAllByWorkingPeriodsAndWorkingRegions(period, region);
    }

    @Override
    public Telesales getSingleTelesales(Long telesalesId) {
        Telesales telesales = this.telesalesRepo.findById(telesalesId).orElseThrow(() -> {
            log.error("telesales with id = " + telesalesId + " not found");
            return new IllegalStateException("telesales with id = " + telesalesId + " not found");
        });
        log.info("Telesales fetched successfully ....................");
        return telesales;
    }

    @Override
    public Telesales getSingleTelesales(String name) {
        Telesales telesales = this.telesalesRepo.findByName(name).orElseThrow(() -> {
            log.error("telesales with name = " + name + " not found");
            return new IllegalStateException("telesales with name = " + name + " not found");
        });
        log.info("Telesales fetched successfully ....................");
        return telesales;
    }

    @Override
    public Telesales getSingleTelesalesByCode(String code) {
        Telesales telesales = this.telesalesRepo.findByCode(code).orElseThrow(() -> {
            log.error("telesales with code = " + code + " not found");
            return new IllegalStateException("telesales with code = " + code + " not found");
        });
        log.info("Telesales fetched successfully ....................");
        return telesales;
    }

    @Override
    @Transactional
    public String updateTelesales(Long telesalesId, Telesales telesales) {

        Telesales targetTelesales = getSingleTelesales(telesalesId);

        if (!telesales.getName().trim().equals("")) {
            targetTelesales.setName(telesales.getName().trim());
        }
        if (!telesales.getCode().trim().equals("")) {
            targetTelesales.setCode(telesales.getCode().trim());
        }
        if (telesales.getWorkingPeriods() != null) {
            targetTelesales.setWorkingPeriods(telesales.getWorkingPeriods());
        }
        if (telesales.getWorkingRegions() != null) {
            targetTelesales.setWorkingRegions(telesales.getWorkingRegions());
        }


        log.info("Telesales updated ....................");
        return "تم تحديث بيانات التلي سيلز بنجاح";
    }

    @Override
    public String deleteTelesales(Long telesalesId) {
        Telesales targetTelesales = getSingleTelesales(telesalesId);
        this.telesalesRepo.deleteById(targetTelesales.getId());
        log.info("Telesales Deleted ....................");
        return "تم حذف التيلي سيلز المُسمي ب " + "<" + targetTelesales.getName() + ">" + " بنجاح";
    }

    private boolean isTelesalesExist(String name, String code) {
        return telesalesRepo.findByNameOrCode(name, code).isPresent();
    }
}
