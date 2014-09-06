package AbstractFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by enrique on 30/08/14.
 */
public abstract class AbstractVest extends View{

    private static final String TAG = "ABstractVest:";

    protected boolean isLocal;
    private float xPos,yPos;
    protected Bitmap mBitmap;
    protected int mWidthDisplay;
    protected int mHeightDisplay;
    private final static int OFFSETPERCENTAGE=5;
    protected int mOffset; //desplazamiento en pixeles !
    private final Paint mPainter= new Paint();
    private RelativeLayout.LayoutParams mLayoutParams;

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
        this.xPos =x;
        this.yPos = y;
        Log.i(TAG,"POSITION x"+this.xPos+"Y:"+this.yPos);

        this.invalidate();
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {


        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();


            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) this.getLayoutParams();
                    this.xPos = X - lParams.leftMargin;
                    this.yPos = Y - lParams.topMargin;
                    this.invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.getLayoutParams();
                    layoutParams.leftMargin = X - (int) xPos;
                    layoutParams.topMargin = Y - (int) yPos;
                    layoutParams.rightMargin = -250;
                    layoutParams.bottomMargin = -250;
                    this.setLayoutParams(layoutParams);
                    this.invalidate();
                    break;
            }

            return true;

    }*/
   /* @Override
    public boolean onDragEvent(DragEvent event){

        switch(event.getAction())
        {
            case DragEvent.ACTION_DRAG_STARTED:
                mLayoutParams = (RelativeLayout.LayoutParams)
                        this.getLayoutParams();

                Log.i(TAG, "Action is DragEvent.ACTION_DRAG_STARTED");
                // Do nothing
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.i(TAG, "Action is DragEvent.ACTION_DRAG_ENTERED");
                int x_cord = (int) event.getX();
                int y_cord = (int) event.getY();
                break;
            case DragEvent.ACTION_DRAG_EXITED :
                Log.i(TAG, "Action is DragEvent.ACTION_DRAG_EXITED");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                mLayoutParams.leftMargin = x_cord;
                mLayoutParams.topMargin = y_cord;
                this.setLayoutParams(mLayoutParams);
                break;
            case DragEvent.ACTION_DRAG_LOCATION  :
                Log.d(TAG, "Action is DragEvent.ACTION_DRAG_LOCATION");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                break;
            case DragEvent.ACTION_DRAG_ENDED   :
                Log.d(TAG, "Action is DragEvent.ACTION_DRAG_ENDED");
                // Do nothing
                break;
            case DragEvent.ACTION_DROP:
                Log.d(TAG, "ACTION_DROP event");
                // Do nothing
                break;
            default: break;
        }
        return true;
    }*/

}
