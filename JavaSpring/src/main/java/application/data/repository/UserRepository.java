package application.data.repository;

import application.data.model.User;
import application.model.User.UserByRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findById(int id);

    @Query(value = "SELECT u.user_id FROM user u, user_role ur, role r where u.user_id=ur.user_id and ur.role_id=r.role_id and r.role=?1 ",nativeQuery = true)
    List<Integer> userByRole(String role);
}
