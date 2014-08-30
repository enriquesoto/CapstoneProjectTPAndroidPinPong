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
public class BrasilBall extends View implements AbstractBall {
    private Resources mImage;
    private Bitmap mBitmap;
    private float xPos,yPos;
    private final Paint mPainter= new Paint();


    public BrasilBall(Context context,int widthDisplay,int heightDisplay) {
        super(context);
        this.mImage = mImage;
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.balonbrasil);
        //RelativeLayout mFrame = (RelativeLayout) context.getResources().getLayout(R.layout.activity_main_futlbol);
        //this.xPos = mFrame.getWidth()/2;
        //this.yPos = mFrame.getHeight()/2;
        this.xPos = 100;
        this.yPos = 100;

    }
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap,xPos,yPos,mPainter);
    }

}
