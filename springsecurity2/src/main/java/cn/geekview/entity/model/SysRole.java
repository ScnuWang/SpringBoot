package cn.geekview.entity.model;

import javax.persistence.*;


//@Table(name="s_role")
@Entity
public class SysRole {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name="role_id",length=10)
    private Integer roleId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "uid", nullable = false)//指向另一个表的外键
//    private SysUser SUser;//角色对应的用户实体

    @Column(name="rolename",length=100)
    private String rolename;//角色名称

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    //    public SysUser getSUser() {
//        return SUser;
//    }
//    public void setSUser(SysUser sUser) {
//        SUser = sUser;
//    }


}

