package terreno;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MenuOpciones {
   
    private final String[] resolucion = {"800x800", "900x900", "1920x1080"};
    private final String[] jugadores = {"2", "3","4","5","6"}; 
    private final String[] rondas = {"1","2","3","4","5","6","7","8",
    "9","10","11","12","13","14",
    "15","16","17","18","19","20"};
    private final String[] gravedad={"Normal","15,81","5,81"};
    private final String[] cantidad = {"0","1", "2","3","4","5","6"};
    private final String[] viento={"-NO-","-SI-"};
    private int opcionActualResolucion = 0;
    private int opcionActualJugadores = 0;
    private int opcionActualRondas = 0;
    private int opcionActualGravedad = 0;
    private int opcionActualCantidad = 0;
    private int opcionActualViento = 0;
    private Button opcion_resolucion;
    private Button opcion_jugadores;
    private Button opcion_rondas;
    private Button opcion_gravedad;
    private Button opcion_cantidad;
    private Button opcion_viento;
    String estilo_botones = 
    "-fx-background-color: #000000; " + 
    "-fx-text-fill: #FFFFFF;" + 
    "-fx-border-color: #FF0000;" + 
    "-fx-border-width: 3px;" +  
    "-fx-background-radius: 0;";
    Image fondo = new Image(getClass().getResourceAsStream("./img/fondo opciones.jpg")); 
    ImageView imageView = new ImageView(fondo); 
    Button volver = new Button("Menu Principal");
    Button flecha_derecha1 = new Button(">");
    Button flecha_izquierda1 = new Button("<");
    HBox menu_resoluciones = new HBox();
    Label labelResolucion = new Label("Resolución");
    HBox texto_resoluciones= new HBox();
    Button flecha_derecha2 = new Button(">");
    Button flecha_izquierda2 = new Button("<");
    Label labelRondas = new Label("Rondas");
    HBox texto_rondas= new HBox();
    HBox menu_rondas = new HBox();
    Button flecha_derecha3 = new Button(">");
    Button flecha_izquierda3 = new Button("<");
    HBox menu_jugadores = new HBox();
    Label labelJugadores = new Label("Jugadores");
    HBox texto_jugadores= new HBox();
    Button flecha_derecha5 = new Button(">");
    Button flecha_izquierda5 = new Button("<");
    HBox menu_cantidad = new HBox();
    Label labelIA = new Label("Jug. Artificiales");
    HBox texto_IA= new HBox();
    
    Label labelEntorno = new Label("Efectos de Entorno");
    
    Label labelGravedad = new Label("Gravedad");  
    HBox menu_gravedad = new HBox();
    HBox texto_gravedad= new HBox(); 
    Button flecha_derecha4 = new Button(">");
    Button flecha_izquierda4 = new Button("<");
    
    Label labelViento = new Label("Viento");  
    HBox menu_viento = new HBox();
    HBox texto_viento= new HBox(); 
    Button flecha_derecha6 = new Button(">");
    Button flecha_izquierda6 = new Button("<");
    Pane paneOpciones;
    Button volverMenu;    
    
    Button[] botonesFlecha = { flecha_derecha1, flecha_izquierda1, flecha_derecha2, flecha_izquierda2,
        flecha_derecha3, flecha_izquierda3, flecha_derecha4, flecha_izquierda4,
        flecha_derecha5, flecha_izquierda5, flecha_derecha6, flecha_izquierda6 };

    public MenuOpciones() {
        
    }  
    
    public void start(Stage stage, ListaJugadores list,Pane panel){
        
        paneOpciones=escenaOpciones();
        Globales.escena.setRoot(paneOpciones);
        paneOpciones.setPrefSize(Globales.alto_resolucion,Globales.ancho_resolucion);
   
        volverMenu.setOnAction(e -> {
            Musica.sonido_click();
            Globales.escena.setRoot(panel);
            Globales.cambiarEscena(Globales.escena);         
        }); 
        stage.show();      
    }
    
    public Pane escenaOpciones(){
        Pane panel = new Pane();
        //Image icono = new Image(getClass().getResourceAsStream("./img/icono opciones.jpg"));                 
     
        imageView.setPreserveRatio(false); 
        panel.getChildren().add(imageView);    
              
        //VOLVER MENU PRINCIPAAL    
        volver.setStyle(estilo_botones);
         
        //////////////////////////////////////////////////////////////////7
        //RESOLUCION     
        opcion_resolucion = new Button(resolucion[opcionActualResolucion]);   
        opcion_resolucion.setStyle(estilo_botones);        
        flecha_derecha1.setStyle(estilo_botones);
        flecha_izquierda1.setStyle(estilo_botones);
        flecha_izquierda1.setDisable(true);
                
        flecha_derecha1.setOnAction(e -> {
            Musica.sonido_click();
            flecha_izquierda1.setDisable(false);
            Globales.resolucion_def=cambiarOpcion(1,resolucion,opcion_resolucion,Globales.resolucion_def,1);
            System.out.println("Actual = "+Globales.resolucion_def);
            if(opcionActualResolucion==0){              
                Globales.alto_resolucion=800;
                Globales.ancho_resolucion=800;
            }
            if(opcionActualResolucion==1){              
                Globales.alto_resolucion=900;
                Globales.ancho_resolucion=900;
            }
            if(opcionActualResolucion==2){
                Globales.stage.setX(0);
                Globales.stage.setY(0);
                Globales.alto_resolucion=1920;
                Globales.ancho_resolucion=1080;
            }
            Globales.cambiarResolucion(Globales.alto_resolucion,Globales.ancho_resolucion);
            if(opcionActualResolucion==resolucion.length-1){
                flecha_derecha1.setDisable(true);
            }
        });
        
        flecha_izquierda1.setOnAction(e -> {
            Musica.sonido_click();
            flecha_derecha1.setDisable(false);
            Globales.resolucion_def=cambiarOpcion(-1,resolucion,opcion_resolucion,Globales.resolucion_def,1);
            System.out.println("Actual = "+Globales.resolucion_def);
            if(opcionActualResolucion==0){
                Globales.alto_resolucion=800;
                Globales.ancho_resolucion=800;
            }
            if(opcionActualResolucion==1){
                Globales.alto_resolucion=900;
                Globales.ancho_resolucion=900;
            }
            if(opcionActualResolucion==2){
                Globales.alto_resolucion=1920;
                Globales.ancho_resolucion=1080;
            }
            Globales.cambiarResolucion(Globales.alto_resolucion,Globales.ancho_resolucion);
            if(opcionActualResolucion==0){
                flecha_izquierda1.setDisable(true);
            }
            
        });
               
        menu_resoluciones.getChildren().addAll(flecha_izquierda1,opcion_resolucion, flecha_derecha1);    
        labelResolucion.setStyle("-fx-text-fill: white;");    
        texto_resoluciones.getChildren().add(labelResolucion);            
        /////////////////////////////////////////////////
        ///RESOLUCION
       
        /////////////////////////////////////////////////////
        //RONDAS
        opcion_rondas = new Button(rondas[opcionActualRondas]);       
        opcion_rondas.setStyle(estilo_botones);
        
        flecha_derecha2.setStyle(estilo_botones);
        flecha_derecha2.setOnAction(e -> {
            Musica.sonido_click();
            Globales.rondas_def=cambiarOpcion(1,rondas,opcion_rondas,Globales.rondas_def,2);
            System.out.println("Actual = "+Globales.rondas_def);
        });

        flecha_izquierda2.setStyle(estilo_botones);
        flecha_izquierda2.setOnAction(e -> {
            Musica.sonido_click();
            Globales.rondas_def=cambiarOpcion(-1,rondas,opcion_rondas,Globales.rondas_def,2);
            System.out.println("Actual = "+Globales.rondas_def);
        });
               
        menu_rondas.getChildren().addAll(flecha_izquierda2,opcion_rondas, flecha_derecha2);
        labelRondas.setStyle("-fx-text-fill: white;");     
        texto_rondas.getChildren().add(labelRondas);
           
        /////////////////////////////////////////////////////
        //RONDAS
        
        
        /////////////////////////////////////////////////////
        //JUGADORES
        opcion_jugadores = new Button(jugadores[opcionActualJugadores]);       
        opcion_jugadores.setStyle(estilo_botones);

        flecha_derecha3.setStyle(estilo_botones);
        flecha_derecha3.setOnAction(e -> {
            Musica.sonido_click();
            flecha_derecha4.setDisable(false); 
            Globales.jugadores_def=cambiarOpcion(1,jugadores,opcion_jugadores,Globales.jugadores_def,3);
            System.out.println("Actual = "+Globales.jugadores_def);
            
        });

        flecha_izquierda3.setStyle(estilo_botones);
        flecha_izquierda3.setOnAction(e -> {
            Musica.sonido_click();
            flecha_izquierda5.setDisable(false);
            Globales.jugadores_def=cambiarOpcion(-1,jugadores,opcion_jugadores,Globales.jugadores_def,3);
            System.out.println("Actual = "+Globales.jugadores_def);
            if(Globales.jugadores_def<Globales.cantidad_def){
                Globales.cantidad_def=cambiarOpcion(-1,cantidad,opcion_cantidad,Globales.cantidad_def,4);
            }
        });
                
        menu_jugadores.getChildren().addAll(flecha_izquierda3,opcion_jugadores, flecha_derecha3);
        labelJugadores.setStyle("-fx-text-fill: white;");          
        texto_jugadores.getChildren().add(labelJugadores);        
        /////////////////////////////////////////////////////
        //JUGADORES
        
        
        ////////////////////////////////////////////
        //CANTIDAD IA´S
        opcion_cantidad = new Button(cantidad[opcionActualCantidad]);        
        opcion_cantidad.setStyle(estilo_botones);
      
        flecha_derecha4.setStyle(estilo_botones);
        flecha_derecha4.setOnAction(e -> { 
            Musica.sonido_click();
            Globales.cantidad_def=cambiarOpcion(1,cantidad,opcion_cantidad,Globales.cantidad_def,4);
            flecha_izquierda5.setDisable(false);
            System.out.println("Actual = "+Globales.cantidad_def); 
            if(Globales.cantidad_def>=Globales.jugadores_def){
                flecha_derecha4.setDisable(true);           
            }
            if(opcionActualCantidad>cantidad.length-1){
                flecha_derecha4.setDisable(true);   
            }
            
            
        });
        
        flecha_izquierda4.setStyle(estilo_botones);
        flecha_izquierda4.setOnAction(e -> {   
            Musica.sonido_click();
            Globales.cantidad_def=cambiarOpcion(-1,cantidad,opcion_cantidad,Globales.cantidad_def,4);         
            System.out.println("Actual = "+Globales.cantidad_def);         
            flecha_derecha4.setDisable(false);
            if(opcionActualCantidad==0){
                flecha_izquierda4.setDisable(true);   
            }
        });
                    
        menu_cantidad.getChildren().addAll(flecha_izquierda4,opcion_cantidad, flecha_derecha4);        
        labelIA.setStyle("-fx-text-fill: white;");       
        texto_IA.getChildren().add(labelIA);
        
        
        labelEntorno.setStyle("-fx-text-fill: white;"); 
        /////////////////////////////////////////
        //GRAVEDAD
        opcion_gravedad = new Button(gravedad[opcionActualGravedad]);      
        opcion_gravedad.setStyle(estilo_botones);
                 
        flecha_derecha5.setStyle(estilo_botones);
        flecha_derecha5.setOnAction(e -> {
            Musica.sonido_click();
            Globales.gravedad_def=cambiarOpcion(1,gravedad,opcion_gravedad,Globales.gravedad_def,5);
            System.out.println("Actual = "+Globales.gravedad_def);    
        });
              
        flecha_izquierda5.setStyle(estilo_botones);
        flecha_izquierda5.setOnAction(e -> {
            Musica.sonido_click();
            Globales.gravedad_def=cambiarOpcion(-1,gravedad,opcion_gravedad,Globales.gravedad_def,5);
            System.out.println("Actual = "+Globales.gravedad_def);      
        });
        
        labelGravedad.setStyle("-fx-text-fill: white;");
        menu_gravedad.getChildren().addAll(flecha_izquierda5,opcion_gravedad, flecha_derecha5);                   
        texto_gravedad.getChildren().add(labelGravedad);     
        ///////////////////////////////////////////////
        //GRAVEDAD
        
        
        
        //VIENTO
        opcion_viento = new Button(viento[opcionActualViento]);      
        opcion_viento.setStyle(estilo_botones);
                 
        flecha_derecha6.setStyle(estilo_botones);
        flecha_derecha6.setOnAction(e -> {
            Musica.sonido_click();
            Globales.viento_def=cambiarOpcion(1,viento,opcion_viento,Globales.viento_def,6);
            System.out.println("Actual = "+Globales.viento_def);    
        });
              
        flecha_izquierda6.setStyle(estilo_botones);
        flecha_izquierda6.setOnAction(e -> {
            Musica.sonido_click();
            Globales.viento_def=cambiarOpcion(-1,viento,opcion_viento,Globales.viento_def,6);
            System.out.println("Actual = "+Globales.viento_def);      
        });
        
        labelViento.setStyle("-fx-text-fill: white;");
        menu_viento.getChildren().addAll(flecha_izquierda6,opcion_viento, flecha_derecha6);                   
        texto_viento.getChildren().add(labelViento);     
        ///////////////////////////////////////////////
        //VIENTO
        
        for (Button flecha : botonesFlecha) {
            flecha.setMaxHeight(150);
        }
        
        
        
        panel.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double widthRatio = newWidth.doubleValue() / 800; 
            imageView.setFitWidth(800*widthRatio);
            volver.setPrefWidth(245*widthRatio);
            volver.setLayoutX(275*widthRatio);
            opcion_resolucion.setPrefWidth(140*widthRatio);
            menu_resoluciones.setLayoutX(275*widthRatio);
            texto_resoluciones.setLayoutX(352*widthRatio);
            flecha_izquierda1.setPrefWidth(52*widthRatio);
            flecha_derecha1.setPrefWidth(52*widthRatio);
            
            opcion_rondas.setPrefWidth(140*widthRatio);
            menu_rondas.setLayoutX(275*widthRatio);
            texto_rondas.setLayoutX(366*widthRatio);
            flecha_izquierda2.setPrefWidth(52*widthRatio);
            flecha_derecha2.setPrefWidth(52*widthRatio);
            
            opcion_jugadores.setPrefWidth(140*widthRatio);
            menu_jugadores.setLayoutX(275*widthRatio);
            texto_jugadores.setLayoutX(357*widthRatio);
            flecha_izquierda3.setPrefWidth(52*widthRatio);
            flecha_derecha3.setPrefWidth(52*widthRatio);
           
            opcion_cantidad.setPrefWidth(140*widthRatio);
            menu_cantidad.setLayoutX(275*widthRatio);
            texto_IA.setLayoutX(338*widthRatio);
            flecha_izquierda4.setPrefWidth(52*widthRatio);
            flecha_derecha4.setPrefWidth(52*widthRatio);
            
            opcion_gravedad.setPrefWidth(140*widthRatio);
            menu_gravedad.setLayoutX(275*widthRatio);
            texto_gravedad.setLayoutX(355*widthRatio);
            flecha_izquierda5.setPrefWidth(52*widthRatio);
            flecha_derecha5.setPrefWidth(52*widthRatio);
            
            opcion_viento.setPrefWidth(140*widthRatio);
            menu_viento.setLayoutX(275*widthRatio);
            texto_viento.setLayoutX(370*widthRatio);
            flecha_izquierda6.setPrefWidth(52*widthRatio);
            flecha_derecha6.setPrefWidth(52*widthRatio);
            
            Font font = Font.font("Serif", FontWeight.NORMAL, 20*widthRatio);
            
            
            volver.setFont(font);
            opcion_resolucion.setFont(font);    
            labelResolucion.setFont(font);
            opcion_rondas.setFont(font);
            labelRondas.setFont(font); 
            opcion_jugadores.setFont(font);
            labelJugadores.setFont(font);
            opcion_cantidad.setFont(font);
            labelIA.setFont(font);
            labelEntorno.setFont(font);
            opcion_gravedad.setFont(font);
            labelGravedad.setFont(font);
            opcion_viento.setFont(font);
            labelViento.setFont(font);
            
            
        });
         
        panel.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            double heightRatio = newHeight.doubleValue() / 800; 
            
            imageView.setFitHeight(800* heightRatio);
            volver.setPrefHeight(50*heightRatio);
            volver.setLayoutY(700*heightRatio);
            
            opcion_resolucion.setPrefHeight(52*heightRatio);
            menu_resoluciones.setLayoutY(70*heightRatio);
            texto_resoluciones.setLayoutY(30*heightRatio);
            flecha_izquierda1.setPrefHeight(52*heightRatio);
            flecha_derecha1.setPrefHeight(52*heightRatio);
            
            opcion_rondas.setPrefHeight(52*heightRatio);
            menu_rondas.setLayoutY(180*heightRatio);
            texto_rondas.setLayoutY(140*heightRatio);
            flecha_izquierda2.setPrefHeight(52*heightRatio);
            flecha_derecha2.setPrefHeight(52*heightRatio);
            
            opcion_jugadores.setPrefHeight(52*heightRatio);
            menu_jugadores.setLayoutY(290*heightRatio);
            texto_jugadores.setLayoutY(250*heightRatio);
            flecha_izquierda3.setPrefHeight(52*heightRatio);
            flecha_derecha3.setPrefHeight(52*heightRatio);
            
            opcion_cantidad.setPrefHeight(52*heightRatio);
            menu_cantidad.setLayoutY(400*heightRatio);
            texto_IA.setLayoutY(360*heightRatio);
            flecha_izquierda4.setPrefHeight(52*heightRatio);
            flecha_derecha4.setPrefHeight(52*heightRatio);
            
            opcion_gravedad.setPrefHeight(52*heightRatio);
            menu_gravedad.setLayoutY(510*heightRatio);
            texto_gravedad.setLayoutY(470*heightRatio);
            flecha_izquierda5.setPrefHeight(52*heightRatio);
            flecha_derecha5.setPrefHeight(52*heightRatio);
            
            opcion_viento.setPrefHeight(52*heightRatio);
            menu_viento.setLayoutY(620*heightRatio);
            texto_viento.setLayoutY(580*heightRatio);
            flecha_izquierda6.setPrefHeight(52*heightRatio);
            flecha_derecha6.setPrefHeight(52*heightRatio);
            
           
            
        });
        
        this.volverMenu=volver;
        panel.getChildren().addAll(menu_resoluciones,texto_resoluciones,menu_rondas,
        texto_rondas,menu_jugadores,texto_jugadores,menu_gravedad,texto_gravedad,
        menu_cantidad,texto_IA,menu_viento,texto_viento,volver);                 
        return panel;
    }
    
    private int cambiarOpcion(int desplazamiento, String[] tipo, Button opcion, int var_opcion, int referencia) {
        int opcionActual=0;
        if(referencia==1){
            opcionActualResolucion = (opcionActualResolucion + desplazamiento) % tipo.length;
            if(opcionActualResolucion<0){
                opcionActualResolucion=0;
            }
            opcionActual=opcionActualResolucion;
        }
        if(referencia==2){
            opcionActualRondas = (opcionActualRondas + desplazamiento) % tipo.length;
            if(opcionActualRondas<0){
                opcionActualRondas=0;
            }
            opcionActual=opcionActualRondas;
        }
        if(referencia==3){
            opcionActualJugadores = (opcionActualJugadores + desplazamiento) % tipo.length;
            if(opcionActualJugadores<0){
                opcionActualJugadores=0;
            }
            opcionActual=opcionActualJugadores;
        }
        if(referencia==4){
            opcionActualCantidad = (opcionActualCantidad + desplazamiento) % tipo.length;
            if(opcionActualCantidad<0){
                opcionActualCantidad=0;
            }
            opcionActual=opcionActualCantidad;
        }
        if(referencia==5){
            opcionActualGravedad = (opcionActualGravedad + desplazamiento) % tipo.length;
            if(opcionActualGravedad<0){
                opcionActualGravedad=0;
            }
            opcionActual=opcionActualGravedad;
        }
        
        if(referencia==6){
            opcionActualViento = (opcionActualViento + desplazamiento) % tipo.length;
            if(opcionActualViento<0){
                opcionActualViento=0;
            }
            opcionActual=opcionActualViento;
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
                    if(opcion_str.equals("800x800")||opcion_str.equals("Normal")
                            ||opcion_str.equals("-NO-")){
                        opcion=0;
                    }
                    if(opcion_str.equals("900x900")||opcion_str.equals("Gravedad")
                            ||opcion_str.equals("-SI-")||opcion_str.equals("15,81")){
                        opcion=1;
                    }
                    if(opcion_str.equals("1920x1080")||opcion_str.equals("5,81")){
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
    
    private void maximo(int opcionActual, String[] eleccion,Button flecha){
        if(opcionActual==eleccion.length-1){
            flecha.setDisable(true);
        }
    }
}
