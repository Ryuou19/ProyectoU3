
package terreno;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MenuOpciones {
    private final String[] resolucion = {"800x800", "900x900", "1200x900"};
    private final String[] jugadores = {"2", "3","4","5","6"};  
    private final String[] entorno={"Ninguno","Gravedad","Viento"};
    private final String[] cantidad = {"0","1", "2","3","4","5"};
    private int opcionActual = 0;
    private Button opcion_resolucion;
    private Button opcion_jugadores; 
    private Button opcion_entorno;
    private Button opcion_cantidad;
    String estilo_botones = 
    "-fx-background-color: #000000; " + 
    "-fx-text-fill: #FFFFFF;" + 
    "-fx-border-color: #FF0000;" + 
    "-fx-border-width: 3px;" +  
    "-fx-background-radius: 0;";
    Font font = Font.font("Serif", FontWeight.NORMAL, 24);
    
    
    public void start(Stage stage,  Scene escena){
        Pane panel = new Pane();
        panel.setPrefSize(300, 300);
        escena.setRoot(panel);
        stage.setWidth(300);
        stage.setHeight(300);
        stage.setX(300); stage.setY(300);
        stage.setScene(escena);
        stage.show();
    }
}
