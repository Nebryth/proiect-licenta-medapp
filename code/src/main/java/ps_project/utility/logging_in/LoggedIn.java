package ps_project.utility.logging_in;

import ps_project.user.UserModel;


public class LoggedIn {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String password;
    private Boolean using2FA;
    private String secretKey;
    private int roleId;
    private int customerId;

    public LoggedIn(UserModel found) {
        this.id = found.getId();
        this.firstName = found.getFirstName();
        this.lastName = found.getLastName();
        this.email = found.getEmail();
        this.roleId = found.getRoleId();
        this.customerId = found.getCustomerId();
    }
}
