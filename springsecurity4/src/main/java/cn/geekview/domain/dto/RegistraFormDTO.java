package cn.geekview.domain.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 *  注册表单数据传输对象
 */
@Data
public class RegistraFormDTO {

    @NotEmpty
    @NotNull
    @Size(max = 255)
    private String username;

    @NotEmpty
    @NotNull
    @Size(min = 6,max = 32)
    private String password;

    @NotEmpty
    @NotNull
    @Email
    private String email;

}
