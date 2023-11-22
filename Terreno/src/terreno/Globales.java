
package terreno;

import javafx.scene.Scene;
import javafx.stage.Stage;


public class Globales {
    public static int alto_resolucion=900;
    public static int ancho_resolucion=900;
    public static int resolucion_def=2;
    public static int jugadores_def=4;
    public static int rondas_def=2;
    public static int entorno_def;
    public static int cantidad_def;
    public static Scene escena;
    public static Stage stage;

    public Globales() {
    }    
    
    public void cambiarEscena(Scene scene){
        stage.setScene(scene);
    }
    
    public void cambiarResolucion(int alto, int ancho){
        stage.setWidth(alto);
        stage.setHeight(alto);
    }
}

