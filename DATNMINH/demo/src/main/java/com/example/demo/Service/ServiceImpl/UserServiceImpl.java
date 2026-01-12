package com.example.demo.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.Service.UserService;
import com.example.demo.Service.Service.auth.JwtService;
import com.example.demo.DTO.UserRequest.CreateRequest;
import com.example.demo.DTO.UserRequest.LoginRequest;
import com.example.demo.DTO.UserRequest.UpdateRequest;
import com.example.demo.DTO.UserResponse.UserResponse;
import com.example.demo.DTO.LoginResponse.LoginResponse;
import com.example.demo.DTO.CourseResponse.CourseResponse;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import com.example.demo.model.Enrollment;
import com.example.demo.Repository.EnrollmentRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private final EnrollmentRepository enrollmentRepository;
    private final CourseMapper courseMapper;


    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
        .map(userMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> FindByRole(User.Role role) {
        return userRepository.findByRole(role)
        .stream().map(userMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> findByFullName(String fullname){
        return userRepository.findByFullname(fullname)
        .stream().map(userMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);}

    @Override
    public UserResponse updateUser(Integer id , UpdateRequest request) {
         User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("khgong tim thay user"));

        userMapper.updateEntity(existingUser, request);
        User updatedUser = userRepository.save(existingUser);
        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse createUser(CreateRequest request) {
         if (userRepository.existsByEmail(request.getEmail())) {
        throw new RuntimeException("Email đã tồn tại");
    }
    
    User user = userMapper.toEntity(request);
    
    if (user.getPassword() != null && !user.getPassword().isEmpty()) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    User savedUser = userRepository.save(user);
    
    return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse getMe(User user) {
            User freshUser = userRepository.findById(user.getId())
        .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toResponse(freshUser);
    }

    @Override
    public List<CourseResponse> getMyCourses(User user) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(user.getId());
        return enrollments.stream()
        .map(enrollment -> userMapper.toCourseResponse(enrollment.getCourse()))
        .collect(Collectors.toList());
    }
    

    @Override
    public List<UserResponse> getStudentsByCourseId(Integer courseId){
        List<User> students = userRepository.findStudentsByCourseId(courseId , User.Role.student);
        return students.stream()
        .map(userMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public UserResponse getStudentById(Integer id){
        User student = userRepository.findByIdAndRole(id, User.Role.student)
        .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return userMapper.toResponse(student);
    }

}
