package com.luxoft.spingsecurity.basicauth.model;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@SequenceGenerator(
    name = "company_seq_gen",
    sequenceName = "company_seq",
    initialValue = 1010
)
@Entity
public class Company {

    @GeneratedValue(generator = "company_seq_gen")
    @Id
    private long id;

    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id && Objects.equals(name, company.name) && Objects.equals(orders, company.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, orders);
    }
}
