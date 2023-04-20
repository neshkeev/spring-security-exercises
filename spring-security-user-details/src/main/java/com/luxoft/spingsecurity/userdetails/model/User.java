package com.luxoft.spingsecurity.userdetails.model;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@SequenceGenerator(
    name = "user_seq_gen",
    sequenceName = "user_seq",
    initialValue = 3010
)
@Entity
public class User {

    @GeneratedValue(generator = "user_seq_gen")
    @Id
    private long id;

    private String login;

    private String password;

    @ElementCollection
    private List<String> roles;

    @JoinTable(name = "user_company")
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Company> companies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles) && Objects.equals(companies, user.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, roles, companies);
    }
}
