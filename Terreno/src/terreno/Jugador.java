package terreno;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;



public class Jugador {

    String color = "";
    public int jugador;
    String nombre="";
    //BALAS INICIALES DE CADA JUGADOR
    int cantidad105= 3;
    int cantidad80=10;
    int cantidad60 =3;
    //SALDO DE CADA JUGADOR
    int saldo;
    //hola
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
  
    public Jugador( String color, int jugador,String nombre){
        this.color = color;
        this.jugador = jugador;
        this.rand=new Random();
        this.random=rand.nextInt(4);
        this.saldo=10000;
        this.nombre=nombre;
        
    }
    
    public void creaTanque(GraphicsContext gc,  int vida, int validar, Terreno terreno){
        if(validar==0){//verifica si se crea desde un terreno completamente nuevo o solo de un cambio de turno
            this.random=rand.nextInt(4);      
        }
        Tank tanque = new Tank(color, jugador);
        tanque.agregarTanque(gc,this.random,vida,terreno);
        this.tanque=tanque;
    }
    
    public void agregar_saldo(int cantidad){
        this.saldo+=cantidad;
        System.out.println("Saldo disponible= "+this.saldo);
    }
}