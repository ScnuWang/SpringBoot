package cn.geekview.domain.repository;

import cn.geekview.domain.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource,Long> {

    @Query(value = "SELECT * FROM t_resource  WHERE id IN ( SELECT resources_id FROM t_role_resources  WHERE roles_id = ( SELECT  id  FROM t_role  WHERE role_name = ?1))",nativeQuery = true)
    List<Resource> findByRoleName(String rolename);
}
