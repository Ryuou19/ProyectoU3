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
    Jugador j1=new Jugador("./img/tanque1.png", 1,"Haaland");
    Jugador j2 = new Jugador("./img/tanque2.png", 2, "Beligoool");
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
        list.setJugador1(j1);
        list.setJugador2(j2);
        primaryStage.setTitle("!TANK WAR!");
        Image icono = new Image(getClass().getResourceAsStream("./img/tanque menu.gif"));
        primaryStage.getIcons().add(icono); 
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        primaryStage.setX(80); primaryStage.setY(20);
        musica(); 
        Pane panel = new Pane();//panel de la interfaz inicial
        Scene scene = new Scene(panel, 1200, 700);
        //imagen tanque inicial con sus propiedades y estilo
        Image imagen = new Image(getClass().getResource("./img/tanque menu.gif").toExternalForm());
        ImageView imageView = new ImageView(imagen);
        imageView.setLayoutX(440); 
        imageView.setLayoutY(270); 
        double nuevoAncho = 300; 
        double nuevoAlto = 200;  
        imageView.setFitWidth(nuevoAncho);
        imageView.setFitHeight(nuevoAlto);
        
        //titulo inicial con gif  
        imagen=new Image(getClass().getResource("./img/text.gif").toExternalForm());
        ImageView titulo = new ImageView(imagen);
        titulo.setLayoutX(390); 
        titulo.setLayoutY(150); 
        nuevoAncho=400;
        nuevoAlto=100;
        titulo.setFitWidth(nuevoAncho);
        titulo.setFitHeight(nuevoAlto);
        
        //fondo
        Rectangle marco = new Rectangle(1200, 700);  
        marco.setFill(Color.rgb(148, 161, 147, 1.0));
        
        //boton comenzar
        HBox boxcomenzar=new HBox();
        Button comenzar = new Button("!!!JUGAR!!!");  
        Font font = Font.font("Serif", FontWeight.NORMAL, 24);//fuente para el texto del boton
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
            volume -= 6.0f;//al comenzar a jugar, se baja un poco el volumen
            control.setValue(volume);
            primaryStage.close();
            Jugar juego = new Jugar(list,resolucion_def,rondas_def,jugadores_def,cantidad_def,entorno_def);//inicia el proceso de jugar
            juego.start(new Stage());
        });
        
        opciones.setOnAction(e -> {
            MenuOpciones options = new MenuOpciones();
            options.start(primaryStage, scene,list);
            volume=-80.0f;
            control.setValue(volume);
        });
        
        //se añade todo al panel
        panel.getChildren().addAll(marco,comenzar,imageView, titulo,opciones);    
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), panel);//fade blanco inicial
        fadeTransition.setFromValue(0); 
        fadeTransition.setToValue(1); 
        fadeTransition.play();
        
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
}