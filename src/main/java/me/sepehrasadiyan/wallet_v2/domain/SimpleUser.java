package me.sepehrasadiyan.wallet_v2.domain;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.sepehrasadiyan.wallet_v2.common.internal.SimpleUserStatusEnum;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "simple_user")
@NoArgsConstructor
public class SimpleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username", length = 64, nullable = false)
    private String username;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private SimpleUserStatusEnum status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<SimpleAccount> accounts = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SimpleUserStatusEnum getStatus() {
        return status;
    }

    public void setStatus(SimpleUserStatusEnum status) {
        this.status = status;
    }

    public List<SimpleAccount> getAccounts() {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        return accounts;
    }

    public void setAccounts(List<SimpleAccount> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(SimpleAccount account) {
        if (account != null) {
            account.setUser(this);
            this.accounts.add(account);
        } else {
            throw new IllegalArgumentException("Account cannot be null");
        }
    }
}