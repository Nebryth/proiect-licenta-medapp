package ps_project.customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerModel, Integer> {
//    CustomerModel findById(Integer id);
    CustomerModel findCustomerModelById(Integer id);
}
