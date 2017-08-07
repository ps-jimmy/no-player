package com.novoda.noplayer.internal.mediaplayer.forwarder;

import android.media.MediaPlayer;

import com.novoda.noplayer.Player;
import com.novoda.utils.NoPlayerLog;

class VideoSizeChangedForwarder implements MediaPlayer.OnVideoSizeChangedListener {

    private final Player.VideoSizeChangedListener videoSizeChangedListener;

    private int previousWidth;
    private int previousHeight;

    VideoSizeChangedForwarder(Player.VideoSizeChangedListener videoSizeChangedListener) {
        this.videoSizeChangedListener = videoSizeChangedListener;
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        if (bothDimensionsHaveChanged(width, height)) {
            videoSizeChangedListener.onVideoSizeChanged(width, height, 0, 1);
        } else {
            NoPlayerLog.w("Video size changed but we have swallowed the event due to only 1 dimension changing");
        }
        previousWidth = width;
        previousHeight = height;
    }

    private boolean bothDimensionsHaveChanged(int width, int height) {
        boolean widthHasChanged = width != previousWidth;
        boolean heightHasChanged = height != previousHeight;
        return widthHasChanged && heightHasChanged;
    }
}