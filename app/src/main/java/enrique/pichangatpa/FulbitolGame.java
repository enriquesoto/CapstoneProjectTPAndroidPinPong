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
        AbstractVest aVestLocale = stadiumFactory.createVest(aContex,widthDisplay,heightDisplay);
        AbstractField aField = stadiumFactory.createField(aContex,widthDisplay,heightDisplay);
        aVestLocale.setLocale(true);
        AbstractVest aVestVisitor = stadiumFactory.createVest(aContex,widthDisplay,heightDisplay);
        aVestVisitor.setLocale(false);


        mFrame.addView((View) aField);
        mFrame.addView((View) aVestLocale);
        mFrame.addView((View) aVestVisitor);
        mFrame.addView((View) aBall);
    }
}
