package terreno;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Tienda extends StackPane {
    Scene escena=new Scene(this, 800, 600);
    

    public void inicializarInterfaz(Stage primaryStage) {
        primaryStage.setTitle("Tienda de Armas");
        Image fondo = new Image(getClass().getResourceAsStream("./img/fondotienda.jpg"));       
        ImageView imageView = new ImageView(fondo);

        
        imageView.setPreserveRatio(false);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);

        // Agrega el ImageView como fondo del StackPane
        this.getChildren().add(imageView);
        
        Button comprarBalaButton = new Button("Comprar Bala");
        comprarBalaButton.setOnAction(event -> {            
            System.out.println("Bala comprada");
        });

        this.getChildren().add(comprarBalaButton);
        
        primaryStage.setX(280); 
        primaryStage.setY(60);    
        primaryStage.setScene(escena);      
    }
}


