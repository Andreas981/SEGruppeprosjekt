import Controller.OrderController;
import Dummy.*;
import View.MainMenuView;

public class Main {



    public static void main(String[] args) {

        //Initializing the dummy database
        InitStart.Init();
        new MainMenuView().displayMenu();

    }
}
