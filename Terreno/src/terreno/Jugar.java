package terreno;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;



public class Jugar  {  
    static Random random = new Random();
    public boolean jugando=true;
    double angulo;
    double velocidad;
    double cañonX;//posicion del angulo del cañon X
    double cañonY;//posicion del angulo del cañon Y
    int impacto;//colision con un tanque
    int distancia=0;
    int altura=0;
    final int pixel = 3;
    int validar=0;//usada para diferenciar si el terreno se reinicia manualmente o por proceso de cambio de turno(boton reiniciar)   
    int tipo=0;//tipo de bala seleccionada
    double deltaTiempo = 0.09;
    int viento;
    
    ListaJugadores listJugador;
    int alto = (Globales.alto_resolucion/pixel);
    int ancho=(Globales.ancho_resolucion/pixel);
    Interfaz interfaz=new Interfaz(Globales.alto_resolucion,Globales.ancho_resolucion);
    
    
    int contador_inicio=0;
    int vidatanque1=100;
    private boolean disparo_en_curso = false;
    

    public Jugar(ListaJugadores listJugador) {
        this.listJugador = listJugador;
        
    }
          
    private static int terreno_random;//variable que guarda la seleccion random del terreno
    
    static{
        terreno_random =1;//random.nextInt(3);
    }
    Terreno terrain = new Terreno(Globales.alto_resolucion/pixel,Globales.ancho_resolucion/pixel, pixel,interfaz.gc);
    Tienda escenaTienda = new Tienda();
    Clasificacion resultados= new Clasificacion();
    

   
    public void start() {
        Musica.agregar_musica_terreno();
        if(revisarEstado()){
            return;
        }
        viento=Globales.cambiarViento(interfaz);//generamos el primer viento
        System.out.println("global gravedad= "+Globales.gravedad_def);
        Globales.stage.setResizable(true);
        if(Globales.gravedad_def==1){
            Globales.gravedad=-15.81;
        }
        if(Globales.gravedad_def==2){
            Globales.gravedad=-5.81;
        }

        if(Globales.rondas_def==0){
            Globales.stage.close();
        }
        //deberia de tomar la variable con lo que hay en configuracion
        interfaz.iniciar_interfaz();
        interfaz.cantidadRondas.setText(Integer.toString(Globales.rondas_def));
        interfaz.cantidadGravedad.setText(Double.toString(Globales.gravedad*-1));
        if(Globales.viento_def==0){
            interfaz.cantidadViento.setText("Nulo");
        }
        
        
        definirPosicion();
        iniciar_terreno();

        interfaz.mostrarJugador(listJugador.getJugadorActual());     

        interfaz.finalizar.setOnAction(event -> {//se apreta finalizar y se termina la ejecucion                
            finalizarJuego();
        });
        interfaz.reiniciar.setOnAction(event -> {//se realiza todo el proceso para reiniciar la partida
            Musica.detenerMusica();
            reiniciar_partida();
        });        
        tipo=0;//reiniciamos el tipo para que no permita disparar la bala anterior sin antes escogerla
        elegir_bala();
        
        interfaz.estadisticas(listJugador);
        interfaz.disparar.setOnAction(event ->{
            Musica.sonido_click();
            if (Jugador.comprobarMunicion(tipo,listJugador)) {//verifica si quedan balas del tipo seleccionado
                HBox aviso=VentanaEmergente.aparecer("¡No quedan balas  \n     de este tipo!",3);
                interfaz.canvasPane.getChildren().add(aviso);
                aviso.setLayoutX(Globales.alto_resolucion/2-70);
                aviso.setLayoutY(Globales.ancho_resolucion/2);
                if(Jugador.comprobarMunicion(1,listJugador)&&Jugador.comprobarMunicion(2,listJugador)&&Jugador.comprobarMunicion(3,listJugador)){
                    aviso=VentanaEmergente.aparecer("Jugador se quedo sin balas....",3);
                    interfaz.canvasPane.getChildren().add(aviso);
                    aviso.setLayoutX(Globales.alto_resolucion/2-120);
                    aviso.setLayoutY(0);
                    Timeline delay = new Timeline(new KeyFrame(Duration.seconds(3), e -> animacionCaida()));
                    delay.play();
                    return;
                }
                tipo=0;                   
                if(Jugador.revisarBalasDisponibles(listJugador)){
                    aviso=VentanaEmergente.aparecer("Finalizando Ronda...",3);
                    interfaz.canvasPane.getChildren().add(aviso);
                    aviso.setLayoutX(Globales.alto_resolucion/2-80);
                    aviso.setLayoutY(0);
                    Timeline delay = new Timeline(new KeyFrame(Duration.seconds(3), e -> finalizarRonda()));
                    delay.play();
                }                                               
            }              
            if(tipo==0){//si no se selecciono nada o se equivoco
                return;
            }
            interfaz.ingresar_disparo();//ingresa los label que guardaran el valor de angulo y velocidad
            String anguloTexto = interfaz.entradaangulo.getText();
            String velocidadTexto = interfaz.entradavelocidad.getText();       
            if(anguloTexto.isEmpty() || velocidadTexto.isEmpty() || anguloTexto.equals("0") || velocidadTexto.equals("0")){//verifica las entradas             
                interfaz.disparar.setDisable(false);
                return;
            }
            angulo = -Double.parseDouble(anguloTexto);
            velocidad = Double.parseDouble(velocidadTexto);
            System.out.println("la velocidad es->"+velocidad);
            Bala nuevaBala = crear_bala();//creamos nueva bala con el tipo seleciconado
            //comprobamos si la bala esta vacia
            if(nuevaBala==null){
                System.out.println("no quedan mas balas ");
            }
            else{
                Musica.sonido_disparo();
                System.out.println("disparo en curso->"+disparo_en_curso);
                disparo_en_curso = false;
                animacionBala(nuevaBala);
            }     
        interfaz.entradaangulo.setText("");
        interfaz.entradavelocidad.setText("");
        }           
        );      
    } 
    
