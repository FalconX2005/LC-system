package uz.pdp.lcsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.lcsystem.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /*@Query(value = "SELECT * FROM users WHERE to_tsvector('simple', first_name || ' ' || last_name) @@ to_tsquery(:query)", nativeQuery = true)
    List<User> searchUsers(@Param("query") String query);
   *//* @Query(value = """
    SELECT * FROM users
    WHERE (:query IS NULL OR to_tsvector('simple', first_name || ' ' || last_name) @@ plainto_tsquery(:query))
    AND (:phoneNumber IS NULL OR phone_number LIKE %:phoneNumber%)
    AND (:group IS NULL OR group_name ILIKE %:group%)
    AND (:role IS NULL OR role ILIKE %:role%)
""", nativeQuery = true)
    List<User> filterUsers(
            @Param("query") String query,
            @Param("phoneNumber") String phoneNumber,
            @Param("group") String group,
            @Param("role") String role
    );
*/
}