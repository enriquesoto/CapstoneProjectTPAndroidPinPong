package AbstractFactory;

import android.content.Context;

/**
 * Created by enrique on 30/08/14.
 */
public class PeruStadiumFactory implements AbstractStadiumFactory{

    private static PeruStadiumFactory perustadium = null;

    private PeruStadiumFactory(){

    }
    @Override
    public PeruVest createVest(Context context,int widthDisplay,int heightDisplay,boolean local) {
        return new PeruVest(context,widthDisplay,heightDisplay,local);
    }

    @Override
    public PeruBall createBall(Context context,int widthDisplay,int heightDisplay) {
        return new PeruBall(context, widthDisplay, heightDisplay);
    }

    @Override
    public PeruField createField(Context context,int widthDisplay,int heightDisplay) {
        return new PeruField(context, widthDisplay, heightDisplay);
    }
    public static PeruStadiumFactory getInstance(){
        if(perustadium ==null )
            perustadium = new PeruStadiumFactory();
        return perustadium;
    }



}