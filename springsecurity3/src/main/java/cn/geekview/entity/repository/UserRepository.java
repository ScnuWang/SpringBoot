package cn.geekview.entity.repository;

import cn.geekview.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);

    User findByUsername(String username);
}
