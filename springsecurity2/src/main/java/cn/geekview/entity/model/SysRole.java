package cn.geekview.entity.model;

import javax.persistence.*;


//@Table(name="s_role")
@Entity
public class SysRole {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name="id",length=10)
    private Integer id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "uid", nullable = false)//指向另一个表的外键
//    private SysUser SUser;//角色对应的用户实体

    @Column(name="name",length=100)
    private String name;//角色名称

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
//    public SysUser getSUser() {
//        return SUser;
//    }
//    public void setSUser(SysUser sUser) {
//        SUser = sUser;
//    }


}

