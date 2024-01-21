import React from 'react'



const NavBar = ({ user, setUser }) => {

  const logout = () => {
    window.localStorage.removeItem('loggedInUser')
    setUser(null)
  }


  if (!user)
    return null


  return (
    <div className='regular-shadow mb-1'>
      <nav class='navbar navbar-expand-lg navbar-dark bg-dark p-3' id='menu'>

        <button className='navbar-brand btn btn-link border border-light p-2'>Welcome</button>

        <ul className="navbar-nav mr-auto">
          <li className="nav-item active">

            Home
          </li>
        </ul>

        
        <div className='inline my-2 my-lg-0 ml-auto'><button className='btn btn-primary' onClick={logout}>Logout</button></div>
      </nav>
    </div>
  )
}

export default NavBar