package enrique.pichangatpa;

import android.content.Context;
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
import AbstractFactory.BrasilField;
import AbstractFactory.BrasilStadiumFactory;
import AbstractFactory.PeruStadiumFactory;
import android.view.ViewGroup;


public class MainFutlbolActivity extends ActionBarActivity  {

    public static RelativeLayout mFrame;

    public final  String DEBUG_TAG = "MAINFULTBOLACTIVITYDEBUG";
    public static final int INDEXLOCALPLAYER = 1;
    public static final int INDEXVISITORPLAYER = 2 ;

    private int _xDelta;
    private int _yDelta;



    private int widthDisplay;
    private int heightDisplay;
    // Gesture Detector
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_futlbol);
        this.mFrame = (RelativeLayout) findViewById(R.id.mainFrame);
        Context aContex = getApplicationContext();
        //mFrame.addView(new BrasilField(aContex,R.drawable.balon3,100,100));

        //widthDisplay = mFrame.getWidth();
       // heightDisplay = mFrame.getHeight();

        //Log.i("XD","centro x:" +this.xPos + " y: " +this.yPos);


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
        aFulbitolGame.createFulbitoGame(new PeruStadiumFactory(),mFrame,widthDisplay,heightDisplay);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 50);

        View aView1 = mFrame.getChildAt(2);
        View aView2 = mFrame.getChildAt(1);

        aView1.setOnTouchListener(new myListener());
        aView2.setOnTouchListener(new myListener());
        //Log.i("W-H","resume" +widthDisplay+"-"+heightDisplay);

    }

    private void setupGestureDetector() {

        mGestureDetector = new GestureDetector( this, new GestureDetector.SimpleOnGestureListener() {



            /* @Override
                        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
                            for (int i=0;i<mFrame.getChildCount();i++){
                                View tmpView =  mFrame.getChildAt(i);
                                Log.i("xdxd","xdxd");
            
                            }a
                            return true;
                        }*/
            @Override
            public boolean onScroll (MotionEvent e1, MotionEvent e2, float distanceX, float distanceY){
                //Log.i(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());

                //AbstractVest aVestLocaleView = (AbstractVest) mFrame.getChildAt(INDEXLOCALPLAYER);
                AbstractVest aVestVisitorView = (AbstractVest) mFrame.getChildAt(INDEXVISITORPLAYER);

                if( aVestVisitorView.intersects(e1.getX(),e1.getY())){

                    Log.i(DEBUG_TAG, "onScroll: x,y inicial " + e1.getX()+e1.getY() + " x,y event 2 "
                            + e2.getX()+","+e2.getY());

                    aVestVisitorView.setPosition(e2.getX(),e2.getY());

                    /*ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) aVestVisitorView.getLayoutParams();

                    marginLayoutParams.leftMargin = (int) ((int) marginLayoutParams.leftMargin - distanceX);
                    marginLayoutParams.topMargin = (int) ((int) marginLayoutParams.topMargin - distanceY);

                    aVestVisitorView.requestLayout();*/



                    return true;
                }







                return true;
            }

        });
    }

    private void updateSizeInfo() {

        widthDisplay = mFrame.getWidth();
        heightDisplay = mFrame.getHeight();
        //Log.i("W-H","updatesize" +widthDisplay+"-"+heightDisplay);
    }
    @Override
    protected void onResume() {
           super.onResume();
        setupGestureDetector();
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
