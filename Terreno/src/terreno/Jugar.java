package terreno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Random;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.stage.Popup;


public class Jugar  {  
    static Random random = new Random();
    private int turno = 1;
    double angulo;
    double velocidad;
    double cañonX;//posicion del angulo del cañon X
    double cañonY;//posicion del angulo del cañon Y
    int victoria;//colision con un tanque
    int distancia=0;
    int altura=0;
    int validar=0;//usada para diferenciar si el terreno se reinicia manualmente o por proceso de cambio de turno(boton reiniciar)   
    int tipo=0;//tipo de bala seleccionada
    double deltaTiempo = 0.1;
    Stage stage;
    ListaJugadores listJugador;
    int alto = 400;
    int ancho=300;
    Interfaz interfaz=new Interfaz(Globales.alto_resolucion,Globales.ancho_resolucion);
    int pixel = 3;
    int contador_inicio=0;
    int vidatanque1=100;
   
    
    

    public Jugar(ListaJugadores listJugador) {
        this.listJugador = listJugador;
        
    }
          
    private static int terreno_random;//variable que guarda la seleccion random del terreno
    static{
        terreno_random = random.nextInt(3);
    }
    Terreno terrain = new Terreno(alto,ancho, pixel,interfaz.gc);
    
    

   
    public void start(Stage primaryStage, Scene scene) {
        stage=Globales.stage;
        stage.setResizable(true);
        listJugador.generarTurnoAleatorio();
        
        if(Globales.rondas_def==0){
            stage.close();
        }
        
 
        
         //deberia de tomar la variable con lo que hay en configuracion         
        interfaz.iniciar_interfaz(stage,scene);
        interfaz.mostrarJugador(listJugador.getJugadorActual());
        iniciar_terreno();
        System.out.println("antes");
        System.out.println("turnos que quedan -> "+listJugador.turnosDisponibles);
        System.out.println("lista de jugadores->"+listJugador.lista);
        
        System.out.println("le toca al "+ (listJugador.getJugadorActual().jugador+1));
        System.out.println("despues de desordenar la lista");
        System.out.println("turnos que quedan -> "+listJugador.turnosDisponibles);

        //ahora el codigo se operara con el jugador que este en su turno -> listJugador.getJugadorActual();

        interfaz.finalizar.setOnAction(event -> {//se apreta finalizar y se termina la ejecucion                
            if(Globales.rondas_def>0){     
                Tienda escenaTienda = new Tienda();
                escenaTienda.inicializarInterfaz(stage, listJugador);
                
                
                System.out.println("Rondas="+Globales.rondas_def);
            }
            if(Globales.rondas_def==0){
                Platform.exit();
            }
            

        });
        interfaz.reiniciar.setOnAction(event -> {//se realiza todo el proceso para reiniciar la partida
            reiniciar_partida();
        });
        
        tipo=0;//reiniciamos el tipo para que no permita disparar la bala anterior sin antes escogerla                        
        elegir_bala();   
        
               
        interfaz.disparar.setOnAction(event ->{
                
                //cambio--------------------------------------------------------------------------------

                if (comprobarMunicion(tipo)) {//verifica si quedan balas del tipo seleccionado
                    System.out.println("no quedan balas de ese tipo");
                    return;
                }
                if(tipo==0){//si no se selecciono nada
                    return;
                }
                ingresar_disparo();//ingresa los label que guardaran el valor de angulo y velocidad
                //--------------------------------------------------------------------------------------
                String anguloTexto = interfaz.entradaangulo.getText();
                String velocidadTexto = interfaz.entradavelocidad.getText();       
                if(anguloTexto.isEmpty() || velocidadTexto.isEmpty() || anguloTexto.equals("0") || velocidadTexto.equals("0")){//verifica las entradas             
                    interfaz.disparar.setDisable(false);
                    return;
                }
                angulo = -Double.parseDouble(anguloTexto);
                velocidad = Double.parseDouble(velocidadTexto);

                    Bala nuevaBala = crear_bala();//creamos nueva bala con el tipo seleciconado
                    //comprobamos si la bala esta vacia
                    if(nuevaBala==null){
                        System.out.println("no quedan mas balas ");
                    }
                    else{
                        new AnimationTimer() {                          
                            @Override
                            public void handle(long now){
                                nuevaBala.dibujo(interfaz.gc,nuevaBala.getDanio());
                                nuevaBala.actualizarPosicion(deltaTiempo, nuevaBala, distancia, altura,interfaz.boxdistancia,interfaz.boxaltura,listJugador.getJugadorActual().getTanque().getCañonY(),listJugador.getJugadorActual().getTanque().getCañonX());
                                victoria=terrain.colision_terreno(interfaz.gc, nuevaBala,terrain.dunas, terrain.matriz,tipo);
                                if(victoria!=0){ // si victoria es distinto de 0 osea impacto a algun jugador
                                    System.out.println("Victoria = "+victoria);
                                    impacto_jugador(victoria,nuevaBala.getDanio());
                                }
                                if (nuevaBala.eliminar()) {
                                    System.out.println("Victoria = "+victoria);
                                    colision_bala();//revisa la colision y calcula la explosion generada por la bala, para tambien calcular el daño de dicha explosion(si es que existe)
                                    System.out.println("hola si se ejecuto colisicion_bala");
                                    stop();
                                    listJugador.generarTurnoAleatorio();//cambiamos el turno
                                    System.out.println("le toca al "+ (listJugador.getJugadorActual().jugador+1));
                                    
                                    animacionCaida();
                                    System.out.println("turnos que quedan -> "+listJugador.turnosDisponibles);
                                    interfaz.mostrarJugador(listJugador.getJugadorActual());
                                }
                            }
                        }.start();
                        interfaz.boxcantidadbalas.setVisible(false);
                    }        
                interfaz.entradaangulo.setText("");
                interfaz.entradavelocidad.setText("");
            }
        );
    } 
    
