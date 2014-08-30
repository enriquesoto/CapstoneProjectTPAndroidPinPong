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
public class PeruBall extends View implements AbstractBall{

    private Resources mImage;
    private Bitmap mBitmap;
    private float xPos,yPos;
    private final Paint mPainter= new Paint();
    private int mHeightDisplay;
    private int mWidthDisplay;

    private static final double PROPORCION=0.18;


    public PeruBall(Context context,int widthDisplay,int heightDisplay) {
        super(context);
        this.mImage = mImage;
        //Drawable mDrawable = getResources().getDrawable(android.R.drawable)
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.balonperu);
        this.mHeightDisplay = heightDisplay;
        this.mWidthDisplay= widthDisplay;
        mPainter.setColor(Color.rgb(255,0,0));
        mPainter.setStrokeWidth(4);

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int height = (int) (metrics.heightPixels - metrics.widthPixels*(double)PROPORCION);
        int width = metrics.widthPixels;

        this.xPos = widthDisplay/2;
        this.yPos = heightDisplay/2;

        Log.i("Peruball","ancho :" +width + " alto: " +height);
        Log.i("XD","centro x:" +this.xPos + " y: " +this.yPos);

    }
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap,xPos-mBitmap.getWidth()/2,yPos-mBitmap.getHeight()/2,mPainter);
        //canvas.drawLine(xPos,0,xPos,mHeightDisplay,mPainter);
        //canvas.drawLine(0,yPos,mWidthDisplay,yPos,mPainter);
    }

}