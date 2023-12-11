package terreno;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class VentanaEmergente {
    
    //UNICO METODO ESTATICO QUE INSTANCIA UNA VENTANA EMERGENTE EN EL JUEGO, CON UN MENSAJE ESPECIFICO Y UN TIEMPO DETERMINADO
    public static HBox aparecer(String mensaje, int duracion) {
        HBox ventana = new HBox();//BOX DEL MENSAJE
        ventana.setStyle("-fx-background-color: rgba(100, 100, 100, 0.7); -fx-background-radius: 10;");//ESTILO
        
        Label label = new Label(mensaje);//TEXTO DEL MENSAJE
        label.setStyle("-fx-font-size: 20; -fx-text-fill: white;");//ESTILO
        
        //GUARDA Y MUESTRA LA VENTANA EMERGENTE
        ventana.getChildren().addAll(label);      
        ventana.setVisible(true);
        
        //DEFINE EL TIEMPO EN EL QUE APARECE EN EL JUEGO LA VENTANA EMERGENTE
        PauseTransition delay = new PauseTransition(Duration.seconds(duracion));
            delay.setOnFinished(e -> ventana.setVisible(false));
            delay.play();            
        return ventana;   
    }   
}

