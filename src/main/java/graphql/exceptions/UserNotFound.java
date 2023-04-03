package graphql.exceptions;

import lombok.Data;

@Data
public class UserNotFound extends RuntimeException{
  public  UserNotFound (String message){
        super(message);
    }
}
