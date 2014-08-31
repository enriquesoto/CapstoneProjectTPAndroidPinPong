package AbstractFactory;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import enrique.pichangatpa.R;
/**
 * Created by enrique on 30/08/14.
 */
public class PeruVest extends View implements AbstractVest{

    private Resources mImage;
    private Bitmap mBitmap;
    private float xPos,yPos;
    private final Paint mPainter= new Paint();
    private boolean isLocal;
    private int mWidthDisplay;
    private int mHeightDisplay;

    private int mOffset;
    private final static int OFFSETPERCENTAGE=5;

    public PeruVest(Context context,int widthDisplay,int heightDisplay) {
        super(context);
        this.mImage = mImage;
        mBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.peruvest);
        this.mHeightDisplay = heightDisplay;
        this.mWidthDisplay= widthDisplay;
        this.mOffset = OFFSETPERCENTAGE*mWidthDisplay/100;

    }
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        if(isLocal)
            canvas.drawBitmap(mBitmap,mOffset-mBitmap.getWidth()/2, mHeightDisplay/2-mBitmap.getHeight()/2, mPainter);
        else
            canvas.drawBitmap(mBitmap,mWidthDisplay-mOffset-mBitmap.getWidth()/2,mHeightDisplay/2-mBitmap.getHeight()/2,mPainter);
    }

    @Override
    public void setLocale(boolean value) {
        this.isLocal = value;
    }

    @Override
    public boolean intersects(float x, float y) {

        if(x>xPos && x<xPos+mBitmap.getWidth() && y>yPos && y<yPos+mBitmap.getWidth())
            return true;

        return false;
    }
}