    public void iniciar_terreno(){//inicializa la matriz del terreno y la dibuja dependiendo de la eleccion random
        if(revisarEstado()){
            return;
        }
        terrain.iniciar();       
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
        Globales.stage.show();       
    }   
    
    public void impacto_jugador(int jugadorImpactado, int danio) {
        if(revisarEstado()){
            return;
        }
        jugadorImpactado--;// Ajustar para acceder al índice
        Jugador jugador = listJugador.lista.get(jugadorImpactado);
        jugador.ajustar_vida(jugador.getVida(), danio);
        //verifica si el jugador actual es diferente del jugador impactado
        boolean es_el_mismo=false;
        if (listJugador.getJugadorActual() != jugador) {
            if (jugador.vida <= 0) {
                listJugador.getJugadorActual().asesionatos++;
                listJugador.getJugadorActual().asesinatosTotales++;
            }
        } 
        else {
            es_el_mismo=true;
        }
        if (jugador.vida <= 0) {
            terrain.borrarHitboxJugador(jugadorImpactado + 1);//valor de representacion en la hitbox del jugador
            if(!es_el_mismo){
                listJugador.eliminarJugador(jugadorImpactado); // Marca al jugador como eliminado
                HBox muerte=VentanaEmergente.aparecer("Jugador "+jugador.nombre+" muerto...", 3);
                interfaz.canvasPane.getChildren().add(muerte);
                muerte.setLayoutX(Globales.alto_resolucion-300);
                muerte.setLayoutY(0);
            }
            else{//si no es el mismo
                listJugador.getJugadorActual().suicidios++;// le agregamos un suicidio
                listJugador.getJugadorActual().suicidiosTotales++;
                HBox muerte=VentanaEmergente.aparecer("Jugador "+jugador.nombre+" se a suicidado..."+"contador de suicidios->"+jugador.suicidios, 3);
                interfaz.canvasPane.getChildren().add(muerte);
                muerte.setLayoutX(Globales.alto_resolucion-300);
                muerte.setLayoutY(0);
                listJugador.eliminarJugador(jugadorImpactado);
            }
            if (listJugador.quedaUnoVivo()) {//Si queda un jugador, termina y reinicia
                finalizarRonda();
            }
        }       
    }
   
