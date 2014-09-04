package AbstractFactory;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;

import enrique.pichangatpa.R;




/**
 * Created by enrique on 30/08/14.
 */
public class BrasilBall extends AbstractBall {

    private Resources mImage;
    private final Paint mPainter= new Paint();


    public BrasilBall(Context context,int widthDisplay,int heightDisplay) {
        super(context,widthDisplay,heightDisplay);
        this.mImage = mImage;
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.balonbrasil);

    }
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap,xPos-mBitmap.getWidth()/2,yPos-mBitmap.getHeight()/2,mPainter);
    }

}
