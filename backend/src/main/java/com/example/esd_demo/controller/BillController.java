package com.example.esd_demo.controller;

import com.example.esd_demo.bean.Bill;
import com.example.esd_demo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/bill")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/get/{studentId}")
    public ResponseEntity<List<Map<String, Object>>> getBills(@PathVariable("studentId") Integer studentId) {
        List<Bill> billList = billService.getBillsForStudent(studentId);

        // Convert Bill objects to a simplified map structure for response
        List<Map<String, Object>> simplifiedBillList = billList.stream()
                .map(bill -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("billId", bill.getBillId());
                    map.put("description", bill.getDescription());
                    map.put("amount", bill.getAmount());
                    map.put("billDate", bill.getBillDate());
                    return map;
                })
                .map(billMap -> {
                    Map<String, Object> simplifiedMap = new HashMap<>();
                    simplifiedMap.put("billId", billMap.get("billId"));
                    simplifiedMap.put("description", billMap.get("description"));
                    simplifiedMap.put("amount", billMap.get("amount"));
                    simplifiedMap.put("billDate", billMap.get("billDate"));
                    return simplifiedMap;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(simplifiedBillList);
    }

    @PostMapping("/pay")
    public ResponseEntity<String> payBills(@RequestBody Map<String, Object> paymentDetails) {
        // Extracting payment details from the request

        Integer billId = (Integer) paymentDetails.get("billId");
        Integer amountPaid = (Integer) paymentDetails.get("amountPaid");

        if (billId != null && amountPaid != null) {
            boolean paymentResult = billService.payBill(billId, amountPaid);
            if (paymentResult) {
                return ResponseEntity.ok("Payment successful");
            } else {
                return ResponseEntity.badRequest().body("Invalid payment or bill not found");
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid request");
        }
    }
}