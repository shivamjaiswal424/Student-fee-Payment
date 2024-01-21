package com.example.esd_demo.dao.impl;

import com.example.esd_demo.dao.BillDAO;
import com.example.esd_demo.bean.Bill;
import com.example.esd_demo.bean.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BillDAOImpl implements BillDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Bill> getBills(Integer studentId) {
        return entityManager.createQuery(
                        "SELECT b FROM Bill b WHERE b.student.studentId = :studentId", Bill.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }

//    @Override
//    public Integer payBills(Integer billId, Integer amountPaid) {
//        try {
//            Bill bill = entityManager.find(Bill.class, billId);
//
//            if (bill != null && bill.getAmount() > 0) {
//                Receipt receipt = new Receipt();
//                receipt.setAmountPaid(amountPaid);
//                double remainingAmount = bill.getAmount() - amountPaid;
//                bill.setAmount(remainingAmount > 0 ? (int) remainingAmount : 0);
//
//                receipt.setBill(bill);
//                entityManager.persist(receipt);
//                entityManager.merge(bill);
//
//                return 1; // Indicate successful payment
//            } else {
//                return 0; // Indicate that the bill was not found or has already been paid
//            }
//        } catch (Exception e) {
//            LOGGER.error("An error occurred while paying the bill: {}", e.getMessage());
//            return -1; // Indicate an error occurred during payment
//        }
//    }


//    @Override
//    public void createBill(Bill bill){
//        entityManager.persist(bill);
//        entityManager.flush();
//    }

    @Override
    public List<Bill> getBillById(Integer billId) {
        return entityManager.createQuery("SELECT b FROM Bill b WHERE b.billId = :billId", Bill.class)
                .setParameter("billId", billId)
                .getResultList();
    }

    @Override
    public void updateBill(Bill bill) {
        entityManager.merge(bill);
    }

    @Override
    public void createReceipt(Receipt receipt) {
        entityManager.persist(receipt);
    }
}