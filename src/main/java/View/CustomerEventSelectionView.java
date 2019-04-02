package View;

import Controller.CustomerEventSelectionController;

import java.util.Scanner;

public class CustomerEventSelectionView {
    private Scanner scanner = new Scanner(System.in);
    CustomerEventSelectionController customerEventSelectionController = new CustomerEventSelectionController();

    public void promptUserForAEventSeletion(){
        customerEventSelectionController.enterCustomerMenu();
        System.out.println("Please enter the event number of the Event you wish to purchase tickets to.");
        String eventNumberInput = scanner.next();
        if(customerEventSelectionController.validateUserSelection(eventNumberInput)!=null){
            new OrderView(customerEventSelectionController.validateUserSelection(eventNumberInput)).displayPromptForSelectingAEvent();

            }else{
            System.out.println("Invalid selection entered");
            promptUserForAEventSeletion();
            }
        }
    }

