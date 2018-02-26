package cn.geekview.domain.entity;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

/**
 *  与注册用户一一对应
 */
@Data
@Entity
@Table(name = "t_validatetoken")
public class ValidateToken {

    private final static int EXPIRY = 24*60;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * @OneToOne 注释只能确定实体与实体的关系是一对一的关系，
     * 不能指定数据库表中的保存的关联字段。
     * 所以此时要结合@JoinColumn标记来指定保存实体关系的配置。
     */
    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "user_id")
    private User user;

    private String token;

    private Date expiryDate;

    public ValidateToken(User user,String token){
        this.user = user;
        this.token = token;
        this.expiryDate = DateTime.now().plusMinutes(EXPIRY).toDate();
    }

    public ValidateToken(){

    }
}
