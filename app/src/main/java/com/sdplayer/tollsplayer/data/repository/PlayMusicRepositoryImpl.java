package com.sdplayer.tollsplayer.data.repository;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;

import com.sdplayer.tollsplayer.domain.repository.PlayMusicRepository;

import java.io.File;

public class PlayMusicRepositoryImpl implements PlayMusicRepository {

    private final MediaPlayer mediaPlayer = new MediaPlayer();
    private final Context context;

    public PlayMusicRepositoryImpl(Context context){
        this.context = context;
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.reset();
    }

    @Override
    public void clear(){
        try{
            mediaPlayer.stop();
            mediaPlayer.release();
        }catch (Exception e){

        }

    }

    @Override
    public void pause(){
        mediaPlayer.pause();
    }

    @Override
    public void resume(){
        mediaPlayer.start();
    }


    @Override
    public void seekPlayer(int progress) {
        mediaPlayer.seekTo(progress);
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void play(String data) {
        Uri myUri = Uri.fromFile(new File(data));
            mediaPlayer.setAudioAttributes(
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            );
            try {
                mediaPlayer.setDataSource(context, myUri);
                mediaPlayer.prepare();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        mediaPlayer.getCurrentPosition();
        mediaPlayer.start();
    }
}
