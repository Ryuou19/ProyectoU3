package terreno;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Random;
import javafx.beans.value.ChangeListener;
import javafx.stage.Popup;

public class Jugar extends Application {  
    static Random random = new Random();
    private int turno = 1;
    double angulo;
    double velocidad;
    double cañonX;//posicion del angulo del cañon X
    double cañonY;//posicion del angulo del cañon Y
    int victoria=0;//colision con un tanque
    int distancia=0;
    int altura=0;
    int validar=0;//usada para diferenciar si el terreno se reinicia manualmente o por proceso de cambio de turno(boton reiniciar)
    int vidatanque1=100;
    int vidatanque2=100;//vidas representadas en la interfaz
    int tipo=0;//tipo de bala seleccionada
    double deltaTiempo = 0.1;
    int rondas=3;
    Stage stage;
    ListaJugadores listJugador;
    int alto = 400;
    int ancho=300;
    int pixel = 3;
    Interfaz interfaz=new Interfaz();

    public Jugar(ListaJugadores listJugador) {
        this.listJugador = listJugador;
    }

    private static int terreno_random;//variable que guarda la seleccion random del terreno
    static{
        terreno_random = random.nextInt(3);
    }
    
    Terreno terrain = new Terreno(alto,ancho, pixel,interfaz.gc);
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage=primaryStage;
        stage.setResizable(false);
        interfaz.iniciar_interfaz(stage);
        iniciar_terreno();
                    
        interfaz.finalizar.setOnAction(event -> {//se apreta finalizar y se termina la ejecucion    
            stage.close();
            Tienda escenaTienda = new Tienda();
            escenaTienda.inicializarInterfaz(stage, listJugador);

        });
        interfaz.reiniciar.setOnAction(event -> {//se realiza todo el proceso para reiniciar la partida
            reiniciar_partida();
        });
        
        tipo=0;//reiniciamos el tipo para que no permita disparar la bala anterior sin antes escogerla
        
        interfaz.balas.setOnAction(e -> {            
            elegir_bala();   
        });
               
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
                                if(turno==1){
                                    nuevaBala.actualizarPosicion(deltaTiempo, nuevaBala, distancia, altura,interfaz.boxdistancia,interfaz.boxaltura,listJugador.getJugador1().getTanque().getCañonY(),listJugador.getJugador1().getTanque().getCañonX());
                                }
                                if(turno==2){
                                    nuevaBala.actualizarPosicion(deltaTiempo, nuevaBala, distancia, altura,interfaz.boxdistancia,interfaz.boxaltura,listJugador.getJugador2().getTanque().getCañonY(),listJugador.getJugador2().getTanque().getCañonX());
                                }                             
                                victoria=terrain.colision_terreno(interfaz.gc, nuevaBala,terrain.dunas, terrain.matriz,tipo);
                                if(victoria==1){
                                    impacto_jugador1(nuevaBala.getDanio());
                                }
                                else if(victoria==2){
                                    impacto_jugador2(nuevaBala.getDanio());
                                }
                                if (nuevaBala.eliminar()) {
                                    colision_bala();//revisa la colision y calcula la explosion generada por la bala, para tambien calcular el daño de dicha explosion(si es que existe)  
                                    if(turno==1){
                                        turno=2;//cambia turno
                                    }
                                    else if(turno==2){
                                        turno=1;//cambia turno
                                    }                                   
                                    stop();
                                    animacionCaida();
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
        
        validar=0;
        if(terreno_random == 0) {
            terrain.terreno_nieve(interfaz.gc, 0.0, 100,validar, terrain);
            animacionCaida();
        }
        if(terreno_random == 1) {
            terrain.terreno_desierto(interfaz.gc, 0.0, 100,validar,terrain);
            animacionCaida();
        }
        if(terreno_random == 2) {
            terrain.terreno_aram(interfaz.gc, 0.0, 100,validar,terrain);
            animacionCaida();
        }
        stage.show();
        /*System.out.println("posicion del tanque1 x e y -> x"+listJugador.getJugador1().getTanque().getCañonX()+"y"+listJugador.getJugador1().getTanque().getCañonY());
        System.out.println("posicion del cañon x e y -> x"+listJugador.getJugador1().getTanque().getPosicionX()+"y"+listJugador.getJugador1().getTanque().getPosicionY());
        System.out.println("posicion del tanque2 x e y -> x"+listJugador.getJugador2().getTanque().getCañonX()+"y"+listJugador.getJugador2().getTanque().getCañonY());
        System.out.println("posicion del cañon x e y -> x"+listJugador.getJugador2().getTanque().getPosicionX()+"y"+listJugador.getJugador2().getTanque().getPosicionY());*/
        validar=1;
    }
    
