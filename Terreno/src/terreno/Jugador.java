package terreno;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;



public class Jugador {

    String color = "";
    public int jugador;
    //BALAS DE CADA JUGADOR
    int cantidad105= 3;
    int cantidad80=10;
    int cantidad60 =3;

    public int getCantidad105() {
        return cantidad105;
    }

    public void setCantidad105(int cantidad105) {
        this.cantidad105 = cantidad105;
    }

    public int getCantidad80() {
        return cantidad80;
    }

    public void setCantidad80(int cantidad80) {
        this.cantidad80 = cantidad80;
    }

    public int getCantidad60() {
        return cantidad60;
    }

    public void setCantidad60(int cantidad60) {
        this.cantidad60 = cantidad60;
    }

    public Random rand;
    private Tank tanque;
    int random;
  
    public Tank getTanque() {
        return tanque;
    }
  
    public Jugador(GraphicsContext gc, String color, int jugador){
        this.color = color;
        this.jugador = jugador;
        this.rand=new Random();
        this.random=rand.nextInt(4);
        
    }
    
    public void creaTanque(GraphicsContext gc, int vida, int validar, Terreno terreno){
        if(validar==0){//verifica si se crea desde un terreno completamente nuevo o solo de un cambio de turno
            this.random=rand.nextInt(4);      
        }
        Tank tanque = new Tank(color, jugador);
        tanque.agregarTanque(gc,this.random, terreno.matriz,vida);
        this.tanque=tanque;
    }
}

