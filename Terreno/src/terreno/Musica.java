package terreno;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Musica {

    public Musica(){

    }
    public static void sonido_click(){
        final String click = "src/terreno/music/click.mp3";
        File archivo_click = new File(click);
        Media click_mp3 = new Media(archivo_click.toURI().toString());
        MediaPlayer Player_click = new MediaPlayer(click_mp3);
        Player_click.play();
    }
    public static void agregar_musica(){
        final String music ="src/terreno/music/musica.mp3";
        File archivo_music = new File(music);
        Media music_mp3 =new Media(archivo_music.toURI().toString());
        MediaPlayer Player_music = new MediaPlayer(music_mp3);
        Player_music.play();
    }

    public static void agregar_musica_terreno(){
        final String music ="src/terreno/music/music_terreno.mp3";
        File archivo_music = new File(music);
        Media music_mp3 =new Media(archivo_music.toURI().toString());
        MediaPlayer Player_music = new MediaPlayer(music_mp3);
        Player_music.play();
    }

}
