package AbstractFactory;

import android.content.Context;

/**
 * Created by enrique on 30/08/14.
 */
public class BrasilStadiumFactory implements AbstractStadiumFactory{

    private static BrasilStadiumFactory brasilStadium =  null;
    private BrasilStadiumFactory(){

    }
    @Override
    public AbstractVest createVest(Context context,int widthDisplay,int heightDisplay,boolean local) {
        return new BrasilVest(context,widthDisplay, heightDisplay,local);
    }

    @Override
    public AbstractBall createBall(Context context,int widthDisplay,int heightDisplay) {
        return new BrasilBall(context,widthDisplay,heightDisplay);
    }

    @Override
    public AbstractField createField(Context context,int widthDisplay,int heightDisplay) {
        return new BrasilField(context, widthDisplay, heightDisplay);
    }

    public static BrasilStadiumFactory getInstance(){
        if(brasilStadium ==null )
            brasilStadium = new BrasilStadiumFactory();
        return brasilStadium;
    }


}