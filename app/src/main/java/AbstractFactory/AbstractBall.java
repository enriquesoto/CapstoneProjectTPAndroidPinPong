package AbstractFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import enrique.pichangatpa.MainFutlbolActivity;

/**
 * Created by enrique on 30/08/14.
 */
public abstract class AbstractBall extends View{

    private final static int RANDOM = 0;

    private final static int SINGLE = 1;
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


    private long mRotate, mDRotate;

    public AbstractBall(Context context,int widthDisplay,int heightDisplay) {
        super(context);


        this.mContex = context;


        this.mHeightDisplay = heightDisplay;
        this.mWidthDisplay = widthDisplay;
        this.xPos = mWidthDisplay/2; //posicion inicial
        this.yPos = mHeightDisplay/2;
        Random aRandom = new Random();
        setSpeedAndDirection(aRandom);
        setRotation(aRandom);
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
                int max = 3;
                int min = -3;
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
                // TODO - implement movement logic.
                // Each time this method is run the BubbleView should
                // move one step. If the BubbleView exits the display,
                // stop the BubbleView's Worker Thread.
                // Otherwise, request that the BubbleView be redrawn.

                //mFrame.removeView(BubbleView.this);
                if(moveWhileIsnotGoal())
                    postInvalidate();
                else
                    stop(false);



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

        // TODO - Return true if the BubbleView has exited the screen

        if(xPos > mWidthDisplay || xPos<0){

            return false;
        }

        if(yPos > mHeightDisplay || yPos<0){
            mDy = -mDy;
            return true;
        }

        return true;

    }
    private void stop(final boolean popped) {

        if (null != mMoverFuture && mMoverFuture.cancel(true)) {

            // This work will be performed on the UI Thread
            LayoutInflater li = LayoutInflater.from(mContex);
            //mFrame = (RelativeLayout) li.inflate(R.layout.activity_main_futlbol, null);
            mFrame= MainFutlbolActivity.mFrame;

            mFrame.post(new Runnable() {
                @Override
                public void run() {

                    // TODO - Remove the BubbleView from mFrame

                    mFrame.removeView(AbstractBall.this);


                    if (popped) {
                        Log.i("Pop!","Pop!");

                        // TODO - If the bubble was popped by user,
                        // play the popping sound

                        //	mSoundPool.play(mSoundId, (float) mVolume / mVolumeMax,
                        //	(float) mVolume / mVolumeMax, 1, 0, 1.0f);

                        //mSoundPool.play(mSoundID, (float)mStreamVolume , (float)mStreamVolume, 1, 0,1.0f);

                    }

                    Log.i("Runneable","Bubble removed from view!");

                }
            });
        }
    }

}
