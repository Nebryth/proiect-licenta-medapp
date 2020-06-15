package project_root.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {
    //Return a user given its email
    UserModel findByEmail(String email);

    //Return a user given its id
    UserModel findById(int id);

    //Return all proj.users given their role
    Iterable<UserModel> findByRoleId(int roleId);

}