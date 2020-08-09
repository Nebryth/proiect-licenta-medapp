package ps_project.customer;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "thesis-proj-schema", name = "customers")
public class CustomerModel {
    @Id
    private Integer id;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String googleMapsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getGoogleMapsId() {
        return googleMapsId;
    }

    public void setGoogleMapsId(String googleMapsId) {
        this.googleMapsId = googleMapsId;
    }
}
