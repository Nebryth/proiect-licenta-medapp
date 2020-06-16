package project_root.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Integer> {
    //Return a user given its email
    UserModel findByEmail(String email);

    Iterable<UserModel> findAll();
    //Return a user given its id
    UserModel findById(int id);

    //Return all proj.users given their role
    Iterable<UserModel> findByRoleId(int roleId);

}