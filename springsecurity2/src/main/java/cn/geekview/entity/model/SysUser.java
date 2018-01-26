package cn.geekview.entity.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Table(name = "s_user")//code11
@Entity
public class SysUser implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "username", length = 120)
    private String username; //用户名
    @Column(name = "email", length = 50)
    private String email;//用户邮箱
    @Column(name = "password", length = 120)
    private String password;//用户密码
    @Temporal(TemporalType.DATE)
    @Column(name = "dob", length = 10)
    private Date dob;//时间

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "SUser")
//    private Set<SysRole> SysRoles = new HashSet<SysRole>(0);// 所对应的角色集合
    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<SysRole> SysRoles;

    public SysUser() {
    }

    public SysUser(String username, String email, String password, Date dob, List<SysRole> SysRoles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.SysRoles = SysRoles;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "SUser")
//    public Set<SysRole> getSysRoles() {
//        return this.SysRoles;
//    }
//
//    public void setSysRoles(Set<SysRole> SysRoles) {
//        this.SysRoles = SysRoles;
//    }


    public List<SysRole> getSysRoles() {
        return SysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        SysRoles = sysRoles;
    }
}
