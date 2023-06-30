package dev.mega.dto;

import java.time.LocalDate;

public record BillsDTO(
        Integer numberOfClients,
        Integer numberOfBills,
        Double totalSale,
        LocalDate date
) {
}
