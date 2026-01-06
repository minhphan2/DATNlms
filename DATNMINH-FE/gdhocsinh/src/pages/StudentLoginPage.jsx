import React from 'react';
import '../assets/CSS/StudentLoginPage.css';
import '../components/StudentLoginComponent.jsx';
import StudentLoginComponent from '../components/StudentLoginComponent.jsx';




function StudentLoginPage() {


  return (
    <div
     style={{
    width: '100vw',
    minHeight: '100vh',
    fontFamily: 'var(--font-family-body)',
    backgroundColor: '#f1f3f5',
        }}
    >
      <header className="header">
        <div className="header-left">
          <img
            src="./picture/logotron.jpg"
            alt="Logo"
            className="header-logo-img"
            data-media-type="image"
          />
          <div className="header-text">
            <span className="header-title-small">HỌC VIỆN NGÂN HÀNG</span>
            <span className="header-title-large">CỔNG HỌC TẬP TRỰC TUYẾN</span>
          </div>
        </div>
        <div className="header-right" data-media-type="banani-button">
          ENGLISH (EN)
          <div
            style={{
              width: 16,
              height: 16,
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
            }}
          >
            <span style={{ fontSize: 16, color: 'white' }}>▼</span>
          </div>
        </div>
      </header>

      <div className="main-container">
        <div className="login-card">
          <img
            src="./picture/logokhien.jpg"
            alt="Banking Academy Logo"
            className="card-logo"
            data-media-type="image"
          />
          <StudentLoginComponent/>
          <a className="lost-password" data-media-type="banani-button" href="#">
            Lost password?
          </a>

          <div className="card-footer">
            <div className="lang-select" data-media-type="banani-button">
              English (en)
              <div
                style={{
                  width: 14,
                  height: 14,
                  display: 'flex',
                  alignItems: 'center',
                  justifyContent: 'center',
                }}
              >
                <span style={{ fontSize: 14, color: '#009ce0' }}>▼</span>
              </div>
            </div>
            <button className="cookies-btn" data-media-type="banani-button">
              Cookies Notice
            </button>
          </div>
        </div>

        <div className="fab-help" data-media-type="banani-button">
          <div
            style={{
              width: 24,
              height: 24,
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
            }}
          >
            <span style={{ fontSize: 24, color: 'white' }}>?</span>
          </div>
        </div>
      </div>
    </div>
  );
}


export default StudentLoginPage;