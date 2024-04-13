package org.example.myhotelweb.service;

import org.example.myhotelweb.model.Booking;

import java.util.List;

public interface IBookingService {
    List<Booking> getAllBookingsByRoomId(Long roomId);
    List<Booking> getAllBookings();
    String saveBooking(Long roomId, Booking booking);
    Booking findByBookingByConfirmationCode(String confirmationCode);
    List<Booking> findAllByGuestEmail(String email);
    List<Booking> getBookingsByUserEmail(String email);

    void cancelBooking(Long bookingId);
}
