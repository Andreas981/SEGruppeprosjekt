import Controller.CustomerMenuController;
import Controller.RegisterUserController;
import Dummy.*;
import View.MainMenuView;
import View.SignInView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        //Initializing the dummy database
        InitStart.Init();

        new MainMenuView().displayMenu();

    }
}
