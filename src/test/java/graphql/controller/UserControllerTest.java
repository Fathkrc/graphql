package graphql.controller;

import graphql.model.Role;
import graphql.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Autowired
     GraphQlTester graphQlTester;

    @BeforeEach
    void setup(){
        createUser(new User("fatih","aaa.com", Role.ADMIN));
        createUser(new User("yasar","aaa.com", Role.ADMIN));
        createUser(new User("fassak","aaa.com", Role.ADMIN));
    }

    void createUser(User user){
        // language=graphql
        String mutation= """
                mutation{
                createUser( userRequest: {username:"%s",mail:"%s" ,role:"%s"}){
                           id
                           username
                           role
                           created
                           updated}
                }
                """.formatted(user.getUsername(),user.getMail(),user.getRole());
       // graphQlTester.document(mutation).execute();

    }

    @Test
    void when_getAllUsers_should_return_UsersList(){
        // language=graphql
        String query= """
                query{
                getAllUsers{
                id
                username
                role
                created
                updated}}""";
        graphQlTester.document(query).
                execute().
                path("getAllUsers").
                entityList(User.class).
                hasSize(3);

    }
}