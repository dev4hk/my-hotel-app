package org.example.myhotelweb.repository;

import org.example.myhotelweb.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
