import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

// User Class
class User {
    private String userId;
    private String password;
    private String profileInfo;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
        this.profileInfo = "Default Profile Info";
    }

    public String getUserId() {
        return userId;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public void updateProfile(String newProfileInfo) {
        this.profileInfo = newProfileInfo;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public String getProfileInfo() {
        return profileInfo;
    }
}

// Question Class
class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

// Exam Class
class Exam {
    private List<Question> questions;
    private int[] selectedAnswers;
    private long duration; // in milliseconds
    private boolean submitted;

    public Exam(List<Question> questions, long duration) {
        this.questions = questions;
        this.selectedAnswers = new int[questions.size()];
        this.duration = duration;
        this.submitted = false;
        for (int i = 0; i < selectedAnswers.length; i++) {
            selectedAnswers[i] = -1; // -1 means no answer selected
        }
    }

    public void start() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!submitted) {
                    submit();
                }
            }
        }, duration);

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < questions.size(); i++) {
            if (submitted) break;
            Question q = questions.get(i);
            System.out.println(q.getQuestionText());
            String[] options = q.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ". " + options[j]);
            }
            System.out.print("Select your answer (1-4): ");
            int answer = scanner.nextInt();
            selectedAnswers[i] = answer - 1;
        }
        if (!submitted) {
            submit();
        }
    }

    public void submit() {
        if (!submitted) {
            submitted = true;
            System.out.println("Exam submitted.");
            // Evaluate the exam here if needed
        }
    }
}

// Main Class
public class OnlineExamSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<User> users = new ArrayList<>();
    private static User currentUser;

    public static void main(String[] args) {
        // Setup initial users
        setupInitialData();

        // Debug: Print all users
        System.out.println("Initial Users:");
        for (User user : users) {
            System.out.println("User ID: " + user.getUserId() + ", Password: " + user.validatePassword("password1")); // Add actual password for debug
        }

        // User Authentication
        if (authenticateUser()) {
            // Main Menu
            while (true) {
                System.out.println("\nSelect Operation:");
                System.out.println("1. Update Profile");
                System.out.println("2. Change Password");
                System.out.println("3. Take Exam");
                System.out.println("4. Logout");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        updateProfile();
                        break;
                    case 2:
                        changePassword();
                        break;
                    case 3:
                        takeExam();
                        break;
                    case 4:
                        System.out.println("Logged out successfully.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static void setupInitialData() {
        users.add(new User("user1", "password1"));
        users.add(new User("user2", "password2"));
    }

    private static boolean authenticateUser() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Debug: Print entered credentials
        System.out.println("Entered User ID: " + userId);
        System.out.println("Entered Password: " + password);

        for (User user : users) {
            // Debug: Print validation process
            System.out.println("Validating User ID: " + user.getUserId());
            System.out.println("Validating Password: " + password + " against " + user.getUserId() + "'s password");

            if (user.getUserId().equals(userId) && user.validatePassword(password)) {
                currentUser = user;
                System.out.println("Authentication Successful!");
                return true;
            }
        }
        System.out.println("Authentication Failed!");
        return false;
    }

    private static void updateProfile() {
        System.out.print("Enter new profile info: ");
        String newProfileInfo = scanner.nextLine();
        currentUser.updateProfile(newProfileInfo);
        System.out.println("Profile updated successfully.");
    }

    private static void changePassword() {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        currentUser.updatePassword(newPassword);
        System.out.println("Password updated successfully.");
    }

    private static void takeExam() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, 0));
        questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1));
        long examDuration = 60000; // 1 minute
        Exam exam = new Exam(questions, examDuration);
        exam.start();
    }
}
