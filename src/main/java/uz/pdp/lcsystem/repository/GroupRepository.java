package uz.pdp.lcsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.lcsystem.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}