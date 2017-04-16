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
        /*User[] users = User.parseFromXML();
        if (users != null) {
            for (User i : users) {
                System.out.println(i);
            }
        }
        User user = new User("Roma","Telyatnik","Aleksandrovich","+375298808076","Telyatnik.loh@gmail.com","BSUIR",1);
        try {
            FileWriter fileWriter = new FileWriter("input.xml",false);
            fileWriter.write(User.parseToXML(users,User.XMLBEGIN));
            fileWriter.write(user.parseToXML(User.XMLEND));
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.parseToXML(User.XMLBEGIN);
        */
    }

}


