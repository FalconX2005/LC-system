package uz.pdp.lcsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.lcsystem.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}