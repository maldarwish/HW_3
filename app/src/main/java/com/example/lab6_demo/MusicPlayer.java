package com.example.lab6_demo;

import android.media.MediaPlayer;

public class MusicPlayer implements MediaPlayer.OnCompletionListener {

    MediaPlayer player;
    int currentPosition = 0;
    int musicIndex = 0;
    int extraIndex = 0;
    private int musicStatus = 0;//0: before playing, 1 playing, 2 paused
    private MusicService musicService;

    static final int[] MUSICPATH = new int[]{
            R.raw.gotechgo,
            R.raw.entersandman,
            R.raw.yaya
    };

    static final int[] EXTRAPATH = new int[]{
            R.raw.clapping,
            R.raw.cheering,
            R.raw.lestgohokies
    };

    static final String[] MUSICNAME = new String[]{
            "Go Tech Go!",
            "Enter Sandman",
            "Yaya Kolo"
    };

    static final String[] EXTRANAME = new String[]{
            "Clapping",
            "Cheering",
            "Go Hokies"
    };

    public MusicPlayer(MusicService service) {

        this.musicService = service;
    }


    public int getMusicStatus() {

        return musicStatus;
    }

    public String getMusicName() {

        return MUSICNAME[musicIndex];
    }

    public void playMusic() {
        player= MediaPlayer.create(this.musicService, MUSICPATH[musicIndex]);
        player.start();
        player.setOnCompletionListener(this);
        musicService.onUpdateMusicName(getMusicName());
        musicStatus = 1;
    }

    public void nextSong() {
        musicIndex = (musicIndex + 1) % MUSICNAME.length;
        player.release();
        player= null;
        playMusic();
    }

    public void restart() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
            currentPosition = 0;
            playMusic();
        }
    }

    public void prevSong() {
        musicIndex = (musicIndex - 1) % MUSICNAME.length;
        player.release();
        player= null;
        playMusic();
    }

    public void pauseMusic() {
        if(player!= null && player.isPlaying()){
            player.pause();
            currentPosition= player.getCurrentPosition();
            musicStatus= 2;
        }
    }

    public void resumeMusic() {
        if(player!= null){
            player.seekTo(currentPosition);
            player.start();
            musicStatus=1;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        musicIndex = (musicIndex +1) % MUSICNAME.length;
        player.release();
        player= null;
        playMusic();
    }
}