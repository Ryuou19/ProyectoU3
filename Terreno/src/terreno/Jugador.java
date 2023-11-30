package terreno;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;



public class  Jugador {

    String color = "";
    public int jugador;
    public  int suicidios=0;
    public boolean activo=true;
    String nombre="";
    //BALAS INICIALES DE CADA JUGADOR
    int cantidad105=0;
    int cantidad80=0;
    int cantidad60=1;
    int vida;
    //SALDO DE CADA JUGADOR
    int saldo;
    int posicionInicalX=0;
    int posicionInicialY=100;
    //hola
    int asesionatos;
    public Random rand;
    private Tank tanque;
    int random;
    String tipo;
    public boolean eliminado = false;

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    
    
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


    public Tank getTanque() {
        return tanque;
    }
  
    public Jugador(int jugador,String nombre,String tipo){
        
        this.jugador = jugador;
        this.rand=new Random();
        this.random=rand.nextInt(4);
        this.saldo=10000;
        this.nombre=nombre;
        this.vida=100;
        this.tipo=tipo;
        //de esta forma no pasamos como parametro la imagenes haciendo mas facil la inicializacion de la clase en la pantalla de inizicio 
        if (jugador==0)
        {
            this.color="./img/tanque1.png";
        }
        if(jugador==1)
        {
            this.color="./img/tanque2.png";
        }
        if(jugador==2)
        {
            this.color="./img/tanque3.png";
        }
        if(jugador==3)
        {
            this.color="./img/tanque4.png";
        }
        if(jugador==4)
        {
            this.color="./img/tanque5.png";
        }
        if (jugador==5)
        {
            this.color="./img/tanque6.png";
        }
        
    }
    
    public void creaTanque(GraphicsContext gc,  int vida, int validar, Terreno terreno){
        if(validar==0){//verifica si se crea desde un terreno completamente nuevo o solo de un cambio de turno
            this.random=rand.nextInt(4);      
        }
        Tank tanque = new Tank(color, jugador);
        tanque.agregarTanque(gc,this.random,vida,terreno,posicionInicalX,posicionInicialY);
        this.tanque=tanque;
    }
    public void descativar()
    {
        this.activo=false;
    }
    public void eliminar() {
        this.eliminado = true;
    }

    public boolean estaEliminado() {
        return eliminado;
    }
    public void agregar_saldo(int cantidad){
        this.saldo+=cantidad;
        System.out.println("Saldo disponible= "+this.saldo);
    }
    
    //funcion que reduce la vida dependiendo del valor de la bala o del radio de explosion
    public int ajustar_vida(int vida, int danio){
        vida-=danio;
        this.vida=vida;
        return vida;
    }
}