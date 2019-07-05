package vn.com.rfim_api.persistences.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "[User]")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    private String fullname;

    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = true)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private List<StocktakeHistory> stocktakeHistories = new ArrayList<>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<StocktakeHistory> getStocktakeHistories() {
        return stocktakeHistories;
    }

    public void setStocktakeHistories(List<StocktakeHistory> stocktakeHistories) {
        this.stocktakeHistories = stocktakeHistories;
    }
}
