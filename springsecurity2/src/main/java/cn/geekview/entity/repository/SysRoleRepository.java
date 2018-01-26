package cn.geekview.entity.repository;

import cn.geekview.entity.model.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysRoleRepository extends JpaRepository<SysRole,Integer>{

    @Override
    List<SysRole> findAll();
}
