package terreno;

import java.io.File;
import java.io.IOException;
import javax.swing.Timer; 
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ActionListener;


public class PantallaInicial extends Application {
     
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
    ListaJugadores list=ListaJugadores.getInstance();
    int cantidadJugadores=2;
    String musicPath;//ruta de musica
    AudioInputStream audioInput;//audio del sistema
    float volume;//volumen
    Clip clip;//reproductor
    FloatControl control;//para controlar la musica
    int resolucion_def=2;
    int jugadores_def=2;
    int rondas_def=1;
    int entorno_def=0;
    int cantidad_def=0;
    Pane panel = new Pane();
    Scene scene = new Scene(panel, 500, 500);
    
    public PantallaInicial() {
    }
      
    public PantallaInicial(int resolucion_def, int rondas_def, int jugadores_def, int cantidad_def, int entorno_def) {
        this.resolucion_def = resolucion_def;
        this.jugadores_def = jugadores_def;
        this.rondas_def = rondas_def;
        this.entorno_def = entorno_def;
        this.cantidad_def = cantidad_def;
    }

    
    
    @Override   
    public void start(Stage primaryStage) {
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("!TANK WAR!");
        Image icono = new Image(getClass().getResourceAsStream("./img/tanque menu.gif"));
        primaryStage.getIcons().add(icono); 
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
       
        musica(); 
        
        
        //imagen tanque inicial con sus propiedades y estilo
        Image imagen = new Image(getClass().getResource("./img/tanque menu.gif").toExternalForm());
        ImageView imageView = new ImageView(imagen);
        
        double nuevoAncho = 450; 
        double nuevoAlto = 300;  
        imageView.setFitWidth(nuevoAncho);
        imageView.setFitHeight(nuevoAlto);
        
        //titulo inicial con gif  
        imagen=new Image(getClass().getResource("./img/text.gif").toExternalForm());
        ImageView titulo = new ImageView(imagen);
        titulo.setLayoutX(390); 
        titulo.setLayoutY(150); 
        nuevoAncho=600;
        nuevoAlto=150;
        titulo.setFitWidth(nuevoAncho);
        titulo.setFitHeight(nuevoAlto);
        
        //fondo
        Rectangle marco = new Rectangle(1600, 800);  
        marco.setFill(Color.rgb(148, 161, 147, 1.0));
        
        //boton comenzar
        HBox boxcomenzar=new HBox();
        Button comenzar = new Button("!!!JUGAR!!!");  
        Font font = Font.font("Serif", FontWeight.NORMAL, 29);//fuente para el texto del boton
        comenzar.setFont(font);
        boxcomenzar.getChildren().add(comenzar);
        comenzar.setLayoutX(510);
        comenzar.setLayoutY(500);
        comenzar.setStyle(
            "-fx-background-color: #000000; " + 
            "-fx-text-fill: #FFFFFF;" + 
            "-fx-border-color: #FF0000;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;" 
        );//estilo del boton
        
        //boton opciones
        HBox boxopciones=new HBox();
        Button opciones = new Button("OPCIONES");  
        opciones.setFont(font);
        boxopciones.getChildren().add(opciones);
        opciones.setLayoutX(518);
        opciones.setLayoutY(580);
        opciones.setStyle(
            "-fx-background-color: #000000; " + 
            "-fx-text-fill: #FFFFFF;" + 
            "-fx-border-color: #FF0000;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;" 
        );
        
        
        comenzar.setOnAction(e -> {         
            Jugar juego = new Jugar(list,resolucion_def,rondas_def,jugadores_def,cantidad_def,entorno_def);//inicia el proceso de jugar
            //list.instanciarJugadores(cantidadJugadores);
            juego.start(primaryStage,scene);
            volume -= 6.0f;//al comenzar a jugar, se baja un poco el volumen
            control.setValue(volume);
        });
        
        opciones.setOnAction(e -> {
            MenuOpciones options = new MenuOpciones();
            options.start(primaryStage, scene,list);
            volume=-80.0f;
            control.setValue(volume);
        });
        
        //se añade todo al panel
        panel.getChildren().addAll(marco,comenzar,imageView, titulo,opciones);    
        /*FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), panel);//fade blanco inicial
        fadeTransition.setFromValue(0); 
        fadeTransition.setToValue(1); 
        fadeTransition.play();*/
        
       
        double porcentajeX = 0.34;
        double porcentajeY = 0.35; 
        imageView.layoutXProperty().bind(scene.widthProperty().multiply(porcentajeX));
        imageView.layoutYProperty().bind(scene.heightProperty().multiply(porcentajeY));
        
        porcentajeX=0.27;
        porcentajeY=0.15;
        titulo.layoutXProperty().bind(scene.widthProperty().multiply(porcentajeX));
        titulo.layoutYProperty().bind(scene.heightProperty().multiply(porcentajeY));
     
        porcentajeX=0.436;
        porcentajeY=0.76;
        comenzar.layoutXProperty().bind(scene.widthProperty().multiply(porcentajeX));
        comenzar.layoutYProperty().bind(scene.heightProperty().multiply(porcentajeY));
        
        porcentajeX=0.44;
        porcentajeY=0.85;
        opciones.layoutXProperty().bind(scene.widthProperty().multiply(porcentajeX));
        opciones.layoutYProperty().bind(scene.heightProperty().multiply(porcentajeY));
        
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //funcion que procesa la musica
    public void musica(){
        musicPath = "src/terreno/music/BAIXO-SLOWED.wav";
        try{
            audioInput = AudioSystem.getAudioInputStream(new File(musicPath));
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            //control de volumen
            control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            //volumen (rango: -80.0 a 6.0206)     
            volume = -20.0f;            
            control.setValue(volume);
            
            Timer timer = new Timer(1200, new ActionListener() {//funcion que genera delay al inicio de la ejecucion para la musica, para adaptarse al fade inicial
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    clip.start();
                }
            });
            
            timer.setRepeats(false);
            timer.start();
        //manejo de exepciones
        }catch(UnsupportedAudioFileException e){
            System.out.println(e.toString());
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }  
    
    public void volver(Stage stage){
        stage.setTitle("Menu Opciones");
        stage.setScene(scene);
        
        musica();
    }
    
}