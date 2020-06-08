package staff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "thesis-proj-schema", name = "staff")
public class Staff {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private Date birthday;
    private String email;
    private @JsonIgnore
    String password;
    private String telephone;
    private String address;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "using_2fa")
    private Boolean using2FA;
    @Column(name = "secret_key")
    private @JsonIgnore
    String secretKey;
}
