package AbstractFactory;

import android.content.Context;

/**
 * Created by enrique on 07/09/14.
 */
public class ArgentinaStadiumFactory implements AbstractStadiumFactory{

    public ArgentinaStadiumFactory(){

    }
    @Override
    public AbstractVest createVest(Context context,int widthDisplay,int heightDisplay,boolean local) {
        return new ArgentinaVest(context,widthDisplay, heightDisplay,local);
    }

    @Override
    public AbstractBall createBall(Context context,int widthDisplay,int heightDisplay) {
        return new ArgentinaBall(context,widthDisplay,heightDisplay);
    }

    @Override
    public AbstractField createField(Context context,int widthDisplay,int heightDisplay) {
        return new ArgentinaField(context, widthDisplay, heightDisplay);
    }
}