    public void ingresar_disparo(){
        interfaz.disparar.setDisable(true);
        Label distanciaLabel = (Label) interfaz.boxdistancia.getChildren().get(1);
        distanciaLabel.setText(" ");                               
        Label alturaLabel = (Label) interfaz.boxaltura.getChildren().get(1);
        alturaLabel.setText(" ");           
    }
    public void impacto_jugador1(int danio){
        int nuevavida1 = listJugador.getJugador1().getTanque().ajustar_vida(vidatanque1, danio);
        vidatanque1 = nuevavida1;
        interfaz.textovida1.setText(nuevavida1+"");
        interfaz.boxtanque1.setVisible(false);
        interfaz.boxtanque2.setVisible(true);
        interfaz.boxvida1.setVisible(false);
        interfaz.boxvida2.setVisible(true);
        interfaz.boxcantidadbalas.setVisible(false);
        if(vidatanque1<=0){
            System.out.println("HA GANADO EL JUGADOR 2!!");
            reiniciar_partida();
        }
    }
    
    public void impacto_jugador2(int danio){
        int nuevavida2 = listJugador.getJugador2().getTanque().ajustar_vida(vidatanque2, danio);
        vidatanque2 = nuevavida2;
        interfaz.textovida2.setText(nuevavida2+"");
        interfaz.boxtanque2.setVisible(false);
        interfaz.boxtanque1.setVisible(true);
        interfaz.boxvida2.setVisible(false);
        interfaz.boxvida1.setVisible(true);
        interfaz.boxcantidadbalas.setVisible(false);
        if(vidatanque2<=0){
            System.out.println("HA GANADO EL JUGADOR 1!!");
            reiniciar_partida();
            
        }
    }
    
    public void colision_bala(){
        calcular_explosion();
        if (turno==1){
            if(terreno_random == 0) {
                terrain.terreno_nieve(interfaz.gc, 0.0, vidatanque1,validar,terrain);
            }
            if(terreno_random == 1) {
                terrain.terreno_desierto(interfaz.gc, 0.0, vidatanque1,validar,terrain);
            }
            if(terreno_random == 2) {
                terrain.terreno_aram(interfaz.gc, 0.0, vidatanque1,validar,terrain);
            }
            interfaz.disparar.setDisable(false);
            interfaz.boxtanque1.setVisible(false);
            interfaz.boxtanque2.setVisible(true);
            interfaz.boxvida1.setVisible(false);
            interfaz.boxvida2.setVisible(true);
            interfaz.balas.setStyle("-fx-font-size: 16px; -fx-font-family: 'Monospaced';");//reinicia el estilo del boton de balas
            tipo=0;
            }
        if(turno==2){
            if(terreno_random == 0) {
                terrain.terreno_nieve(interfaz.gc, 0.0, vidatanque2,validar,terrain);
            }
            if(terreno_random == 1) {
                terrain.terreno_desierto(interfaz.gc, 0.0, vidatanque2,validar,terrain);
            }
            if(terreno_random == 2) {
                terrain.terreno_aram(interfaz.gc, 0.0, vidatanque2,validar,terrain);
            }
            interfaz.disparar.setDisable(false);
            interfaz.boxtanque2.setVisible(false);
            interfaz.boxtanque1.setVisible(true);
            interfaz.boxvida2.setVisible(false);
            interfaz.boxvida1.setVisible(true);
            interfaz.balas.setStyle("-fx-font-size: 16px; -fx-font-family: 'Monospaced';");
            interfaz.boxcantidadbalas.setVisible(false);
            tipo=0;
        }
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
        
        if(turno==1){
            cañonX = listJugador.getJugador1().getTanque().getCañonX();
            cañonY = listJugador.getJugador1().getTanque().getCañonY();
            nuevaBala = new Bala((int) cañonX, (int) cañonY, pixel , angulo, velocidad,0,danio,listJugador.getJugador1());
        }
        
        if(turno==2){
            cañonX = listJugador.getJugador2().getTanque().getCañonX();
            cañonY = listJugador.getJugador2().getTanque().getCañonY();
            angulo = 180 - angulo;
            nuevaBala = new Bala((int) cañonX, (int) cañonY, pixel , angulo, velocidad,0,danio,listJugador.getJugador2());                                 
        }
        return nuevaBala;
    }
    
