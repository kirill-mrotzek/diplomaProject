package org.telran.web.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount;

    public Storage() {

    }

    public Storage(Long id, Long amount) {
        this.id = id;
        this.amount = amount;
    }

    public Storage(Long amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
