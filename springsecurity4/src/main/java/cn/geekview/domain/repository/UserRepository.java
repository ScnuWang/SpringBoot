package cn.geekview.domain.repository;

import cn.geekview.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {

    User findByUsername(String username);

    User findByEmail(String eamil);

}
