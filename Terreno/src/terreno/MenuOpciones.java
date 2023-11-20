package terreno;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

public class MenuOpciones {
    
    String musicPath;//ruta de musica
    AudioInputStream audioInput;//audio del sistema
    float volume;//volumen
    Clip clip;//reproductor
    FloatControl control;//para controlar la musica
    private final String[] resolucion = {"800x800", "900x900", "1200x900"};
    private final String[] jugadores = {"2", "3","4","5","6"}; 
    private final String[] rondas = {"1","2","3","4","5","6","7","8",
    "9","10","11","12","13","14",
    "15","16","17","18","19","20"};
    private final String[] entorno={"Ninguno","Gravedad","Viento"};
    private final String[] cantidad = {"0","1", "2","3","4","5"};
    private int opcionActual = 0;
    private Button opcion_resolucion;
    private Button opcion_jugadores;
    private Button opcion_rondas;
    private Button opcion_entorno;
    private Button opcion_cantidad;
    String estilo_botones = 
    "-fx-background-color: #000000; " + 
    "-fx-text-fill: #FFFFFF;" + 
    "-fx-border-color: #FF0000;" + 
    "-fx-border-width: 3px;" +  
    "-fx-background-radius: 0;";
    Font font = Font.font("Serif", FontWeight.NORMAL, 24);
    Pane panel = new Pane();
    Scene escena=new Scene(panel,1500,900);
    Image icono = new Image(getClass().getResourceAsStream("./img/icono opciones.jpg"));                 
    Image fondo = new Image(getClass().getResourceAsStream("./img/fondo opciones.jpg"));     

    public MenuOpciones() {
    }  
    
