package com.pccw.rest_api.controller;

import com.pccw.rest_api.api.SendEmailApi;
import com.pccw.rest_api.exception.ResourceNotFoundException;
import com.pccw.rest_api.model.User;
import com.pccw.rest_api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;



@RestController
@RequestMapping("/api/v1")
public class UserController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    private SendEmailApi sendEmailApi = new SendEmailApi();
    

    /**
     * Get all users list.
     *
     * @return the list
     */
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Gets users by id.
     *
     * @param userId the user id
     * @return the users by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long userId)
        throws ResourceNotFoundException {
    	logger.info("Fetching User with id {}", userId);
        User user =
            userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    /**
     * Create user user.
     *
     * @param user the user
     * @return the user
     */
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        // sendEmailApi.sendEmail(user);
    	logger.info("Creating User : {}", user);
        final String recipientAddress = user.getEmail();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(recipientAddress);
    
        System.out.printf("********* message to ******** %s", recipientAddress);
        msg.setSubject("Registeration success!");
        msg.setText("Welcome \n Registration done successfully");  
        javaMailSender.send(msg);    
        return userRepository.save(user);  
    }

    /**
     * Update user response entity.
     *
     * @param userId the user id
     * @param userDetails the user details
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
        @PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
        throws ResourceNotFoundException {
    	logger.info("Updating User with id {}", userId);
        User user =
            userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

        user.setEmail(userDetails.getEmail());
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        user.setUpdatedAt(new Date());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Delete user map.
     *
     * @param userId the user id
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
    	logger.info("Deleting User with id {}", userId);
        User user =
            userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }    
}
