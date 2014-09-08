package enrique.pichangatpa;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import AbstractFactory.AbstractStadiumFactory;
import AbstractFactory.AbstractVest;
import AbstractFactory.ArgentinaStadiumFactory;
import AbstractFactory.BrasilField;
import AbstractFactory.BrasilStadiumFactory;
import AbstractFactory.BrasilVest;
import AbstractFactory.ChileStadiumFactory;
import AbstractFactory.PeruStadiumFactory;
import AbstractFactory.PeruVest;

import android.view.ViewGroup;

import java.util.ArrayList;

public class MainFutlbolActivity extends ActionBarActivity  {

    public static RelativeLayout mFrame;

    public final  String DEBUG_TAG = "MAINFULTBOLACTIVITYDEBUG";
    public static final int INDEXLOCALPLAYER = 1;
    public static final int INDEXVISITORPLAYER = 2 ;

    private int _xDelta;
    private int _yDelta;
    private ArrayList<String> mCountryList = new ArrayList<String>();
    private String[] countryTeamArray;
    private String[] opponentTeamArray;
    private int countryTeamIndex;
    private int opponentTeamIndex;


    private int widthDisplay;
    private int heightDisplay;
    // Gesture Detector
    private GestureDetector mGestureDetector;


    public static enum Strings{
        PERU,BRAZIL;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_futlbol);
        this.mFrame = (RelativeLayout) findViewById(R.id.mainFrame);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            countryTeamIndex= extras.getInt("CountryTeam");
            opponentTeamIndex = extras.getInt("OpponentTeam");

        }
        Resources res = getResources();
        countryTeamArray  = res.getStringArray(R.array.country_arrays);
        opponentTeamArray = res.getStringArray(R.array.rival_array);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_futlbol, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void crearStadium(AbstractStadiumFactory stadiumFactory){
        //stadiumFactory.createBall(stadiumFactory)
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        updateSizeInfo();
        initApplication();
    }

    private void initApplication() {

        FulbitolGame aFulbitolGame = new FulbitolGame(getApplicationContext());
        AbstractVest aVestVisitor = null;
        AbstractStadiumFactory anAbstractStadiumFactory = null;
        switch (countryTeamIndex){
            case 0:
                aVestVisitor = new PeruVest(getApplicationContext(),widthDisplay,heightDisplay,false);

            break;
            case 1:
                aVestVisitor = new BrasilVest(getApplicationContext(),widthDisplay,heightDisplay,false);

                break;

            default:
                return;

        }

        switch (opponentTeamIndex){
            case 0:
                anAbstractStadiumFactory = PeruStadiumFactory.getInstance();

                break;
            case 1:
                anAbstractStadiumFactory = ChileStadiumFactory.getInstance();
                break;
            case 2:
                anAbstractStadiumFactory = ArgentinaStadiumFactory.getInstance();
                break;
            case 3:
                anAbstractStadiumFactory = BrasilStadiumFactory.getInstance();
                break;
            default:
                return;

        }

        aFulbitolGame.createFulbitoGame(anAbstractStadiumFactory,mFrame,widthDisplay,heightDisplay,aVestVisitor);

        //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 50);

        View aView1 = mFrame.getChildAt(2);
        View aView2 = mFrame.getChildAt(1);

        aView1.setOnTouchListener(new myListener());
        aView2.setOnTouchListener(new myListener());
        //Log.i("W-H","resume" +widthDisplay+"-"+heightDisplay);

    }

    private void updateSizeInfo() {

        widthDisplay = mFrame.getWidth();
        heightDisplay = mFrame.getHeight();
        //Log.i("W-H","updatesize" +widthDisplay+"-"+heightDisplay);
    }
    @Override
    protected void onResume() {
           super.onResume();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // TODO - delegate the touch to the gestureDetector

        //return mGestureDetector.onTouchEvent(event);
        return  true;

    }



    public class myListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            final int X = (int) motionEvent.getRawX();
            final int Y = (int) motionEvent.getRawY();
            AbstractVest aVest = (AbstractVest) view;
            RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        if (!aVest.intersects(X,Y)){
                            return false;
                        }


                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:

                        lParams.leftMargin = X - _xDelta;
                        lParams.topMargin = Y - _yDelta;
                        lParams.rightMargin = -250;
                        lParams.bottomMargin = -250;


                         ((AbstractVest) view).setPosition(lParams.leftMargin,lParams.topMargin);
                      //view.setLayo                        ((AbstractVest) view).setPosition(layoutParams.leftMargin,layoutParams.topMargin);layoutParams.topMargin);
                        break;
                }


            mFrame.invalidate();
            return true;
        }
    }
}
