import java.util.Scanner;
import Model.User;
import Model.UserService;

public class UserManagementApp {
    private static final UserService userService = new UserService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nUser Management Console");
            System.out.println("1. Create User");
            System.out.println("2. Search User by UUID");
            System.out.println("3. Update User by UUID");
            System.out.println("4. Delete User by UUID");
            System.out.println("5. Display All Users");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createUser();
                case 2 -> searchUser();
                case 3 -> updateUser();
                case 4 -> deleteUser();
                case 5 -> displayUsers();
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void createUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        User user = userService.createUser(name, email);
        System.out.println("User created successfully: " + user);
    }

    private static void searchUser() {
        System.out.print("Enter UUID: ");
        String uuid = scanner.nextLine();
        User user = userService.searchUserByUuid(uuid);
        if (user != null) {
            System.out.println("User found: " + user);
        } else {
            System.out.println("User not found.");
        }
    }

    private static void updateUser() {
        System.out.print("Enter UUID: ");
        String uuid = scanner.nextLine();
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Set as deleted? (true/false): ");
        boolean isDeleted = scanner.nextBoolean();
        scanner.nextLine();

        if (userService.updateUserByUuid(uuid, name, email, isDeleted)) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void deleteUser() {
        System.out.print("Enter UUID: ");
        String uuid = scanner.nextLine();
        if (userService.deleteUserByUuid(uuid)) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void displayUsers() {
        System.out.print("Enter page number: ");
        int pageNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        userService.getPaginatedUsers(pageNumber, 5).forEach(System.out::println);
    }
}
