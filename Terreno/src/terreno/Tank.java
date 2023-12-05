package terreno;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;// import usado para dibujar hitbox con el fin de visualizarla


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
    int vida=100;
//cambio----------------------------------------------------    
    int gravedad=9;
    int dañoAltura=10;//si cae a mas de 5 pies de altura se hace daño

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
    public void crearHitbox( GraphicsContext gc, Terreno terreno,Jugador jugador) {
        int hitboxAncho=1;
        int hitboxLargo=2;
        int marcar_hitbox = jugador.jugador + 2; // se pone un 2 para tanque 0
        System.out.println("marca de la hitbox="+marcar_hitbox);
        for(int i=0;i<ancho+hitboxAncho;i++){
            for (int j=0;j<alto+hitboxLargo;j++){

               //actualizar la colision de la matriz
               int ajustar_posicion=6;//Esta variable se usa para colocar la hitbox del tanque en el lugar correcto, ya que por dimensiones del canvas necesita moverse 6 espacios hacia abajo para quedar correcto
               int posXMatriz = (posicionX / 3 + i);
               int posYMatriz = (posicionY / 3 + j+ajustar_posicion);
               
                if (posXMatriz >= 0 && posXMatriz < terreno.matriz.length && posYMatriz >= 0 && posYMatriz < terreno.matriz[0].length){
    
                        
                        terreno.matriz[posXMatriz][posYMatriz] = marcar_hitbox;
                        //gc.setFill(Color.GREEN);
                        //gc.fillOval(posXMatriz*3 ,posYMatriz*3, 3 , 3 );
                }
            }
        }
    }
    public boolean esta_dentro_de_terreno(Terreno terreno) {
        int limiteDerecho = terreno.matriz.length * 3; // Límite derecho de la matriz
        int limiteInferior = terreno.matriz[0].length * 3; // Límite inferior de la matriz

        if (posicionX >= 0 && posicionY >= 0) {
            if (posicionX + ancho <= limiteDerecho && posicionY + alto <= limiteInferior) {
                return true;
            }
        }
        return false;
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
                    if(contadorDunas==3)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /*public void caidaTanque(GraphicsContext gc, Terreno terrain, int tipoTerreno) {
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
    }*/

    
    
    public void dibuarTanque(GraphicsContext gc)
    {
           Image tanque = new Image(getClass().getResourceAsStream(color));
           gc.drawImage(tanque, posicionX-7, posicionY-12, 70, 70);
    }



}