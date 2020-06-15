package project_root.utilities.logging_in;

import project_root.user.UserModel;

public class LoggedIn {
    private int id;
    private String email;
    private String password;
    private String secretKey;
    private int roleId;
    private int customerId;

    public LoggedIn(UserModel found) {
        this.id = found.getId();
        this.email = found.getEmail();
        this.roleId = found.getRoleId();
        this.customerId = found.getCustomerId();
    }
}
