package com.example.esd_demo.service;

import com.example.esd_demo.bean.Bill;
import com.example.esd_demo.bean.Receipt;
import com.example.esd_demo.dao.BillDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BillService {
    private final BillDAO billDAO;

    @Autowired
    public BillService(BillDAO billDAO) {
        this.billDAO = billDAO;
    }

    public boolean payBill(Integer billId, Integer amountPaid) {
        Bill bill = null;
        List<Bill> billList = billDAO.getBillById(billId);
        if (billList != null && !billList.isEmpty()) {
            bill = billList.get(0); // Retrieve the first bill from the list
        }

        if (bill != null && isValidPayment(bill, amountPaid)) {
            Receipt receipt = new Receipt();
            receipt.setAmountPaid(amountPaid);
            receipt.setDateOfPayment(String.valueOf(LocalDate.now())); // Set the date of payment

            bill.deductAmount(amountPaid);

            receipt.setBill(bill);
            billDAO.updateBill(bill);
            billDAO.createReceipt(receipt);

            return true; // Payment successful
        }
        return false; // Payment failed
    }

    private boolean isValidPayment(Bill bill, Integer amountPaid) {
        return amountPaid > 0 && amountPaid <= bill.getAmount();
    }

    public List<Bill> getBillsForStudent(Integer studentId){
        return billDAO.getBills(studentId);
    }
}