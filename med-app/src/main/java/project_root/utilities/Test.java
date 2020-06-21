package project_root.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import project_root.user.UserModel;
import project_root.user.UserRepository;

public class Test {

    @Autowired
    static UserRepository userRepository;

    public static void main(String[] args)
    {

        UserModel found;

        //= userRepository.findByEmail("first@mailinator.com");

//        System.out.println(found.toString());
//        Iterable<UserModel> found2 = userRepository.findAll();
//        System.out.println(found.toString());
    }
}
