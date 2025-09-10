    package com.hems.To_Do_List.controller;

    import com.hems.To_Do_List.service.JwtService;
    import com.hems.To_Do_List.service.RegisterService;
    import com.hems.To_Do_List.model.Register;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.List;

    @RestController
    public class RegisterController {
        @Autowired
        private RegisterService registerService;
        @Autowired
        private JwtService jwtService;
        @Autowired
        AuthenticationManager authenticationManager;
        @PostMapping("register")
        public ResponseEntity<String> addUser(@RequestBody Register registerId){
            Register savedUser = registerService.addUser(registerId);

            if (savedUser != null && savedUser.getId() != null) {
                return ResponseEntity.ok("User saved successfully: " + savedUser.getUsername());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not saved!");
            }

        }
        @GetMapping("registers")
        public List<Register> addUser(){
            return registerService.getUsers();

        }

        @PostMapping("login")
        public String login(@RequestBody Register user){
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            if(authentication.isAuthenticated()){

                return jwtService.generateToken(user.getUsername());
            }else{
                return "failer";
            }
        }

        

    }
