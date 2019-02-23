package View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginView {

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

        if(Controller.loggInUser.login(userRole, username, password)){
            System.out.println("You're logged in");
            /*switch (userRole){
                case 1:
                    View.OrganizerView();
                    break;
                case 2:
                    View.CustomerView();
                    break;
                default:
                    System.out.println("Something went wrong");
                    break;
            }*/
        }else{
            System.out.println("Sorry, you could not login");
            login();
        }


    }
}
