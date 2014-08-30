package AbstractFactory;

import android.content.Context;

/**
 * Created by enrique on 30/08/14.
 */
public class BrasilStadiumFactory implements AbstractStadiumFactory{

    public BrasilStadiumFactory(){

    }
    @Override
    public AbstractVest createVest(Context context,int widthDisplay,int heightDisplay) {
        return new BrasilVest(context,widthDisplay, heightDisplay);
    }

    @Override
    public AbstractBall createBall(Context context,int widthDisplay,int heightDisplay) {
        return new BrasilBall(context,widthDisplay,heightDisplay);
    }

    @Override
    public AbstractField createField(Context context,int widthDisplay,int heightDisplay) {
        return new BrasilField(context, widthDisplay, heightDisplay);
    }
}