import java.util.*;

abstract class Components {
    String name;
    int age;
    String country;

    abstract void sign();
    abstract void display(String name, int age, String country, int score, String email, int password, String username);
}

class Signup extends Components {
    public String username;
    public String email;
    public int password;
    private int Limit = 20;
    private int reaffirm_password;
    public int score = 0;

    Signup(String name, int age, String country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    void sign() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your username :");
        username = sc.nextLine();
        System.out.println("Write your email (must include '@gmail.com'):");
        email = sc.nextLine();
        System.out.println("Write your password (only numbers in 8-digits) :");
        password = sc.nextInt();
        System.out.println("Write your reaffirm-password (only numbers in 8-digits) :");
        reaffirm_password = sc.nextInt();
        for (int i = 1; i <= Limit; i++) {
            if (reaffirm_password != password) {
                System.out.println("Sorry, password does not match. Try again.");
                reaffirm_password = sc.nextInt();
            } else {
                break;
            }
        }
        score++;
        display(name, age, country, score, email, password, username);
    }

    void display(String name, int age, String country, int score, String email, int password, String username) {
        System.out.println("Your email : " + email);
        System.out.println("Your password : " + password);
        System.out.println("Your username : " + username);
        System.out.println("Now you can Login.\n");
        Login l = new Login(name, age, country, score, email, password, username);
        l.login();
    }
}

class Login extends Signup {
    Login(String name, int age, String country, int score, String Email, int pass, String username) {
        super(name, age, country);
        this.score = score;
        this.email = Email;
        this.password = pass;
        this.username = username;
    }

    void login() {
        if (score == 0) {
            System.out.println("Sorry, your account has not been created yet.");
            Menu m = new Menu();
            m.choice();
        } else {
            String enteredEmail;
            int enteredPassword;
            System.out.println(">>>>>>Login<<<<<<");
            Scanner sc = new Scanner(System.in);
            System.out.println("Write your email :");
            enteredEmail = sc.nextLine();
            System.out.println("Write your password (only numbers) :");
            enteredPassword = sc.nextInt();
            boolean loginSuccessful = false;

            for (int i = 1; i <= 10; i++) { // Limit login attempts to 10
                if (enteredEmail.equals(this.email) && enteredPassword == this.password) {
                    loginSuccessful = true;
                    break;
                } else {
                    System.out.println("Sorry, you have entered incorrect email or password. Please try again.");
                    System.out.println("Attempts left: " + (10 - i));
                    sc.nextLine(); // Consume the newline character
                    System.out.println("Write your email:");
                    enteredEmail = sc.nextLine();
                    System.out.println("Write your password (only numbers):");
                    enteredPassword = sc.nextInt();
                }
            }

            if (loginSuccessful) {
                display2(name);
            } else {
                System.out.println("Too many unsuccessful attempts.\nSorry, You have to create your account again. Returning to the main menu.");
                Menu m = new Menu();
                m.choice();
            }
        }
    }

    void display2(String name) {
        System.out.println("Email : " + email);
        System.out.println("Password : " + password);
        System.out.println("Username : " + username);
        System.out.println("Your account has now been created.\n>>>Congratulations " + name);
    }
}

class Menu {
    String name;
    int age;
    String country;
    int score = 0;
    String email = null;
    String username = null;
    int password = 0;

    void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Assalamo-Alaikum Brother, What's your name ?");
        name = sc.nextLine();
        System.out.println("What's your age ?");
        age = sc.nextInt();
        if (age > 0) {
            System.out.println("Proceeding...");
        } else if (age == 0) {
            System.out.println("Sorry, age cannot be zero.");
            System.exit(0);
        } else {
            System.out.println("Sorry, age cannot be a negative number.");
            System.exit(0);
        }
        System.out.println("What's your Country ?");
        sc.nextLine();
        country = sc.nextLine();
        System.out.println("Dear " + name + ", You have chosen to create your account.");
        System.out.println("Create your account or simply sign-in if you have one. ");
        choice();
    }

    void choice() {
        Scanner sc = new Scanner(System.in);
        System.out.println(">>>1. Sign-up\n>>>2. Login\n>>>3. Exit");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Signup s = new Signup(name, age, country);
                s.sign();
                break;
            case 2:
                Login log = new Login(name, age, country, score, email, password, username);
                log.login();
                break;
            case 3:
                System.out.println("Thank you for visiting. GoodBye.");
                System.exit(0);
        }
    }
}

public class Project_Work {
    public static void main(String[] args) {
        Menu m = new Menu();
        m.menu();
    }
}

