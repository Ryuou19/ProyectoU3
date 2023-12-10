
package terreno;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Random;


public class Globales {
    public static int alto_resolucion=800;
    public static int ancho_resolucion=800;
    public static int resolucion_def=0;
    public static int jugadores_def=3;
    public static int rondas_def=1;
    public static int cantidad_def=0;
    public static int gravedad_def=0;
    public static double gravedad=-9.81;
    public static int viento_def=0;
    public static Scene escena;
    public static Stage stage;
    public static final int FPS = 80; 
    public static long lastFrameTime = 0;
    public static final long timePerFrame = 1000000000 / FPS;

    public Globales() {
    }    
    
    public static void cambiarEscena(Scene scene){
        stage.setScene(scene);
    }
    
    public static void cambiarResolucion(int alto, int ancho){
        stage.setWidth(alto);
        stage.setHeight(ancho);
    }
    
    public static void congelar(int segundos){
        try{
            Thread.sleep(1000*segundos); 
        }catch (InterruptedException e) {}
    }
    
    public static int cambiarViento(Interfaz interfaz) {
        if(Globales.viento_def==0){
            return 0;
        }
        Random random = new Random();
        int viento_actual = random.nextInt(10) + 1; // Genera un número entre 1 y 10
        interfaz.cantidadViento.setText(Integer.toString(viento_actual)+" M/S");
        viento_actual+=19;      
        System.out.println("viento actual" + viento_actual);
        return viento_actual;
    }
}

