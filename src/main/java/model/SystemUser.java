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

    public SystemUser user() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        return this;
    }
}
