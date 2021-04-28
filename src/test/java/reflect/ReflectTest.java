package reflect;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectTest {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setAge(1);
        user1.setId("1");
        user1.setName("hh");
        User user2 = new User();
        user2.setAge(2);
        user2.setId("2");
        user2.setName("lll");
        userList.add(user1);
        userList.add(user2);



        peekUser("name",userList);
    }

    private static User peekUser(String filed, List<User> users) {
        Field declaredField = null;
        try {
            declaredField = User.class.getDeclaredField(filed);
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        for (User user : users) {
            try {
                declaredField.setAccessible(true);
                Object o = declaredField.get(user);
                if (StringUtils.equalsIgnoreCase(o.toString(), "lll")) {
                    return user;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
