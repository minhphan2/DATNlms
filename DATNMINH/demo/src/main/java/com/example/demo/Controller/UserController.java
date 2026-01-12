package com.example.demo.Controller;


import com.example.demo.Service.Service.UserService;
import com.example.demo.DTO.UserResponse.UserResponse;
import com.example.demo.DTO.CourseResponse.CourseResponse;
import com.example.demo.DTO.UserRequest.CreateRequest;
import com.example.demo.DTO.UserRequest.UpdateRequest;


import com.example.demo.model.User;

import lombok.RequiredArgsConstructor;

import java.util.List;

//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;



@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService UserService;


    @GetMapping("/me")
   public ResponseEntity<UserResponse> getMe(@AuthenticationPrincipal User user){
         return ResponseEntity.ok(UserService.getMe(user));
   }
    

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(UserService.findAll());
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserResponse>> GetByRole(@PathVariable User.Role role){
        return ResponseEntity.ok(UserService.FindByRole(role));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateRequest request){
        UserResponse userResponse = UserService.createUser(request);
        return ResponseEntity.ok(userResponse);

    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer id, @RequestBody UpdateRequest request){
        UserResponse userResponse = UserService.updateUser(id,request);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/my-courses")
    public ResponseEntity<List<CourseResponse>> getMyCourses(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(UserService.getMyCourses(user));
    }

    @GetMapping("/bycourse/{courseId}")
    public ResponseEntity<List<UserResponse>> getStudentsByCourseId(@PathVariable Integer courseId){
        return ResponseEntity.ok(UserService.getStudentsByCourseId(courseId));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<UserResponse> getStudentById(@PathVariable Integer id){
        return ResponseEntity.ok(UserService.getStudentById(id));
    }
    

}
