import Dummy.*;
import View.LoginView;

public class Main {

    public static void main(String[] args) {
        InitStart.Init();

        new LoginView().login();

    }

}
