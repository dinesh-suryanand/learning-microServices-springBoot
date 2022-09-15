package in.dinesh.restful.restfulservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<User>();
    private static int userCount = 0;
    static {
        users.add(new User(++userCount,"Adam", LocalDate.now().minusYears(20).minusWeeks(3)));
        users.add(new User(++userCount,"Basha", LocalDate.now().minusYears(23).minusWeeks(13)));
        users.add(new User(++userCount,"Chandu", LocalDate.now().minusYears(30).minusWeeks(14)));
    }

    public List<User> findAll(){
        return users;
    }

    public User saveUser(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public User findUser(long id){
        Predicate<? super User> predicate = user -> Objects.equals(user.getId(), id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(long id){
        Predicate<? super User> predicate = user -> Objects.equals(user.getId(), id);
        users.removeIf(predicate);
    }
}
