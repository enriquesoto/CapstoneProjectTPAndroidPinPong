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
 * Created by enrique on 07/09/14.
 */
public class ArgentinaField extends View implements AbstractField {

    private Resources mImage;
    private Bitmap mBitmap;
    private float xPos, yPos;
    private final Paint mPainter = new Paint();
    private Bitmap mScaleBitmap;


    public ArgentinaField(Context context, int widthDisplay, int heightDisplay) {
        super(context);
        this.mImage = mImage;
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.canchaargentina);
        mScaleBitmap = Bitmap.createScaledBitmap(mBitmap, widthDisplay, heightDisplay, false);


    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.drawBitmap(mScaleBitmap, xPos, yPos, mPainter);
    }
}
