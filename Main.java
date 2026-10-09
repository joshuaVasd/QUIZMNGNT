import java.util.ArrayList;
import java.util.Scanner;

public class Main {

// ---------- Data (in-memory, as stated in the proposal) ----------
    static class User {
        String id;
        String password;
        String role; // "Admin", "Instructor", "Student"

        User(String id, String password, String role) {
            this.id = id;
            this.password = password;
            this.role = role;
        }
    }

    static ArrayList<User> users = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);



// ---------- Start ----------
    public static void main(String[] args) {
        //sample accounts
        users.add(new User("admin", "admin123", "Admin"));
        users.add(new User("1001", "instructor123", "Instructor"));
        users.add(new User("1002", "student123", "Student"));

        mainMenu();
    }

// ---------- Main Menu  ----------
    static void mainMenu() {
        while (true) {                                  // loop = connector M
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Login");
            System.out.println("2. Change password");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {                           
                case "1":
                    login();
                    break;
                case "2":
                    changePassword();
                    break;
                case "3":
                    System.out.println("Goodbye!");     // 3.) Exit -> End
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }



// ---------- Login ----------
    static void login() {
        int attempts = 0;                               // "Set attempts = 0" (once, before the loop)

        while (true) {                                  // loop = connector L
            System.out.println("\n--- LOGIN ---");
            System.out.print("Enter user ID: ");
            String id = sc.nextLine().trim();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            User user = authenticate(id, password);     // "Valid credentials?"

            if (user != null) {                         // Yes
                openDashboard(user);                    // "Identify User Role" -> B1 / C1 / D1
                return;                                 // Logout -> M1 -> Main Menu
            }

            attempts++;                                 // No -> "Adds 1 to attempts"

            if (attempts == 3) {                        // "Attempts = 3?" Yes
                System.out.println("Too many failed attempts. Continue to Change Password.");
                changePassword();                       // connector Z
                return;
            }

            // "Attempts = 3?" No
            System.out.println("Invalid credentials. Attempts left: " + (3 - attempts));
        }                                               // back to L (NOT back to attempts = 0)
    }

    static User authenticate(String id, String password) {
        for (User u : users) {
            if (u.id.equals(id) && u.password.equals(password)) {
                return u;
            }
        }
        return null;
    }



// ---------- Change password ----------
    static void changePassword() {
        while (true) {                                  // loop = connector Z
            System.out.print("\nEnter account user ID: ");
            String id = sc.nextLine().trim();

            User found = null;                          // "User exists?"
            for (User u : users) {
                if (u.id.equals(id)) {
                    found = u;
                    break;
                }
            }

            if (found == null) {                        // No
                System.out.println("User not found");
                continue;                               // -> Z (ask again)
            }

            System.out.print("Enter new password: ");
            String newPassword = sc.nextLine().trim();

            if (newPassword.isEmpty()) {
                System.out.println("Password cannot be empty.");
                continue;
            }

            found.password = newPassword;             // "System updates the user's password"
            System.out.println("Password Updated");
            return;                                     // -> M
        }
    }



// ---------- Dashboards ----------
    static void openDashboard(User user) {
        switch (user.role) {
            case "Admin":
                adminDashboard();
                break;
            case "Instructor":
                instructorDashboard();
                break;
            case "Student":
                studentDashboard();
                break;
        }
    }



// ---------- Admin ----------
    static void adminDashboard() {                     
        while (true) {
            System.out.println("\n===== ADMIN DASHBOARD =====");
            System.out.println("1. Add user");
            System.out.println("2. Remove user");
            System.out.println("3. Approve quiz");
            System.out.println("4. View users");
            System.out.println("5. Logout");
            System.out.print("Select task: ");
            String choice = sc.nextLine().trim();

            if(choice.equals("5")){
                return;
            }else if(choice.equals("1")){
                System.out.println("moggas");
            }else if(choice.equals("2")){
                System.out.println("yo");
            }else if(choice.equals("3")){
                System.out.println("igggas");
            }

        }
    }



// ---------- instructor ----------
    static void instructorDashboard() {              
        while (true) {
            System.out.println("\n===== INSTRUCTOR DASHBOARD =====");
            System.out.println("1. Create quiz");
            System.out.println("2. View student grade");
            System.out.println("3. View created quiz status");
            System.out.println("4. Logout");
            System.out.print("Select task: ");
            String choice = sc.nextLine().trim();

            if (choice.equals("4")) return;
            else if (choice.equals("1")) System.out.println("(Create quiz: not implemented yet)");

            else if (choice.equals("2")) System.out.println("(View student grade: not implemented yet)");
            
            else if (choice.equals("3")) System.out.println("(View quiz status: not implemented yet)");

            else System.out.println("Invalid choice.");
        }
    }



// ---------- Student ----------
    static void studentDashboard() {                    
        while (true) {
            System.out.println("\n===== STUDENT DASHBOARD =====");
            System.out.println("1. View instructor list");
            System.out.println("2. View and take quiz");
            System.out.println("3. View grades");
            System.out.println("4. Logout");
            System.out.print("Select task: ");
            String choice = sc.nextLine().trim();

            if (choice.equals("4")) return;
            else if (choice.equals("1")) System.out.println("(Instructor list: not implemented yet)");

            else if (choice.equals("2")) System.out.println("(Take quiz: not implemented yet)");

            else if (choice.equals("3")) System.out.println("(View grades: not implemented yet)");
            
            else System.out.println("Invalid choice.");
        }
    }
}