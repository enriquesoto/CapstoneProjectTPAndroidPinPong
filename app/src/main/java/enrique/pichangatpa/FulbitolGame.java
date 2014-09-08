package enrique.pichangatpa;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import AbstractFactory.AbstractBall;
import AbstractFactory.AbstractField;
import AbstractFactory.AbstractStadiumFactory;
import AbstractFactory.AbstractVest;

/**
 * Created by enrique on 30/08/14.
 */
public class FulbitolGame extends  Thread{

    private static final String TAG = "FulbitoGame" ;
    private boolean running;
    private Context aContex;
    private AbstractBall aBall;

    FulbitolGame(Context aContex){
        this.aContex = aContex;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void createFulbitoGame(AbstractStadiumFactory stadiumFactory,
                                  RelativeLayout mFrame, int widthDisplay, int heightDisplay, AbstractVest aVestVisitor){

        aBall = stadiumFactory.createBall(aContex,widthDisplay,heightDisplay);

        //AbstractBall aBall2 = stadiumFactory.createBall(aContex,widthDisplay,heightDisplay);

        AbstractVest aVestLocale = stadiumFactory.createVest(aContex,widthDisplay,heightDisplay,true);
        AbstractField aField = stadiumFactory.createField(aContex,widthDisplay,heightDisplay);
        //aVestLocale.setLocale(true);
        //aVestVisitor = stadiumFactory.createVest(aContex,widthDisplay,heightDisplay,false);
        //aVestVisitor.setLocale(false);


        mFrame.addView((View) aField);  //0
        mFrame.addView(aVestLocale); //1 local
        mFrame.addView(aVestVisitor); // 2 index visitante
        mFrame.addView(aBall); //3



        //mFrame.addView(aBall2); //4
        aVestLocale.start();
        //this.run();


    }
    @Override
    public void run(){

        while(running){
            if(aBall == null){
                running = false;
            }
        }
    }
}
