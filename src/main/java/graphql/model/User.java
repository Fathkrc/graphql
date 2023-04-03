package graphql.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity(name = "'users'")
@AllArgsConstructor
@NoArgsConstructor
public class User extends ParentEntity{
    private String username;

    private String mail;

    @Enumerated(EnumType.STRING)
    private Role role;


}
