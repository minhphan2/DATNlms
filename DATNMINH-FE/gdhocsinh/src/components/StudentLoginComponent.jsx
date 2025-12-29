import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../api/authapi/Login";
import '../assets/CSS/StudentLoginPage.css';
import { useEffect } from "react";

function StudentLoginComponent() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        if (localStorage.getItem('token')) {
            navigate('/dashboard');
        }
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            const data = await login(email, password);
            localStorage.setItem('token', data.token);
            localStorage.setItem('user', JSON.stringify(data.user)); // Lưu cả user
            navigate('/dashboard');
        } catch (error) {
            setError('Sai tài khoản hoặc mật khẩu');
        }
    };

    return (
        <form onSubmit={handleSubmit} className="login-form">
            <div className="input-group">
            <input className="input-feild"
                type="email"
                value={email}
                onChange={e => setEmail(e.target.value)}
                placeholder="Email"
                required
            />
            </div>
            <div className="input-group">
            <input className="input-feild"
                type="password"
                value={password}
                onChange={e => setPassword(e.target.value)}
                placeholder="Password"
                required
            />
            </div>
            <button type="submit" className="login-btn">Đăng nhập</button>
            {error && <div style={{ color: 'red' }}>{error}</div>}
        </form>
    );
}

export default StudentLoginComponent;