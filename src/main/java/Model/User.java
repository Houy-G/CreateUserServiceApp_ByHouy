package Model;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class User {
    private static int idCounter = 1;
    private final int id;
    private final String uuid;
    private String name;
    private String email;
    private boolean isDeleted;

    public User(String name, String email) {
        this.id = idCounter++;
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.isDeleted = false;
    }
    @Override
    public String toString() {
        return String.format("ID: %d | UUID: %s | Name: %s | Email: %s | isDeleted: %b",
                id, uuid, name, email, isDeleted);
    }
}
