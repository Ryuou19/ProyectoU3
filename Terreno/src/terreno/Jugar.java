package terreno;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;
import javafx.stage.Popup;

public class Jugar extends Application {  
    Bala balaAux = null;
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
    public Label textodistancia;//distancia maxima mostrada en la interfaz a traves de la variable distancia
    public Label textoaltura;//altura maxima mostrada en la interfaz a traves de la variable altura
    public StackPane root = new StackPane();
    public Scene scene = new Scene(root, 1200, 700);
    public Canvas canvas = new Canvas(1200, 900);
    public GraphicsContext gc = canvas.getGraphicsContext2D();

    //ANGULO
    HBox boxangulo = new HBox();
    Text text1 = new Text("Angulo");
    TextField entradaangulo = new TextField();

    //VELOCIDAD
    HBox boxvelocidad = new HBox();
    TextField entradavelocidad = new TextField();

    //JUGADOR
    HBox boxjugador = new HBox();
    Text textjugador=new Text("Turno Actual");

    //TANQUE 1
    HBox boxtanque1 = new HBox();
    Image tanque1 = new Image(getClass().getResourceAsStream("tanque1.png"));
    ImageView imagentanque1 = new ImageView(tanque1);

    //TANQUE 2
    HBox boxtanque2 = new HBox();
    Image tanque2 = new Image(getClass().getResourceAsStream("tanque2.png"));
    ImageView imagentanque2 = new ImageView(tanque2);

    //DISPARO
    HBox boxdisparo= new HBox();
    Button disparar = new Button("!DISPARAR!");

    //DISTANCIA
    HBox boxdistancia=new HBox();
    Text textdistancia= new Text("Distancia = ");

    //ALTURA
    HBox boxaltura=new HBox();
    Text textaltura= new Text("Altura = ");
    
    //MARCO
    Rectangle marco = new Rectangle(300, 560+25, 520, 100);
    
    //VIDA
    HBox boxvida1= new HBox();   
    HBox boxvida2= new HBox();
    Text textvida1=new Text("Vida = ");
    Text textvida2=new Text("Vida = ");
    Label textovida1 = new Label(vidatanque1+"");
    Label textovida2 = new Label(vidatanque2+"");
    
    //BOTON REINICIAR
    HBox boxreiniciar= new HBox();
    Button reiniciar = new Button("NUEVA PARTIDA");
    
    //BOTON FINALIZAR
    HBox boxfinalizar= new HBox();
    Button finalizar = new Button("FINALIZAR JUEGO");
    
    //BOTON SELECCIONAR BALA
    HBox boxbalas=new HBox();
    Button balas = new Button("BALAS");
    
    //CANTIDAD BALAS
    HBox boxcantidadbalas=new HBox();
    Label textcantidad= new Label("");
    
    int alto = 400;
    int ancho=300;
    int pixel = 3;

    Jugador jugador1 = new Jugador(gc, "tanque1.png", 1);
    Jugador jugador2 = new Jugador(gc, "tanque2.png", 2);
    
    //singleton-------------------------------------------------------------
    ListaJugadores listJugador = ListaJugadores.getInstance();
   
    //n-------------------------------------------------------------    
    

    private static int terreno_random;//variable que guarda la seleccion random del terreno

    static {
        terreno_random = random.nextInt(3);
    }
    
    //cambion-------------------------------------------------------------
    Terreno terrain = new Terreno(alto,ancho, pixel,gc);
    //n-------------------------------------------------------
    
    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        iniciar_interfaz(primaryStage);
        iniciar_terreno(primaryStage);
                    
        finalizar.setOnAction(event -> {//se apreta finalizar y se termina la ejecucion           
            Platform.exit();
        });
        reiniciar.setOnAction(event -> {//se realiza todo el proceso para reiniciar la partida
//cambio---------------------------------------------------------- 
            terrain.setContador(0);
//----------------------------------------------------------------
            int nuevoTerreno = random.nextInt(3);         
            while (nuevoTerreno == terreno_random) {
                nuevoTerreno = random.nextInt(3);
            }            
            terreno_random = nuevoTerreno;
            iniciar_terreno(primaryStage);
            turno=1;
            boxtanque2.setVisible(false);
            boxtanque1.setVisible(true);
            boxvida2.setVisible(false);
            boxvida1.setVisible(true);
            listJugador.getJugador1().setCantidad105(3);
            listJugador.getJugador1().setCantidad80(10);
            listJugador.getJugador1().setCantidad60(3);
            listJugador.getJugador2().setCantidad105(3);
            listJugador.getJugador2().setCantidad80(10);
            listJugador.getJugador2().setCantidad60(3);
            vidatanque1=100;
            vidatanque2=100;
            textovida1.setText(vidatanque1+"");
            textovida2.setText(vidatanque2+"");
        });
        
