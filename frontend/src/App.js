import { useState, useEffect } from 'react';
import loginService from './services/login';
import billService from './services/bills';
import Notification from './components/Notification';
import LoginForm from './components/LoginForm';
import NavBar from './components/NavBar';
import Bills from './components/Bills';

const App = () => {
  const [user, setUser] = useState(null);
  const [bills, setBills] = useState([]);
  const [notification, setNotification] = useState(null);
  const [notificationType, setNotificationType] = useState(null);

  const notificationHandler = (message, type) => {
    setNotification(message);
    setNotificationType(type);

    setTimeout(() => {
      setNotificationType(null);
      setNotification(null);
    }, 3000);
  };

  const handleLogin = async (credentials) => {
    try {
      const userObject = await loginService.login(credentials);
      setUser(userObject);
      window.localStorage.setItem('loggedInUser', JSON.stringify(userObject));

      notificationHandler(`Logged in successfully as ${userObject.firstName}`, 'success');
    } catch (exception) {
      notificationHandler('Log in failed. Check username and password entered', 'error');
    }
  };

  const payBill = async (billId) => {
    try {
      await billService.payBill(billId); 
      notificationHandler(`Successfully paid the bill`, 'success');
    } catch (exception) {
      notificationHandler(`Failed to pay the bill`, 'error');
    }
  };
  

  useEffect(() => {
    const loggedInUser = window.localStorage.getItem('loggedInUser');
    if (loggedInUser) {
      setUser(JSON.parse(loggedInUser));
    }
  }, []);

  useEffect(() => {
    async function fetchData() {
      if (user) {
        try {
          const userBills = await billService.getUserBills(user);
          console.log('User Bills:', userBills); // Check the retrieved bills
          setBills(userBills);
        } catch (error) {
          console.error('Error fetching bills:', error);
        }
      }
    }
    fetchData();
  }, [user]);


  return (
    <div>
      
      <div className='text-center page-header p-2 regular-text-shadow regular-shadow'>
        <div className='display-4 font-weight-bold'>
          IIITB Payment Portal
        </div>
      </div>

      
      <Notification notification={notification} type={notificationType} />

      
      {user === null && <LoginForm startLogin={handleLogin} />}

      
      {user !== null && <NavBar user={user} setUser={setUser} />}

      
      {user !== null && <Bills bills={bills} payBill={payBill} />} 
    </div>
  );
};

export default App;