    public void iniciar_terreno(){//inicializa la matriz del terreno y la dibuja dependiendo de la eleccion random
        
        terrain.iniciar();
        definifirPosicion();
        validar=0;
        if(terreno_random == 0) {
            terrain.terreno_nieve(interfaz.gc, 0.0, 100,validar, terrain,alto,ancho);
            animacionCaida();
        }
        if(terreno_random == 1) {
            terrain.terreno_desierto(interfaz.gc, 0.0, 100,validar,terrain,alto,ancho);
            animacionCaida();
        }
        if(terreno_random == 2) {
            terrain.terreno_aram(interfaz.gc, 0.0, 100,validar,terrain,alto,ancho);
            animacionCaida();
        }
        stage.show();
        /*System.out.println("posicion del tanque1 x e y -> x"+listJugador.getJugador1().getTanque().getCañonX()+"y"+listJugador.getJugador1().getTanque().getCañonY());
        System.out.println("posicion del cañon x e y -> x"+listJugador.getJugador1().getTanque().getPosicionX()+"y"+listJugador.getJugador1().getTanque().getPosicionY());
        System.out.println("posicion del tanque2 x e y -> x"+listJugador.getJugador2().getTanque().getCañonX()+"y"+listJugador.getJugador2().getTanque().getCañonY());
        System.out.println("posicion del cañon x e y -> x"+listJugador.getJugador2().getTanque().getPosicionX()+"y"+listJugador.getJugador2().getTanque().getPosicionY());*/
        validar=1;
        imprimirVidaJugadores();
    }
    
    public void ingresar_disparo(){
        interfaz.disparar.setDisable(true);
        Label distanciaLabel = (Label) interfaz.boxdistancia.getChildren().get(1);
        distanciaLabel.setText(" ");                               
        Label alturaLabel = (Label) interfaz.boxaltura.getChildren().get(1);
        alturaLabel.setText(" ");           
    }
    public void impacto_jugador(int jugadorImpactado,int danio){
        jugadorImpactado=jugadorImpactado-1; //restamos para acceder al indice
        Jugador jugador = listJugador.lista.get(jugadorImpactado);
        jugador.ajustar_vida(jugador.getVida(), danio);
        listJugador.getJugadorActual().saldo+=10; // le damos 10 monedas al tanque actual por impactar
        
       
        
        interfaz.boxcantidadbalas.setVisible(false);
        if(jugador.vida<=0){
            listJugador.getJugadorActual().asesionatos+=1;// le agregamos 1 asesinato
            listJugador.eliminarJugador(jugadorImpactado);//eliminamos el jugador de la lista para que no se vuelva a dibujar
            Globales.jugadores_def-=1;
            
            if(Globales.jugadores_def==1) // si queda un jugador terminamos y reiniciamos
            {
                reiniciar_partida();
            }
        }
        System.out.println("daño del tanque "+listJugador.getJugadorActual()+"hacia el tanque"+listJugador.lista.get(jugadorImpactado));
        imprimirVidaJugadores();
    }
    
    
    public void colision_bala(){
        calcular_explosion();
        if(terreno_random == 0) {
            terrain.terreno_nieve(interfaz.gc, 0.0, vidatanque1,validar,terrain,alto,ancho);
        }
        if(terreno_random == 1) {
            terrain.terreno_desierto(interfaz.gc, 0.0, vidatanque1,validar,terrain,alto,ancho);
        }
        if(terreno_random == 2) {
            terrain.terreno_aram(interfaz.gc, 0.0, vidatanque1,validar,terrain,alto,ancho);
        }
        interfaz.disparar.setDisable(false);
        //interfaz.boxtanque1.setVisible(false);
        //interfaz.boxtanque2.setVisible(true);
        //interfaz.boxvida1.setVisible(false);
        //interfaz.boxvida2.setVisible(true);
        
        tipo=0;
    }    
    public Bala crear_bala(){
        Bala nuevaBala = null;
        int danio=0;
        if(tipo==1){
            danio=30;
        }
        if(tipo==2){
            danio=40;
        }
        if(tipo==3){
            danio=50;
        }
        cañonX = listJugador.getJugadorActual().getTanque().getCañonX();
        cañonY = listJugador.getJugadorActual().getTanque().getCañonY();
        nuevaBala = new Bala((int) cañonX, (int) cañonY, pixel , angulo, velocidad,0,danio,listJugador.getJugadorActual());
        return nuevaBala;
    }
    
