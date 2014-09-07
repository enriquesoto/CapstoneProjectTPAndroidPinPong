package AbstractFactory;

import android.content.Context;

/**
 * Created by enrique on 07/09/14.
 */
public class ChileStadiumFactory implements AbstractStadiumFactory{

    public ChileStadiumFactory(){

    }
    @Override
    public AbstractVest createVest(Context context,int widthDisplay,int heightDisplay,boolean local) {
        return new ChileVest(context,widthDisplay, heightDisplay,local);
    }

    @Override
    public AbstractBall createBall(Context context,int widthDisplay,int heightDisplay) {
        return new ChileBall(context,widthDisplay,heightDisplay);
    }

    @Override
    public AbstractField createField(Context context,int widthDisplay,int heightDisplay) {
        return new ChileField(context, widthDisplay, heightDisplay);
    }
}
