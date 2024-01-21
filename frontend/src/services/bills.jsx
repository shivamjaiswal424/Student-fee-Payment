import axios from 'axios';

const billsUrl = 'http://localhost:9191/api/bill';

const getUserBills = async (student) => {
  try {
    const response = await axios.get(`${billsUrl}/get/${student.studentId}`);
    return response.data;
  } catch (error) {
    throw new Error('Error fetching user bills: ' + error.message);
  }
};

const payBill = async (paymentDetails) => {

  const response = await axios.post(`${billsUrl}/pay`, paymentDetails)

  return response.data;
};

const exportObject = { getUserBills, payBill };

export default exportObject;
