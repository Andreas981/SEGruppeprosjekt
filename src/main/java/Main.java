import Controller.OrderController;
import Controller.SeatedEventController;
import Dummy.*;
import View.MainMenuView;

public class Main {



    public static void main(String[] args) {

        //Initializing the dummy database
        InitStart.Init();
        new MainMenuView().displayMenu();

    }
}