        tipo=0;//reiniciamos el tipo para que no permita disparar la bala anterior sin antes escogerla
        balas.setOnAction(e -> {            
            balas.setDisable(true);
            disparar.setDisable(true);//no podemos disparar mientras escogemos la bala
            boxcantidadbalas.setVisible(true);
            
            //VENTANA
            Popup ventana = new Popup();//popup que muestra las balas a elegir
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
                    textcantidad.setText(int_string);//muestra la cantidad de balas disponibles
                    
                }
                else{
                    String int_string = Integer.toString(listJugador.getJugador2().getCantidad60());
                    textcantidad.setText(int_string);//muestra la cantidad de balas disponibles
                }
                textcantidad.setStyle("-fx-text-fill: green;");
                balas.setStyle("-fx-font-size: 16px; -fx-font-family: 'Monospaced';-fx-font-weight: bold;-fx-text-fill: #008000;");
                tipo=1;//ajusta el tipo
                ventana.hide();
                balas.setDisable(false);
                disparar.setDisable(false);
            });
            
            bala2.setOnAction(event -> {//escoge bala 2
                if(turno==1){
                    String int_string = Integer.toString(listJugador.getJugador1().getCantidad80());
                    textcantidad.setText(int_string);//lo mismo de bala1
                    
                }
                else{
                    String int_string = Integer.toString(listJugador.getJugador2().getCantidad80());
                    textcantidad.setText(int_string);//lo mismo de bala1
                }
                textcantidad.setStyle("-fx-text-fill: blue;");
                balas.setStyle("-fx-font-size: 16px; -fx-font-family: 'Monospaced';-fx-font-weight: bold;-fx-text-fill: #0000FF;");
                tipo=2;//ajusta el tipo
                ventana.hide();
                balas.setDisable(false);
                disparar.setDisable(false);
            });
            
            bala3.setOnAction(event -> {//escoge bala 3
                if(turno==1){
                    String int_string = Integer.toString(listJugador.getJugador1().getCantidad105());
                    textcantidad.setText(int_string);
                }
                else{
                    String int_string = Integer.toString(listJugador.getJugador2().getCantidad105());
                    textcantidad.setText(int_string);
                }
                textcantidad.setStyle("-fx-text-fill: red;");
                balas.setStyle("-fx-font-size: 16px; -fx-font-family: 'Monospaced';-fx-font-weight: bold;-fx-text-fill: #FF0000;");
                tipo=3;//ajusta el tipo
                ventana.hide(); 
                balas.setDisable(false);
                disparar.setDisable(false);
            });
            ventana.setX(705);
            ventana.setY(690);
            
            tipos.getChildren().addAll(bala1,bala2,bala3);   
            ventana.getContent().add(tipos);           
            ventana.show(primaryStage);
        
        });
               
        disparar.setOnAction(event ->{         
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
                String anguloTexto = entradaangulo.getText();
                String velocidadTexto = entradavelocidad.getText();       
                if(anguloTexto.isEmpty() || velocidadTexto.isEmpty() || anguloTexto.equals("0") || velocidadTexto.equals("0")){//verifica las entradas             
                    disparar.setDisable(false);
                    return;
                }
                angulo = -Double.parseDouble(anguloTexto);
                velocidad = Double.parseDouble(velocidadTexto);
                if (turno == 1) {                                       
                    Bala nuevaBala = crear_bala();//creamos nueva bala con el tipo seleciconado
                    //comprobamos si la bala esta vacia
                    if(nuevaBala==null){
                        System.out.println("no quedan mas balas ");
                    }
                    else{
                        new AnimationTimer() {
                            //double tiempoAnterior = System.nanoTime() / 1e9*5;//valor que ajusta la velocidad de ejecucion del trayecto de la bala
                            @Override
                            public void handle(long now){
                                nuevaBala.dibujo(gc);
                                //double tiempoActual = System.nanoTime() / 1e9*5;
                                double deltaTiempo = 0.1;
                                nuevaBala.actualizarPosicion(deltaTiempo, nuevaBala, distancia, altura,boxdistancia,boxaltura,listJugador.getJugador1().getTanque().getCañonY(),listJugador.getJugador1().getTanque().getCañonX());
                                //tiempoAnterior = tiempoActual;
                                victoria=terrain.colision_terreno(gc, nuevaBala,terrain.dunas, terrain.matriz,tipo);
                                if(victoria==1){
                                    impacto_jugador1(balaAux.getDanio());
                                }
                                else if(victoria==2){
                                    impacto_jugador2(balaAux.getDanio());
                                }
                                if (nuevaBala.eliminar()) {
                                    colision_bala();//revisa la colision y calcula la explosion generada por la bala, para tambien calcular el daño de dicha explosion(si es que existe)  
                                    turno=2;//cambia turno
                                    stop();
                                    animacionCaida();
                                }
                            }
                        }.start();
                        boxcantidadbalas.setVisible(false);
                    }
                }               
                else if (turno == 2) {                            
                    Bala nuevaBala = crear_bala();
                    //comprobamos si la bala esta vacia
                    if(nuevaBala==null){
                        System.out.println("no quedan mas balas ");
                    }
                    else
                    {
                        new AnimationTimer() {
                            //double relacion=1e9*5;//valor que ajusta la velocidad de ejecucion del trayecto de la bala, para acercarlo a una parabola(con viento)
                            //double tiempoAnterior = System.nanoTime() / relacion;
                            @Override
                            public void handle(long now){
                                nuevaBala.dibujo(gc);
                                //double tiempoActual = System.nanoTime() / relacion;
                                double deltaTiempo = 0.1;
                                nuevaBala.actualizarPosicion(deltaTiempo, nuevaBala, distancia, altura,boxdistancia,boxaltura,listJugador.getJugador2().getTanque().getCañonY(),listJugador.getJugador2().getTanque().getCañonX());//posicionar la bala en un tiempo determinado
                                //tiempoAnterior = tiempoActual;
                                victoria=terrain.colision_terreno(gc, nuevaBala,terrain.dunas, terrain.matriz,tipo);//revisa donde colisiono la bala
                                if(victoria==1){
                                    impacto_jugador1(balaAux.getDanio());//impacto dependiendo del daño de la bala
                                }
                                else if(victoria==2){
                                    impacto_jugador2(balaAux.getDanio());//impacto dependiendo del daño de la bala
                                }
                                if (nuevaBala.eliminar()) {
                                    colision_bala();//revisa la colision y calcula la explosion generada por la bala, para tambien calcular el daño de dicha explosion(si es que existe)                                                                      
                                    turno=1;//cambia turno
                                    stop();
                                    animacionCaida();
                                    
                                }
                            }
                        }.start();
                    }
                }
                entradaangulo.setText("");
                entradavelocidad.setText("");
            }
        );
    }

    public static int getRandom(){
        return terreno_random;
    }
    
    public void iniciar_interfaz(Stage primaryStage){//inicia todo lo visual e interactivo de la interfaz de juego    
        primaryStage.setScene(scene);
        Pane canvasPane = new Pane();
        canvasPane.setPrefSize(1200, 900);
        root.getChildren().add(canvasPane);
        canvasPane.getChildren().add(canvas);
        int mover=25;
        
        //ANGULO
        boxangulo.setSpacing(10);
        text1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        entradaangulo.setPrefWidth(40);
        boxangulo.getChildren().addAll(text1, entradaangulo);
        boxangulo.setLayoutX(350); 
        boxangulo.setLayoutY(585+mover);
               
        //VELOCIDAD
        boxvelocidad.setSpacing(10);
        Text text2 = new Text("Velocidad");
        text2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        entradavelocidad.setPrefWidth(40);
        boxvelocidad.getChildren().addAll(text2, entradavelocidad);
        boxvelocidad.setLayoutX(325); 
        boxvelocidad.setLayoutY(615+mover);
                    
        //JUGADOR
        boxjugador.getChildren().add(textjugador);
        textjugador.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        boxjugador.setLayoutX(498); 
        boxjugador.setLayoutY(570+mover);

        //TANQUE 1
        imagentanque1.setFitWidth(120);
        imagentanque1.setFitHeight(120);
        boxtanque1.getChildren().add(imagentanque1);
        boxtanque1.setLayoutX(500); 
        boxtanque1.setLayoutY(550+mover);
               
        //TANQUE 2
        imagentanque2.setFitWidth(120);
        imagentanque2.setFitHeight(120);
        boxtanque2.getChildren().add(imagentanque2);
        boxtanque2.setLayoutX(500);
        boxtanque2.setLayoutY(550+mover);
       
        //DISPARO
        disparar.setStyle("-fx-font-size: 16px; -fx-font-family: 'Monospaced'; ");
        boxdisparo.getChildren().add(disparar);
        boxdisparo.setLayoutX(650); 
        boxdisparo.setLayoutY(590+mover);
                       
        //DISTANCIA   
        boxdistancia.setSpacing(6);
        textodistancia = new Label(distancia + " Metros");
        textdistancia.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        textdistancia.setFill(Color.BLACK);
        textodistancia.setTextFill(Color.RED);
        textodistancia.setFont(Font.font("Arial", 16));
        textdistancia.setTranslateY(-1); 
        boxdistancia.getChildren().addAll(textdistancia,textodistancia);
        boxdistancia.setLayoutY(5);
              
        //ALTURA
        boxaltura.setSpacing(6);
        textoaltura = new Label(altura + " Metros");      
        textaltura.setFont(Font.font("Arial", FontWeight.BOLD, 20));       
        textaltura.setFill(Color.BLACK);       
        textoaltura.setTextFill(Color.RED);
        textoaltura.setFont(Font.font("Arial", 16));                
        textaltura.setTranslateY(-1);   
        boxaltura.getChildren().addAll(textaltura,textoaltura);
        boxaltura.setLayoutX(31);
        boxaltura.setLayoutY(30);
               
        //MARCO
        marco.setFill(null); // Relleno transparente
        marco.setStroke(Color.SKYBLUE); // Color de la línea del marco
        canvasPane.getChildren().add(marco);

        //VIDA
        textvida1.setFill(Color.GREEN); 
        textvida1.setFont(Font.font("Monospaced", FontWeight.BOLD, 20)); 
        textvida2.setFill(Color.GREEN); 
        textvida2.setFont(Font.font("Monospaced", FontWeight.BOLD, 20)); 
        textovida1.setTextFill(Color.BLACK);
        textovida2.setTextFill(Color.RED);
        textovida1.setFont(Font.font("Arial",FontWeight.BOLD, 20));
        textovida2.setFont(Font.font("Arial",FontWeight.BOLD, 20));  
        textovida1.setTranslateX(-5);
        textovida2.setTranslateX(-5);
        boxvida1.getChildren().addAll(textvida1,textovida1);
        boxvida2.getChildren().addAll(textvida2,textovida2);
        boxvida1.setLayoutX(652);
        boxvida1.setLayoutY(565+mover);
        boxvida2.setLayoutX(652);
        boxvida2.setLayoutY(565+mover);
        
        //BOTON REINICIAR
        Font font = Font.font("Serif", FontWeight.NORMAL, 20);
        reiniciar.setFont(font);
        reiniciar.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #FF0000;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );
        boxreiniciar.getChildren().add(reiniciar);
        boxreiniciar.setLayoutX(995); 
        boxreiniciar.setLayoutY(648);
        
        //BOTON FINALIZAR
        finalizar.setFont(font);
        finalizar.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #FF0000;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );
        boxfinalizar.getChildren().add(finalizar);
        boxfinalizar.setLayoutX(10); 
        boxfinalizar.setLayoutY(648);
        
        //BOTON BALAS
        balas.setStyle("-fx-font-size: 16px; -fx-font-family: 'Monospaced';");
        boxbalas.getChildren().add(balas);
        boxbalas.setLayoutX(675); 
        boxbalas.setLayoutY(648);
        
        //CANTIDAD BALAS
        textcantidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        boxcantidadbalas.getChildren().add(textcantidad);
        boxcantidadbalas.setLayoutX(753);
        boxcantidadbalas.setLayoutY(652);
        
      
        //SE AGREGA TODO AL CANVASPANE
        canvasPane.getChildren().addAll(boxangulo,boxvelocidad,
                boxjugador,boxtanque1,boxtanque2, boxdisparo, 
                boxdistancia, boxaltura, boxvida1,boxvida2, 
                boxreiniciar, boxfinalizar, boxbalas, boxcantidadbalas);
        
        boxtanque2.setVisible(false);
        boxtanque1.setVisible(true);
        boxvida2.setVisible(false);
        boxvida1.setVisible(true);
    }
    
    public void iniciar_terreno(Stage primaryStage){//inicializa la matriz del terreno y la dibuja dependiendo de la eleccion random
        listJugador.setJugador1(jugador1);
        listJugador.setJugador2(jugador2);
        terrain.iniciar();
        
        validar=0;
        if(terreno_random == 0) {
            terrain.terreno_nieve(gc, 0.0, 100,validar, terrain);
            animacionCaida();
        }
        if(terreno_random == 1) {
            terrain.terreno_desierto(gc, 0.0, 100,validar,terrain);
            animacionCaida();
        }
        if(terreno_random == 2) {
            terrain.terreno_aram(gc, 0.0, 100,validar,terrain);
            animacionCaida();
        }
        primaryStage.show();
        System.out.println("posicion del tanque1 x e y -> x"+listJugador.getJugador1().getTanque().getCañonX()+"y"+listJugador.getJugador1().getTanque().getCañonY());
        System.out.println("posicion del cañon x e y -> x"+listJugador.getJugador1().getTanque().getPosicionX()+"y"+listJugador.getJugador1().getTanque().getPosicionY());
        System.out.println("posicion del tanque2 x e y -> x"+listJugador.getJugador2().getTanque().getCañonX()+"y"+listJugador.getJugador2().getTanque().getCañonY());
        System.out.println("posicion del cañon x e y -> x"+listJugador.getJugador2().getTanque().getPosicionX()+"y"+listJugador.getJugador2().getTanque().getPosicionY());
        validar=1;
    }
    
    public void ingresar_disparo(){
        disparar.setDisable(true);
        Label distanciaLabel = (Label) boxdistancia.getChildren().get(1);
        distanciaLabel.setText(" ");                               
        Label alturaLabel = (Label) boxaltura.getChildren().get(1);
        alturaLabel.setText(" ");           
    }
    public void impacto_jugador1(int danio){
        int nuevavida1 = listJugador.getJugador1().getTanque().ajustar_vida(vidatanque1, danio);
        vidatanque1 = nuevavida1;
        textovida1.setText(nuevavida1+"");
        boxtanque1.setVisible(false);
        boxtanque2.setVisible(true);
        boxvida1.setVisible(false);
        boxvida2.setVisible(true);
        boxcantidadbalas.setVisible(false);
        if(vidatanque1<=0){
            System.out.println("HA GANADO EL JUGADOR 2!!");
            Platform.exit(); 
        }
    }
    
    public void impacto_jugador2(int danio){
        int nuevavida2 = listJugador.getJugador2().getTanque().ajustar_vida(vidatanque2, danio);
        vidatanque2 = nuevavida2;
        textovida2.setText(nuevavida2+"");
        boxtanque2.setVisible(false);
        boxtanque1.setVisible(true);
        boxvida2.setVisible(false);
        boxvida1.setVisible(true);
        boxcantidadbalas.setVisible(false);
        if(vidatanque2<=0){
            System.out.println("HA GANADO EL JUGADOR 1!!");
            Platform.exit(); 
        }
    }
    
    public void colision_bala(){
        calcular_explosion();
        if (turno==1){
            if(terreno_random == 0) {
                terrain.terreno_nieve(gc, 0.0, vidatanque1,validar,terrain);
            }
            if(terreno_random == 1) {
                terrain.terreno_desierto(gc, 0.0, vidatanque1,validar,terrain);
            }
            if(terreno_random == 2) {
                terrain.terreno_aram(gc, 0.0, vidatanque1,validar,terrain);
            }
            disparar.setDisable(false);
            boxtanque1.setVisible(false);
            boxtanque2.setVisible(true);
            boxvida1.setVisible(false);
            boxvida2.setVisible(true);
            balas.setStyle("-fx-font-size: 16px; -fx-font-family: 'Monospaced';");//reinicia el estilo del boton de balas
            tipo=0;
            }
        if(turno==2){
            if(terreno_random == 0) {
                terrain.terreno_nieve(gc, 0.0, vidatanque2,validar,terrain);
            }
            if(terreno_random == 1) {
                terrain.terreno_desierto(gc, 0.0, vidatanque2,validar,terrain);
            }
            if(terreno_random == 2) {
                terrain.terreno_aram(gc, 0.0, vidatanque2,validar,terrain);
            }
            disparar.setDisable(false);
            boxtanque2.setVisible(false);
            boxtanque1.setVisible(true);
            boxvida2.setVisible(false);
            boxvida1.setVisible(true);
            balas.setStyle("-fx-font-size: 16px; -fx-font-family: 'Monospaced';");
            boxcantidadbalas.setVisible(false);
            tipo=0;
        }
    }    
    public Bala crear_bala(){
        Bala nuevaBala = null;
        if(turno==1){
            cañonX = listJugador.getJugador1().getTanque().getCañonX();
            cañonY = listJugador.getJugador1().getTanque().getCañonY();
            if(tipo==1)
            {
                nuevaBala = new Bala((int) cañonX, (int) cañonY, pixel , angulo, velocidad,0,30);
                balaAux = nuevaBala;
                listJugador.getJugador1().setCantidad60(listJugador.getJugador1().getCantidad60()-1);

            }
            if(tipo==2)
            {
                nuevaBala = new Bala((int) cañonX, (int) cañonY, pixel , angulo, velocidad,0,40);
                balaAux = nuevaBala;
                listJugador.getJugador1().setCantidad80(listJugador.getJugador1().getCantidad80()-1);

            }
            if(tipo==3){
            {
                nuevaBala = new Bala((int) cañonX, (int) cañonY, pixel , angulo, velocidad,0,50);
                balaAux = nuevaBala;
                listJugador.getJugador1().setCantidad105(listJugador.getJugador1().getCantidad105()-1);
            }
        }
        }
        if(turno==2){
            cañonX = listJugador.getJugador2().getTanque().getCañonX();
            cañonY = listJugador.getJugador2().getTanque().getCañonY();
            angulo = 180 - angulo;
            if(tipo==1)
            {
                nuevaBala = new Bala((int) cañonX, (int) cañonY, pixel , angulo, velocidad,0,30);
                balaAux = nuevaBala;
                    listJugador.getJugador2().setCantidad60(listJugador.getJugador2().getCantidad60()-1);

            }
            if(tipo==2)
            {
                nuevaBala = new Bala((int) cañonX, (int) cañonY, pixel , angulo, velocidad,0,40);
                balaAux = nuevaBala;
                listJugador.getJugador2().setCantidad80(listJugador.getJugador2().getCantidad80()-1);

            }
            if(tipo==3)
            {
                nuevaBala = new Bala((int) cañonX, (int) cañonY, pixel , angulo, velocidad,0,50);
                balaAux = nuevaBala;
                listJugador.getJugador2().setCantidad105(listJugador.getJugador2().getCantidad105()-1);

            }
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
    
    
    ///Esta parte de codigo fue la que intentamos usar para la caida, pero no nos dio el tiempo suficiente:(
    
    /*public void revisar_caida(Jugador jugador1,Jugador jugador2){
        contador=0;
        int tanque=0;
        for(int i=0;i<terrain.matriz.length/2;i++){
            for (int j=0;j<terrain.matriz[i].length;j++){
                if(terrain.matriz[i][j]==4 && terrain.dunas[i][j+1]==-1 || 
                    terrain.matriz[i][j]==4 && terrain.dunas[i][j+1]==0){
                    gc.setFill(Color.BLACK  );
                    gc.fillOval(i * 3,j * 3, 3, 3);     
                    contador++;
                }
                
                if(i==200){
                    contador=0;
                    tanque=1;
                }
            }
        }
        if(contador==40){
            gc.setFill(Color.BLACK  );
            gc.fillOval(300,300, 30, 30);
            if(tanque==0){
                jugador1.getTanque().setBajada(30);
                System.out.println("bajada= "+jugador1.getTanque().getBajada());

            }
            if(tanque==1){
                jugador2.getTanque().setBajada(30);
                System.out.println("bajada= "+jugador2.getTanque().getBajada());
            }

        }
        System.out.println("Contador actual= "+contador);                     
    }*/
    public void animacionCaida()
    {
            listJugador.getJugador1().getTanque().caidaTanque(gc,terrain,terreno_random);
            listJugador.getJugador2().getTanque().caidaTanque(gc, terrain, terreno_random);
    }
}
