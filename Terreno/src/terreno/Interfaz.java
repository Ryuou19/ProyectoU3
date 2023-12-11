package terreno;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Interfaz {
    
    int alto;//largo de la interfaz
    int ancho;//ancho de la interfaz

    public Interfaz(int alto, int ancho) {
        this.alto = alto;
        this.ancho = ancho;
    }
  
    public Label textodistancia;//distancia maxima mostrada en la interfaz a traves de la variable distancia
    public Label textoaltura;//altura maxima mostrada en la interfaz a traves de la variable altura
    
    //IMAGEN HUD
    Image imagenHud= new Image(getClass().getResourceAsStream("./img/hud.jpg"));
    ImageView hud= new ImageView(imagenHud);
    
    //ANGULO
    HBox boxangulo = new HBox(10);
    TextField entradaangulo = new TextField();
    
    //VELOCIDAD
    HBox boxvelocidad = new HBox(10);
    TextField entradavelocidad = new TextField();

    //JUGADOR
    Text textjugador=new Text("Turno Actual");

    //TANQUE 1
    Image tanque1 = new Image(getClass().getResourceAsStream("./img/tanque1.png"));
    ImageView imagentanque1 = new ImageView(tanque1);
    ImageView iconoTanque1 = new ImageView(tanque1);

    //TANQUE 2
    Image tanque2 = new Image(getClass().getResourceAsStream("./img/tanque2.png"));
    ImageView imagentanque2 = new ImageView(tanque2);
    ImageView iconoTanque2 = new ImageView(tanque2);
    
    //TANQUE 3
    Image tanque3 = new Image(getClass().getResourceAsStream("./img/tanque3.png"));
    ImageView imagentanque3 = new ImageView(tanque3);
    ImageView iconoTanque3 = new ImageView(tanque3);
        
    //TANQUE 4
    Image tanque4 = new Image(getClass().getResourceAsStream("./img/tanque4.png"));
    ImageView imagentanque4 = new ImageView(tanque4);
    ImageView iconoTanque4 = new ImageView(tanque4);
    
    //TANQUE 5
    Image tanque5 = new Image(getClass().getResourceAsStream("./img/tanque5.png"));
    ImageView imagentanque5 = new ImageView(tanque5);
    ImageView iconoTanque5 = new ImageView(tanque5);
        
    //TANQUE 6
    Image tanque6 = new Image(getClass().getResourceAsStream("./img/tanque6.png"));
    ImageView imagentanque6 = new ImageView(tanque6);
    ImageView iconoTanque6 = new ImageView(tanque1);
       
    //LISTA TANQUES
    ImageView[] imagenes={imagentanque1,imagentanque2,imagentanque3,imagentanque4,imagentanque5,imagentanque6};
    
    //DISPARO
    Button disparar = new Button();

    //DISTANCIA
    VBox boxdistancia=new VBox(7);
    

    //ALTURA
    VBox boxaltura=new VBox(7);
    
    //VIDA    
    ProgressBar barraDeVida = new ProgressBar();
    
    //BOTON REINICIAR
    Button reiniciar = new Button();
    Image image1 = new Image(getClass().getResourceAsStream("./img/botonReiniciar.png"));
    ImageView imagenBotonReiniciar = new ImageView(image1);
    //BOTON FINALIZAR
    Button finalizar = new Button();
    Image image = new Image(getClass().getResourceAsStream("./img/botonFinalizar.png"));
    ImageView imagenBotonFinalizar = new ImageView(image);
      
    //BOTONES BALAS
    HBox tipos = new HBox();
    Button bala1 = new Button("60m");  
    Button bala2 = new Button("80m");  
    Button bala3 = new Button("105m");
    
    //CANTIDAD BALAS
    HBox cantidad = new HBox(20);
    Text textcantidad1= new Text("");
    Text textcantidad2= new Text("");
    Text textcantidad3= new Text("");
    

    //NODOS DE ESTADISTICAS
    VBox estadisticas=new VBox(-10);
    HBox num1= new HBox(10);
    HBox num2= new HBox(10);
    HBox num3= new HBox(10);
    HBox num4= new HBox(10);
    HBox num5= new HBox(10);
    HBox num6= new HBox(10);
    ProgressBar barra1 = new ProgressBar();
    ProgressBar barra2 = new ProgressBar();
    ProgressBar barra3 = new ProgressBar();
    ProgressBar barra4 = new ProgressBar();
    ProgressBar barra5 = new ProgressBar();
    ProgressBar barra6 = new ProgressBar();
    Text cantidadBalas1=new Text();
    Text cantidadBalas2=new Text();
    Text cantidadBalas3=new Text();
    Text cantidadBalas4=new Text();
    Text cantidadBalas5=new Text();
    Text cantidadBalas6=new Text();
    ArrayList<ImageView> listaTanques=new ArrayList<>();
    ArrayList<Text> listaBalas=new ArrayList<>();
    ArrayList<ProgressBar> listaBarras=new ArrayList<>();
    ArrayList<HBox> cajaEstadisticas=new ArrayList<>();
    
    
    //IMAGENES ICONOS
    Image dist = new Image(getClass().getResourceAsStream("./img/distancia.png"));
    ImageView distancia = new ImageView(dist);
    Image alt = new Image(getClass().getResourceAsStream("./img/altura.png"));
    ImageView altura = new ImageView(alt);
    Image ang = new Image(getClass().getResourceAsStream("./img/angulo.png"));
    ImageView angulo = new ImageView(ang);
    Image vel = new Image(getClass().getResourceAsStream("./img/velocidad.png"));
    ImageView velocidad = new ImageView(vel);
    Image disp = new Image(getClass().getResourceAsStream("./img/disparo.jpg"));
    ImageView iconoDisparo = new ImageView(disp);
    
    //ASESINATOS
    Image asesinatos = new Image(getClass().getResourceAsStream("./img/kills.png"));
    ImageView kills = new ImageView(asesinatos);
    HBox asesinato=new HBox(15);
    Text cantidadAsesinatos=new Text("0");
    
    //SUICIDIOS
    Image sui = new Image(getClass().getResourceAsStream("./img/suicidio.png"));
    ImageView suicidios = new ImageView(sui);
    HBox suicidio=new HBox(15);
    Text cantidadSuicidios=new Text("0");
    
    //RONDAS
    Image round = new Image(getClass().getResourceAsStream("./img/ronda.png"));
    ImageView ronda = new ImageView(round);
    HBox rondas=new HBox(15);
    Text cantidadRondas=new Text("0");
    
    //GRAVEDAD
    Image grav = new Image(getClass().getResourceAsStream("./img/gravedad.png"));
    ImageView gravity = new ImageView(grav);
    HBox gravedad=new HBox(15);
    Text cantidadGravedad=new Text("9.81");
    
    //VIENTO
    Image wind = new Image(getClass().getResourceAsStream("./img/viento.png"));
    ImageView viento = new ImageView(wind);
    HBox vientos=new HBox(15);
    Text cantidadViento=new Text("0"+" M/S");
 
    GraphicsContext gc;//DIBUJO
    Pane canvasPane = new Pane();//PANEL
    
    //VARIABLES DEL LISTENER
    private ChangeListener<Number> widthListener;
    private ChangeListener<Number> heightListener;
    
    
    public void iniciar_interfaz(){//inicia todo lo visual e interactivo de la interfaz de juego         
        canvasPane.setPrefSize(alto, ancho);//definir dimensiones del panel     
        
        
        Canvas canvas = new Canvas(alto, ancho);//canvas para dibujo
        GraphicsContext newgc = canvas.getGraphicsContext2D();//dibujo graficos
        gc=newgc;
        
        Globales.escena.setRoot(canvasPane);//se define el panel en la escena
        canvasPane.getChildren().add(canvas);//agrega canvas
        Globales.stage.setScene(Globales.escena);//se cambia la escena a la interfasz
        
        //FONDO HUD
        hud.setPreserveRatio(false);      
        canvasPane.getChildren().add(hud);
        
        //ANGULO
        boxangulo.setSpacing(10);    
        boxangulo.getChildren().addAll(angulo, entradaangulo);
                     
        //VELOCIDAD
        boxvelocidad.setSpacing(10);
        boxvelocidad.getChildren().addAll(velocidad, entradavelocidad);
                                
        //TANQUES
        for (ImageView imagen : imagenes) {              
            canvasPane.getChildren().add(imagen); //agrega cada imagen al pane
        }
                
        //DISPARO
        iconoDisparo.setPreserveRatio(false);
        disparar.setGraphic(iconoDisparo);
        disparar.setStyle(//estilo
            "-fx-background-color: transparent; " +
            "-fx-border-color: transparent; " +
            "-fx-padding: 0;" // Ajustar el relleno del botón a 0 para eliminar cualquier espacio adicional
        );
                             
        //DISTANCIA   
        textodistancia = new Label(0 + " Metros");
        textodistancia.setTextFill(Color.RED);      
        boxdistancia.getChildren().addAll(distancia,textodistancia);
    
        //ALTURA
        textoaltura = new Label(0 + " Metros");                           
        textoaltura.setTextFill(Color.RED); 
        boxaltura.getChildren().addAll(altura,textoaltura);
                    
        //VIDA       
        barraDeVida.setStyle("-fx-control-inner-background: black; " +
                             "-fx-accent: #00FF00; " +
                             "-fx-background-color: black, black; " +
                             "-fx-background-insets: 0, 2; " +
                             "-fx-background-radius: 0.5em;");
               
        //ESTADISTICAS
        cajaEstadisticas.add(num1);
        cajaEstadisticas.add(num2);
        cajaEstadisticas.add(num3);
        cajaEstadisticas.add(num4);
        cajaEstadisticas.add(num5);
        cajaEstadisticas.add(num6);
        listaTanques.add(iconoTanque1);
        listaTanques.add(iconoTanque2);
        listaTanques.add(iconoTanque3);
        listaTanques.add(iconoTanque4);
        listaTanques.add(iconoTanque5);
        listaTanques.add(iconoTanque6);
        listaBarras.add(barra1);
        listaBarras.add(barra2);
        listaBarras.add(barra3);
        listaBarras.add(barra4);
        listaBarras.add(barra5);
        listaBarras.add(barra6);
        listaBalas.add(cantidadBalas1);
        listaBalas.add(cantidadBalas2);
        listaBalas.add(cantidadBalas3);
        listaBalas.add(cantidadBalas4);
        listaBalas.add(cantidadBalas5);
        listaBalas.add(cantidadBalas6);
        
        //agregar nodos a la caja de estadisticas
        for (int i=0;i<Globales.jugadores_def;i++){  
            cajaEstadisticas.get(i).getChildren().add(listaTanques.get(i));
            cajaEstadisticas.get(i).getChildren().add(listaBarras.get(i));    
            cajaEstadisticas.get(i).getChildren().add(listaBalas.get(i)); 
            cajaEstadisticas.get(i).getChildren().get(1).setStyle("-fx-control-inner-background: black; " +
                             "-fx-accent: #00FF00; " +
                             "-fx-background-color: black, black; " +
                             "-fx-background-insets: 0, 2; " +
                             "-fx-background-radius: 0.5em;");
        }
        
        estadisticas.getChildren().addAll(num1,num2,num3,num4,num5,num6);
        
        //ASESINATOS
        asesinato.getChildren().addAll(kills,cantidadAsesinatos);
        
        //SUICIDIO
        suicidio.getChildren().addAll(suicidios,cantidadSuicidios);
        
        //RONDAS
        rondas.getChildren().addAll(ronda,cantidadRondas);
        
        //GRAVEDAD
        gravedad.getChildren().addAll(gravity,cantidadGravedad);
        
        //VIENTO
        vientos.getChildren().addAll(viento,cantidadViento);
        viento.setRotate(180);
        
        //BOTON REINICIAR       
        imagenBotonReiniciar.setPreserveRatio(false);      
        reiniciar.setGraphic(imagenBotonReiniciar); 
        reiniciar.setStyle(
            "-fx-background-color: transparent; " +
            "-fx-border-color: transparent;"
        );
              
        //BOTON FINALIZAR       
        imagenBotonFinalizar.setPreserveRatio(false);     
        finalizar.setGraphic(imagenBotonFinalizar); 
        finalizar.setStyle(
            "-fx-background-color: transparent; " +
            "-fx-border-color: transparent;"
        );
         
        //BOTON BALAS
        tipos.setStyle("-fx-background-color: #C0C0C0;");
        bala1.setStyle("-fx-background-color: " + "Green" + "; -fx-min-width: 25px; -fx-min-height: 30px; -fx-text-fill: white;");
        bala2.setStyle("-fx-background-color: " + "Blue" + "; -fx-min-width: 25px; -fx-min-height: 30px; -fx-text-fill: white;");
        bala3.setStyle("-fx-background-color: " + "Red" + "; -fx-min-width: 25px; -fx-min-height: 30px; -fx-text-fill: white;");        
        tipos.getChildren().addAll(bala1,bala2,bala3);
        
        //CANTIDAD BALAS       
        textcantidad1.setFill(Color.GREEN);       
        textcantidad2.setFill(Color.BLUE);     
        textcantidad3.setFill(Color.RED);
        cantidad.getChildren().addAll(textcantidad1,textcantidad2,textcantidad3);
        
        //Metodo listener para ajustar los nodos de la interfaz (ignorar, es puro "fxml")
        widthListener= (obs, oldWidth, newWidth) -> {
            double widthRatio = newWidth.doubleValue() / 800;
            hud.setFitWidth(800*widthRatio);       
            hud.setLayoutX(0);
            
            angulo.setFitWidth(30*widthRatio);
            entradaangulo.setPrefWidth(35*widthRatio);
            boxangulo.setLayoutX(140*widthRatio); 
            
            velocidad.setFitWidth(30*widthRatio);
            entradavelocidad.setPrefWidth(35*widthRatio);
            boxvelocidad.setLayoutX(225*widthRatio); 
        
            textjugador.setFont(Font.font("Arial", FontWeight.BOLD, 20*widthRatio));
            textjugador.setLayoutX(330*widthRatio); 
            
            for (ImageView imagen : imagenes) {
                imagen.setFitWidth(120*widthRatio);               
                imagen.setLayoutX(335*widthRatio);               
            }
            for (ImageView imagen : listaTanques) {
                imagen.setFitWidth(50*widthRatio);                             
            }
            for (ProgressBar barra : listaBarras) {
                barra.setPrefWidth(100*widthRatio);                             
            }
            
            for (Text balas : listaBalas) {
                balas.setFont(Font.font("Arial", 16*widthRatio));
            }
            estadisticas.setLayoutX(15*widthRatio);
                   
            iconoDisparo.setFitWidth(70*widthRatio);
            disparar.setLayoutX(545*widthRatio); 
            
            textodistancia.setFont(Font.font("Arial", 16*widthRatio));
            textodistancia.setTranslateX(-17*widthRatio);
            distancia.setFitWidth(30*widthRatio);
            boxdistancia.setLayoutX(170*widthRatio);
                      
            textoaltura.setFont(Font.font("Arial", 16*widthRatio));
            textoaltura.setTranslateX(-17*widthRatio);
            altura.setFitWidth(30*widthRatio);
            boxaltura.setLayoutX(260*widthRatio);
            
            barraDeVida.setPrefWidth(120*widthRatio);
            barraDeVida.setLayoutX(335*widthRatio);    
            
            asesinato.setLayoutX(465*widthRatio);
            kills.setFitWidth(30*widthRatio);
            cantidadAsesinatos.setFont(Font.font("Arial", 16*widthRatio));
            
            suicidio.setLayoutX(465*widthRatio);
            suicidios.setFitWidth(30*widthRatio);
            cantidadSuicidios.setFont(Font.font("Arial", 16*widthRatio));
            
            rondas.setLayoutX(520*widthRatio);
            rondas.setSpacing(10*widthRatio);
            ronda.setFitWidth(30*widthRatio);
            cantidadRondas.setFont(Font.font("Arial", 16*widthRatio));
            
            gravedad.setLayoutX(600*widthRatio);
            gravedad.setSpacing(10*widthRatio);
            gravity.setFitWidth(30*widthRatio);
            cantidadGravedad.setFont(Font.font("Arial", 16*widthRatio));
            
            vientos.setLayoutX(700*widthRatio);
            vientos.setSpacing(10*widthRatio);
            viento.setFitWidth(30*widthRatio);
            cantidadViento.setFont(Font.font("Arial", 16*widthRatio));
                    
            imagenBotonReiniciar.setFitWidth(100*widthRatio);
            reiniciar.setLayoutX(10*widthRatio); 
        
            imagenBotonFinalizar.setFitWidth(100*widthRatio);            
            finalizar.setLayoutX(10*widthRatio); 
            
            tipos.setLayoutX(640*widthRatio);
            bala1.setPrefWidth(47*widthRatio);
            bala2.setPrefWidth(47*widthRatio);
            bala3.setPrefWidth(47*widthRatio);
            
            cantidad.setSpacing(36*widthRatio);
            cantidad.setLayoutX(660*widthRatio);
            textcantidad1.setFont(Font.font("Arial", FontWeight.BOLD, 20*widthRatio));
            textcantidad2.setFont(Font.font("Arial", FontWeight.BOLD, 20*widthRatio));
            textcantidad3.setFont(Font.font("Arial", FontWeight.BOLD, 20*widthRatio));          
        };
         
        //Metodo listener para ajustar los nodos de la interfaz (ignorar, es puro "fxml")
        heightListener=(obs, oldHeight, newHeight) -> {
            double heightRatio = newHeight.doubleValue() / 800; 
            hud.setFitHeight(800/2*heightRatio);
            hud.setLayoutY(630*heightRatio);
              
            angulo.setFitHeight(30*heightRatio);
            entradaangulo.setPrefHeight(30*heightRatio);
            boxangulo.setLayoutY(730*heightRatio);
                      
            velocidad.setFitHeight(30*heightRatio);
            entradavelocidad.setPrefHeight(30*heightRatio);
            boxvelocidad.setLayoutY(730*heightRatio);
            
            textjugador.setLayoutY(670*heightRatio);
            
            for (ImageView imagen : imagenes) {
                imagen.setFitHeight(150*heightRatio);
                imagen.setLayoutY(620*heightRatio);
            }
            
            for (ImageView imagen : listaTanques) {
                imagen.setFitHeight(50*heightRatio);   
                imagen.setTranslateY(-23*heightRatio);
            }
            
            for (ProgressBar barra : listaBarras) {
                barra.setPrefHeight(20*heightRatio);                             
            }
            
            estadisticas.setLayoutY(20*heightRatio);
            
            iconoDisparo.setFitHeight(70*heightRatio);          
            disparar.setLayoutY(690*heightRatio);
            
            distancia.setFitHeight(30*heightRatio);          
            boxdistancia.setLayoutY(650*heightRatio);
                       
            altura.setFitHeight(30*heightRatio);   
            boxaltura.setLayoutY(650*heightRatio);
            
            barraDeVida.setLayoutY(760*heightRatio);
            barraDeVida.setPrefHeight(20*heightRatio);
            
            asesinato.setLayoutY(690*heightRatio);
            kills.setFitHeight(30*heightRatio);
            cantidadAsesinatos.setTranslateY(6);
            if(Globales.resolucion_def==2){
                cantidadAsesinatos.setTranslateY(-1);
            }
            else{
                cantidadAsesinatos.setTranslateY(6);
            }
                      
            suicidio.setLayoutY(730*heightRatio);
            suicidios.setFitHeight(30*heightRatio);
            if(Globales.resolucion_def==2){
                cantidadSuicidios.setTranslateY(-1);
            }
            else{
                cantidadSuicidios.setTranslateY(6);
            }
            
            rondas.setLayoutY(640*heightRatio);
            ronda.setFitHeight(30*heightRatio);
            if(Globales.resolucion_def==2){
                cantidadRondas.setTranslateY(-1);
            }
            else{
                cantidadRondas.setTranslateY(6);
            }
            
            gravedad.setLayoutY(640*heightRatio);
            gravity.setFitHeight(30*heightRatio);
            if(Globales.resolucion_def==2){
                cantidadGravedad.setTranslateY(-1);
            }
            else{
                cantidadGravedad.setTranslateY(6);
            }
            
            vientos.setLayoutY(640*heightRatio);
            viento.setFitHeight(30*heightRatio);
            if(Globales.resolucion_def==2){
                cantidadViento.setTranslateY(-1);
            }
            else{
                cantidadViento.setTranslateY(6);
            }
                       
            imagenBotonReiniciar.setFitHeight(50*heightRatio);
            reiniciar.setLayoutY(650*heightRatio);
            
            imagenBotonFinalizar.setFitHeight(50*heightRatio);
            finalizar.setLayoutY(710*heightRatio);
            
            tipos.setLayoutY(700*heightRatio);
            bala1.setPrefHeight(35*heightRatio);
            bala2.setPrefHeight(35*heightRatio);
            bala3.setPrefHeight(35*heightRatio);
            
            cantidad.setLayoutY(740*heightRatio);
            textcantidad1.setFont(Font.font("Arial", FontWeight.BOLD, 20*heightRatio));
            textcantidad2.setFont(Font.font("Arial", FontWeight.BOLD, 20*heightRatio));            
            textcantidad3.setFont(Font.font("Arial", FontWeight.BOLD, 20*heightRatio));           
        };
        
        //Se reinician los valores del listener
        widthListener.changed(null, null, canvasPane.getWidth());
        heightListener.changed(null, null, canvasPane.getHeight()); 
        canvasPane.widthProperty().addListener(widthListener);
        canvasPane.heightProperty().addListener(heightListener);
        
        //SE AGREGA TODO AL CANVASPANE
        canvasPane.getChildren().addAll(boxangulo,boxvelocidad,
            textjugador,disparar, boxdistancia, boxaltura, barraDeVida,
            asesinato,suicidio,rondas,gravedad,vientos,reiniciar,
            finalizar, tipos, cantidad,estadisticas);
               
    }
    
    //Muestra y actualiza los valores del miniHud de vida y balas de los tanques en el juego
    public void estadisticas(ListaJugadores listJugador){
        int i=0;
        int balastotales;//balas totales del jugador en ese instante
        for (Jugador jugador:listJugador.lista){           
            listaBarras.get(i).setProgress(jugador.getVida()/100.0);
            //Cambiar color de barra de vida dependiendo de su cantidad restante
            if(jugador.getVida()>=60){
                listaBarras.get(i).setStyle("-fx-control-inner-background: black; " +
                                 "-fx-accent: #00FF00; " +
                                 "-fx-background-color: black, black; " +
                                 "-fx-background-insets: 0, 2; " +
                                 "-fx-background-radius: 0.5em;");
            } 
            if(jugador.getVida()<60){
                listaBarras.get(i).setStyle("-fx-control-inner-background: black; " +
                                 "-fx-accent: #FFA500; " +
                                 "-fx-background-color: black, black; " +
                                 "-fx-background-insets: 0, 2; " +
                                 "-fx-background-radius: 0.5em;");
            }
            if(jugador.getVida()<25){
                listaBarras.get(i).setStyle("-fx-control-inner-background: black; " +
                                 "-fx-accent: red; " +
                                 "-fx-background-color: black, black; " +
                                 "-fx-background-insets: 0, 2; " +
                                 "-fx-background-radius: 0.5em;");
            } 
            balastotales=jugador.cantidad60+jugador.cantidad80+jugador.cantidad105;
            listaBalas.get(i).setText(Integer.toString(balastotales));         
            i++;
        }   
    }
    //Metodo que hace el cambio de jugador y sus valores en la interfaz
    public void mostrarJugador(Jugador jugador){
        for (ImageView imagen : imagenes) {
            imagen.setVisible(false);
        }
        imagenes[jugador.jugador].setVisible(true);//imagen
        
        //asesinatos y suicidios
        cantidadAsesinatos.setText(Integer.toString(jugador.asesinatosTotales));
        cantidadSuicidios.setText(Integer.toString(jugador.suicidiosTotales));
        
        //cantidad de balas
        textcantidad1.setText(Integer.toString(jugador.cantidad60));
        textcantidad2.setText(Integer.toString(jugador.cantidad80));
        textcantidad3.setText(Integer.toString(jugador.cantidad105));
        
        //barra vida
        barraDeVida.setProgress(jugador.getVida()/100.0);
        
        //cambia color dependiendo de su cantidad de vida
        if(jugador.getVida()>=60){
            barraDeVida.setStyle("-fx-control-inner-background: black; " +
                             "-fx-accent: #00FF00; " +
                             "-fx-background-color: black, black; " +
                             "-fx-background-insets: 0, 2; " +
                             "-fx-background-radius: 0.5em;");
        } 
        if(jugador.getVida()<60){
            barraDeVida.setStyle("-fx-control-inner-background: black; " +
                             "-fx-accent: #FFA500; " +
                             "-fx-background-color: black, black; " +
                             "-fx-background-insets: 0, 2; " +
                             "-fx-background-radius: 0.5em;");
        }
        if(jugador.getVida()<25){
            barraDeVida.setStyle("-fx-control-inner-background: black; " +
                             "-fx-accent: red; " +
                             "-fx-background-color: black, black; " +
                             "-fx-background-insets: 0, 2; " +
                             "-fx-background-radius: 0.5em;");
        }             
    }  
    
    //ingresa el disparo que se escribio en los textfield
    public void ingresar_disparo(){
        disparar.setDisable(true);
        Label distanciaLabel = (Label) boxdistancia.getChildren().get(1);
        distanciaLabel.setText(" ");                               
        Label alturaLabel = (Label) boxaltura.getChildren().get(1);
        alturaLabel.setText(" ");           
    }    
}
