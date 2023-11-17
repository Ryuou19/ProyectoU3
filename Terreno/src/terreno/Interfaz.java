package terreno;

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
 


public class Interfaz {
    
    int alto;
    int ancho;

    public Interfaz(int alto, int ancho) {
        this.alto = alto*3;
        this.ancho = ancho*3;
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
    HBox boxtanque1 = new HBox();
    Image tanque1 = new Image(getClass().getResourceAsStream("./img/tanque1.png"));
    ImageView imagentanque1 = new ImageView(tanque1);

    //TANQUE 2
    HBox boxtanque2 = new HBox();
    Image tanque2 = new Image(getClass().getResourceAsStream("./img/tanque2.png"));
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
    Label textovida1 = new Label(100+"");
    Label textovida2 = new Label(100+"");
    
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
    GraphicsContext gc;
    
    public void iniciar_interfaz(Stage primaryStage, Scene escena){//inicia todo lo visual e interactivo de la interfaz de juego         
        Pane canvasPane = new Pane();
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
}
