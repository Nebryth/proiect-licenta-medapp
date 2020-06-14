package utilities.logging_in;

import users.User;

public class LoggedIn {
    private int id;
    private String email;
    private String password;
    private String secretKey;
    private int roleId;
    private int customerId;

    public LoggedIn(User found) {
        this.id = found.getId();
        this.email = found.getEmail();
        this.roleId = found.getRoleId();
        this.customerId = found.getCustomerId();
    }
}
