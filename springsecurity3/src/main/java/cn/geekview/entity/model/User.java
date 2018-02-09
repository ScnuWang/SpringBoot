package cn.geekview.entity.model;

import cn.geekview.annotation.ValidEmail;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "t_user")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;//主键

    @NotNull
    @NotEmpty
    private String username;//用户名

    @NotNull
    @NotEmpty
    private String password;//密码

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;//邮箱

    private boolean enabled;//是否激活

    /**
     *  CascadeType.DETACH
        Cascade detach operation，级联脱管/游离操作。
        如果你要删除一个实体，但是它有外键无法删除，你就需要这个级联权限了。它会撤销所有相关的外键关联。
     */
    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    public User(){
        super();
        this.enabled=false;
    }

    // 获取用户有哪些角色（对应框架就是有哪些权限）
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles = this.roles;
        if (roles != null) {
            for (Role role : roles) {
                SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
                grantedAuthorities.add(grantedAuthority);
            }
        }
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
