package model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Scanner;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SystemUser {
    private String username;
    private String password;

    public SystemUser user(Scanner input) {
        System.out.println("Enter username: ");
        String username = input.nextLine();
        System.out.println("Enter password: ");
        String password = input.nextLine();
        return this;
    }
}
