package terreno;

import javafx.scene.Scene;
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
    
    int alto;
    int ancho;

    public Interfaz(int alto, int ancho) {
        this.alto = alto;
        this.ancho = ancho;
    }
  
    public Label textodistancia;//distancia maxima mostrada en la interfaz a traves de la variable distancia
    public Label textoaltura;//altura maxima mostrada en la interfaz a traves de la variable altura
    
    Image imagenHud= new Image(getClass().getResourceAsStream("./img/hud.jpg"));
    ImageView hud= new ImageView(imagenHud);
    
    //ANGULO
    HBox boxangulo = new HBox();
    Text text1 = new Text("Angulo");
    TextField entradaangulo = new TextField();
    
    //VELOCIDAD
    HBox boxvelocidad = new HBox();
    Text text2 = new Text("Velocidad");
    TextField entradavelocidad = new TextField();

    //JUGADOR
    HBox boxjugador = new HBox();
    Text textjugador=new Text("Turno Actual");

    //TANQUE 1
    Image tanque1 = new Image(getClass().getResourceAsStream("./img/tanque1.png"));
    ImageView imagentanque1 = new ImageView(tanque1);

    //TANQUE 2
    Image tanque2 = new Image(getClass().getResourceAsStream("./img/tanque2.png"));
    ImageView imagentanque2 = new ImageView(tanque2);
    
    //TANQUE 3
    Image tanque3 = new Image(getClass().getResourceAsStream("./img/tanque3.png"));
    ImageView imagentanque3 = new ImageView(tanque3);
        
    //TANQUE 4
    Image tanque4 = new Image(getClass().getResourceAsStream("./img/tanque4.png"));
    ImageView imagentanque4 = new ImageView(tanque4);
    
    //TANQUE 5
    Image tanque5 = new Image(getClass().getResourceAsStream("./img/tanque5.png"));
    ImageView imagentanque5 = new ImageView(tanque5);
        
    //TANQUE 6
    Image tanque6 = new Image(getClass().getResourceAsStream("./img/tanque6.png"));
    ImageView imagentanque6 = new ImageView(tanque6);
       
    //LISTA TANQUES
    ImageView[] imagenes={imagentanque1,imagentanque2,imagentanque3,imagentanque4,imagentanque5,imagentanque6};
    
    //DISPARO
    Button disparar = new Button("!DISPARAR!");

    //DISTANCIA
    HBox boxdistancia=new HBox();
    Text textdistancia= new Text("Distancia = ");

    //ALTURA
    HBox boxaltura=new HBox();
    Text textaltura= new Text("Altura = ");

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
    VBox tipos = new VBox();
    Button bala1 = new Button("60mm");  
    Button bala2 = new Button("80mm");  
    Button bala3 = new Button("105mm");
    
    
    //CANTIDAD BALAS
    VBox cantidad = new VBox(8);
    Text textcantidad1= new Text("");
    Text textcantidad2= new Text("");
    Text textcantidad3= new Text("");
    GraphicsContext gc;
    Pane canvasPane = new Pane();
    
    public void iniciar_interfaz( Scene escena){//inicia todo lo visual e interactivo de la interfaz de juego         
        Globales.cambiarResolucion(Globales.alto_resolucion-1, Globales.ancho_resolucion-1);
        canvasPane.setPrefSize(alto, ancho);       
        
        
        Canvas canvas = new Canvas(alto, ancho);
        GraphicsContext newgc = canvas.getGraphicsContext2D();
        gc=newgc;
        
        escena.setRoot(canvasPane);
        canvasPane.getChildren().add(canvas);
        Globales.stage.setScene(escena);
        
        //FONDO HUD
        hud.setPreserveRatio(false);      
        canvasPane.getChildren().add(hud);
        //ANGULO
        boxangulo.setSpacing(10);    
        boxangulo.getChildren().addAll(text1, entradaangulo);
        
               
        //VELOCIDAD
        boxvelocidad.setSpacing(10);
        boxvelocidad.getChildren().addAll(text2, entradavelocidad);
        
                    
        //JUGADOR
        boxjugador.getChildren().add(textjugador);
        
        //TANQUES
        for (ImageView imagen : imagenes) {              
            canvasPane.getChildren().add(imagen); //agrega cada imagen al pane
        }
        
            
        //DISPARO
        disparar.setStyle("-fx-font-size: 14px; -fx-font-family: 'Monospaced'; ");
        
       
                       
        //DISTANCIA   
        boxdistancia.setSpacing(6);
        textodistancia = new Label(0 + " Metros");
        
        textdistancia.setFill(Color.BLACK);
        textodistancia.setTextFill(Color.RED);
        
        textdistancia.setTranslateY(-1); 
        boxdistancia.getChildren().addAll(textdistancia,textodistancia);
        
              
        
        
        
        
        //ALTURA
        boxaltura.setSpacing(6);
        textoaltura = new Label(0 + " Metros");      
             
        textaltura.setFill(Color.BLACK);       
        textoaltura.setTextFill(Color.RED);
                     
        textaltura.setTranslateY(-1);   
        boxaltura.getChildren().addAll(textaltura,textoaltura);
       
               
        
        
        //VIDA
         
        barraDeVida.setStyle("-fx-control-inner-background: black; " +
                             "-fx-accent: #00FF00; " +
                             "-fx-background-color: black, black; " +
                             "-fx-background-insets: 0, 2; " +
                             "-fx-background-radius: 0.5em;");
         
        
        
        
        
        
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
        
        
        canvasPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double widthRatio = newWidth.doubleValue() / 800;
            hud.setFitWidth(800*widthRatio);       
            hud.setLayoutX(0);
            
            text1.setFont(Font.font("Arial",FontWeight.BOLD, 20*widthRatio)); 
            entradaangulo.setPrefWidth(40*widthRatio);
            boxangulo.setLayoutX(180*widthRatio); 
            
            text2.setFont(Font.font("Arial", FontWeight.BOLD, 20*widthRatio));
            entradavelocidad.setPrefWidth(40*widthRatio);
            boxvelocidad.setLayoutX(155*widthRatio); 
        
            textjugador.setFont(Font.font("Arial", FontWeight.BOLD, 20*widthRatio));
            boxjugador.setLayoutX(350*widthRatio); 
            
            for (ImageView imagen : imagenes) {
                imagen.setFitWidth(120*widthRatio);               
                imagen.setLayoutX(355*widthRatio);               
            } 
            disparar.setPrefWidth(120*widthRatio);
            disparar.setLayoutX(500*widthRatio); 
            
            boxaltura.setLayoutX(30*widthRatio);
            
            barraDeVida.setPrefWidth(120*widthRatio);
            barraDeVida.setLayoutX(500*widthRatio);    
            
            imagenBotonReiniciar.setFitWidth(100*widthRatio);
            reiniciar.setLayoutX(25*widthRatio); 
        
            imagenBotonFinalizar.setFitWidth(100*widthRatio);            
            finalizar.setLayoutX(25*widthRatio); 
            
            tipos.setLayoutX(680*widthRatio);
            bala1.setPrefWidth(70*widthRatio);
            bala2.setPrefWidth(70*widthRatio);
            bala3.setPrefWidth(70*widthRatio);
            
            cantidad.setLayoutX(660*widthRatio);
            textcantidad1.setFont(Font.font("Arial", FontWeight.BOLD, 20*widthRatio));
            textcantidad2.setFont(Font.font("Arial", FontWeight.BOLD, 20*widthRatio));
            textcantidad3.setFont(Font.font("Arial", FontWeight.BOLD, 20*widthRatio));
            
        });
         
        canvasPane.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            double heightRatio = newHeight.doubleValue() / 800; 
            hud.setFitHeight(800/2*heightRatio);
            hud.setLayoutY(630*heightRatio);
                    
            entradaangulo.setPrefHeight(30*heightRatio);
            boxangulo.setLayoutY(660*heightRatio);
            
            entradavelocidad.setPrefHeight(30*heightRatio);
            boxvelocidad.setLayoutY(700*heightRatio);
            
            boxjugador.setLayoutY(645*heightRatio);
            
            for (ImageView imagen : imagenes) {
                imagen.setFitHeight(120*heightRatio);
                imagen.setLayoutY(630*heightRatio);
            }
            disparar.setPrefHeight(30*heightRatio);
            disparar.setLayoutY(690*heightRatio);
            
            textdistancia.setFont(Font.font("Arial", FontWeight.BOLD, 20*heightRatio));
            textodistancia.setFont(Font.font("Arial", 16*heightRatio));
            boxdistancia.setLayoutY(2*heightRatio);
            
            textaltura.setFont(Font.font("Arial", FontWeight.BOLD, 20*heightRatio));  
            textoaltura.setFont(Font.font("Arial", 16*heightRatio));   
            boxaltura.setLayoutY(30*heightRatio);
            
            barraDeVida.setLayoutY(660*heightRatio);
            barraDeVida.setPrefHeight(20*heightRatio);
            
            imagenBotonReiniciar.setFitHeight(30*heightRatio);
            reiniciar.setLayoutY(650*heightRatio);
            
            imagenBotonFinalizar.setFitHeight(30*heightRatio);
            finalizar.setLayoutY(700*heightRatio);
            
            tipos.setLayoutY(650*heightRatio);
            bala1.setPrefHeight(30*heightRatio);
            bala2.setPrefHeight(30*heightRatio);
            bala3.setPrefHeight(30*heightRatio);
            
            cantidad.setLayoutY(655*heightRatio);
            textcantidad1.setFont(Font.font("Arial", FontWeight.BOLD, 20*heightRatio));
            textcantidad2.setFont(Font.font("Arial", FontWeight.BOLD, 20*heightRatio));
            textcantidad3.setFont(Font.font("Arial", FontWeight.BOLD, 20*heightRatio));
        });
        //SE AGREGA TODO AL CANVASPANE
        canvasPane.getChildren().addAll(boxangulo,boxvelocidad,
                boxjugador,disparar, boxdistancia, boxaltura, barraDeVida, 
                reiniciar, finalizar, tipos, cantidad);
        
        
        
        
        
    }
    
    public void mostrarJugador(Jugador jugador){
        for (ImageView imagen : imagenes) {
            imagen.setVisible(false);
        }
        imagenes[jugador.jugador].setVisible(true);
        
        textcantidad1.setText(Integer.toString(jugador.cantidad60));
        textcantidad2.setText(Integer.toString(jugador.cantidad80));
        textcantidad3.setText(Integer.toString(jugador.cantidad105));
        
        barraDeVida.setProgress(jugador.getVida()/100.0);
        
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
}
