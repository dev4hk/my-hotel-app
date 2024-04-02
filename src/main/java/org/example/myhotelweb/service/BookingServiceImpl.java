package org.example.myhotelweb.service;

import lombok.RequiredArgsConstructor;
import org.example.myhotelweb.exception.InvalidBookingRequestException;
import org.example.myhotelweb.exception.ResourceNotFoundException;
import org.example.myhotelweb.model.Booking;
import org.example.myhotelweb.model.Room;
import org.example.myhotelweb.repository.BookingRepository;
import org.example.myhotelweb.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements IBookingService {

    private final BookingRepository bookingRepository;
    private final IRoomService roomService;


    @Override
    public List<Booking> getAllBookingsByRoomId(Long roomId) {
        return bookingRepository.findByRoomId(roomId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public String saveBooking(Long roomId, Booking bookingRequest) {
        if(bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())) {
            throw new InvalidBookingRequestException("Check-in date must come before check-out date");
        }
        Room room = roomService.getRoomById(roomId).get();
        List<Booking> currentBookings = room.getBookings();
        boolean isRoomAvailable = isRoomAvailable(bookingRequest, currentBookings);
        if(isRoomAvailable) {
            room.addBooking(bookingRequest);
            bookingRepository.save(bookingRequest);
        }
        else {
            throw new InvalidBookingRequestException("Room not available");
        }
        return bookingRequest.getBookingConfirmationCode();
    }

    private boolean isRoomAvailable(Booking bookingRequest, List<Booking> currentBookings) {
        return currentBookings.stream()
                .noneMatch(existingBooking ->
                        bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
                                || bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
                                || (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate()) && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate()) && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate()) && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate()) && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))
                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate()) && bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))
                );

    }

    @Override
    public Booking findByBookingConfirmationCode(String confirmationCode) {
        return bookingRepository.findByBookingConfirmationCode(confirmationCode)
                .orElseThrow(() -> new ResourceNotFoundException("No booking found with the booking code :" + confirmationCode));
    }

    @Override
    public List<Booking> findAllByGuestEmail(String email) {
        return bookingRepository.findAllByGuestEmail(email);
    }

    @Override
    public List<Booking> getBookingsByUserEmail(String email) {
        return bookingRepository.findAllByGuestEmail(email);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
