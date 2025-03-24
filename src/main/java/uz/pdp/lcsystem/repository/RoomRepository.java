package uz.pdp.lcsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.lcsystem.entity.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByName(String name);/*
    @Query(value = "SELECT * FROM rooms WHERE to_tsvector('simple', title) @@ to_tsquery(:keyword)", nativeQuery = true)
    List<Room> searchByTitle(@Param("keyword") String keyword);*/

}