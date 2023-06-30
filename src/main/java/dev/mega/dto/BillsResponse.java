package dev.mega.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BillsResponse(
        String telesalesName,
        Integer numberOfClients,
        Integer numberOfBills,
        Double totalSale,
        LocalDate date
) {
}
