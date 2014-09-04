package AbstractFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by enrique on 30/08/14.
 */
public abstract class AbstractVest extends View{

    protected boolean isLocal;
    private float xPos,yPos;
    protected Bitmap mBitmap;
    protected int mWidthDisplay;
    protected int mHeightDisplay;
    private final static int OFFSETPERCENTAGE=5;
    protected int mOffset; //desplazamiento en pixeles !
    private final Paint mPainter= new Paint();



    public AbstractVest(Context context,int widthDisplay,int heightDisplay) {
        super(context);
        this.mHeightDisplay = heightDisplay;
        this.mWidthDisplay= widthDisplay;
        this.mOffset = OFFSETPERCENTAGE*mWidthDisplay/100;
        this.xPos = mOffset;
        this.yPos = mHeightDisplay/2;
    }


    public void setLocale(boolean value){
        this.isLocal = value;
    };

    public boolean intersects(float x,float y){
        if(x>xPos-mBitmap.getWidth()/2 && x<xPos+mBitmap.getWidth()/2 &&
                y>yPos-mBitmap.getHeight() && y<yPos+mBitmap.getHeight()/2)
            return true;

        return false;
    };

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        if(!isLocal)
            this.xPos = mWidthDisplay-xPos;

        canvas.drawBitmap(mBitmap,xPos-mBitmap.getWidth()/2,yPos-mBitmap.getHeight()/2,mPainter);


    }
}
