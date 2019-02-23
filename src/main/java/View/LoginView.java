package View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginView {
    /*
    - Ask for which role the user is
    - Prompt for username
    - Prompt for password
    - Check if it's a match
     */
    public void login(){
        System.out.println("\nWelcome");
        System.out.println("Are you a (1)Organizer or (2)Customer?");

        int userRole;
        try{
            userRole = new Scanner(System.in).nextInt();

            if (userRole == 1 || userRole == 2){
                askForUserName(userRole);
            }else{
                System.out.println("Sorry, that is not an option");
            }
        }catch (InputMismatchException e){
            System.out.println("Sorry, that is not an option");
            login();
        }

    }

    private void askForUserName(int userRole) {
        System.out.print("\nUsername: ");
        String username = new Scanner(System.in).next();
        System.out.print("Password: ");
        String password = new Scanner(System.in).next();

        //Calls on controller to login

    }
}
