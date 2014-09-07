package AbstractFactory;

import android.content.Context;
import android.graphics.BitmapFactory;

import enrique.pichangatpa.R;

/**
 * Created by enrique on 07/09/14.
 */
public class ArgentinaVest extends AbstractVest {




    public ArgentinaVest(Context context,int widthDisplay,int heightDisplay,boolean local){
        super(context,widthDisplay,heightDisplay,local);

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.vestargentina);

        this.mDy = 4;
    }
}