    public void start(Stage stage, ListaJugadores list,Scene scene){
        PantallaInicial inicio=new PantallaInicial();
        stage.setFullScreen(true);
        inicio.scene=scene;
        
        panel.setPrefSize(1500, 900);       
        ImageView imageView = new ImageView(fondo);   
        imageView.setPreserveRatio(false);
        imageView.setFitWidth(1380);
        imageView.setFitHeight(780);       
        panel.getChildren().add(imageView);              
        escena.setRoot(panel);
        /*stage.setWidth(500);
        stage.setHeight(630);
        stage.setX(400); stage.setY(60);*/
        
        
        //VOLVER MENU PRINCIPAAL
        Button volverMenu = new Button("Volver al Menú Principal");
        volverMenu.setFont(font);
        volverMenu.setStyle(estilo_botones);
        
        //////////////////////////////////////////////////////////////////7
        //RESOLUCION     
        opcion_resolucion = new Button(resolucion[opcionActual]);
        opcion_resolucion.setMinWidth(200);
        opcion_resolucion.setFont(font);
        opcion_resolucion.setStyle(estilo_botones);
        
        Button flecha_derecha1 = new Button(">");
        flecha_derecha1.setMinWidth(50);
        flecha_derecha1.setMinHeight(50);
        flecha_derecha1.setStyle(estilo_botones);
        flecha_derecha1.setOnAction(e -> {
            Globales.resolucion_def=cambiarOpcion(1,resolucion,opcion_resolucion,Globales.resolucion_def);
            System.out.println("Actual = "+Globales.resolucion_def);
        });
        
        Button flecha_izquierda1 = new Button("<");
        flecha_izquierda1.setMinWidth(50); 
        flecha_izquierda1.setMinHeight(50); 
        flecha_izquierda1.setStyle(estilo_botones);
        flecha_izquierda1.setOnAction(e -> {
            Globales.resolucion_def=cambiarOpcion(-1,resolucion,opcion_resolucion,Globales.resolucion_def);
            System.out.println("Actual = "+Globales.resolucion_def);
        });
        
        HBox menu_resoluciones = new HBox();
        menu_resoluciones.getChildren().addAll(flecha_izquierda1,opcion_resolucion, flecha_derecha1);
        menu_resoluciones.setLayoutX(100);
        menu_resoluciones.setLayoutY(40);
        
        Label labelResolucion = new Label("Resolución");
        labelResolucion.setFont(font);
        labelResolucion.setStyle("-fx-text-fill: white;");
        
        HBox texto_resoluciones= new HBox();
        texto_resoluciones.getChildren().add(labelResolucion);
        texto_resoluciones.setLayoutX(195); 
        texto_resoluciones.setLayoutY(5);       
        /////////////////////////////////////////////////
        ///RESOLUCION
       
        /////////////////////////////////////////////////////
        //RONDAS
        opcion_rondas = new Button(rondas[opcionActual]);
        opcion_rondas.setMinWidth(200);
        opcion_rondas.setFont(font);
        opcion_rondas.setStyle(estilo_botones);
               
        Button flecha_derecha2 = new Button(">");
        flecha_derecha2.setMinWidth(50);
        flecha_derecha2.setMinHeight(50);
        flecha_derecha2.setStyle(estilo_botones);
        flecha_derecha2.setOnAction(e -> {
            Globales.rondas_def=cambiarOpcion(1,rondas,opcion_rondas,Globales.rondas_def);
            System.out.println("Actual = "+Globales.rondas_def);
        });
        
        Button flecha_izquierda2 = new Button("<");
        flecha_izquierda2.setMinWidth(50);
        flecha_izquierda2.setMinHeight(50);
        flecha_izquierda2.setStyle(estilo_botones);
        flecha_izquierda2.setOnAction(e -> {
            Globales.rondas_def=cambiarOpcion(-1,rondas,opcion_rondas,Globales.rondas_def);
            System.out.println("Actual = "+Globales.rondas_def);
        });
        
        HBox menu_rondas = new HBox();
        menu_rondas.getChildren().addAll(flecha_izquierda2,opcion_rondas, flecha_derecha2);
        menu_rondas.setLayoutX(100);
        menu_rondas.setLayoutY(140);
        
        Label labelRondas = new Label("Rondas");
        labelRondas.setFont(font);
        labelRondas.setStyle("-fx-text-fill: white;");
        
        HBox texto_rondas= new HBox();
        texto_rondas.getChildren().add(labelRondas);
        texto_rondas.setLayoutX(210); 
        texto_rondas.setLayoutY(100);    
        /////////////////////////////////////////////////////
        //JUGADORES
        
        
        /////////////////////////////////////////////////////
        //JUGADORES
        opcion_jugadores = new Button(jugadores[opcionActual]);
        opcion_jugadores.setMinWidth(200);
        opcion_jugadores.setFont(font);
        opcion_jugadores.setStyle(estilo_botones);
               
        Button flecha_derecha3 = new Button(">");
        flecha_derecha3.setMinWidth(50);
        flecha_derecha3.setMinHeight(50);
        flecha_derecha3.setStyle(estilo_botones);
        flecha_derecha3.setOnAction(e -> {
            Globales.jugadores_def=cambiarOpcion(1,jugadores,opcion_jugadores,Globales.jugadores_def);
            System.out.println("Actual = "+Globales.jugadores_def);
        });
        
        Button flecha_izquierda3 = new Button("<");
        flecha_izquierda3.setMinWidth(50);
        flecha_izquierda3.setMinHeight(50);
        flecha_izquierda3.setStyle(estilo_botones);
        flecha_izquierda3.setOnAction(e -> {
            Globales.jugadores_def=cambiarOpcion(-1,jugadores,opcion_jugadores,Globales.jugadores_def);
            System.out.println("Actual = "+Globales.jugadores_def);
        });
        
        HBox menu_jugadores = new HBox();
        menu_jugadores.getChildren().addAll(flecha_izquierda3,opcion_jugadores, flecha_derecha3);
        menu_jugadores.setLayoutX(100);
        menu_jugadores.setLayoutY(240);
        
        Label labelJugadores = new Label("Jugadores");
        labelJugadores.setFont(font);
        labelJugadores.setStyle("-fx-text-fill: white;");
        
        HBox texto_jugadores= new HBox();
        texto_jugadores.getChildren().add(labelJugadores);
        texto_jugadores.setLayoutX(200); 
        texto_jugadores.setLayoutY(200);    
        /////////////////////////////////////////////////////
        //JUGADORES
        
        
        ////////////////////////////////////////////
        //CANTIDAD IA´S
        opcion_cantidad = new Button(cantidad[opcionActual]);
        opcion_cantidad.setMinWidth(200);
        opcion_cantidad.setFont(font);
        opcion_cantidad.setStyle(estilo_botones);
               
        Button flecha_derecha5 = new Button(">");
        flecha_derecha5.setMinWidth(50); 
        flecha_derecha5.setMinHeight(50);
        flecha_derecha5.setStyle(estilo_botones);
        flecha_derecha5.setOnAction(e -> {         
            Globales.cantidad_def=cambiarOpcion(1,cantidad,opcion_cantidad,Globales.cantidad_def);
            System.out.println("Actual = "+Globales.cantidad_def); 
        });
        
        Button flecha_izquierda5 = new Button("<");
        flecha_izquierda5.setMinWidth(50); 
        flecha_izquierda5.setMinHeight(50);
        flecha_izquierda5.setStyle(estilo_botones);
        flecha_izquierda5.setOnAction(e -> {         
            Globales.cantidad_def=cambiarOpcion(-1,cantidad,opcion_cantidad,Globales.cantidad_def);
            System.out.println("Actual = "+Globales.cantidad_def); 
        });
             
        HBox menu_cantidad = new HBox();
        menu_cantidad.getChildren().addAll(flecha_izquierda5,opcion_cantidad, flecha_derecha5);
        menu_cantidad.setLayoutX(100);
        menu_cantidad.setLayoutY(340);
        
        Label labelIA = new Label("Jug. Artificiales");
        labelIA.setFont(font);
        labelIA.setStyle("-fx-text-fill: white;");
        
        HBox texto_IA= new HBox();
        texto_IA.getChildren().add(labelIA);
        texto_IA.setLayoutX(175); 
        texto_IA.setLayoutY(300); 
        
        
        /////////////////////////////////////////
        //ENTORNO
        opcion_entorno = new Button(entorno[opcionActual]);
        opcion_entorno.setMinWidth(200);
        opcion_entorno.setFont(font);
        opcion_entorno.setStyle(estilo_botones);
               
        Button flecha_derecha4 = new Button(">");
        flecha_derecha4.setMinWidth(50); 
        flecha_derecha4.setMinHeight(50);
        flecha_derecha4.setStyle(estilo_botones);
        flecha_derecha4.setOnAction(e -> {
            Globales.entorno_def=cambiarOpcion(1,entorno,opcion_entorno,Globales.entorno_def);
            System.out.println("Actual = "+Globales.entorno_def);    
        });
        
        Button flecha_izquierda4 = new Button("<");
        flecha_izquierda4.setMinWidth(50);
        flecha_izquierda4.setMinHeight(50);
        flecha_izquierda4.setStyle(estilo_botones);
        flecha_izquierda4.setOnAction(e -> {
            Globales.entorno_def=cambiarOpcion(-1,entorno,opcion_entorno,Globales.entorno_def);
            System.out.println("Actual = "+Globales.entorno_def);      
        });
        
        HBox menu_entorno = new HBox();
        menu_entorno.getChildren().addAll(flecha_izquierda4,opcion_entorno, flecha_derecha4);
        menu_entorno.setLayoutX(100); 
        menu_entorno.setLayoutY(440);
        
        Label labelEntorno = new Label("Efectos de Entorno");
        labelEntorno.setFont(font);
        labelEntorno.setStyle("-fx-text-fill: white;");
        
        HBox texto_entorno= new HBox();
        texto_entorno.getChildren().add(labelEntorno);
        texto_entorno.setLayoutX(160); 
        texto_entorno.setLayoutY(400); 
        ///////////////////////////////////////////////
        //ENTORNO
        
        
        /////////////////////////////////////////////////
        //VOLVER
        HBox volver=new HBox();
        volver.getChildren().add(volverMenu);
        volver.setLayoutX(110);
        volver.setLayoutY(520);
        /////////////////////////////////////////////////
        //VOLVER
        
        volverMenu.setOnAction(e -> {
            detenerMusica();
            inicio.mostrar_inicio(stage);
            
        });
        
        panel.getChildren().addAll(menu_resoluciones,texto_resoluciones,menu_rondas,
        texto_rondas,menu_jugadores,texto_jugadores,menu_entorno,texto_entorno,
        menu_cantidad,texto_IA,volver);                 
        stage.setFullScreen(true);  
        stage.show();
    }
    
    
    private int cambiarOpcion(int desplazamiento, String[] tipo, Button opcion, int var_opcion) {
        opcionActual = (opcionActual + desplazamiento) % tipo.length;
        if(opcionActual<0){
            opcionActual=tipo.length-1;
        }       
        opcion.setText(tipo[opcionActual]);
        var_opcion=opcion_def(tipo,opcionActual,var_opcion);
        return var_opcion;
    }   
    
