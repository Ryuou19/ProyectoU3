
package terreno;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Globales {
    public static int alto_resolucion=800;//largo de la escena
    public static int ancho_resolucion=800;//ancho de la escena
    public static int resolucion_def=0;//opcion de resolucion
    public static int jugadores_def=2;//cantidad de jugadores
    public static int rondas_def=1;//cantidad de rondas
    public static int cantidad_def=0;//cantidad de bots
    public static int gravedad_def=0;//opcion de gravedad
    public static double gravedad=-9.81;//gravedad de juego
    public static int viento_def=0;//opcion de viento
    public static Scene escena;
    public static Stage stage;
    public static final int FPS = 80;//fps de disparo
    public static long lastFrameTime = 0;//calculo de frame
    public static long timePerFrame = 1000000000 / FPS;//calculo de frame

    public Globales() {
    }    
    //Metodo para cambiar de escena
    public static void cambiarEscena(Scene scene){
        stage.setScene(scene);
    }
    //Metodo  para cambiar resolucion
    public static void cambiarResolucion(int alto, int ancho){
        stage.setWidth(alto);
        stage.setHeight(ancho);
    }
    //Metodo para congelar ejecucion al impacto de una bala
    public static void congelar(int segundos){
        try{
            Thread.sleep(1000*segundos); 
        }catch (InterruptedException e) {}
    }  
}

