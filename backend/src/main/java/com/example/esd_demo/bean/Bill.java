package com.example.esd_demo.bean;

import jakarta.persistence.*;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "billId")
@Getter
@Entity
@Table(name = "Bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billId;
    @lombok.Setter
    @Column
    private String description;
    @Column(nullable = false)
    private Integer amount;
    @lombok.Setter
    @Column(nullable = false)
    private String billDate;

    @lombok.Setter
    @ManyToOne
    @JoinColumn(name = "studentId", nullable = false)
    private Student student;

    @lombok.Setter
    @OneToMany(mappedBy = "bill", fetch = FetchType.EAGER)
    private List<Receipt> receiptList;

    public Bill(){
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void deductAmount(Integer amountToDeduct) {
        if (this.amount >= amountToDeduct) {
            this.amount -= amountToDeduct;
        } else {
            this.amount = 0;
        }
    }
}