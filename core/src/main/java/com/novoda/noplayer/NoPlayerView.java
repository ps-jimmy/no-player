package com.novoda.noplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.novoda.notils.caster.Views;

public class NoPlayerView extends FrameLayout implements PlayerView {

    private final PlayerViewSurfaceHolder surfaceHolderProvider;

    private SimpleExoPlayerView playerView;
    private SurfaceView surfaceView;

    public NoPlayerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        surfaceHolderProvider = new PlayerViewSurfaceHolder();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.noplayer_view, this);
        playerView = Views.findById(this, R.id.simple_player_view);
        surfaceView = (SurfaceView) playerView.getVideoSurfaceView();
        surfaceView.getHolder().addCallback(surfaceHolderProvider);
    }

    @Override
    public View getContainerView() {
        return surfaceView;
    }

    @Override
    public SurfaceHolderRequester getSurfaceHolderRequester() {
        return surfaceHolderProvider;
    }

    @Override
    public Player.VideoSizeChangedListener getVideoSizeChangedListener() {
        return videoSizeChangedListener;
    }

    @Override
    public SimpleExoPlayerView simplePlayerView() {
        return playerView;
    }

    @Override
    public void showSubtitles() {

    }

    @Override
    public void hideSubtitles() {

    }

    @Override
    public void removeControls() {
        playerView.setUseController(false);
    }

    private final Player.VideoSizeChangedListener videoSizeChangedListener = new Player.VideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
// TODO:            aspectRatioChangeListener.onVideoSizeChanged(width, height, unappliedRotationDegrees, pixelWidthHeightRatio);
        }
    };

}
