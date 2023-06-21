package ru.practicum.shareit.item.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.booking.dto.BookingShortResponseDto;

import java.util.List;

@Data
@Builder
public class ItemResponseDto {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private BookingShortResponseDto nextBooking;
    private BookingShortResponseDto lastBooking;
    private List<CommentResponseDto> comments;
}