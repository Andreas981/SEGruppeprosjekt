package View;

public class RegisterUserView {

    public RegisterUserView(){

    }

    public void displayPromptForUserName() {
        System.out.println("Please enter a desired username:");
    }

    public void displayErrorToUser(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void displayPromptForUserFirstname() {
        System.out.println("Please enter your firstname:");
    }

    public void displayPromptForUserLastname() {
        System.out.println("Please enter your lastname:");
    }

    public void displayPromptForUserEmail() {
        System.out.println("Please enter your email:");
    }

    public void displayPromptForUserPhoneNumber() {
        System.out.println("Please enter a valid phone number:");
    }

    public void displayPromptForUserPassword() {
        System.out.println("Please type in a valid password:");
    }

    public void displayPromptForBirthdate(){
        System.out.println("Please enter a valid birth date. \n" +
                "In format: YYYY-MM-DD");
    }

    public void displayUserRegistered(){
        System.out.println("User registered successfully");
    }

}
