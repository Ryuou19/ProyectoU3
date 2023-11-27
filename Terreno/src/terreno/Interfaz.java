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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 


public class Interfaz {
    
    int alto;
    int ancho;

    public Interfaz(int alto, int ancho) {
        this.alto = alto;
        this.ancho = ancho;
    }
  
    public Label textodistancia;//distancia maxima mostrada en la interfaz a traves de la variable distancia
    public Label textoaltura;//altura maxima mostrada en la interfaz a traves de la variable altura
    
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
    HBox boxvida= new HBox();      
    ProgressBar barraDeVida = new ProgressBar();
    
    //BOTON REINICIAR
    HBox boxreiniciar= new HBox();
    Button reiniciar = new Button();
    Image image1 = new Image(getClass().getResourceAsStream("./img/botonReiniciar.png"));
    ImageView imagenBotonReiniciar = new ImageView(image1);
    //BOTON FINALIZAR
    HBox boxfinalizar= new HBox();
    Button finalizar = new Button();
    Image image = new Image(getClass().getResourceAsStream("./img/botonFinalizar.png"));
    ImageView imagenBotonFinalizar = new ImageView(image);
      
    //BOTONES BALAS
    HBox tipos = new HBox();
    Button bala1 = new Button("60mm");  
    Button bala2 = new Button("80mm");  
    Button bala3 = new Button("105mm");
    
    
    //CANTIDAD BALAS
    HBox boxcantidadbalas=new HBox();
    Label textcantidad= new Label("");
    GraphicsContext gc;
    Pane canvasPane = new Pane();
    
    public void iniciar_interfaz(Stage primaryStage, Scene escena){//inicia todo lo visual e interactivo de la interfaz de juego         
        
        canvasPane.setPrefSize(alto, ancho);       
        
        
        Canvas canvas = new Canvas(alto, ancho);
        GraphicsContext newgc = canvas.getGraphicsContext2D();
        gc=newgc;
        
        escena.setRoot(canvasPane);
        canvasPane.getChildren().add(canvas);
        primaryStage.setScene(escena);
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
        
        //TANQUES
        for (ImageView imagen : imagenes) {
            imagen.setFitWidth(120);
            imagen.setFitHeight(120);
            imagen.setLayoutX(500);
            imagen.setLayoutY(575);
            canvasPane.getChildren().add(imagen); // Agrega cada imagen al Pane
        }
            
        //DISPARO
        disparar.setStyle("-fx-font-size: 16px; -fx-font-family: 'Monospaced'; ");
        boxdisparo.getChildren().add(disparar);
        boxdisparo.setLayoutX(660); 
        boxdisparo.setLayoutY(590+mover);
                       
        //DISTANCIA   
        boxdistancia.setSpacing(6);
        textodistancia = new Label(0 + " Metros");
        textdistancia.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        textdistancia.setFill(Color.BLACK);
        textodistancia.setTextFill(Color.RED);
        textodistancia.setFont(Font.font("Arial", 16));
        textdistancia.setTranslateY(-1); 
        boxdistancia.getChildren().addAll(textdistancia,textodistancia);
        boxdistancia.setLayoutY(5);
              
        //ALTURA
        boxaltura.setSpacing(6);
        textoaltura = new Label(0 + " Metros");      
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
        barraDeVida.setPrefWidth(120);  
        barraDeVida.setStyle("-fx-control-inner-background: black; " +
                             "-fx-accent: #00FF00; " +
                             "-fx-background-color: black, black; " +
                             "-fx-background-insets: 0, 2; " +
                             "-fx-background-radius: 0.5em;");
        boxvida.getChildren().addAll(barraDeVida);     
        boxvida.setLayoutX(660);
        boxvida.setLayoutY(565+mover);
        
        
        
        
        //BOTON REINICIAR
        imagenBotonReiniciar.setFitWidth(100);
        imagenBotonReiniciar.setFitHeight(30);
        imagenBotonReiniciar.setPreserveRatio(false);
        
        
        reiniciar.setGraphic(imagenBotonReiniciar); 
        reiniciar.setStyle(
            "-fx-background-color: transparent; " +
            "-fx-border-color: transparent;"
        );
        
        boxreiniciar.getChildren().add(reiniciar);
        boxreiniciar.setLayoutX(50); 
        boxreiniciar.setLayoutY(600);
        
        //BOTON FINALIZAR
        imagenBotonFinalizar.setFitWidth(100);
        imagenBotonFinalizar.setFitHeight(30);
        imagenBotonFinalizar.setPreserveRatio(false);
        
        finalizar.setGraphic(imagenBotonFinalizar); 
        finalizar.setStyle(
            "-fx-background-color: transparent; " +
            "-fx-border-color: transparent;"
        );
        boxfinalizar.getChildren().add(finalizar);
        boxfinalizar.setLayoutX(50); 
        boxfinalizar.setLayoutY(650);
        
        //BOTON BALAS
        tipos.setStyle("-fx-background-color: #C0C0C0;");
        bala1.setStyle("-fx-background-color: " + "Green" + "; -fx-min-width: 25px; -fx-min-height: 30px; -fx-text-fill: white;");
        bala2.setStyle("-fx-background-color: " + "Blue" + "; -fx-min-width: 25px; -fx-min-height: 30px; -fx-text-fill: white;");
        bala3.setStyle("-fx-background-color: " + "Red" + "; -fx-min-width: 25px; -fx-min-height: 30px; -fx-text-fill: white;"); 
        tipos.setLayoutX(642);
        tipos.setLayoutY(650);
        tipos.getChildren().addAll(bala1,bala2,bala3);
        
        //CANTIDAD BALAS
        textcantidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        boxcantidadbalas.getChildren().add(textcantidad);
        boxcantidadbalas.setLayoutX(645);
        boxcantidadbalas.setLayoutY(620);
        
      
        //SE AGREGA TODO AL CANVASPANE
        canvasPane.getChildren().addAll(boxangulo,boxvelocidad,
                boxjugador,boxdisparo, boxdistancia, boxaltura, boxvida, 
                boxreiniciar, boxfinalizar, tipos, boxcantidadbalas);
        
        
        boxvida.setVisible(true);
    }
    
    public void mostrarJugador(Jugador jugador){
        for (ImageView imagen : imagenes) {
            imagen.setVisible(false);
        }
        imagenes[jugador.jugador].setVisible(true);
        
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
