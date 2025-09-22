package com.paypal.UserMs.dto;



import com.paypal.UserMs.entity.UserEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private Long walletId;


    public UserEntity toEntity() {
        UserEntity user = new UserEntity();
        user.setId(this.id);
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setWalletId(this.walletId);
        return user;
    }

}
