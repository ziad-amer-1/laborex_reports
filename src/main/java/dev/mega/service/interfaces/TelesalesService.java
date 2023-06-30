package dev.mega.service.interfaces;

import dev.mega.entity.Telesales;

import java.util.List;
import java.util.Map;

public interface TelesalesService {

    String createTelesales(Telesales telesales);

    List<Telesales> getAllTelesales();

    List<Map<String, String>> findAllTelesalesByWorkingPeriodsAndWorkingRegions(String period, String region);

    Telesales getSingleTelesales(Long telesalesId);

    Telesales getSingleTelesales(String name);

    Telesales getSingleTelesalesByCode(String code);

    String updateTelesales(Long telesalesId, Telesales telesales);

    String deleteTelesales(Long telesalesId);

}
