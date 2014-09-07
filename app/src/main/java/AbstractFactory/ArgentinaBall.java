package AbstractFactory;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import enrique.pichangatpa.R;

/**
 * Created by enrique on 07/09/14.
 */
public class ArgentinaBall extends AbstractBall {

    private Resources mImage;
    private final Paint mPainter = new Paint();


    public ArgentinaBall(Context context, int widthDisplay, int heightDisplay) {
        super(context, widthDisplay, heightDisplay);
        this.mImage = mImage;
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.balonargentina);

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, xPos - mBitmap.getWidth() / 2, yPos - mBitmap.getHeight() / 2, mPainter);
    }
}