    public Boolean comprobarMunicion(int tipo)//comprueba si es que las balas que posee el jugador estan vacias
    {
        switch (tipo){
            case 1:
                return listJugador.getJugadorActual().getCantidad60() == 0;
            case 2:
                return listJugador.getJugadorActual().getCantidad80() == 0;
            case 3:
                return listJugador.getJugadorActual().getCantidad105() == 0;
            default:
                break;
        }

        return false;
    }
    
    public void calcular_explosion(){//calcula la explosion en base a un area que se genera con el contacto entre la explosion y la hitbox del tanque(para efectos mas realistas)
        int area=0;
        int tanque=0;
        int choque=0;
        for(int i=0;i<terrain.matriz.length;i++){
            for(int j=0;j<terrain.matriz[i].length;j++){
                choque=terrain.matriz[i][j];
                if(terrain.explosion[i][j]==1 && choque==2){
                    area++;
                    tanque=1;
                
                }
                if(terrain.explosion[i][j]==1 && choque==3){
                    area++;
                    tanque=2;
                 
                }
                if(terrain.explosion[i][j]==1 && choque==4){
                    area++;
                    tanque=3;
                
                }
                if(terrain.explosion[i][j]==1 && choque==5){
                    area++;
                    tanque=4;
                    
                }
                if(terrain.explosion[i][j]==1 && choque==6){
                    area++;
                    tanque=5;
                    
                }
                if(terrain.explosion[i][j]==1 && choque==7){
                    area++;
                    tanque=6;
                    
                }
            }
        }
        System.out.println("el area es "+area);
        area = Math.round(area / 2);//se divide a la mitad ya que la hitbox del tanque tiene 200 valores, y la vida del tanque es 100, para hacerlo mas preciso
        if(area>terrain.radio){//en caso de que el area sea mayor al daño propio de la bala, se inflinge el daño base de la bala partido a la mitad, ya que no fue un disparo directo como tal
            switch (terrain.radio) {
                case 5 -> area=30/2;
                case 10 -> area=40/2;
                case 15 -> area=50/2;
                default -> {
                }
            }
        }
        if(tanque!=0){
            impacto_jugador(tanque,area);//realiza el impacto  a todos los tanques
            System.out.println("daño de area");
            imprimirVidaJugadores();
        }
        reiniciar_matriz(terrain.explosion);//reinicia la matriz de explosion para que asi no guarde valores de explosiones anteriores     
    }
    
    public void reiniciar_matriz(int explosion[][]) {
        for (int i = 0; i < explosion.length; i++) {
            for (int j = 0; j < explosion[i].length; j++) {
                explosion[i][j] = 0;
            }
        }
    }

