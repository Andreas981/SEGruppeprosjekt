package View;

import Controller.CustomerMenuController;

import java.util.Scanner;

public class CustomerEventSelectionView {
    private Scanner scanner = new Scanner(System.in);
    CustomerMenuController customerMenuController = new CustomerMenuController();

    public void promptUserForAEventSeletion(){
        customerMenuController.enterCustomerMenu();
        System.out.println("Please enter the event number of the performance you wish to purchase tickets to.");
        String eventNumberInput = scanner.next();
        if(customerMenuController.validateUserSelection(eventNumberInput)!=null){
            new OrderView(customerMenuController.validateUserSelection(eventNumberInput)).displayPromptForSelectingAEvent();
            System.out.println("Success");
            }else{
            System.out.println("Invalid selection entered");
            }
        }
    }

