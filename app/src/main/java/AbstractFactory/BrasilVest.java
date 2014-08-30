package AbstractFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import enrique.pichangatpa.R;
/**
 * Created by enrique on 30/08/14.
 */
public class BrasilVest extends View implements AbstractVest {

    private Bitmap mBitmap;
    private float xPos,yPos;
    private final Paint mPainter= new Paint();


    public BrasilVest(Context context,int widthDisplay,int heightDisplay){
        super(context);

        mBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.brasilvest);


    }
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, xPos, yPos, mPainter);
    }
}
