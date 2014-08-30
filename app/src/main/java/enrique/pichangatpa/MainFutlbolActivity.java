package enrique.pichangatpa;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import AbstractFactory.AbstractStadiumFactory;
import AbstractFactory.BrasilField;
import AbstractFactory.BrasilStadiumFactory;
import AbstractFactory.PeruStadiumFactory;


public class MainFutlbolActivity extends ActionBarActivity {

    RelativeLayout mFrame;
    private int widthDisplay;
    private int heightDisplay;
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
}
