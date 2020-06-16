package project_root.utilities.logging_in;

import project_root.user.UserModel;

public class LoggedIn {
    private Integer id;
    private String email;
    private String password;
    private Boolean using2FA;
    private String secretKey;
    private Integer customerId;
    private Integer roleId;

    public LoggedIn(UserModel found) {
        this.id = found.getId();
        this.email = found.getEmail();
        this.roleId = found.getRoleId();
        this.customerId = found.getCustomerId();
        this.using2FA = found.getUsing2FA();
        this.secretKey = found.getSecretKey();
    }
}
