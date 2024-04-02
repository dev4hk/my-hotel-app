package org.example.myhotelweb.service;

import lombok.RequiredArgsConstructor;
import org.example.myhotelweb.exception.ResourceNotFoundException;
import org.example.myhotelweb.model.Room;
import org.example.myhotelweb.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements IRoomService{

    private final RoomRepository roomRepository;

    @Override
    public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws SQLException, IOException {
        Room room = new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        if(!file.isEmpty()) {
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            room.setPhoto(photoBlob);
        }
        return roomRepository.save(room);
    }

    @Override
    public List<String> getAllRoomTypes() {
        return roomRepository.findDistinctRoomTypes();
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException {
        Optional<Room> room = roomRepository.findById(roomId);
        if(room.isEmpty()) {
            throw new ResourceNotFoundException("Room not found.");
        }
        Blob photoBlob = room.get().getPhoto();
        if(photoBlob != null) {
            return photoBlob.getBytes(1, (int) photoBlob.length());
        }
        return null;
    }

    @Override
    public Optional<Room> getRoomById(Long roomId) {
        return roomRepository.findById(roomId);
    }

    @Override
    public void deleteRoom(Long roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if(room.isPresent()) {
            roomRepository.deleteById(roomId);
        }
    }
}
