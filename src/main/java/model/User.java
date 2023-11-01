package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class User {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String type;

}
