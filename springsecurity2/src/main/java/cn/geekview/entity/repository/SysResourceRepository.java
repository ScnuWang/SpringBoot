package cn.geekview.entity.repository;

import cn.geekview.entity.model.SysResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysResourceRepository extends JpaRepository<SysResource,Integer> {





    /**
     *  通过角色名称获取资源列表
     * @param rolename
     * @return
     *
     */
    @Query(value = "SELECT resource_string FROM s_resource  WHERE resource_id = ( SELECT resource_id FROM s_resource_role  WHERE role_id = ( SELECT  id  FROM s_role  WHERE 'name' = ?1))",nativeQuery = true)
    List<SysResource> findByRoleName(String rolename);
}