    public void animacionCaida() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                terrain.borrarHitboxAnterior();
                boolean todosEnSuelo = true;
                if (terreno_random == 0) {
                    terrain.terreno_nieve(interfaz.gc, 0.0, 100, 1, terrain, alto, ancho);
                } else if (terreno_random == 1) {
                    terrain.terreno_desierto(interfaz.gc, 0.0, 100, 1, terrain, alto, ancho);
                } else if (terreno_random == 2) {
                    terrain.terreno_aram(interfaz.gc, 0.0, 100, 1, terrain, alto, ancho);
                }
                for (Jugador jugador : listJugador.getLista()) {
                    Tank tanque = jugador.getTanque();
                    int posicionInicialY = tanque.getPosicionY(); //posicion de donde cae

                    if (!tanque.estaSobreDuna(terrain)) {
                        tanque.posicionY += tanque.gravedad;
                        todosEnSuelo = false;
                    }
                    if(contador_inicio!=0)
                    {
                        int alturaCaida = tanque.getPosicionY() - posicionInicialY; // Calcular la altura de la caída
                        if (alturaCaida > tanque.dañoAltura) { // daño altura es de 10, tiene que caer de 10 de altura para hacer el daño
                            int dañoPorCaida = calcularDañoPorAltura(alturaCaida);
                            jugador.ajustar_vida(jugador.getVida(), dañoPorCaida);
                        }
                    }
                    tanque.dibuarTanque(interfaz.gc);
                    tanque.modificarCañon(interfaz.gc, 0);

                    if (todosEnSuelo) {
                         // eliminamos hitboxes anteriores
                        tanque.crearHitbox(interfaz.gc, terrain,jugador);
                        contador_inicio++;
                    }
                }
                if (todosEnSuelo) {
                    stop();
                    System.out.println("------daño de caida ----");
                    imprimirVidaJugadores();
                }
            }
        }.start();
    }


    
    public void elegir_bala(){
        interfaz.disparar.setDisable(true);//no podemos disparar mientras escogemos la bala
        interfaz.boxcantidadbalas.setVisible(true);
        int posicionx=-20;
        int posiciony=80;
        
         

        interfaz.bala1.setOnAction(event -> {//escoge bala 1

            String int_string = Integer.toString(listJugador.getJugadorActual().getCantidad60());
            interfaz.textcantidad.setText(int_string);//muestra la cantidad de balas disponibles

            interfaz.textcantidad.setStyle("-fx-text-fill: green;");
            
            tipo=1;//ajusta el tipo
            
            interfaz.disparar.setDisable(false);
        });
            
        interfaz.bala2.setOnAction(event -> {//escoge bala 2

            String int_string = Integer.toString(listJugador.getJugadorActual().getCantidad80());
            interfaz.textcantidad.setText(int_string);//lo mismo de bala1
            interfaz.textcantidad.setStyle("-fx-text-fill: blue;");
            
            tipo=2;//ajusta el tipo
           
            interfaz.disparar.setDisable(false);
        });
            
        interfaz.bala3.setOnAction(event -> {//escoge bala 3

            String int_string = Integer.toString(listJugador.getJugadorActual().getCantidad105());
            interfaz.textcantidad.setText(int_string);
            interfaz.textcantidad.setStyle("-fx-text-fill: red;");
            
            tipo=3;//ajusta el tipo
            
            interfaz.disparar.setDisable(false);
        });
            
            
              
    }
    
    public void reiniciar_partida(){
        contador_inicio=0;
        terrain.setContador(0);
            int nuevoTerreno = random.nextInt(3);         
            while (nuevoTerreno == terreno_random) {
                nuevoTerreno = random.nextInt(3);
            }            
            terreno_random = nuevoTerreno;
            listJugador.getLista().clear();
            listJugador.instanciarJugadores(Globales.jugadores_def);
            iniciar_terreno();

           
            vidatanque1=100;
           
            interfaz.textovida.setText("Vida = "+100);
    }
    public static int getRandom(){
        return terreno_random;
    }
    
    
    void definifirPosicion()
    {
        //int largo = (ancho*pixel);
        int largo = Globales.alto_resolucion-20;
        int ancho_segmento=largo/Globales.jugadores_def;
        for(int i=0;i<Globales.jugadores_def;i++)
        {
            int min=ancho_segmento*i;
            int max=ancho_segmento*(i+1);
            int posicion_inicial=random.nextInt(max-min)+min;
            listJugador.getLista().get(i).posicionInicalX=posicion_inicial;

        }

    }
    public int calcularDañoPorAltura(int altura) {
        return altura / 2;
    }
    public void imprimirVidaJugadores() {
        System.out.println("Estado actual de la vida de los jugadores:");
        for (int i = 0; i < listJugador.getLista().size(); i++) {
            Jugador jugadorActual = listJugador.getLista().get(i);
            int vidaActual = jugadorActual.getVida();
            System.out.println("Jugador " + (i + 1) + ": " + vidaActual + " de vida");
        }
    }
    
    
}
