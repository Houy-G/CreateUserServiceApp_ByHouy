package Model;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<User> users = new ArrayList<>();

    public User createUser(String name, String email) {
        User user = new User(name, email);
        users.add(user);
        TelegramNotifier.sendNotification("New User Created: " + user);
        return user;
    }

    public User searchUserByUuid(String uuid) {
        return users.stream().filter(user -> user.getUuid().equals(uuid)).findFirst().orElse(null);
    }

    public boolean updateUserByUuid(String uuid, String name, String email, boolean isDeleted) {
        User user = searchUserByUuid(uuid);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setDeleted(isDeleted);
            return true;
        }
        return false;
    }

    public boolean deleteUserByUuid(String uuid) {
        User user = searchUserByUuid(uuid);
        if (user != null) {
            user.setDeleted(true);
            return true;
        }
        return false;
    }

    public List<User> getPaginatedUsers(int pageNumber, int pageSize) {
        return users.stream()
                .filter(user -> !user.isDeleted())
                .skip((long) (pageNumber - 1) * pageSize)
                .limit(pageSize)
                .toList();
    }
}
