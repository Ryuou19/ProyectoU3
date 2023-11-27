package terreno;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class VentanaEmergente {

    public static HBox aparecer(String mensaje, int duracion) {
        HBox ventana = new HBox();
        ventana.setStyle("-fx-background-color: rgba(100, 100, 100, 0.7); -fx-background-radius: 10;");
        
        Label label = new Label(mensaje);
        label.setStyle("-fx-font-size: 20; -fx-text-fill: white;");
        
        ventana.getChildren().addAll(label);      
        ventana.setVisible(true);
        
        PauseTransition delay = new PauseTransition(Duration.seconds(duracion));
            delay.setOnFinished(e -> ventana.setVisible(false));
            delay.play();
             
        return ventana;
    
    }

    
}