    private int opcion_def(String[] tipo, int opcionActual, int opcion){
        String opcion_str;
        for (int i=0;i<tipo.length;i++){
            if(i==opcionActual){
                System.out.println("Opcion Actual= "+opcionActual);
                if(tipo[i].length()>2){
                    opcion_str=tipo[i];
                    System.out.println("ELECCION STR= "+opcion_str);
                    if(opcion_str.equals("800x800")||opcion_str.equals("Ninguno")){
                        opcion=0;
                    }
                    if(opcion_str.equals("900x900")||opcion_str.equals("Gravedad")){
                        opcion=1;
                    }
                    if(opcion_str.equals("1200x900")||opcion_str.equals("Viento")){
                        opcion=2;
                    }              
                }
                else{
                    opcion=Integer.parseInt(tipo[i]);
                    System.out.println("ELECCION= "+opcion);
                }               
            }         
        }
        return opcion;
    }
    
    public void mostrar(Stage stage){
        detenerMusica();
        stage.setTitle("Menu Opciones");
        stage.getIcons().add(icono);
        stage.setScene(escena); 
        musicPath="src/terreno/music/musicaMenuOpciones.wav";   
        detenerMusica();
        musica(musicPath);
    }
    
    
    
    public void musica(String musica){
        try{
            audioInput = AudioSystem.getAudioInputStream(new File(musica));
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            //control de volumen
            control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            //volumen (rango: -80.0 a 6.0206)     
            volume = -20.0f;            
            control.setValue(volume);
            
            Timer timer = new Timer(0, new ActionListener() {//funcion que genera delay al inicio de la ejecucion para la musica, para adaptarse al fade inicial
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
    
    public void detenerMusica() {
        if (clip != null && clip.isOpen()) {
            clip.stop();
        }
}
}
