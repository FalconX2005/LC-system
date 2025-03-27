package uz.pdp.lcsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lcsystem.entity.Finance;

public interface FinanceRepository extends JpaRepository<Finance, Long> {
}