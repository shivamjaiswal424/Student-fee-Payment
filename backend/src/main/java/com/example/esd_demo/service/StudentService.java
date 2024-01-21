package com.example.esd_demo.service;

import com.example.esd_demo.bean.Bill;
import com.example.esd_demo.bean.Receipt;
import com.example.esd_demo.bean.Student;
import com.example.esd_demo.dao.BillDAO;
import com.example.esd_demo.dao.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentDAO studentDAO;
    private final BillDAO billDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO, BillDAO billDAO) {
        this.studentDAO = studentDAO;
        this.billDAO = billDAO;
    }

    public Student login(Student student) {
        return studentDAO.findByEmailAndPassword(student.getEmail(), student.getPassword());
    }

    public List<Bill> getBillsForLoggedInStudent(Student student) {
        return billDAO.getBills(student.getStudentId());
    }

//    public boolean payBill(Student student, Integer billId, Integer amountPaid) {
//        Student loggedInStudent = studentDAO.findByEmailAndPassword(student.getEmail(), student.getPassword());
//
//        if (loggedInStudent != null) {
//            List<Bill> bills = billDAO.getBillById(billId);
//
//            for (Bill bill : bills) {
//                if (bill.getAmount() >= amountPaid) {
//                    Receipt receipt = new Receipt();
//                    receipt.setAmountPaid(amountPaid);
//
//                    int remainingAmount = bill.getAmount() - amountPaid;
//                    bill.setAmount(Math.max(remainingAmount, 0));
//
//                    receipt.setBill(bill);
//                    billDAO.updateBill(bill);
//                    billDAO.createReceipt(receipt);
//
//                    return true; // Payment successful
//                }
//
//            }
//        }
//        return false; // Payment failed
//    }

}