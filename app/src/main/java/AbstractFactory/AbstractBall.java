package AbstractFactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.SoundPool;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import enrique.pichangatpa.MainFutlbolActivity;
import enrique.pichangatpa.R;


import android.content.Context;

/**
 * Created by enrique on 30/08/14.
 */
public abstract class AbstractBall extends View {

    private final static int RANDOM = 0;

    private final static int SINGLE = 1;
    private static final String TAG = "ABSTRACTBALL";
    private static int speedMode = SINGLE;
    private final static int STILL = 2;
    private float mDx, mDy;  //
    private ScheduledFuture<?> mMoverFuture; //mnanejo de hilos
    protected int mHeightDisplay; //ancho y alto de la pantalla
    protected int mWidthDisplay;
    protected Bitmap mBitmap; //bitmap a dibujar
    protected float xPos,yPos; //posición de la pelota
    private static final int REFRESH_RATE = 20;
    private Context mContex;
    private RelativeLayout mFrame;
    private AudioManager mAudioManager;
    protected int mSoundID;

    protected float mStreamVolume;

    private final static int MAX_STREAMS = 10; //mio


    private SoundPool mSoundPool;

    private boolean running;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    private long mRotate, mDRotate;

    public AbstractBall(Context context,int widthDisplay,int heightDisplay) {
        super(context);


        this.mContex = context;
        mFrame= MainFutlbolActivity.mFrame;

        this.mHeightDisplay = heightDisplay;
        this.mWidthDisplay = widthDisplay;
        this.xPos = mWidthDisplay/2; //posicion inicial
        this.yPos = mHeightDisplay/2;
        Random aRandom = new Random();
        setSpeedAndDirection(aRandom);
        setRotation(aRandom);
        this.running = true;

        mAudioManager = (AudioManager) mContex.getSystemService(android.content.Context.AUDIO_SERVICE);

        mStreamVolume = (float) mAudioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC)
                / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mSoundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC,0);

        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // sellama cuando el sonido se carga totalmente
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                //if (status == 0)
                    //setupGestureDetector();
            }
        });
        //mSoundID = mSoundPool.load(this,mContex.getResources().openRawResource(R.raw.terminator),1);
        mSoundID = mSoundPool.load(mContex,R.raw.terminator,1);
        start();



    }
    private void setRotation(Random r) {

        if (speedMode == RANDOM) {

            // TODO - set rotation in range [1..3]
            int max=3,min=1;
            mDRotate = (r.nextInt(max-min+1)+min);


        } else {

            mDRotate = 0;

        }
    }
    private void setSpeedAndDirection(Random r) {

        // Used by test cases
      /*  switch (speedMode) {

            case SINGLE:

                // Fixed speed
                mDx = 10;
                mDy = 10;
                break;

            case STILL:

                // No speed
                mDx = 50;
                mDy = 50;
                break;

            default:*/

                // TODO - Set movement direction and speed
                // Limit movement speed in the x and y
                // direction to [-3..3].
                int max = 8;
                int min = -8;
                do {
                    mDx = (r.nextInt(max-min+1)+min);

                    mDy = (r.nextInt(max-min+1)+min);

                }while (mDx == 0 || mDy == 0);



    }
    private void start() {

        // Creates a WorkerThread
        ScheduledExecutorService executor = Executors
                .newScheduledThreadPool(1);

        // Execute the run() in Worker Thread every REFRESH_RATE
        // milliseconds
        // Save reference to this job in mMoverFuture
        mMoverFuture = executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                //while (running){
                    if(moveWhileIsnotGoal())
                        postInvalidate();
                    else{
                        stop(true);
                    }

                //}
                // TODO - implement movement logic.
                // Each time this method is run the BubbleView should
                // move one step. If the BubbleView exits the display,
                // stop the BubbleView's Worker Thread.
                // Otherwise, request that the BubbleView be redrawn.

                //mFrame.removeView(BubbleView.this);




            }
        }, 0, REFRESH_RATE, TimeUnit.MILLISECONDS);
    }
    private synchronized boolean intersects(float x, float y) {

        // TODO - Return true if the BubbleView intersects position (x,y)

        if(x>xPos && x<xPos+mBitmap.getWidth() && y>yPos && y<yPos+mBitmap.getWidth())
            return true;

        return false;
    }

    private synchronized boolean moveWhileIsnotGoal() {

        // TODO - Move the BubbleView
        // Returns true if the BubbleView has exited the screen
        xPos += mDx;
        yPos += mDy;



        if(isNotGoal()) return true;


        return false;

    }
    private boolean isNotGoal() {

        // TODO - Return false if is goal

        if(xPos > mWidthDisplay || xPos<0){

            //mDx = -mDx;


            return false;
        }

        if(yPos > mHeightDisplay || yPos<0){
            mDy = -mDy;
            return true;
        }
        //si choco con barritas

        AbstractVest aVestLocal =(AbstractVest) mFrame.getChildAt(1);
        AbstractVest aVestVisitor = (AbstractVest) mFrame.getChildAt(2);

        if(aVestVisitor!=null){
            //Log.i(TAG,"x axis:" + aVestVisitor.getxPos() + "y axis" + aVestVisitor.getyPos());
            if((
                    ( xPos >= aVestVisitor.getxPos()-aVestVisitor.getmBitmap().getWidth()/2) &&
                            (yPos >= aVestVisitor.getyPos()- aVestVisitor.getmBitmap().getHeight()/2) &&
                            (yPos <= aVestVisitor.getyPos()+aVestVisitor.getmBitmap().getHeight()/2)

            )){

                Log.i(TAG,"x axis:" + aVestVisitor.getxPos() + "y axis" + aVestVisitor.getyPos());
                Log.i(TAG,"x pos:" + xPos + "y pos" + yPos);
                Log.i(TAG,"vest width:" + aVestVisitor.getmBitmap().getWidth() + "height" + aVestVisitor.getmBitmap().getHeight());
                mDx = -(mDx+mDx/100);

            }

        }
        if(aVestLocal!=null){
            //Log.i(TAG,"x axis:" + aVestVisitor.getxPos() + "y axis" + aVestVisitor.getyPos());
            if((
                    ( xPos <= aVestLocal.getxPos()+aVestLocal.getmBitmap().getWidth()/2) &&
                            (yPos >= aVestLocal.getyPos()- aVestLocal.getmBitmap().getHeight()/2) &&
                            (yPos <= aVestLocal.getyPos()+aVestLocal.getmBitmap().getHeight()/2)

            )){

                Log.i(TAG,"x axis:" + aVestLocal.getxPos() + "y axis" + aVestLocal.getyPos());
                Log.i(TAG,"x pos:" + xPos + "y pos" + yPos);
                Log.i(TAG,"vest width:" + aVestLocal.getmBitmap().getWidth() + "height" + aVestLocal.getmBitmap().getHeight());
                mDx = -(mDx+mDx/100);

            }

        }

        return true;

    }
    private void stop(final boolean popped) {

        if (null != mMoverFuture && mMoverFuture.cancel(true)) {

            // This work will be performed on the UI Thread
            LayoutInflater li = LayoutInflater.from(mContex);
            //mFrame = (RelativeLayout) li.inflate(R.layout.activity_main_futlbol, null);


            mFrame.post(new Runnable() {
                @Override
                public void run() {

                    // TODO - Remove the BubbleView from mFrame

                    mFrame.removeView(AbstractBall.this);


                    if (popped) {
                        Log.i("Pop!","Pop!");

                        // TODO - If the bubble was popped by user,
                        // play the popping sound

                        mSoundPool.play(mSoundID, (float)mStreamVolume , (float)mStreamVolume, 1, 0,1.0f);

                    }

                    Log.i("Runneable","Bubble removed from view!");

                }
            });
        }
    }


}
