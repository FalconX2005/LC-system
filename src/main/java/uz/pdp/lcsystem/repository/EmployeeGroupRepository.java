package uz.pdp.lcsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lcsystem.entity.EmployeeGroup;

public interface EmployeeGroupRepository extends JpaRepository<EmployeeGroup, Long> {
}