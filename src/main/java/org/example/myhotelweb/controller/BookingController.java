package org.example.myhotelweb.controller;

import lombok.RequiredArgsConstructor;
import org.example.myhotelweb.exception.ResourceNotFoundException;
import org.example.myhotelweb.model.Booking;
import org.example.myhotelweb.model.Room;
import org.example.myhotelweb.response.BookingResponse;
import org.example.myhotelweb.response.RoomResponse;
import org.example.myhotelweb.service.IBookingService;
import org.example.myhotelweb.service.IRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/bookings")
@RestController
public class BookingController {

    private final IBookingService bookingService;
    private final IRoomService roomService;

    @PostMapping("/room/{roomId}/booking")
    public ResponseEntity<?> saveBooking(@PathVariable Long roomId, @RequestBody Booking request) {
        String confirmationCode = bookingService.saveBooking(roomId, request);
        return ResponseEntity.ok(confirmationCode);
    }

    @GetMapping("/all-bookings")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingResponse response = getBookingResponse(booking);
            bookingResponses.add(response);
        }
        return ResponseEntity.ok(bookingResponses);
    }

    @GetMapping("/confirmation/{confirmationCode}")
    public ResponseEntity<?> getBookingByConfirmationCode(@PathVariable String confirmationCode) {
        Booking booking = bookingService.findByBookingConfirmationCode(confirmationCode);
        BookingResponse bookingResponse = getBookingResponse(booking);
        return ResponseEntity.ok(bookingResponse);
    }

    @GetMapping("/user/{email}/bookings")
    public ResponseEntity<List<BookingResponse>> getBookingsByUserEmail(@PathVariable String email) {
        List<Booking> bookings = bookingService.getBookingsByUserEmail(email);
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingResponse bookingResponse = getBookingResponse(booking);
            bookingResponses.add(bookingResponse);
        }
        return ResponseEntity.ok(bookingResponses);
    }

    private BookingResponse getBookingResponse(Booking booking) {
        Room room = roomService.getRoomById(booking.getRoom().getId()).get();
        RoomResponse roomResponse = new RoomResponse(
                room.getId(),
                room.getRoomType(),
                room.getRoomPrice());
        return new BookingResponse(
                booking.getBookingId(),
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                booking.getGuestFullName(),
                booking.getGuestEmail(),
                booking.getNumOfAdults(),
                booking.getNumOfChildren(),
                booking.getTotalNumberOfGuests(),
                booking.getBookingConfirmationCode(),
                roomResponse
        );
    }

}
