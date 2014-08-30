package AbstractFactory;

import android.content.Context;

/**
 * Created by enrique on 30/08/14.
 */
public class PeruStadiumFactory implements AbstractStadiumFactory{
    @Override
    public PeruVest createVest(Context context,int widthDisplay,int heightDisplay) {
        return new PeruVest(context,widthDisplay,heightDisplay);
    }

    @Override
    public PeruBall createBall(Context context,int widthDisplay,int heightDisplay) {
        return new PeruBall(context, widthDisplay, heightDisplay);
    }

    @Override
    public PeruField createField(Context context,int widthDisplay,int heightDisplay) {
        return new PeruField(context, widthDisplay, heightDisplay);
    }
}