    public Boolean comprobarMunicion(int tipo)//comprueba si es que las balas que posee el jugador estan vacias
    {
        if(turno==1){   
            switch (tipo){
                case 1:                 
                    return listJugador.getJugador1().getCantidad60() == 0;
                case 2:
                    return listJugador.getJugador1().getCantidad80() == 0;                  
                case 3:                    
                    return listJugador.getJugador1().getCantidad105() == 0;
                default:
                    break;
            }        
        }
        if(turno==2){
            switch (tipo) {
                case 1:
                    return listJugador.getJugador2().getCantidad60() == 0;
                case 2:                   
                    return listJugador.getJugador2().getCantidad80() == 0;
                case 3:                   
                    return listJugador.getJugador2().getCantidad105() == 0;
                default:
                    break;
            } 
        }
        return false;
    }
    
    public void calcular_explosion(){//calcula la explosion en base a un area que se genera con el contacto entre la explosion y la hitbox del tanque(para efectos mas realistas)
        int area=0;
        int tanque=0;
        for(int i=0;i<terrain.matriz.length;i++){
            for(int j=0;j<terrain.matriz[i].length;j++){
                if(terrain.matriz[i][j]==2 && terrain.explosion[i][j]==1 || terrain.matriz[i][j]==3 && terrain.explosion[i][j]==1){
                    area++;
                    if(terrain.matriz[i][j]==2){//explosion cerca del tanque 1
                        tanque=1;
                    }
                    if(terrain.matriz[i][j]==3){//explosion cerca del tanque 2
                        tanque=2;
                    }           
                }
            }
        }
        area = Math.round(area / 2);//se divide a la mitad ya que la hitbox del tanque tiene 200 valores, y la vida del tanque es 100, para hacerlo mas preciso
        if(area>terrain.radio){//en caso de que el area sea mayor al daño propio de la bala, se inflinge el daño base de la bala partido a la mitad, ya que no fue un disparo directo como tal
            switch (terrain.radio) {
                case 5:
                    area=30/2;
                    break;
                case 10:
                    area=40/2;
                    break;
                case 15:
                    area=50/2;
                    break;
                default:
                    break;
            }
        }
        if(tanque==1){
            impacto_jugador1(area);//realiza el impacto y el ajuste de la vida del tanque1
        }
        if(tanque==2){
            impacto_jugador2(area);;//realiza el impacto y el ajuste de la vida del tanque1
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
    
    public void animacionCaida(){
        listJugador.getJugador1().getTanque().caidaTanque(interfaz.gc,terrain,terreno_random);
        listJugador.getJugador2().getTanque().caidaTanque(interfaz.gc, terrain, terreno_random);
    }
    
    public void elegir_bala(){
        interfaz.balas.setDisable(true);
        interfaz.disparar.setDisable(true);//no podemos disparar mientras escogemos la bala
        interfaz.boxcantidadbalas.setVisible(true);
        int posicionx=-20;
        int posiciony=80;
        //VENTANA
        Popup popup = new Popup();
        popup.setX(572+posicionx);
        popup.setY(510+posiciony);
        HBox tipos = new HBox(10);
        tipos.setStyle("-fx-background-color: #C0C0C0;");
        
        //BOTONES
        Button bala1 = new Button("60mm");
        bala1.setStyle("-fx-background-color: " + "Green" + "; -fx-min-width: 25px; -fx-min-height: 30px; -fx-text-fill: white;");
        Button bala2 = new Button("80mm");
        bala2.setStyle("-fx-background-color: " + "Blue" + "; -fx-min-width: 25px; -fx-min-height: 30px; -fx-text-fill: white;");;
        Button bala3 = new Button("105mm");
        bala3.setStyle("-fx-background-color: " + "Red" + "; -fx-min-width: 25px; -fx-min-height: 30px; -fx-text-fill: white;");    

        bala1.setOnAction(event -> {//escoge bala 1
            if(turno==1){
                String int_string = Integer.toString(listJugador.getJugador1().getCantidad60());
                interfaz.textcantidad.setText(int_string);//muestra la cantidad de balas disponibles

            }
            else{
                String int_string = Integer.toString(listJugador.getJugador2().getCantidad60());
                interfaz.textcantidad.setText(int_string);//muestra la cantidad de balas disponibles
            }
            interfaz.textcantidad.setStyle("-fx-text-fill: green;");
            interfaz.balas.setStyle("-fx-font-size: 16px; -fx-font-family: 'Monospaced';-fx-font-weight: bold;-fx-text-fill: #008000;");
            tipo=1;//ajusta el tipo
            popup.hide();
            interfaz.balas.setDisable(false);
            interfaz.disparar.setDisable(false);
        });
            
        bala2.setOnAction(event -> {//escoge bala 2
            if(turno==1){
                String int_string = Integer.toString(listJugador.getJugador1().getCantidad80());
                interfaz.textcantidad.setText(int_string);//lo mismo de bala1

            }
            else{
                String int_string = Integer.toString(listJugador.getJugador2().getCantidad80());
                interfaz.textcantidad.setText(int_string);//lo mismo de bala1
            }
            interfaz.textcantidad.setStyle("-fx-text-fill: blue;");
            interfaz.balas.setStyle("-fx-font-size: 16px; -fx-font-family: 'Monospaced';-fx-font-weight: bold;-fx-text-fill: #0000FF;");
            tipo=2;//ajusta el tipo
            popup.hide();
            interfaz.balas.setDisable(false);
            interfaz.disparar.setDisable(false);
        });
            
        bala3.setOnAction(event -> {//escoge bala 3
            if(turno==1){
                String int_string = Integer.toString(listJugador.getJugador1().getCantidad105());
                interfaz.textcantidad.setText(int_string);
            }
            else{
                String int_string = Integer.toString(listJugador.getJugador2().getCantidad105());
                interfaz.textcantidad.setText(int_string);
            }
            interfaz.textcantidad.setStyle("-fx-text-fill: red;");
            interfaz.balas.setStyle("-fx-font-size: 16px; -fx-font-family: 'Monospaced';-fx-font-weight: bold;-fx-text-fill: #FF0000;");
            tipo=3;//ajusta el tipo
            popup.hide(); 
            interfaz.balas.setDisable(false);
            interfaz.disparar.setDisable(false);
        });
        ChangeListener<Number> stagePositionListenerX = (obs, oldValue, newValue) -> {
            double x = stage.getX() + 500+posicionx;
            double y = stage.getY() + 500+posiciony;
            popup.setX(x);
            popup.setY(y);
        };

        ChangeListener<Number> stagePositionListenerY = (obs, oldValue, newValue) -> {
            double x = stage.getX() + 500+posicionx;
            double y = stage.getY() + 500+posiciony;
            popup.setX(x);
            popup.setY(y);
        };
        stage.xProperty().addListener(stagePositionListenerX);
        stage.yProperty().addListener(stagePositionListenerY);
            
        tipos.getChildren().addAll(bala1,bala2,bala3);   
        popup.getContent().add(tipos);           
        popup.show(stage);
    }
    
    public void reiniciar_partida(){
        terrain.setContador(0);
            int nuevoTerreno = random.nextInt(3);         
            while (nuevoTerreno == terreno_random) {
                nuevoTerreno = random.nextInt(3);
            }            
            terreno_random = nuevoTerreno;
            iniciar_terreno();
            turno=1;
            interfaz.boxtanque2.setVisible(false);
            interfaz.boxtanque1.setVisible(true);
            interfaz.boxvida2.setVisible(false);
            interfaz.boxvida1.setVisible(true);
            vidatanque1=100;
            vidatanque2=100;
            interfaz.textovida1.setText(vidatanque1+"");
            interfaz.textovida2.setText(vidatanque2+"");
    }
    public static int getRandom(){
        return terreno_random;
    }
}
