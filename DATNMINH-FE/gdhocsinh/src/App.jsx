
import StudentLoginPage from './pages/StudentLoginPage.jsx';
import './assets/CSS/StudentLoginPage.css'
import {BrowserRouter,Route,Routes} from 'react-router-dom';
import StudentDashBoard from './pages/StudentDashBoard.jsx';
import { Navigate } from 'react-router-dom';



function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Navigate to="/login"/>}/>
        <Route path='/login' element={<StudentLoginPage/>}/>
        <Route path='/dashboard' element={localStorage.getItem('token')? <StudentDashBoard/> : <Navigate to="/login" />}/>
      </Routes>
    </BrowserRouter>
  )
}

export default App
