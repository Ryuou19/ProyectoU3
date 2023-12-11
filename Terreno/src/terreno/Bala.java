package terreno;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Bala {
    private static double distancia = 40;//distancia entre el tanque y el disparo
    private int danio;//damage de bala
    public double ejeX;//valor eje x de bala
    public double ejeY;//valor eje x de bala
    public int distanciaRecorrida=0;//distancia de bala
    public int alturaMaxima = 0;//altura de bala
    public int radiojugador;//dimension de bala
    public double anguloLanzamiento;//angulo inicial de bala
    public double velocidadLanzamiento;//velocidad inicial de bala
    public double velocidadX;//velocidad de bala en un instante X
    public double velocidadY;//velocidad de bala en un instante Y
    public boolean eliminar=false;//eliminar bala al chocar
    public int contador;//variable para comenzar a calcular distancia y altura de bala
 
    
    //Constructor
    public Bala(int x, int y, int radiojugador, double anguloLanzamiento, double velocidadLanzamiento,int contador, int danio, Jugador jugador) {
        this.radiojugador = radiojugador;
        this.anguloLanzamiento = Math.toRadians(anguloLanzamiento); 
        this.velocidadLanzamiento = velocidadLanzamiento;   
        double cañonX = x + distancia * Math.cos(this.anguloLanzamiento);
        double cañonY = y + distancia * Math.sin(this.anguloLanzamiento);
        this.ejeX = cañonX;
        this.ejeY = cañonY;
        this.velocidadX = velocidadLanzamiento * Math.cos(this.anguloLanzamiento);
        this.velocidadY = velocidadLanzamiento * Math.sin(this.anguloLanzamiento);
        this.contador = contador;
        this.danio=danio;
        //dependiendo del damage de la bala, se descuenta aqui la cantidad al inicializarse
        if(danio==30){
            jugador.setCantidad60(jugador.getCantidad60()-1);
        }
        if(danio==40){
            jugador.setCantidad80(jugador.getCantidad80()-1);
        }
        if(danio==50){
            jugador.setCantidad105(jugador.getCantidad105()-1);
        }
    }
    
    //Dibuja la bala en el entorno de GraphicsContext
    public void dibujo(GraphicsContext gc, int tipo){
        if(tipo==30){
            gc.setFill(Color.GREEN);
        }
        if(tipo==40){
            gc.setFill(Color.BLUE);
        }
        if(tipo==50){
            gc.setFill(Color.RED);
        }
        gc.fillOval(ejeX , ejeY, 2 * radiojugador, 2 * radiojugador);
    }
    
    public void marcar() {//la bala choca con algo
        eliminar=true;
    }
    
    public boolean eliminar(){//la bala se elimina al impactar
        return eliminar;
    }
    
    public int getDanio() {//tomar damage de bala
        return danio;
    }
    
    //Funcion que realiza el movimiento parabolico de la bala a traves de posiciones en eje x e y al factor deltaTiermpo
    public void actualizarPosicion(double deltaTiempo, Bala player, int distancia, int altura,  VBox boxdistancia, VBox boxaltura,double posicionInicialY,double posicionInicialX){
        ejeX += (velocidadX) * deltaTiempo;
        velocidadY -= Globales.gravedad * deltaTiempo;
        ejeY += velocidadY * deltaTiempo;
        double nuevaPosX = player.ejeX + player.velocidadX * deltaTiempo;
        double nuevaPosY = player.ejeY + player.velocidadY * deltaTiempo;
        player.ejeX = (int) nuevaPosX;
        player.ejeY = (int) nuevaPosY;
        //se calcula la distancia recorrida
        distanciaRecorrida = (int) (ejeX - posicionInicialX);
        distancia = Math.abs(distanciaRecorrida);
        //se comienza a contar la altura 
        if(contador==0){
            int alturaActual = (int) (posicionInicialY - ejeY);
            if (alturaActual<=0){
                alturaMaxima=0;
            }
            else{
                alturaActual = Math.max(alturaActual, 0);//se asegura que la altura no sea negativa
                alturaMaxima = Math.max(alturaMaxima, alturaActual);
            }
        }
        else{
            int alturaActual = (int) (posicionInicialY - ejeY);
            //calcula la altura actual
            alturaActual = Math.max(alturaActual, 0);//se asegura que la altura no sea negativa
            alturaMaxima = Math.max(alturaMaxima, alturaActual);

        }
        //mete el valor de distancia y altura de forma visual en la interfaz de juego
        Label distanciaLabel = (Label) boxdistancia.getChildren().get(1);
        distanciaLabel.setText(distancia + " Metros");
        Label alturaLabel = (Label) boxaltura.getChildren().get(1);
        alturaLabel.setText(alturaMaxima   + " Metros");
        this.contador++;
    }
    
    //Renueva el viento en cada nueva instancia de bala 
    public static int cambiarViento(Interfaz interfaz) {
        if(Globales.viento_def==0){//si el viento no esta activado
            return 0;
        }
        Random random = new Random();
        int viento_actual = random.nextInt(10) + 1;//viento con valor entre 1 y 10
        interfaz.cantidadViento.setText(Integer.toString(viento_actual)+" M/S");
        viento_actual+=19;//se suaviza para efectos mas realistas  
        return viento_actual;
    }
}