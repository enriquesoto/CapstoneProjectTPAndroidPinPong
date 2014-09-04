package AbstractFactory;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.widget.RelativeLayout;
import android.app.Activity;

import enrique.pichangatpa.R;

/**
 * Created by enrique on 30/08/14.
 */
public class PeruBall extends AbstractBall{

    private Resources mImage;


    private final Paint mPainter= new Paint();


    public PeruBall(Context context,int widthDisplay,int heightDisplay) {
        super(context,widthDisplay,heightDisplay);
        mPainter.setColor(Color.rgb(255,0,0));
        mPainter.setStrokeWidth(5);
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.balonperu);

    }
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap,xPos-mBitmap.getWidth()/2,yPos-mBitmap.getHeight()/2,mPainter);
        canvas.drawLine(mWidthDisplay/2,0,mWidthDisplay/2,mHeightDisplay,mPainter);
        canvas.drawLine(0,mHeightDisplay/2,mWidthDisplay,mHeightDisplay/2,mPainter);
        canvas.drawLine(xPos,0,xPos,mHeightDisplay,mPainter);
        canvas.drawLine(0,yPos,mWidthDisplay,yPos,mPainter);
    }


}
