package terreno;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class PantallaInicial extends Application {
           
    ListaJugadores list=ListaJugadores.getInstance();
    static Pane panel = new Pane();
    MenuOpciones options = new MenuOpciones();
    Image icono = new Image(getClass().getResourceAsStream("./img/tanque menu.gif"));
    Image imagen = new Image(getClass().getResource("./img/tanque menu.gif").toExternalForm());
    Image titulo1=new Image(getClass().getResource("./img/text.gif").toExternalForm());
    ImageView imageView = new ImageView(imagen);        
    ImageView titulo = new ImageView(titulo1);
    Button opciones = new Button("OPCIONES");  
    Button comenzar = new Button("!!!JUGAR!!!");
    Rectangle marco = new Rectangle(1920, 1080);  
    int validar=0;
    public PantallaInicial() {
    }
    
    public static void main(String[] args) {
        launch(args);
    }
      
    @Override   
    public void start(Stage primaryStage) {
        Globales.escena=new Scene(panel,Globales.alto_resolucion,Globales.ancho_resolucion); 
        Globales.stage=primaryStage;
        //Globales.stage.setResizable(false);
        Globales.stage.getIcons().add(icono); 
        Globales.cambiarResolucion(Globales.alto_resolucion, Globales.ancho_resolucion);
        
        //Globales.stage.initStyle(StageStyle.UNDECORATED); usada para bloquear el movimiento con el cursor de la ventana        
        //fondo   
        marco.setFill(Color.rgb(148, 161, 147, 1.0));
        
        //boton comenzar       
        Font font = Font.font("Serif", FontWeight.NORMAL, 26);//fuente para el texto del boton
        comenzar.setFont(font);
        comenzar.setMinHeight(10);
        comenzar.setStyle(
            "-fx-background-color: #000000; " + 
            "-fx-text-fill: #FFFFFF;" + 
            "-fx-border-color: #FF0000;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;" 
        );//estilo del boton
        
        //boton opciones        
        opciones.setFont(font);
        opciones.setMinHeight(10);      
        opciones.setStyle(
            "-fx-background-color: #000000; " + 
            "-fx-text-fill: #FFFFFF;" + 
            "-fx-border-color: #FF0000;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;" 
        );
             
        comenzar.setOnAction(e -> {         
            list.instanciarJugadores(Globales.jugadores_def,Globales.cantidad_def);
            Globales.rondas_def++;
            Tienda tienda=new Tienda();
            tienda.inicializarInterfaz( list);
        });
        
        opciones.setOnAction(e -> {
            if(validar==0){
                options.start(Globales.stage,list,panel);
                validar=1;
            }
            if(validar==1){
                Globales.escena.setRoot(options.paneOpciones);
                Globales.cambiarEscena(Globales.escena);
            }       
        });
        
        // Agregar ChangeListener al ancho y alto del Pane principal
        panel.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double widthRatio = newWidth.doubleValue() / 800; 
            titulo.setLayoutX(190 * widthRatio); 
            titulo.setFitWidth(420* widthRatio);
            imageView.setLayoutX(160 * widthRatio); 
            imageView.setFitWidth(480* widthRatio);
            comenzar.setLayoutX(310 * widthRatio); 
            comenzar.setPrefWidth(180 * widthRatio);
            opciones.setLayoutX(310 * widthRatio); 
            opciones.setPrefWidth(180 * widthRatio);
            
        });

        panel.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            double heightRatio = newHeight.doubleValue() / 800; 
            titulo.setLayoutY(100 * heightRatio); 
            titulo.setFitHeight(120* heightRatio);
            imageView.setLayoutY(220 * heightRatio); 
            imageView.setFitHeight(300* heightRatio);
            comenzar.setLayoutY(540 * heightRatio);
            comenzar.setPrefHeight(55* heightRatio);
            opciones.setLayoutY(600 * heightRatio);
            opciones.setPrefHeight(55* heightRatio);
            
        });
        
        //se añade todo al panel(agregar imageView)
        panel.getChildren().addAll(marco,comenzar,imageView, titulo,opciones);    
                
        Globales.stage.setScene(Globales.escena);
        Globales.stage.show();
    }
}