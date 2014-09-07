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
public class BrasilVest extends AbstractVest {




    public BrasilVest(Context context,int widthDisplay,int heightDisplay,boolean local){
        super(context,widthDisplay,heightDisplay,local);

        mBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.vestbrasil);

        this.mDy = 5;


    }
}
