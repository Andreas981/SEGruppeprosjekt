package View;

public class LogInView {

    public void displayPromptForUserRoleChoice() {
        System.out.println("\nAre you a (1)Organizer or (2)Customer?");
    }

    public void displayPromptForNotAnOption() {
        System.out.println("Sorry, that is not an option");
    }

    public void displayPromptForSomwthingWentWrong() {
        System.out.println("Something went wrong while logging in");
    }

    public void displayPromptForLoggedIn() {
        System.out.println("You're logged in");
    }

    public void displayPromptForUsername() {
        System.out.print("\nUsername: ");
    }

    public void displayPromptForPassword() {
        System.out.print("Password: ");
    }
}
