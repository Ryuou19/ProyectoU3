package terreno;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Musica {
    
    public static MediaPlayer player;

    public Musica(){

    }
    public static void sonido_click(){
        final String click = "src/terreno/music/click.mp3";
        File archivo_click = new File(click);
        Media music_mp3 =new Media(archivo_click.toURI().toString());
        MediaPlayer Player_music = new MediaPlayer(music_mp3);
        Player_music.play();
        
 
    }
    public static void sonido_compra(){
        final String click = "src/terreno/music/dinero.mp3";
        File archivo_click = new File(click);
        Media music_mp3 =new Media(archivo_click.toURI().toString());
        MediaPlayer Player_music = new MediaPlayer(music_mp3);
        Player_music.play();
        
 
    }
    public static void agregar_musica(){
        final String music ="src/terreno/music/musica.mp3";
        File archivo_music = new File(music);
        Media musicMp3 =new Media(archivo_music.toURI().toString());
     
        
         detenerMusica();
        
        player = new MediaPlayer(musicMp3);
        player.play();
    }

    public static void agregar_musica_terreno(){
        final String music ="src/terreno/music/Gow.mp3";
        File archivo_music = new File(music);
        Media musicMp3 =new Media(archivo_music.toURI().toString());
        
        
         detenerMusica();
        
        player = new MediaPlayer(musicMp3);
         player.setVolume(0.1);
        player.play();
        
        
    }
    
     public static void sonido_disparo(){
        final String music ="src/terreno/music/disparo.mp3";
        File archivo_music = new File(music);
        Media music_mp3 =new Media(archivo_music.toURI().toString());
        MediaPlayer Player_music = new MediaPlayer(music_mp3);
        Player_music.play();
     }
        
       public static void sonido_colision(){
        final String music ="src/terreno/music/colision.mp3";
        File archivo_music = new File(music);
        Media music_mp3 =new Media(archivo_music.toURI().toString());
        MediaPlayer Player_music = new MediaPlayer(music_mp3);
        Player_music.play();
    }
         public static void agregar_musica_tienda(){
            final String music ="src/terreno/music/tienda.mp3";
            File archivo_music = new File(music);
            Media musicMp3 =new Media(archivo_music.toURI().toString());


             detenerMusica();

            player = new MediaPlayer(musicMp3);
             player.setVolume(0.1);
            player.play();
        
        
    }
        public static void detenerMusica() {
        if (player != null) {
            player.stop();
        }
    }
    

}
