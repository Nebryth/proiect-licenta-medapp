package ps_project.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains all the methods definitions for the existing 'users' operations
 */
@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {

    /**
     * Returns a user when the email is provided
     *
     * @param email
     * @return UserModel
     */
    UserModel findByEmail(String email);


    /**
     * Returns the user with the given id
     *
     * @param id
     * @return UserModel
     */
    UserModel findUserById(int id);


    /**
     * Returns all the users with a given role id
     *
     * @param roleId
     * @return UserModel
     */
    // Iterable<UserModel> findUserByRoleid(int roleId);

    // List<UserModel> findAll(Iterable<Integer> id);
    // List<UserModel>
}
