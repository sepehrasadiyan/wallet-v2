package me.sepehrasadiyan.wallet_v2.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.sepehrasadiyan.wallet_v2.common.internal.SimpleUserStatusEnum;


@ToString
@Entity
@Table(name = "simple_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimpleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username", length = 64, nullable = false)
    private String username;


    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private SimpleUserStatusEnum status;

}