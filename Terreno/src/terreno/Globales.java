
package terreno;

import javafx.scene.Scene;
import javafx.stage.Stage;


public class Globales {
    public static int alto_resolucion;
    public static int ancho_resolucion;
    public static int resolucion_def=0;
    public static int jugadores_def=3;
    public static int rondas_def=2;
    public static int entorno_def;
    public static int cantidad_def=2;
    public static Scene escena;
    public static Stage stage;
    public static final int FPS = 80; // Ajusta esto a la cantidad deseada de FPS
    public static long lastFrameTime = 0;
    public static final long timePerFrame = 1000000000 / FPS;

    public Globales() {
    }    
    
    public static void cambiarEscena(Scene scene){
        stage.setScene(scene);
    }
    
    public static void cambiarResolucion(int alto, int ancho){
        stage.setWidth(alto);
        stage.setHeight(alto);
    }
    
    public static void congelar(int segundos){
        try{
            Thread.sleep(1000*segundos); 
        }catch (InterruptedException e) {}
    }
}

