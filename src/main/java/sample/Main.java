package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import static javafx.application.Application.launch;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(this.getClass().getResource("../GUI/LoginWindow.fxml"));
        primaryStage.setTitle("Login Window");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        /*Person[] people = Person.parseFromXML();
        if (people != null) {
            for (Person i : people) {
                System.out.println(i);
            }
        }
        Person user = new Person("Roma","Telyatnik","Aleksandrovich","+375298808076","Telyatnik.loh@gmail.com","BSUIR",1);
        try {
            FileWriter fileWriter = new FileWriter("input.xml",false);
            fileWriter.write(Person.parseToXML(people,Person.XMLBEGIN));
            fileWriter.write(user.parseToXML(Person.XMLEND));
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.parseToXML(Person.XMLBEGIN);
        */
    }

}


