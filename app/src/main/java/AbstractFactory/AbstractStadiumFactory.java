package AbstractFactory;

import android.content.Context;

/**
 * Created by enrique on 30/08/14.
 */
public interface AbstractStadiumFactory {

    public AbstractVest createVest(Context context,int widthDisplay,int heightDisplay);
    public AbstractBall createBall(Context context,int widthDisplay,int heightDisplay);
    public AbstractField createField(Context context,int widthDisplay,int heightDisplay);

}
