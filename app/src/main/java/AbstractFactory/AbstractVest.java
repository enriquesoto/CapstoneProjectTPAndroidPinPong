package AbstractFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import enrique.pichangatpa.MainFutlbolActivity;

/**
 * Created by enrique on 30/08/14.
 */
public abstract class AbstractVest extends View{

    private final static int RANDOM = 0;

    private final static int SINGLE = 1;

    private static final String TAG = "ABstractVest:";
    private static int speedMode = SINGLE;
    private final static int STILL = 2;
    protected boolean isLocal;
    private float xPos,yPos;
    protected float mDy; //direccion para moverse
    protected Bitmap mBitmap;
    protected int mWidthDisplay;
    protected int mHeightDisplay;
    private final static int OFFSETPERCENTAGE=5;
    protected int mOffset; //desplazamiento en pixeles !
    private final Paint mPainter= new Paint();
    private RelativeLayout.LayoutParams mLayoutParams;
    private boolean isBot;
    private ScheduledFuture<?> mMoverFuture; //mnanejo de hilos
    private static final int REFRESH_RATE = 20;
    private RelativeLayout mFrame;

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean isBot) {
        this.isBot = isBot;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
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

    public AbstractVest(Context context,int widthDisplay,int heightDisplay,boolean local) {
        super(context);
        this.mHeightDisplay = heightDisplay;
        this.mWidthDisplay= widthDisplay;
        this.isLocal = local;
        this.mOffset = OFFSETPERCENTAGE*mWidthDisplay/100;
        this.xPos = mOffset;

        this.mFrame = MainFutlbolActivity.mFrame;
        if(!isLocal)
            this.xPos = mWidthDisplay-mOffset;
        this.yPos = mHeightDisplay/2;
        mPainter.setStrokeWidth(5);
        mPainter.setStyle(Paint.Style.STROKE);

    }


    public void setLocale(boolean value){
        this.isLocal = value;
    };

    public synchronized boolean intersects(float x, float y) {

        // TODO - Return true if the BubbleView intersects position (x,y)

        if(x>xPos-mBitmap.getWidth()/2 && x<xPos+mBitmap.getWidth()/2 &&
                y>yPos-mBitmap.getHeight()/2 && y<yPos+mBitmap.getHeight()/2)
            return true;

        return false;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {


        canvas.drawBitmap(mBitmap,xPos-mBitmap.getWidth()/2,yPos-mBitmap.getHeight()/2,mPainter);
        canvas.drawLine(mWidthDisplay/2,0,mWidthDisplay/2,mHeightDisplay,mPainter);
        canvas.drawLine(0,mHeightDisplay/2,mWidthDisplay,mHeightDisplay/2,mPainter);
        canvas.drawLine(xPos,0,xPos,mHeightDisplay,mPainter);
        canvas.drawLine(0,yPos,mWidthDisplay,yPos,mPainter);
        canvas.drawRoundRect(new RectF(xPos-mBitmap.getWidth()/2,yPos-mBitmap.getHeight()/2,xPos+mBitmap.getWidth()/2,yPos+mBitmap.getHeight()/2),10,10,mPainter);


        //Log.i(TAG,"POSITION x"+this.xPos+"Y:"+this.yPos);


    }

    public void setPosition(float x,float y){
        this.xPos = mOffset;
        if(!isLocal)
            this.xPos = mWidthDisplay-mOffset;
        this.yPos = y;
        Log.i(TAG,"POSITION x"+this.xPos+"Y:"+this.yPos);

        this.invalidate();
    }

    public void start() {

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

    private synchronized boolean moveWhileIsnotGoal() {

        // TODO - Move the BubbleView
        // Returns true if the BubbleView has exited the screen
        if(!isLocal)
            this.xPos = mWidthDisplay-mOffset;
        AbstractBall aBall = (AbstractBall) mFrame.getChildAt(3);
        if(aBall != null){
            float distance = this.yPos-aBall.getyPos() ;
            if(distance > 0)
                yPos +=-mDy;
            else
                yPos +=mDy;

        }

        else
            return false;

        //if(isNotGoal()) return true;


        return true;

    }

    private void stop(final boolean popped) {

        if (null != mMoverFuture && mMoverFuture.cancel(true)) {

            // This work will be performed on the UI Thread
            //LayoutInflater li = LayoutInflater.from(mContex);
            //mFrame = (RelativeLayout) li.inflate(R.layout.activity_main_futlbol, null);


            mFrame.post(new Runnable() {
                @Override
                public void run() {

                    // TODO - Remove the BubbleView from mFrame

                    mFrame.removeView(AbstractVest.this);


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
