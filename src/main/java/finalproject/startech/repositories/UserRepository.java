package finalproject.startech.repositories;

import finalproject.startech.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);

    List<UserEntity> findByEmailConfirmedTrue();
}
