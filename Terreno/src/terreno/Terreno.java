package terreno;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Terreno{
    public int[][] matriz;//matriz general del juego
    public int pixel;//dimension de cada pixel
    public int[][]dunas;//matriz de terreno
    public int [][] explosion;//matriz de explosion
    public int radio=0;//radio en que se realiza una explosion
    private int contador=0;//verifica si es primera vez que se crea el terreno
    public int reduccionHud=66;//espacio que se destina para el hud y sus elementos
    Image nieve = new Image(getClass().getResourceAsStream("./img/frozen.jpg"));//imagen nieve
    Image desierto  = new Image(getClass().getResourceAsStream("./img/desiertoo.jpg"));//imagen desierto
    Image lol = new Image(getClass().getResourceAsStream("./img/bosque.jpg"));//imagen bosque
    ListaJugadores listJugador=ListaJugadores.getInstance();//instancia de los jugadores

    public Terreno(int alto, int ancho, int pixel,GraphicsContext gc ) {
        this.pixel=pixel;
        this.matriz=new int[alto][ancho];
        this.dunas=new int[alto][ancho];
        this.explosion=new int[alto][ancho];
        
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public void iniciar() {//inicializamos todas las matrices usadas en el juego
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++) {
                matriz[i][j]=0;
            }
        }
        for(int i=0;i<dunas.length;i++) {
            for(int j=0;j<dunas[i].length;j++) {
                dunas[i][j]=0;
            }
        }
        for(int i=0;i<explosion.length;i++) {
            for(int j=0;j<explosion[i].length;j++) {
                explosion[i][j]=0;
            }
        }
        if(Globales.resolucion_def==1){//si es 900x900 destinamos mas espacio para el hud
            reduccionHud=74;
        }
        if(Globales.resolucion_def==2){//si es 1920x1080 destinamos mas espacio para el hud
            reduccionHud=87;
        }
    }
    public void agregarImagenDeFondo(GraphicsContext gc) {//agregamos las imagenes de fondo
        if (Jugar.getRandom() == 0) {
            gc.drawImage(nieve, 0, 0, Globales.alto_resolucion, Globales.ancho_resolucion);
        }
        if (Jugar.getRandom() == 1) {
            gc.drawImage(desierto, 0, -100, Globales.alto_resolucion, Globales.ancho_resolucion);
        }
        if (Jugar.getRandom() == 2) {
            gc.drawImage(lol, 0, 0, Globales.alto_resolucion, Globales.ancho_resolucion-200);
        }

    }

    public void terreno_nieve(GraphicsContext gc, Double angulo, int vida, int validar, Terreno terreno,int alto, int ancho) {//terreno nevado
        double cambio=0;//VARIABLE MUY IMPORTANTE QUE SE USA PARA AJUSTAR EL DIBUJO DEL TERRENO A LAS DISTINTAS RESOLUCIONES, CON EL OBJETIVO QUE SEAN IGUALES EN DIMENSIONES
        if(Globales.resolucion_def==1){//900x900
            cambio=0.01;//valor de ajuste
        }
        if(Globales.resolucion_def==2){//900x900
            cambio=0.055;//valor de ajuste
        }
        int escala = this.pixel;
        double nivel_mar = 0.5;//altura base en la que se encuentra el terreno
        double amplitud = 0.08+cambio;//define el limite alto y bajo de las dunas(un valor mayor significa mas altura y valle en las montanas)
        double frecuencia = 0.087-cambio/1.1;//frecuencia con la que se dibujan las montanas
        agregarImagenDeFondo(gc);
        
        //PRIMER CICLO DE DIBUJO DEL TERRENO       
        for (int i = 0; i < alto/2; i++) {
            for (int j = 0; j < ancho-reduccionHud; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {
                        gc.setFill(Color.rgb(255, 255, 255));
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }
        //AJUSTAMOS NUEVAMENTE LOS VALORES PARA GENERAR UN RELIEVE E IRREGULARIDAD EN EL TERRENO
        nivel_mar=0.4;
        amplitud = 0.21+cambio;
        frecuencia = 0.0485-cambio/1.9201;
        
        //SEGUNDO CICLO DE DIBUJO DEL TERRENO
        for (int i = alto/2; i < alto; i++) {
            for (int j = 0; j < ancho-reduccionHud; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {
                        gc.setFill(Color.rgb(255, 255, 255));
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }
        if(contador==0){//verificamos que es la primera vez que se dibuja el terreno
            colocarTanquesTerreno(gc, angulo, vida, validar, terreno, alto, ancho);
        }
        contador++;
    }

    public void terreno_desierto(GraphicsContext gc, Double angulo, int vida, int validar, Terreno terreno,int alto, int ancho) {//terreno desertico   
        int escala = this.pixel;
        double cambio=0;//VARIABLE MUY IMPORTANTE QUE SE USA PARA AJUSTAR EL DIBUJO DEL TERRENO A LAS DISTINTAS RESOLUCIONES, CON EL OBJETIVO QUE SEAN IGUALES EN DIMENSIONES
        if(Globales.resolucion_def==1){
            cambio=0.008;
        } 
        if(Globales.resolucion_def==2){
            cambio=0.045;
        }     
        double nivel_mar = 0.55-cambio*2;//altura base en la que se encuentra el terreno
        double frecuencia = 0.095-cambio/0.81;//frecuencia con la que se dibujan las montanas
        double amplitud = 0.08+cambio/1.3;//define el limite alto y bajo de las dunas(un valor mayor significa mas altura y valle en las montanas)
        
        agregarImagenDeFondo(gc);
        
        //PRIMER CICLO DE DIBUJO DEL TERRENO
        for (int i = 0; i < alto/2; i++) {
            for (int j = 0; j < ancho-reduccionHud; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {
                        gc.setFill(Color.rgb(230, 190, 130));
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }
        //REAJUSTAMOS VALORES
        amplitud = 0.05+cambio/1.2;
        frecuencia = 0.06-cambio/1.3;
        nivel_mar = 0.50-cambio*2.7;
        
        //SEGUNDO CICLO DE DIBUJO DEL TERRENO
        for (int i = alto/2; i < alto; i++) {
            for (int j = 0; j < ancho-reduccionHud; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {                     
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }
        if(contador==0){//VERIFICAMOS SI ES LA PRIMERA VEZ QUE SE DIBUJA
            colocarTanquesTerreno(gc, angulo, vida, validar, terreno, alto, ancho);
        }
        contador++;
    }
    
    //ESTE ES LITERAL EL MISMO PROCESO QUE TODOS LOS DEMAS TERRENOS, SOLO QUE TIENE DISTINTOS VALORES Y MAS CICLOS DE DIVISION
    public void terreno_aram(GraphicsContext gc, Double angulo, int vida, int validar, Terreno terreno,int alto, int ancho) {//terreno de bosque
        
        double cambio=0;//VARIABLE MUY IMPORTANTE QUE SE USA PARA AJUSTAR EL DIBUJO DEL TERRENO A LAS DISTINTAS RESOLUCIONES, CON EL OBJETIVO QUE SEAN IGUALES EN DIMENSIONES
        if(Globales.resolucion_def==1){
            cambio=0.0084;
        } 
        if(Globales.resolucion_def==2){
            cambio=0.045;
        }
        
        int escala = this.pixel;
        double nivel_mar = 0.5;
        double amplitud = 0.07+cambio/1.3;
        double frecuencia = 0.1-cambio/0.8;
        agregarImagenDeFondo(gc);

        for (int i = 0; i < alto/4; i++) {
            for (int j = 0; j < ancho-reduccionHud; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {
                        gc.setFill(Color.rgb(139, 69, 19));
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }

        amplitud = 0.21+cambio/1.0;
        frecuencia = 0.045-cambio/1.7;

        for (int i = alto/4; i < alto/2; i++) {
            for (int j = 0; j < ancho-reduccionHud; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {
                        
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }

        amplitud = 0.1+cambio/0.9;
        frecuencia = 0.045-cambio/1.71;
        for (int i = alto/2; i < alto*3/4; i++) {
            for (int j = 0; j < ancho-reduccionHud; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {
                        
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }

        amplitud = 0.07+cambio/1.0;
        frecuencia = 0.1-cambio/0.97;
        for (int i = alto*3/4; i < alto; i++) {
            for (int j = 0; j < ancho-reduccionHud; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {
                        
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }
        if(contador==0){
            colocarTanquesTerreno(gc, angulo, vida, validar, terreno, alto, ancho);
        }
        contador++;
    }
    
    //METODO QUE REGISTRA LA COLISION Y RETORNA EL VALOR DE DONDE SE COLISIONO, SI UN TANQUE O ALGO MAS
    public int colision_terreno(GraphicsContext gc, Bala bala, int dunas[][], int matriz[][], int tipo) {
        int x = (int) bala.ejeX / pixel;//traspasamos la posicion x a relacion escala de la matriz y no de los pixeles
        int y = (int) bala.ejeY / pixel;//traspasamos la posicion y a relacion escala de la matriz y no de los pixeles
        if (x >= 0 && x < matriz.length && y >= 0 && y < matriz[1].length) {
            int valorMatriz=matriz[x][y];
            if (valorMatriz>=2) {
                bala.marcar();
                //BUSCAMOS EL VALOR DEL TANQUE GOLPEADO
                if(valorMatriz==2){
                    return 1;
                }
                if(valorMatriz==3){
                    return 2;
                }
                if(valorMatriz==4){
                    return 3;
                }
                if(valorMatriz==5){
                    return 4;
                }
                if(valorMatriz==6){
                    return 5;
                }
                if(valorMatriz==7){
                    return 6;
                }
                
                
            } else if (dunas[x][y] == 1) {//colision en el terreno
                bala.marcar();
                matriz[x][y] = 0;
                //DEPENDIENDO DE LA BALA SE ELIGE EL RADIO DE EXPLOSION
                if(tipo==1){
                    radio=8;
                }
                if(tipo==2){
                    radio=12;
                }
                if(tipo==3){
                    radio=16;
                }
                for (int i = x - radio; i <= x + radio; i++) {//realiza la explosion del disparo dependiendo del tipo de bala en base a un radio
                    for (int j = y - radio; j <= y + radio; j++) {
                        // Calcula la distancia desde el punto de impacto (i-j)
                        double distancia= Math.sqrt((i - x) * (i - x) + (j - y) * (j - y));
                        // Verifica que la celda esté dentro del radio circular (pero no en el límite)
                        if (i >= 0 && i < dunas.length && j >= 0 && j < dunas[i].length && distancia < radio) {//verifica los limites de la matriz
                            dunas[i][j] = -1;
                            explosion[i][j]=1;//se añade un valor de explosion a la matriz dicha
                            int x1 = i * pixel;
                            int y1 = j * pixel;
                            gc.clearRect(x1, y1, pixel, pixel);//se limpia la zona de la matriz que fue destruida
                        }
                    }
                }
            }
        }
        //SI SE SALE DE LOS LIMITES DE LA INTERFAZ
        if (x > Globales.alto_resolucion/3 || x < 0 || y > Globales.ancho_resolucion/3) {
            bala.marcar();
        }
        return 0;
    }
    //CREA LOS TANQUES Y POSICIONA SU DISPARO 
    public void colocarTanquesTerreno(GraphicsContext gc, Double angulo, int vida, int validar, Terreno terreno, int alto, int ancho) {
        for (Jugador jugador : listJugador.getLista()) {
            jugador.creaTanque(gc, vida, validar, terreno);
            jugador.getTanque().modificarCañon(gc, angulo);
        }
    }
    
    //METODO QUE BORRA LA HITBOX PASADA POR SI UN TANQUE SE MUEVE AL CAER
    public void borrarHitboxAnterior() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] >= 2) {
                    matriz[i][j] = 0;
                }
            }
        }
    }
    
    //BORRA LA HITBOX DEL JUGADOR EN CASO DE HABER MUERTO
    public void borrarHitboxJugador(int indiceJugador) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j]== indiceJugador) {
                    matriz[i][j] = 0;
                }
            }
        }
    }
    
    //REINICIA LA MATRIZ DE EXPLOSION PARA QUE NO GUARDE EXPLOSIONES PASADAS Y APLIQUE DAMAGES INEXISTENTE
    public void reiniciar_matriz(int explosion[][]) {
        for (int i = 0; i < explosion.length; i++) {
            for (int j = 0; j < explosion[i].length; j++) {
                explosion[i][j] = 0;
            }
        }
    }
}  
