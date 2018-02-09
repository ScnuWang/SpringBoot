package cn.geekview.entity.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;//角色名称

    /**
     *  mappedBy:声明于关系的被维护方，声明的值为关系的维护方的关系对象属性名。
     *  被维护方不会主动去维护关联关系,真正的关系维护，掌握在维护方的手中。
     *  cascade:级联关系，给当前设置的实体操作另一个实体的权限,不要随便给all权限操作,应该根据业务需求选择所需的级联关系.
     *  更多解释：https://www.jianshu.com/p/e8caafce5445
     */
    @ManyToMany(mappedBy = "roles",cascade = {CascadeType.MERGE,CascadeType.DETACH},fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();//一个角色可以有多个用户

    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    private Set<Resource> resources = new HashSet<>();//一个角色可以调用多个资源

}
