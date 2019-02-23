import Dummy.*;
import View.LoginView;

public class Main {

    public static void main(String[] args) {
        //Initializing the dummy database
        InitStart.Init();

        LoginView.login();

    }

}