    public void colision_bala(){
        Musica.sonido_colision();
        if(revisarEstado()){
            return;
        }
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
        tipo=0;
    } 
    
    public Bala crear_bala(){
        if(revisarEstado()){
            return null;
        }
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
        Bala nuevaBala = new Bala((int) cañonX, (int) cañonY, pixel , angulo, velocidad,0,danio,listJugador.getJugadorActual());
        return nuevaBala;
    }

    public void calcular_explosion(){//calcula la explosion en base a un area que se genera con el contacto entre la explosion y la hitbox del tanque(para efectos mas realistas)
        if(revisarEstado()){
            return;
        }
        int area=0;
        int tanque=0;
        int choque;
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
            if(terrain.radio==5){ 
                area=30/2;
            }
            if(terrain.radio==10){ 
                area=40/2;
            }
            if(terrain.radio==15){ 
                area=50/2;
            }    
        }
        if(tanque!=0){
            impacto_jugador(tanque,area);//realiza el impacto al tanque
            System.out.println("daño de area");
        }
        terrain.reiniciar_matriz(terrain.explosion);//reinicia la matriz de explosion para que asi no guarde valores de explosiones anteriores     
    }

    public void animacionCaida() {

        if(revisarEstado()){
            return;
        }
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!disparo_en_curso) {
                    disparo_en_curso = true;
                }
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
                    if(!jugador.estaEliminado())
                    {
                        Tank tanque = jugador.getTanque();
                        int posicionInicialY = tanque.getPosicionY(); //posicion de donde cae

                        if (!tanque.estaSobreDuna(terrain)) {
                            tanque.posicionY += Math.abs(Globales.gravedad);
                            todosEnSuelo = false;
                        }
                        //revisamos si esta en fuera de terreno
                        if(!tanque.esta_dentro_de_terreno(terrain))
                        {
                            stop();
                            disparo_en_curso=false;
                            HBox muerte=VentanaEmergente.aparecer("Jugador "+jugador.nombre+" muerto...", 3);
                            interfaz.canvasPane.getChildren().add(muerte);
                            muerte.setLayoutX(Globales.alto_resolucion-300);
                            muerte.setLayoutY(0);
                            listJugador.getJugadorActual().suicidios++;// le agregamos un suicidio
                            listJugador.eliminarJugador(listJugador.getJugadorActual().jugador);
                            Boolean terminar=revisarJugadores();
                            if(terminar){
                                return;
                            }
                            listJugador.generarTurnoAleatorio();
                            interfaz.mostrarJugador(listJugador.getJugadorActual());

                            if (listJugador.getJugadorActual().tipo.equals("bot")) {
                                iniciar_bot();
                            }
                            if(listJugador.quedaUnoVivo()) // si queda uno vivo terminamos la ronda
                            {
                                finalizarRonda();
                            }
                            animacionCaida();// para que se redibujen los tanques
                            return;
                        }
                        if(contador_inicio!=0)
                        {
                            // revisamos de donde esta cayendo
                            int alturaCaida = tanque.getPosicionY() - posicionInicialY; // Calcular la altura de la caída
                            if (alturaCaida > tanque.dañoAltura) { // daño altura es de 10, tiene que caer de 10 de altura para hacer el daño
                                int dañoPorCaida = calcularDañoPorAltura(alturaCaida);
                                System.out.println("daño inlfijido->"+dañoPorCaida);
                                jugador.ajustar_vida(jugador.getVida(), dañoPorCaida);
                                System.out.println("vida del jugador->"+jugador.vida);
                                if(jugador.vida<=0) // si el daño de la caida mato al tanque
                                {
                                    stop();
                                    disparo_en_curso=false;
                                    listJugador.getJugadorActual().suicidios++;// le agregamos un suicidio
                                    HBox muerte=VentanaEmergente.aparecer("Jugador "+jugador.nombre+" se a suicidado..."+"contador de suicidios->"+jugador.suicidios, 3);
                                    interfaz.canvasPane.getChildren().add(muerte);
                                    muerte.setLayoutX(Globales.alto_resolucion-300);
                                    muerte.setLayoutY(0);
                                    listJugador.eliminarJugador(listJugador.getJugadorActual().jugador);
                                    Boolean terminar=revisarJugadores();
                                    if(terminar){
                                        return;
                                    }
                                    listJugador.generarTurnoAleatorio();
                                    interfaz.mostrarJugador(listJugador.getJugadorActual());
                                    if (listJugador.getJugadorActual().tipo.equals("bot")) {
                                        iniciar_bot();
                                    }

                                    if(listJugador.quedaUnoVivo()) // si queda uno vivo terminamos la ronda
                                    {
                                        finalizarRonda();
                                    }
                                    animacionCaida();
                                    return;
                                }
                            }
                        }
                        tanque.dibuarTanque(interfaz.gc);
                        tanque.modificarCañon(interfaz.gc, 0);

                        if (todosEnSuelo) {
                             // eliminamos hitboxes anteriores
                            tanque.crearHitbox(interfaz.gc, terrain,jugador);

                        }
                    }
                }
                if (todosEnSuelo) {
                    disparo_en_curso=false;
                    stop();
                    Boolean terminar=revisarJugadores();// marcamos a los jugadores que no podran jugar
                    if(terminar) {
                        return;
                    }
                    System.out.println("animacion bala ->");
                    listJugador.generarTurnoAleatorio();//cambiamo turno
                    contador_inicio++;
                    interfaz.mostrarJugador(listJugador.getJugadorActual());
                    if (listJugador.getJugadorActual().tipo.equals("bot")) {
                        iniciar_bot();
                    }
                }
            }
        }.start();
    }


    public void elegir_bala(){
        if(listJugador.getJugadorActual().cantidad60==0){
            interfaz.bala1.setDisable(true);
        }
        if(listJugador.getJugadorActual().cantidad80==0){
            interfaz.bala2.setDisable(true);
        }
        if(listJugador.getJugadorActual().cantidad105==0){
            interfaz.bala3.setDisable(true);
        }
        interfaz.disparar.setDisable(true);//no podemos disparar mientras escogemos la bala
        
        interfaz.bala1.setOnAction(event -> {//escoge bala 1
            Musica.sonido_click();
            String int_string = Integer.toString(listJugador.getJugadorActual().getCantidad60());
            interfaz.textcantidad1.setText(int_string);//muestra la cantidad de balas disponibles       
            tipo=1;//ajusta el tipo          
            interfaz.disparar.setDisable(false);
        });
            
        interfaz.bala2.setOnAction(event -> {//escoge bala 2 
            Musica.sonido_click();
            String int_string = Integer.toString(listJugador.getJugadorActual().getCantidad80());
            interfaz.textcantidad2.setText(int_string);//lo mismo de bala1
            tipo=2;//ajusta el tipo   
            interfaz.disparar.setDisable(false);
        });
            
        interfaz.bala3.setOnAction(event -> {//escoge bala 3     
            Musica.sonido_click();
            String int_string = Integer.toString(listJugador.getJugadorActual().getCantidad105());
            interfaz.textcantidad3.setText(int_string);
            tipo=3;//ajusta el tipo       
            interfaz.disparar.setDisable(false);
        });                      
    }
    
    public void reiniciar_partida(){
        jugando=false;
        listJugador.revivir_jugadores();
        generarTerrenoNuevo();       
        escenaTienda.inicializarInterfaz(listJugador);
        System.out.println("hola soy el boton reiniciar");
        //metodos y codigo para reiniciar todos los valores de progreso de los jugadores          
    }
    public static int getRandom(){
        return terreno_random;
    }

    void definirPosicion() {
        if (revisarEstado()) {
            return;
        }
        int largo=0;
        System.out.println("el largo de la resolucion es->"+Globales.alto_resolucion);
        if(Globales.alto_resolucion>699 && Globales.alto_resolucion<=799)
        {
             largo = Globales.alto_resolucion - 200;
        }
        if(Globales.alto_resolucion>799 && Globales.alto_resolucion<=899)
        {
            largo = Globales.alto_resolucion - 250;
        }
        if(Globales.alto_resolucion>899 && Globales.alto_resolucion<=1919)
        {
            largo = Globales.alto_resolucion - 1000;
        }
        int ancho_segmento = largo / Globales.jugadores_def;

        int aux = -1;  // Inicializar con un valor que no se encuentre en el rango

        for (int i = 0; i < Globales.jugadores_def; i++) {
            int min = ancho_segmento * i;
            int max = ancho_segmento * (i + 1);
            System.out.println(min+"-"+max);
            // ajustamos el maximo y el minimo
            min=min+10;
            max=max-10;
            // Definir la posición inicial
            double factorDispersión = 1.5;
            int nuevaPosicion = generarPosicionUnica(min, max, aux, factorDispersión);
            listJugador.getLista().get(i).posicionInicalX = nuevaPosicion;
            System.out.println("posicion geenerada -> x" + nuevaPosicion);

            // Actualizar aux con la nueva posición
            aux = nuevaPosicion;
        }
    }

    private int generarPosicionUnica(int min, int max, int posicionAnterior, double factorDispersión) {
        Random random = new Random();
        int distanciaMinima = 75;
        int nuevaPosicion;
        do {
            nuevaPosicion = random.nextInt((int) (factorDispersión * (max - min))) + min;
        } while (Math.abs(nuevaPosicion - posicionAnterior) < distanciaMinima);

        return nuevaPosicion;
    }

    public int calcularDañoPorAltura(int altura) {
        return (int) (altura * Math.abs(Globales.gravedad)/ 100);
    }
    
    public void imprimirVidaJugadores() {
        System.out.println("Estado actual de la vida de los jugadores:");
        for (int i = 0; i < listJugador.lista.size(); i++) {
            Jugador jugadorActual = listJugador.lista.get(i);
            int vidaActual = jugadorActual.getVida();
            System.out.println("Jugador " + (i + 1) + ": " + vidaActual + " de vida");
        }
    }
    
    public void finalizarRonda(){
        
        listJugador.revivir(); //marcamos todos los jugadores como vivos
        terrain.borrarHitboxAnterior();//eliminamos las hitbox anteriores
        jugando=false;
        Jugador.pagar_ronda(listJugador);      
        resultados.mostrarTabla(escenaTienda,listJugador);
             
    } 
    public void generarTerrenoNuevo(){
        int terrenoAnterior=terreno_random;
        do{
            terreno_random=random.nextInt(3);
        }while (terreno_random == terrenoAnterior);
    }
    
    public void finalizarJuego(){
        Platform.exit();
    }
    
    public void elegir_bala_bot(){
        tipo=random.nextInt(3)+1;
    }

    public void animacionBala(Bala nuevaBala) {
        if(revisarEstado()){
            return;
        }
        
        
        
        new AnimationTimer() {

            long lastWindChangeTime = 0; // Variable para almacenar la última vez que cambió la dirección del viento
            int windChangeInterval = 50_000_000; // Cambiar la dirección del viento cada 1/20 de segundo
            double vientoIncremento = viento/ 20.0; // Reducir la cantidad que se suma al eje x a 1/20

            @Override
            public void handle(long now) {
                if (now - Globales.lastFrameTime >= Globales.timePerFrame) {
                    Globales.lastFrameTime = now;

                    if (!disparo_en_curso) {
                        disparo_en_curso = true;
                    }

                    nuevaBala.dibujo(interfaz.gc, nuevaBala.getDanio());
                    //actualizar la posición de la bala
                    nuevaBala.actualizarPosicion(deltaTiempo, nuevaBala, distancia, altura, interfaz.boxdistancia, interfaz.boxaltura, listJugador.getJugadorActual().getTanque().getCañonY(), listJugador.getJugadorActual().getTanque().getCañonX());

                    //cambiar la dirección del viento cada 1/20 de segundo
                    if (now - lastWindChangeTime >= windChangeInterval) {
                        lastWindChangeTime = now;
                        nuevaBala.ejeX = nuevaBala.ejeX + vientoIncremento; // Sumar 1/20 al eje x para simular el viento
                    }
                    impacto = terrain.colision_terreno(interfaz.gc, nuevaBala, terrain.dunas, terrain.matriz, tipo);

                    if (impacto != 0) {
                        System.out.println("Victoria = " + impacto);
                        impacto_jugador(impacto, nuevaBala.getDanio());
                    }

                    if (nuevaBala.eliminar()) {

                        System.out.println("Victoria = " + impacto);
                        Globales.congelar(1);
                        stop();
                        colision_bala();
                        disparo_en_curso = false;
                        viento=Globales.cambiarViento(interfaz);//cambiamos la dirección del viento
                        interfaz.estadisticas(listJugador);
                        animacionCaida();
                    }
                }
            }
        }.start();
    }

    public void iniciar_bot() {
        if(revisarEstado()){
            return;
        }
        if (!disparo_en_curso) {

            List<Integer> tiposDisponibles = new ArrayList<>();
            tiposDisponibles.add(1);tiposDisponibles.add(2);tiposDisponibles.add(3);

            boolean balaEncontrada = false;

            //verifica cada tipo de bala hasta encontrar una con municiones o agotar todas las opciones
            while (!tiposDisponibles.isEmpty() && !balaEncontrada) {
                
                int indiceAleatorio = random.nextInt(tiposDisponibles.size());
                tipo = tiposDisponibles.get(indiceAleatorio);
                balaEncontrada = !Jugador.comprobarMunicion(tipo,listJugador);

                if (!balaEncontrada) {
                    // Si no hay municiones para este tipo de balas, se elimina de la lista y se prueba el siguiente
                    tiposDisponibles.remove(indiceAleatorio);
                    tipo=0;
                }
            }
            
            revisarJugadores();

            // Configuración del disparo de la bala para el bot
            velocidad = random.nextDouble() * 35 + 30; // Ajusta estos valores según tu juego
            angulo = 60 + random.nextDouble() * 60; // angulos entre 60 y 120
            angulo=(-1)*angulo;// ajuste pq estan invertidos los angulos

            if(tipo==1){
                interfaz.textcantidad1.setText(Integer.toString(listJugador.getJugadorActual().getCantidad60()));
            }
            if(tipo==2){
                interfaz.textcantidad2.setText(Integer.toString(listJugador.getJugadorActual().getCantidad80()));
            }
            if(tipo==3){
                interfaz.textcantidad3.setText(Integer.toString(listJugador.getJugadorActual().getCantidad105()));
            }
            HBox textBot=VentanaEmergente.aparecer("Jugando un bot...",2);
            interfaz.canvasPane.getChildren().add(textBot);
            textBot.setLayoutX(Globales.alto_resolucion/2-70);
            textBot.setLayoutY(0);
            // Crear y disparar la bala
            Bala nuevaBala = crear_bala();
            animacionBala(nuevaBala);
        }
    }
  
    public Boolean revisarJugadores(){
        for(Jugador jugador : listJugador.lista){
            if((jugador.cantidad105+jugador.cantidad80+jugador.cantidad60)<=0||jugador.eliminado==true){
                System.out.println(" se desactivo ->"+(jugador.jugador+1));
                listJugador.desactivarJugador(jugador.jugador);
            }
        }
        if(listJugador.quedaUnoActivo())
        {   
            System.out.println("!!QUEDA UNO ACTIVO!!");
            finalizarRonda();
            return true;
        }
        return false;
    }
   
    public boolean revisarEstado(){
        return jugando == false;
    }

}
