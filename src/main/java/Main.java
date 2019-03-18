import Dummy.*;
import View.MainMenuView;

public class Main {



    public static void main(String[] args) {


        if(new PaymentStub().payPal("andreni"))
            System.out.println("True");
        else
            System.out.println("False");


        //Initializing the dummy database
        InitStart.Init();
        new MainMenuView().displayMenu();

    }
}
