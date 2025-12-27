package com.example.demo.Service.ServiceImpl.authImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.LoginResponse.LoginResponse;
import com.example.demo.DTO.UserRequest.LoginRequest;
import com.example.demo.Service.Service.auth.JwtService;
import com.example.demo.Service.Service.auth.LoginService;
import com.example.demo.model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
     @Autowired
    private JwtService jwtService;
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


   
    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(()-> new RuntimeException("Khong itmthay tai khoan"));

        if("inactive".equals(user.getStatus())){
            throw new RuntimeException("tai khoan chua kich hoat");
        }

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Sai mat khau");
        }

        String token = jwtService.generateToken(user);


        return userMapper.toLoginResponse(user, token);
    }
    

    @Override
    public void logout(String token) {
       
    }
}
