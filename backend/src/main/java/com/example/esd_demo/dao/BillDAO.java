package com.example.esd_demo.dao;

import com.example.esd_demo.bean.Bill;
import com.example.esd_demo.bean.Receipt;

import java.util.List;

public interface BillDAO {
    List<Bill> getBills(Integer studentId);
    List<Bill> getBillById(Integer billId);
    void updateBill(Bill bill);
    //void createBill(Bill bill);
    void createReceipt(Receipt receipt);
    //Integer payBills(Integer billId, Integer amountPaid);
}