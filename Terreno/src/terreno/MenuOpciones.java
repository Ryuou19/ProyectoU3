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
   
    private final String[] resolucion = {"800x800", "900x900", "1920x1080"};//opciones de resolucion
    private final String[] jugadores = {"2", "3","4","5","6"}; //opciones de jugadores
    private final String[] rondas = {"1","2","3","4","5","6","7","8",
    "9","10","11","12","13","14",
    "15","16","17","18","19","20"};//opciones de rondas
    private final String[] gravedad={"Normal","15,81","5,81"};//opciones de gravedad
    private final String[] cantidad = {"0","1", "2","3","4","5","6"};//opciones de bots
    private final String[] viento={"-NO-","-SI-"};//opciones de viento
    private int opcionActualResolucion = 0;//indice actual de resolucion
    private int opcionActualJugadores = 0;//indice actual de jugadores
    private int opcionActualRondas = 0;//indice actual de rondas
    private int opcionActualGravedad = 0;//indice actual de gravedad
    private int opcionActualCantidad = 0;//indice actual de bots
    private int opcionActualViento = 0;//indice actual de viento
    private Button opcion_resolucion = new Button(resolucion[opcionActualResolucion]);//boton que muestra el valor de resolucion 
    private Button opcion_jugadores = new Button(jugadores[opcionActualJugadores]);//boton que muestra el valor de jugadores
    private Button opcion_rondas = new Button(rondas[opcionActualRondas]);//boton que muestra el valor de rondas
    private Button opcion_gravedad = new Button(gravedad[opcionActualGravedad]);//boton que muestra el valor de gravedad
    private Button opcion_cantidad = new Button(cantidad[opcionActualCantidad]);//boton que muestra el valor de bots
    private Button opcion_viento = new Button(viento[opcionActualViento]);//boton que muestra el valor de viento
    String estilo_botones = //estilo para todos los botones
    "-fx-background-color: #000000; " + 
    "-fx-text-fill: #FFFFFF;" + 
    "-fx-border-color: #FF0000;" + 
    "-fx-border-width: 3px;" +  
    "-fx-background-radius: 0;";
    
    Image fondo = new Image(getClass().getResourceAsStream("./img/fondo opciones.jpg"));//imagen de fondo 
    ImageView imageView = new ImageView(fondo);//view de fondo
    Button volver = new Button("Menu Principal");//boton para volver
    
    Button flecha_derecha1 = new Button(">");//flecha derecha de resolucion
    Button flecha_izquierda1 = new Button("<");//flecha izquierda de resolucion
    HBox menu_resoluciones = new HBox();//box de resolucion
    Label labelResolucion = new Label("Resolución");//texto 
    HBox texto_resoluciones= new HBox();//box para texto
    
    Button flecha_derecha2 = new Button(">");//flecha derecha de rondas
    Button flecha_izquierda2 = new Button("<");//flecha izquierda de rondas
    Label labelRondas = new Label("Rondas");//texto
    HBox texto_rondas= new HBox();//box para texto
    HBox menu_rondas = new HBox();//box de rondas
    
    Button flecha_derecha3 = new Button(">");//flecha derecha de jugadores
    Button flecha_izquierda3 = new Button("<");//flecha izquierda de jugadores
    HBox menu_jugadores = new HBox();//box de jugadores
    Label labelJugadores = new Label("Jugadores");//texto
    HBox texto_jugadores= new HBox();//box para texto
    
    Button flecha_derecha5 = new Button(">");//flecha derecha de bots
    Button flecha_izquierda5 = new Button("<");//flecha izquierda de bots
    HBox menu_cantidad = new HBox();//box de bots
    Label labelIA = new Label("Jug. Artificiales");//texto
    HBox texto_IA= new HBox();//box para texto
     
    Label labelGravedad = new Label("Gravedad");//texto
    HBox menu_gravedad = new HBox();//box de gravedad
    HBox texto_gravedad= new HBox(); //box para texto
    Button flecha_derecha4 = new Button(">");//flecha derecha de gravedad
    Button flecha_izquierda4 = new Button("<");//flecha izquierda de gravedad
    
    Label labelViento = new Label("Viento");//texto
    HBox menu_viento = new HBox();//box de viento
    HBox texto_viento= new HBox();//box para texto
    Button flecha_derecha6 = new Button(">");//flecha derecha de viento
    Button flecha_izquierda6 = new Button("<");//flecha izquierda de gravedad
    
    Pane paneOpciones;//panel
    Button volverMenu;//boton para volver
    
    //lista para botones
    Button[] botonesFlecha = { flecha_derecha1, flecha_izquierda1, flecha_derecha2, flecha_izquierda2,
        flecha_derecha3, flecha_izquierda3, flecha_derecha4, flecha_izquierda4,
        flecha_derecha5, flecha_izquierda5, flecha_derecha6, flecha_izquierda6 };
   
    //lista para las opciones
    Button[] botonesOpciones={opcion_resolucion,opcion_jugadores,opcion_rondas,
        opcion_gravedad,opcion_cantidad,opcion_viento};
    
    //lista para label
    Label[] textos={labelResolucion,labelRondas,labelJugadores,labelIA,labelGravedad,labelViento};
    
    public MenuOpciones() {
        
    }  
    
    public void start(Stage stage, ListaJugadores list,Pane panel){
        
        paneOpciones=escenaOpciones();//guardar panel creado en el metodo
        Globales.escena.setRoot(paneOpciones);//definir panel actual
        paneOpciones.setPrefSize(Globales.alto_resolucion,Globales.ancho_resolucion);//definir dimension del panel
        
        //si se decide salir del menu de opciones
        volverMenu.setOnAction(e -> {
            Musica.sonido_click();//sonido
            //se vuelve al menu principal
            Globales.escena.setRoot(panel);
            Globales.cambiarEscena(Globales.escena);         
        }); 
        stage.show();      
    }
    
    public Pane escenaOpciones(){
        Pane panel = new Pane();                
        
        //IMAGEN DE FONDO
        imageView.setPreserveRatio(false); 
        panel.getChildren().add(imageView);    
              
        //VOLVER MENU PRINCIPAAL    
        volver.setStyle(estilo_botones);
         
        //BLOQUEAR TODAS LAS FLECHAS DE LA IZQUIERDA
        flecha_izquierda1.setDisable(true);
        flecha_izquierda2.setDisable(true);
        flecha_izquierda3.setDisable(true);
        flecha_izquierda4.setDisable(true);
        flecha_izquierda5.setDisable(true);
        flecha_izquierda6.setDisable(true);
        
        //DEFINIR ESTILO DE TODAS LAS FLECHAS
        for (Button flecha : botonesFlecha) {
            flecha.setStyle(estilo_botones);
            flecha.setMaxHeight(150);
        }
        
        //DEFINIR ESTILO DE TODAS LAS OPCIONES
        for (Button opcion : botonesOpciones) {
            opcion.setStyle(estilo_botones);
        }
        
        //DEFINIR ESTILO DE TODOS LOS LABEL
        for (Label text : textos) {
            text.setStyle("-fx-text-fill: white;");
        }
        
        /////////////////////////////////////////////////////////
        //RESOLUCION     
        /////////////////////////////////////////////////////////                         
        flecha_derecha1.setOnAction(e -> {
            Musica.sonido_click();//sonido
            flecha_izquierda1.setDisable(false);
            //se defina la opcion en globales por el cambio de la flecha
            Globales.resolucion_def=cambiarOpcion(1,resolucion,opcion_resolucion,Globales.resolucion_def,1);
            //se cambia la resolucion
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
            Musica.sonido_click();//
            flecha_derecha1.setDisable(false);
            //se defina la opcion en globales por el cambio de la flecha
            Globales.resolucion_def=cambiarOpcion(-1,resolucion,opcion_resolucion,Globales.resolucion_def,1);
            //se ajusta la resolucion
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
        texto_resoluciones.getChildren().add(labelResolucion);            
        /////////////////////////////////////////////////
        ///RESOLUCION
        /////////////////////////////////////////////////
        
        /////////////////////////////////////////////////////
        //RONDAS
        ////////////////////////////////////////////////////          
        flecha_derecha2.setOnAction(e -> {
            flecha_izquierda2.setDisable(false);
            Musica.sonido_click();//sonido
            //se defina la opcion en globales por el cambio de la flecha
            Globales.rondas_def=cambiarOpcion(1,rondas,opcion_rondas,Globales.rondas_def,2);
            if(opcionActualRondas==rondas.length-1){
                flecha_derecha2.setDisable(true);
            }
        });
        
        flecha_izquierda2.setOnAction(e -> {
            flecha_derecha2.setDisable(false);
            Musica.sonido_click();//sonido
            //se defina la opcion en globales por el cambio de la flecha
            Globales.rondas_def=cambiarOpcion(-1,rondas,opcion_rondas,Globales.rondas_def,2);
            if(opcionActualRondas==0){
                flecha_izquierda2.setDisable(true);
            }
        });
             
        menu_rondas.getChildren().addAll(flecha_izquierda2,opcion_rondas, flecha_derecha2);    
        texto_rondas.getChildren().add(labelRondas);
           
        /////////////////////////////////////////////////////
        //RONDAS
        /////////////////////////////////////////////////////
        
        
        /////////////////////////////////////////////////////
        //JUGADORES
        /////////////////////////////////////////////////////     
             
        flecha_derecha3.setOnAction(e -> {
            flecha_izquierda3.setDisable(false);
            Musica.sonido_click();//sonido
            flecha_derecha4.setDisable(false); 
            //se defina la opcion en globales por el cambio de la flecha
            Globales.jugadores_def=cambiarOpcion(1,jugadores,opcion_jugadores,Globales.jugadores_def,3);
            if(opcionActualJugadores==jugadores.length-1){
                flecha_derecha3.setDisable(true);
            }
        });
      
        flecha_izquierda3.setOnAction(e -> {
            flecha_derecha3.setDisable(false);
            Musica.sonido_click();//sonido
            //se defina la opcion en globales por el cambio de la flecha
            Globales.jugadores_def=cambiarOpcion(-1,jugadores,opcion_jugadores,Globales.jugadores_def,3);;
            if(Globales.jugadores_def<Globales.cantidad_def){
                Globales.cantidad_def=cambiarOpcion(-1,cantidad,opcion_cantidad,Globales.cantidad_def,4);
            }
            if(opcionActualJugadores==0){
                flecha_izquierda3.setDisable(true);
            }
        });
                
        menu_jugadores.getChildren().addAll(flecha_izquierda3,opcion_jugadores, flecha_derecha3);         
        texto_jugadores.getChildren().add(labelJugadores);        
        /////////////////////////////////////////////////////
        //JUGADORES
        /////////////////////////////////////////////////////
        
        
        //////////////////////////////////////////////
        //CANTIDAD IA´S
        //////////////////////////////////////////////  
        
        flecha_derecha4.setOnAction(e -> { 
            flecha_izquierda4.setDisable(false);
            Musica.sonido_click();//sonido
            //se defina la opcion en globales por el cambio de la flecha
            Globales.cantidad_def=cambiarOpcion(1,cantidad,opcion_cantidad,Globales.cantidad_def,4); 
            if(Globales.cantidad_def>=Globales.jugadores_def){
                flecha_derecha4.setDisable(true);           
            }
            if(opcionActualCantidad>cantidad.length-1){
                flecha_derecha4.setDisable(true);   
            }
            if(opcionActualCantidad==cantidad.length-1){
                flecha_derecha4.setDisable(true);
            }          
        });
               
        flecha_izquierda4.setOnAction(e -> {   
            flecha_derecha4.setDisable(false);
            Musica.sonido_click();//sonido
            //se defina la opcion en globales por el cambio de la flecha
            Globales.cantidad_def=cambiarOpcion(-1,cantidad,opcion_cantidad,Globales.cantidad_def,4);              
            flecha_derecha4.setDisable(false);
            if(opcionActualCantidad==0){
                flecha_izquierda4.setDisable(true);   
            }
            if(opcionActualCantidad==0){
                flecha_izquierda4.setDisable(true);
            }
        });
                    
        menu_cantidad.getChildren().addAll(flecha_izquierda4,opcion_cantidad, flecha_derecha4);         
        texto_IA.getChildren().add(labelIA);
                
        ///////////////////////////////////////////
        //GRAVEDAD
        //////////////////////////////////////////
        
        flecha_derecha5.setOnAction(e -> {
            flecha_izquierda5.setDisable(false);
            Musica.sonido_click();//sonido
            //se defina la opcion en globales por el cambio de la flecha
            Globales.gravedad_def=cambiarOpcion(1,gravedad,opcion_gravedad,Globales.gravedad_def,5);  
            if(opcionActualGravedad==gravedad.length-1){
                flecha_derecha5.setDisable(true);
            }
        });
                    
        flecha_izquierda5.setOnAction(e -> {
            flecha_derecha5.setDisable(false);
            Musica.sonido_click();//sonido
            //se defina la opcion en globales por el cambio de la flecha
            Globales.gravedad_def=cambiarOpcion(-1,gravedad,opcion_gravedad,Globales.gravedad_def,5);  
            if(opcionActualGravedad==0){
                flecha_izquierda5.setDisable(true);
            }
        });
        
        menu_gravedad.getChildren().addAll(flecha_izquierda5,opcion_gravedad, flecha_derecha5);                   
        texto_gravedad.getChildren().add(labelGravedad); 
        
        ///////////////////////////////////////////////
        //GRAVEDAD
        ///////////////////////////////////////////////
        
        
        ///////////////////////////////////////////////
        //VIENTO
        ///////////////////////////////////////////////
              
        flecha_derecha6.setOnAction(e -> {
            flecha_izquierda6.setDisable(false);
            Musica.sonido_click();//sonido
            //se defina la opcion en globales por el cambio de la flecha
            Globales.viento_def=cambiarOpcion(1,viento,opcion_viento,Globales.viento_def,6);  
            if(opcionActualViento==viento.length-1){
                flecha_derecha6.setDisable(true);
            }
        });
                     
        flecha_izquierda6.setOnAction(e -> {
            flecha_derecha6.setDisable(false);
            Musica.sonido_click();//sonido
            //se defina la opcion en globales por el cambio de la flecha
            Globales.viento_def=cambiarOpcion(-1,viento,opcion_viento,Globales.viento_def,6);  
            if(opcionActualViento==0){
                flecha_izquierda6.setDisable(true);
            }
        });
               
        menu_viento.getChildren().addAll(flecha_izquierda6,opcion_viento, flecha_derecha6);                   
        texto_viento.getChildren().add(labelViento);     
        ///////////////////////////////////////////////
        //VIENTO
        ///////////////////////////////////////////////

        
        
        //METODO LISTENER PARA AJUSTAR NODOS A LA RESOLUCION (IGNORAR, ES PURO FXML)
        panel.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double widthRatio = newWidth.doubleValue() / 800; 
          
            for (Button flecha : botonesFlecha) {
                flecha.setPrefWidth(52*widthRatio);
            }
            
            imageView.setFitWidth(800*widthRatio);
            volver.setPrefWidth(245*widthRatio);
            volver.setLayoutX(275*widthRatio);
            opcion_resolucion.setPrefWidth(140*widthRatio);
            menu_resoluciones.setLayoutX(275*widthRatio);
            texto_resoluciones.setLayoutX(352*widthRatio);
                   
            opcion_rondas.setPrefWidth(140*widthRatio);
            menu_rondas.setLayoutX(275*widthRatio);
            texto_rondas.setLayoutX(366*widthRatio);
                       
            opcion_jugadores.setPrefWidth(140*widthRatio);
            menu_jugadores.setLayoutX(275*widthRatio);
            texto_jugadores.setLayoutX(357*widthRatio);
                      
            opcion_cantidad.setPrefWidth(140*widthRatio);
            menu_cantidad.setLayoutX(275*widthRatio);
            texto_IA.setLayoutX(338*widthRatio);
                       
            opcion_gravedad.setPrefWidth(140*widthRatio);
            menu_gravedad.setLayoutX(275*widthRatio);
            texto_gravedad.setLayoutX(355*widthRatio);
                      
            opcion_viento.setPrefWidth(140*widthRatio);
            menu_viento.setLayoutX(275*widthRatio);
            texto_viento.setLayoutX(370*widthRatio);
                      
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
            opcion_gravedad.setFont(font);
            labelGravedad.setFont(font);
            opcion_viento.setFont(font);
            labelViento.setFont(font);                    
        });
         
        //METODO LISTENER PARA AJUSTAR NODOS A LA RESOLUCION (IGNORAR, ES PURO FXML)
        panel.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            double heightRatio = newHeight.doubleValue() / 800; 
            
            for (Button flecha : botonesFlecha) {
                flecha.setPrefHeight(52*heightRatio);
            }
            
            imageView.setFitHeight(800* heightRatio);
            volver.setPrefHeight(50*heightRatio);
            volver.setLayoutY(700*heightRatio);
            
            opcion_resolucion.setPrefHeight(52*heightRatio);
            menu_resoluciones.setLayoutY(70*heightRatio);
            texto_resoluciones.setLayoutY(30*heightRatio);
                      
            opcion_rondas.setPrefHeight(52*heightRatio);
            menu_rondas.setLayoutY(180*heightRatio);
            texto_rondas.setLayoutY(140*heightRatio);
                      
            opcion_jugadores.setPrefHeight(52*heightRatio);
            menu_jugadores.setLayoutY(290*heightRatio);
            texto_jugadores.setLayoutY(250*heightRatio);          
            
            opcion_cantidad.setPrefHeight(52*heightRatio);
            menu_cantidad.setLayoutY(400*heightRatio);
            texto_IA.setLayoutY(360*heightRatio);
                      
            opcion_gravedad.setPrefHeight(52*heightRatio);
            menu_gravedad.setLayoutY(510*heightRatio);
            texto_gravedad.setLayoutY(470*heightRatio);
            
            opcion_viento.setPrefHeight(52*heightRatio);
            menu_viento.setLayoutY(620*heightRatio);
            texto_viento.setLayoutY(580*heightRatio);          
        });
        
        //se define boton para volver
        this.volverMenu=volver;
        //se agrega todo al panel
        panel.getChildren().addAll(menu_resoluciones,texto_resoluciones,menu_rondas,
        texto_rondas,menu_jugadores,texto_jugadores,menu_gravedad,texto_gravedad,
        menu_cantidad,texto_IA,menu_viento,texto_viento,volver);                 
        return panel;
    }
    
    
    //METODO QUE CAMBIA VISUALMENTE LA OPCION EN BASE A UNA REFERENCIA
    private int cambiarOpcion(int desplazamiento, String[] tipo, Button opcion, int var_opcion, int referencia) {
        int opcionActual=0;
        if(referencia==1){//REFERENCIA 1=RESOLUCION
            opcionActualResolucion = (opcionActualResolucion + desplazamiento) % tipo.length;
            if(opcionActualResolucion<0){
                opcionActualResolucion=0;
            }
            opcionActual=opcionActualResolucion;
        }
        if(referencia==2){//REFERENCIA 2=RONDAS
            opcionActualRondas = (opcionActualRondas + desplazamiento) % tipo.length;
            if(opcionActualRondas<0){
                opcionActualRondas=0;
            }
            opcionActual=opcionActualRondas;
        }
        if(referencia==3){//REFERENCIA 3=JUGADORES
            opcionActualJugadores = (opcionActualJugadores + desplazamiento) % tipo.length;
            if(opcionActualJugadores<0){
                opcionActualJugadores=0;
            }
            opcionActual=opcionActualJugadores;
        }
        if(referencia==4){//REFERENCIA 4=BOTS
            opcionActualCantidad = (opcionActualCantidad + desplazamiento) % tipo.length;
            if(opcionActualCantidad<0){
                opcionActualCantidad=0;
            }
            opcionActual=opcionActualCantidad;
        }
        if(referencia==5){//REFERENCIA 5=GRAVEDAD
            opcionActualGravedad = (opcionActualGravedad + desplazamiento) % tipo.length;
            if(opcionActualGravedad<0){
                opcionActualGravedad=0;
            }
            opcionActual=opcionActualGravedad;
        }       
        if(referencia==6){//REFERENCIA 6=VIENTO
            opcionActualViento = (opcionActualViento + desplazamiento) % tipo.length;
            if(opcionActualViento<0){
                opcionActualViento=0;
            }
            opcionActual=opcionActualViento;
        }                   
        opcion.setText(tipo[opcionActual]);//se muestra el texto
        var_opcion=opcion_def(tipo,opcionActual,var_opcion);
        return var_opcion;
    }   
    
    //METODO QUE DEFINE LA OPCION COMO UN ENTERO INDEPENDIENTE DE SU TIPO
    //PARA SER GUARDADA EN GLOBALES
    private int opcion_def(String[] tipo, int opcionActual, int opcion){
        String opcion_str;
        for (int i=0;i<tipo.length;i++){
            if(i==opcionActual){
                if(tipo[i].length()>2){
                    opcion_str=tipo[i];
                    //REVISA LAS OPCIONES QUE NO SON ENTEROS Y LOS AJUSTA AL TIPO
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
                }               
            }         
        }
        return opcion;
    }    
}
