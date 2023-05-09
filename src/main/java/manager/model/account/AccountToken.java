package manager.model.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountToken {
    private Long id;
    private String username;
    private String avatar;
    private String token;

    private List<Role> roles;
}
