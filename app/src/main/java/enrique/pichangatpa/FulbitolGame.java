package enrique.pichangatpa;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import AbstractFactory.AbstractBall;
import AbstractFactory.AbstractField;
import AbstractFactory.AbstractStadiumFactory;
import AbstractFactory.AbstractVest;

/**
 * Created by enrique on 30/08/14.
 */
public class FulbitolGame {


    private Context aContex;

    FulbitolGame(Context aContex){
        this.aContex = aContex;
    }

    public void createFulbitoGame(AbstractStadiumFactory stadiumFactory,
                                  RelativeLayout mFrame, int widthDisplay, int heightDisplay){

        AbstractBall aBall = stadiumFactory.createBall(aContex,widthDisplay,heightDisplay);

        AbstractVest aVestLocale = stadiumFactory.createVest(aContex,widthDisplay,heightDisplay,true);
        AbstractField aField = stadiumFactory.createField(aContex,widthDisplay,heightDisplay);
        //aVestLocale.setLocale(true);
        AbstractVest aVestVisitor = stadiumFactory.createVest(aContex,widthDisplay,heightDisplay,false);
        //aVestVisitor.setLocale(false);


        mFrame.addView((View) aField);  //0
        mFrame.addView(aVestLocale); //1 local
        mFrame.addView(aVestVisitor); // 2 index visitante
        mFrame.addView(aBall); //3

    }
}
