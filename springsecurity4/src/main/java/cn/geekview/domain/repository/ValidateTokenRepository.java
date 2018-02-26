package cn.geekview.domain.repository;

import cn.geekview.domain.entity.ValidateToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidateTokenRepository extends JpaRepository<ValidateToken,Long> {

    ValidateToken findByToken(String token);
}
