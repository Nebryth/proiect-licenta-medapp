package project_root.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import project_root.user.UserModel;
import project_root.user.UserRepository;

public class Test {

    static UserRepository userRepository;

    @Autowired
    public static void main(String[] args)
    {

//      UserModel found = userRepository.findByEmail("first@mailinator.com");
        Iterable<UserModel> found = userRepository.findAll();
      System.out.println(found.toString());
    }
}
