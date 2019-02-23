import Dummy.*;

public class Main {

    public static void main(String[] args) {
        InitStart.Init();
        System.out.println(Database.organizers.get(0).getPassword());
    }

}
