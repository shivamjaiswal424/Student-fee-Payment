package com.example.esd_demo.bean;

import jakarta.persistence.*;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "receiptId")
@Getter
@Entity
@Table(name = "Receipt")
public class Receipt {
    @lombok.Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer receiptId;

    @ManyToOne
    @JoinColumn(name="billId", nullable = false)
    private Bill bill;

    @lombok.Setter
    @Column(nullable = false)
    private Integer amountPaid;

    @lombok.Setter
    @Column(nullable = false)
    private String dateOfPayment;

    public Receipt() {
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

}