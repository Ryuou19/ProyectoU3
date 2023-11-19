package terreno;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;// import usado para dibujar hitbox con el fin de visualizarla
import javafx.application.Platform;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Tank{
    Globales c=new Globales();
    public int posicionX;
    public int posicionY=300;
    public String color;
    public int jugadorTanque;
    private double angulo;
    private int cañonX;
    private int cañonY;
    int ancho;
    int alto;   
    int vida;
//cambio----------------------------------------------------    
    int gravedad=9;
    int dañoAltura=10;//si cae a mas de 5 pies de altura se hace daño
    int alto_resolucion = new Globales().alto_resolucion;
    int ancho_resolucion = new Globales().ancho_resolucion;

//----------------------------------------------------
    ListaJugadores listaJugador = ListaJugadores.getInstance();
    public Tank(String color, int jugador){
        this.color=color;
        this.jugadorTanque = jugador;
        this.ancho=19;
        this.alto=8;
        this.vida = 100; 
    }
    
    public int getCañonX() {
        return cañonX;
    }

    public int getCañonY() {
        return cañonY;
    }
    
    public double getAngulo() {
        return angulo;
    }

    public int getVida() {
        return vida;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }
    
//cambio--------------------------------------------------------------------------------------------------------    
    public void setPosicion1(int valor){
    posicionY=valor;
    }
        public void setPosicion0(int valor){
    posicionX=valor;
    }
    public void setCañonX(int cañonX) {
        this.cañonX = cañonX;
    }

    public void setCañonY(int cañonY) {
        this.cañonY = cañonY;
    }
//-------------------------------------------------------------------------------------------------------- 
    public void modificarCañon(GraphicsContext gc, double angulo){
        this.angulo = angulo;
        int x = posicionX;
        int y = posicionY;
        
        int cx = x + 35;//valor usado para que la bala salga desde fuera de la hitbox del tanque, para que no se suicide de inmediato
        int cy = y + 35;
        double anguloRad = Math.toRadians(angulo);

        cañonX = (int) (cx + Math.cos(anguloRad) * 2);
        cañonY = (int) (cy + Math.sin(anguloRad) * 2);

        gc.save();
        gc.translate(cañonX, cañonY);
        gc.rotate(angulo);
        gc.restore();
    }
       
    public void agregarTanque(GraphicsContext gc, int ran,int vida,Terreno terreno,int posicionX, int posicionY) {
      this.posicionX= posicionX;
      this.posicionY = posicionY;
    }
    //cambio--------------------------------------------------------------------------------------------------------    
    public void crearHitbox( GraphicsContext gc, Terreno terreno) {
        int hitboxAncho=1;
        int hitboxLargo=2;
        for(int i=0;i<ancho+hitboxAncho;i++){
            for (int j=0;j<alto+hitboxLargo;j++){
               //actualizar la colision de la matriz
               int ajustar_posicion=6;//Esta variable se usa para colocar la hitbox del tanque en el lugar correcto, ya que por dimensiones del canvas necesita moverse 6 espacios hacia abajo para quedar correcto
               int posXMatriz = (posicionX / 3 + i);
               int posYMatriz = (posicionY / 3 + j+ajustar_posicion);
               if  (posXMatriz >= 0 && posXMatriz < terreno.matriz.length && posYMatriz >= 0 && posYMatriz < terreno.matriz[0].length){
                   int marcar_hitbox = 0;
                   if(jugadorTanque==0){
                        marcar_hitbox=jugadorTanque+2;// la primera marca del jugador 1 sera un 2 dado que las dunas son 1
                        terreno.matriz[posXMatriz][posYMatriz] = marcar_hitbox;
                       gc.setFill(Color.GREEN);
                       gc.fillOval(posXMatriz*3 ,posYMatriz*3, 3 , 3 );
                   }
                    else{
                        terreno.matriz[posXMatriz][posYMatriz] = marcar_hitbox; // 3,4,5 seran las marcas de los demas tanques
                       gc.setFill(Color.RED);
                       gc.fillOval(posXMatriz*3 ,posYMatriz*3, 3 , 3 );
                   }
                   marcar_hitbox=+1;
               }
            }    
        } 
    }
    public boolean estaSobreDuna(Terreno terreno) {
        int hitboxAncho = 1;
        int contadorDunas = 0;
        for(int i = 0; i < ancho + hitboxAncho; i++) {
            for(int j = alto; j < alto + 2; j++) {
                int ajustar_posicion = 6;
                int posXMatriz = (posicionX / 3 + i);
                int posYMatriz = (posicionY / 3 + j + ajustar_posicion);
                if (posXMatriz >= 0 && posXMatriz < terreno.dunas.length && posYMatriz >= 0 && posYMatriz < terreno.dunas[0].length) {
                    if(terreno.dunas[posXMatriz][posYMatriz] == 1) {
                        contadorDunas++;
                    }
                    if(contadorDunas==5)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void caidaTanque(GraphicsContext gc, Terreno terrain, int tipoTerreno) {
        float contador[] = {0};
        AnimationTimer animation = new AnimationTimer() {

            @Override
            public void handle(long now) {
                boolean caidaCompleta = true;
                if (tipoTerreno == 0) {
                    terrain.terreno_nieve(gc, 0.0, 100, 1,terrain,alto_resolucion,ancho_resolucion);

                }
                if (tipoTerreno == 1) {
                    terrain.terreno_desierto(gc, 0.0, 100, 1,terrain,alto_resolucion,ancho_resolucion);

                }
                if (tipoTerreno == 2) {
                    terrain.terreno_aram(gc, 0.0, 100, 1,terrain,alto_resolucion,ancho_resolucion);

                }
                for (Jugador jugador : listaJugador.getLista()) {

                    Tank tanque = jugador.getTanque();
                    if (!tanque.estaSobreDuna(terrain)) {
                        tanque.posicionY += tanque.gravedad;
                        caidaCompleta = false;
                    }

                    tanque.dibuarTanque(gc);
                }
                if (caidaCompleta) {
                    this.stop();
                }
            }
        };
        animation.start();
    }

    //funcion que reduce la vida dependiendo del valor de la bala o del radio de explosion
    public int ajustar_vida(int vida, int danio){
        vida-=danio;
        this.vida=vida;
        return vida;
    }
    
    public void dibuarTanque(GraphicsContext gc)
    {

           Image tanque = new Image(getClass().getResourceAsStream(color));
           gc.drawImage(tanque, posicionX-7, posicionY-12, 70, 70);
    }



}