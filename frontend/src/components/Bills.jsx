import React, { useState } from 'react';

const Bill = ({ bill, payBill, installmentAmount, handleInputChange }) => {
  const handlePayBill = () => {
    if (installmentAmount <= 0) {
      alert('Please enter a valid installment amount.');
      return;
    }

    const paymentDetails = {
      billId: bill.billId,
      amountPaid: parseFloat(installmentAmount),
    };

    payBill(paymentDetails);
  };

  return (
    <tr key={bill.billId}>
      <td>{bill.description}</td>
      <td>{bill.billDate}</td>
      <td>{bill.amount}</td>
      <td>
        <input
          type="number"
          value={installmentAmount}
          onChange={(e) => handleInputChange(e, bill.billId)} // Pass bill ID to identify the input
          placeholder="Enter installment amount"
        />
      </td>
      <td>
        <button onClick={handlePayBill}>Pay Bill</button>
      </td>
    </tr>
  );
};

const Bills = ({ bills, payBill }) => {
  const [installmentAmount, setInstallmentAmount] = useState(0);
  const handleInputChange = (e, billId) => {
    const value = e.target.value;
    setInstallmentAmount({
      ...installmentAmount,
      [billId]: value,
    });
  };

  return (
    <div className='container'>
      <div className='m-5 p-2 rounded regular-shadow' id="bills">
        <h2 className='ml-2'>Your Bills</h2>
        <table cellPadding={10} className='table table-striped table-bordered'>
          <thead>
            <tr>
              <th>Bill Description</th>
              <th>Bill Date</th>
              <th>Bill Amount</th>
              <th>Installment Amount</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {bills.map((bill) => (
              <Bill
                key={bill.billId}
                bill={bill}
                payBill={payBill}
                installmentAmount={installmentAmount[bill.billId]}
                handleInputChange={handleInputChange}
              />
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Bills;
