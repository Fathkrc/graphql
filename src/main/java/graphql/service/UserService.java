package graphql.service;

import graphql.exceptions.UserNotFound;
import graphql.model.User;
import graphql.model.UserRequest;
import graphql.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
       return repository.findAll();
    }

    public User getUserById(Long id) {
      return  repository.findById(id).orElseThrow(()-> new UserNotFound("User not found"));
    }

    public User createUser(UserRequest userRequest) {
        User user=User.builder().username(userRequest.getUsername())
                .mail(userRequest.getMail()).role(userRequest.getRole()).build();
        return repository.save(user);
    }

    public User updateUser(UserRequest userRequest) {
        User existingUser=getUserById(userRequest.getId());
        existingUser.setMail(userRequest.getMail());
        existingUser.setRole(userRequest.getRole());
        existingUser.setUsername(userRequest.getUsername());
        return repository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User existingUser=getUserById(id);
        repository.deleteById(id);

    }
}
