package dev.mega.dto;

import lombok.Builder;

@Builder
public record CallsResponse(
        String telesalesName,
        Integer totalCalls,
        Integer positiveCalls,
        String date
) {
}
