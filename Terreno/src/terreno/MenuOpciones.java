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
    private final String[] entorno={"Ninguno","Gravedad","Viento"};
    private final String[] cantidad = {"0","1", "2","3","4","5"};
    private int opcionActualResolucion = 0;
    private int opcionActualJugadores = 0;
    private int opcionActualRondas = 0;
    private int opcionActualEntorno = 0;
    private int opcionActualCantidad = 0;
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
    Button flecha_derecha4 = new Button(">");
    Button flecha_izquierda4 = new Button("<");
    HBox menu_entorno = new HBox();
    Label labelEntorno = new Label("Efectos de Entorno");
    HBox texto_entorno= new HBox();   
    Pane paneOpciones;
    Button volverMenu;    

    public MenuOpciones() {
        
    }  
    
    public void start(Stage stage, ListaJugadores list,Pane panel){
        
        paneOpciones=escenaOpciones();
        Globales.escena.setRoot(paneOpciones);
        paneOpciones.setPrefSize(Globales.alto_resolucion,Globales.ancho_resolucion);
   
        volverMenu.setOnAction(e -> {
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
        
        flecha_derecha1.setOnAction(e -> {
            Globales.resolucion_def=cambiarOpcion(1,resolucion,opcion_resolucion,Globales.resolucion_def,1);
            System.out.println("Actual = "+Globales.resolucion_def);
            if(opcionActualResolucion==0){
                Globales.stage.setX(280);
                Globales.alto_resolucion=800;
                Globales.ancho_resolucion=800;
            }
            if(opcionActualResolucion==1){
                Globales.stage.setX(230);
                Globales.alto_resolucion=900;
                Globales.ancho_resolucion=900;
            }
            if(opcionActualResolucion==2){
                Globales.stage.setX(-20);
                Globales.alto_resolucion=1920;
                Globales.ancho_resolucion=1080;
            }
            Globales.cambiarResolucion(Globales.alto_resolucion,Globales.ancho_resolucion);
        });
        
        flecha_izquierda1.setOnAction(e -> {
            Globales.resolucion_def=cambiarOpcion(-1,resolucion,opcion_resolucion,Globales.resolucion_def,1);
            System.out.println("Actual = "+Globales.resolucion_def);
            if(opcionActualResolucion==0){
                Globales.stage.setX(280);
                Globales.alto_resolucion=800;
                Globales.ancho_resolucion=800;
            }
            if(opcionActualResolucion==1){
                Globales.stage.setX(230);
                Globales.alto_resolucion=900;
                Globales.ancho_resolucion=900;
            }
            if(opcionActualResolucion==2){
                Globales.stage.setX(-20);
                Globales.alto_resolucion=1920;
                Globales.ancho_resolucion=1080;
            }
            Globales.cambiarResolucion(Globales.alto_resolucion,Globales.ancho_resolucion);
            //inicio.ajustarResolucion(imageview,titulo);
            //ajustarResolucion(imageView);
            
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
            Globales.rondas_def=cambiarOpcion(1,rondas,opcion_rondas,Globales.rondas_def,2);
            System.out.println("Actual = "+Globales.rondas_def);
        });

        flecha_izquierda2.setStyle(estilo_botones);
        flecha_izquierda2.setOnAction(e -> {
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
            Globales.jugadores_def=cambiarOpcion(1,jugadores,opcion_jugadores,Globales.jugadores_def,3);
            System.out.println("Actual = "+Globales.jugadores_def);
        });

        flecha_izquierda3.setStyle(estilo_botones);
        flecha_izquierda3.setOnAction(e -> {
            Globales.jugadores_def=cambiarOpcion(-1,jugadores,opcion_jugadores,Globales.jugadores_def,3);
            System.out.println("Actual = "+Globales.jugadores_def);
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
      
        flecha_derecha5.setStyle(estilo_botones);
        flecha_derecha5.setOnAction(e -> {         
            Globales.cantidad_def=cambiarOpcion(1,cantidad,opcion_cantidad,Globales.cantidad_def,4);
            System.out.println("Actual = "+Globales.cantidad_def); 
        });
        
        flecha_izquierda5.setStyle(estilo_botones);
        flecha_izquierda5.setOnAction(e -> {         
            Globales.cantidad_def=cambiarOpcion(-1,cantidad,opcion_cantidad,Globales.cantidad_def,4);
            System.out.println("Actual = "+Globales.cantidad_def); 
        });
                    
        menu_cantidad.getChildren().addAll(flecha_izquierda5,opcion_cantidad, flecha_derecha5);        
        labelIA.setStyle("-fx-text-fill: white;");       
        texto_IA.getChildren().add(labelIA);
        
        
        
        /////////////////////////////////////////
        //ENTORNO
        opcion_entorno = new Button(entorno[opcionActualEntorno]);      
        opcion_entorno.setStyle(estilo_botones);
                 
        flecha_derecha4.setStyle(estilo_botones);
        flecha_derecha4.setOnAction(e -> {
            Globales.entorno_def=cambiarOpcion(1,entorno,opcion_entorno,Globales.entorno_def,5);
            System.out.println("Actual = "+Globales.entorno_def);    
        });
              
        flecha_izquierda4.setStyle(estilo_botones);
        flecha_izquierda4.setOnAction(e -> {
            Globales.entorno_def=cambiarOpcion(-1,entorno,opcion_entorno,Globales.entorno_def,5);
            System.out.println("Actual = "+Globales.entorno_def);      
        });
        
        
        menu_entorno.getChildren().addAll(flecha_izquierda4,opcion_entorno, flecha_derecha4);        
        labelEntorno.setStyle("-fx-text-fill: white;");        
        texto_entorno.getChildren().add(labelEntorno);     
        ///////////////////////////////////////////////
        //ENTORNO
        
        
        //ajustarResolucion(imageView);
        
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
            flecha_izquierda5.setPrefWidth(52*widthRatio);
            flecha_derecha5.setPrefWidth(52*widthRatio);
            
            opcion_entorno.setPrefWidth(140*widthRatio);
            menu_entorno.setLayoutX(275*widthRatio);
            texto_entorno.setLayoutX(338*widthRatio);
            flecha_izquierda4.setPrefWidth(52*widthRatio);
            flecha_derecha4.setPrefWidth(52*widthRatio);
            
        });
    
        
        panel.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            double heightRatio = newHeight.doubleValue() / 800; 
            Font font = Font.font("Serif", FontWeight.NORMAL, 19);
            
            imageView.setFitHeight(800* heightRatio);
            volver.setPrefHeight(50*heightRatio);
            volver.setLayoutY(600*heightRatio);
            
            opcion_resolucion.setPrefHeight(52*heightRatio);
            menu_resoluciones.setLayoutY(120*heightRatio);
            texto_resoluciones.setLayoutY(90*heightRatio);
            flecha_izquierda1.setPrefHeight(52*heightRatio);
            flecha_derecha1.setPrefHeight(52*heightRatio);
            
            opcion_rondas.setPrefHeight(52*heightRatio);
            menu_rondas.setLayoutY(220*heightRatio);
            texto_rondas.setLayoutY(190*heightRatio);
            flecha_izquierda2.setPrefHeight(52*heightRatio);
            flecha_derecha2.setPrefHeight(52*heightRatio);
            
            opcion_jugadores.setPrefHeight(52*heightRatio);
            menu_jugadores.setLayoutY(320*heightRatio);
            texto_jugadores.setLayoutY(290*heightRatio);
            flecha_izquierda3.setPrefHeight(52*heightRatio);
            flecha_derecha3.setPrefHeight(52*heightRatio);
            
            opcion_cantidad.setPrefHeight(52*heightRatio);
            menu_cantidad.setLayoutY(420*heightRatio);
            texto_IA.setLayoutY(390*heightRatio);
            flecha_izquierda5.setPrefHeight(52*heightRatio);
            flecha_derecha5.setPrefHeight(52*heightRatio);
            
            opcion_entorno.setPrefHeight(52*heightRatio);
            menu_entorno.setLayoutY(520*heightRatio);
            texto_entorno.setLayoutY(490*heightRatio);
            flecha_izquierda4.setPrefHeight(52*heightRatio);
            flecha_derecha4.setPrefHeight(52*heightRatio);
            
            if(opcionActualResolucion==0){
                font = Font.font("Serif", FontWeight.NORMAL, 19);
            }
            if(opcionActualResolucion==1){
                font = Font.font("Serif", FontWeight.NORMAL, 22);
            }
            if(opcionActualResolucion==2){
                font = Font.font("Serif", FontWeight.NORMAL, 45);
            }
            volver.setFont(font);
            opcion_resolucion.setFont(font);    
            labelResolucion.setFont(font);
            opcion_rondas.setFont(font);
            labelRondas.setFont(font); 
            opcion_jugadores.setFont(font);
            labelJugadores.setFont(font);
            opcion_cantidad.setFont(font);
            labelIA.setFont(font);
            opcion_entorno.setFont(font);
            labelEntorno.setFont(font);
        });
        
        this.volverMenu=volver;
        panel.getChildren().addAll(menu_resoluciones,texto_resoluciones,menu_rondas,
        texto_rondas,menu_jugadores,texto_jugadores,menu_entorno,texto_entorno,
        menu_cantidad,texto_IA,volver);                 
        return panel;
    }
    
    private int cambiarOpcion(int desplazamiento, String[] tipo, Button opcion, int var_opcion, int referencia) {
        int opcionActual=0;
        if(referencia==1){
            opcionActualResolucion = (opcionActualResolucion + desplazamiento) % tipo.length;
            if(opcionActualResolucion<0){
                opcionActualResolucion=tipo.length-1;
            }
            opcionActual=opcionActualResolucion;
        }
        if(referencia==2){
            opcionActualRondas = (opcionActualRondas + desplazamiento) % tipo.length;
            if(opcionActualRondas<0){
                opcionActualRondas=tipo.length-1;
            }
            opcionActual=opcionActualRondas;
        }
        if(referencia==3){
            opcionActualJugadores = (opcionActualJugadores + desplazamiento) % tipo.length;
            if(opcionActualJugadores<0){
                opcionActualJugadores=tipo.length-1;
            }
            opcionActual=opcionActualJugadores;
        }
        if(referencia==4){
            opcionActualCantidad = (opcionActualCantidad + desplazamiento) % tipo.length;
            if(opcionActualCantidad<0){
                opcionActualCantidad=tipo.length-1;
            }
            opcionActual=opcionActualCantidad;
        }
        if(referencia==5){
            opcionActualEntorno = (opcionActualEntorno + desplazamiento) % tipo.length;
            if(opcionActualEntorno<0){
                opcionActualEntorno=tipo.length-1;
            }
            opcionActual=opcionActualEntorno;
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
    
    public void ajustarResolucion(ImageView imagen){
        flecha_izquierda1.setMinWidth(50);
        flecha_izquierda1.setMinHeight(50);
        flecha_derecha1.setMinWidth(50);
        flecha_derecha1.setMinHeight(50);
        flecha_izquierda2.setMinWidth(50);
        flecha_izquierda2.setMinHeight(50);
        flecha_derecha2.setMinWidth(50);
        flecha_derecha2.setMinHeight(50);
        flecha_izquierda3.setMinWidth(50);
        flecha_izquierda3.setMinHeight(50);
        flecha_derecha3.setMinWidth(50);
        flecha_derecha3.setMinHeight(50);
        flecha_izquierda4.setMinWidth(50);
        flecha_izquierda4.setMinHeight(50);
        flecha_derecha4.setMinWidth(50);
        flecha_derecha4.setMinHeight(50);
        flecha_izquierda5.setMinWidth(50);
        flecha_izquierda5.setMinHeight(50);
        flecha_derecha5.setMinWidth(50);
        flecha_derecha5.setMinHeight(50);
        
        if(Globales.alto_resolucion==800){
            Font font = Font.font("Serif", FontWeight.NORMAL, 19);      
        
            
            
            
            
            
            
            opcion_jugadores.setPrefWidth(140);
            opcion_jugadores.setPrefHeight(50);
            opcion_jugadores.setFont(font);
            menu_jugadores.setLayoutX(275);
            menu_jugadores.setLayoutY(300);       
            labelJugadores.setFont(font);          
            texto_jugadores.setLayoutX(357); 
            texto_jugadores.setLayoutY(270);   
            
            flecha_izquierda3.setPrefWidth(50);           
            flecha_izquierda3.setPrefHeight(50);           
            flecha_derecha3.setPrefWidth(50);           
            flecha_derecha3.setPrefHeight(50);
            
            opcion_cantidad.setPrefWidth(140);
            opcion_cantidad.setPrefHeight(50);
            opcion_cantidad.setFont(font);
            menu_cantidad.setLayoutX(275);
            menu_cantidad.setLayoutY(390);       
            labelIA.setFont(font);
            texto_IA.setLayoutX(336); 
            texto_IA.setLayoutY(360); 
            
            flecha_izquierda4.setPrefWidth(50);           
            flecha_izquierda4.setPrefHeight(50);           
            flecha_derecha4.setPrefWidth(50);           
            flecha_derecha4.setPrefHeight(50);
            
            opcion_entorno.setPrefWidth(140);
            opcion_entorno.setPrefHeight(50);
            opcion_entorno.setFont(font);
            menu_entorno.setLayoutX(275); 
            menu_entorno.setLayoutY(480);    
            labelEntorno.setFont(font);           
            texto_entorno.setLayoutX(322); 
            texto_entorno.setLayoutY(450); 
            
            flecha_izquierda5.setPrefWidth(50);           
            flecha_izquierda5.setPrefHeight(50);           
            flecha_derecha5.setPrefWidth(50);           
            flecha_derecha5.setPrefHeight(50);
        }
        
        if(Globales.alto_resolucion==900){
            Font font = Font.font("Serif", FontWeight.NORMAL, 22);      
            imagen.setFitWidth(900);
            imagen.setFitHeight(850);
            
            volver.setLayoutX(300);
            volver.setLayoutY(560);
            volver.setPrefWidth(280);
            volver.setPrefHeight(50);
            volver.setFont(font);
            
            opcion_resolucion.setPrefWidth(180);
            opcion_resolucion.setPrefHeight(50);
            opcion_resolucion.setFont(font);
            menu_resoluciones.setLayoutX(300);
            menu_resoluciones.setLayoutY(130);
            labelResolucion.setFont(font);
            texto_resoluciones.setLayoutX(390); 
            texto_resoluciones.setLayoutY(95);
            
            flecha_izquierda1.setPrefWidth(50);           
            flecha_izquierda1.setPrefHeight(50);           
            flecha_derecha1.setPrefWidth(50);           
            flecha_derecha1.setPrefHeight(50);
            
            opcion_rondas.setPrefWidth(180);
            opcion_rondas.setPrefHeight(50);
            opcion_rondas.setFont(font);
            menu_rondas.setLayoutX(300);
            menu_rondas.setLayoutY(220);     
            labelRondas.setFont(font);            
            texto_rondas.setLayoutX(408); 
            texto_rondas.setLayoutY(190); 
            
            flecha_izquierda2.setPrefWidth(50);           
            flecha_izquierda2.setPrefHeight(50);           
            flecha_derecha2.setPrefWidth(50);           
            flecha_derecha2.setPrefHeight(50);
            
            opcion_jugadores.setPrefWidth(180);
            opcion_jugadores.setPrefHeight(50);
            opcion_jugadores.setFont(font);
            menu_jugadores.setLayoutX(300);
            menu_jugadores.setLayoutY(310);       
            labelJugadores.setFont(font);          
            texto_jugadores.setLayoutX(397); 
            texto_jugadores.setLayoutY(280);  
            
            flecha_izquierda3.setPrefWidth(50);           
            flecha_izquierda3.setPrefHeight(50);           
            flecha_derecha3.setPrefWidth(50);           
            flecha_derecha3.setPrefHeight(50);
            
            opcion_cantidad.setPrefWidth(180);
            opcion_cantidad.setPrefHeight(50);
            opcion_cantidad.setFont(font);
            menu_cantidad.setLayoutX(300);
            menu_cantidad.setLayoutY(400);       
            labelIA.setFont(font);
            texto_IA.setLayoutX(370); 
            texto_IA.setLayoutY(370); 
            
            flecha_izquierda4.setPrefWidth(50);           
            flecha_izquierda4.setPrefHeight(50);           
            flecha_derecha4.setPrefWidth(50);           
            flecha_derecha4.setPrefHeight(50);
            
            opcion_entorno.setPrefWidth(180);
            opcion_entorno.setPrefHeight(50);
            opcion_entorno.setFont(font);
            menu_entorno.setLayoutX(300); 
            menu_entorno.setLayoutY(490);    
            labelEntorno.setFont(font);           
            texto_entorno.setLayoutX(360); 
            texto_entorno.setLayoutY(460); 
            
            flecha_izquierda5.setPrefWidth(50);           
            flecha_izquierda5.setPrefHeight(50);           
            flecha_derecha5.setPrefWidth(50);           
            flecha_derecha5.setPrefHeight(50);
        }
        
        if(Globales.alto_resolucion==1920){
            //AJUSTAR VALORES DE Y PARA RESOLUCION DE PROFESOR
            Font font = Font.font("Serif", FontWeight.NORMAL, 30);      
            imagen.setFitWidth(1920);
            imagen.setFitHeight(1080);
            
            volver.setLayoutX(520);
            volver.setLayoutY(660);
            volver.setPrefWidth(350);
            volver.setPrefHeight(60);
            volver.setFont(font);
            
            opcion_resolucion.setPrefWidth(200);
            opcion_resolucion.setPrefHeight(60);
            opcion_resolucion.setFont(font);
            menu_resoluciones.setLayoutX(520);
            menu_resoluciones.setLayoutY(150);
            labelResolucion.setFont(font);
            texto_resoluciones.setLayoutX(410+210); 
            texto_resoluciones.setLayoutY(105); 
                     
            flecha_izquierda1.setPrefWidth(70);           
            flecha_izquierda1.setPrefHeight(60);           
            flecha_derecha1.setPrefWidth(70);           
            flecha_derecha1.setPrefHeight(60);
            
            opcion_rondas.setPrefWidth(200);
            opcion_rondas.setPrefHeight(60);
            opcion_rondas.setFont(font);
            menu_rondas.setLayoutX(300+220);
            menu_rondas.setLayoutY(260);     
            labelRondas.setFont(font);            
            texto_rondas.setLayoutX(425+220); 
            texto_rondas.setLayoutY(220); 
            
            
            flecha_izquierda2.setPrefWidth(70);          
            flecha_izquierda2.setPrefHeight(60);        
            flecha_derecha2.setPrefWidth(70);        
            flecha_derecha2.setPrefHeight(60);
            
            opcion_jugadores.setPrefWidth(200);
            opcion_jugadores.setPrefHeight(60);
            opcion_jugadores.setFont(font);
            menu_jugadores.setLayoutX(300+220);
            menu_jugadores.setLayoutY(370);       
            labelJugadores.setFont(font);          
            texto_jugadores.setLayoutX(415+220); 
            texto_jugadores.setLayoutY(330); 
            
            
            flecha_izquierda3.setPrefWidth(70);         
            flecha_izquierda3.setPrefHeight(60);          
            flecha_derecha3.setPrefWidth(70);         
            flecha_derecha3.setPrefHeight(60);
            
            opcion_cantidad.setPrefWidth(200);
            opcion_cantidad.setPrefHeight(60);
            opcion_cantidad.setFont(font);
            menu_cantidad.setLayoutX(300+220);
            menu_cantidad.setLayoutY(490);       
            labelIA.setFont(font);
            texto_IA.setLayoutX(385+220); 
            texto_IA.setLayoutY(440); 
            
         
            flecha_izquierda5.setPrefWidth(70);          
            flecha_izquierda5.setPrefHeight(60);                
            flecha_derecha5.setPrefWidth(60);
            flecha_derecha5.setPrefHeight(60);
            
            opcion_entorno.setPrefWidth(210);
            opcion_entorno.setPrefHeight(60);
            opcion_entorno.setFont(font);
            menu_entorno.setLayoutX(300+220); 
            menu_entorno.setLayoutY(600);    
            labelEntorno.setFont(font);           
            texto_entorno.setLayoutX(370+220); 
            texto_entorno.setLayoutY(560); 
            
            
            flecha_izquierda4.setPrefWidth(70);        
            flecha_izquierda4.setPrefHeight(60);          
            flecha_derecha4.setPrefWidth(70);  
            flecha_derecha4.setPrefHeight(60);
        }
    }   
}
