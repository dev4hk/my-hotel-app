package org.example.myhotelweb.repository;

import org.example.myhotelweb.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByRoomId(Long roomId);

    Optional<Booking> findByBookingConfirmationCode(String confirmationCode);
    List<Booking> findAllByGuestEmail(String email);
}
