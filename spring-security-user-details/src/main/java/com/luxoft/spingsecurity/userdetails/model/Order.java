package com.luxoft.spingsecurity.userdetails.model;


import javax.persistence.*;
import java.util.Objects;

@SequenceGenerator(
    name = "order_seq_gen",
    sequenceName = "order_seq",
    initialValue = 2010
)
@Entity
@Table(name = "orders")
public class Order {

    @GeneratedValue(generator = "order_seq_gen")
    @Id
    private long id;

    private double amount;

    @ManyToOne
    private Company customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Company getCustomer() {
        return customer;
    }

    public void setCustomer(Company customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.amount, amount) == 0 && Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, customer);
    }
}
