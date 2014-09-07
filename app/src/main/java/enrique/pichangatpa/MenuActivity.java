package enrique.pichangatpa;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import enrique.pichangatpa.R;

public class MenuActivity extends ActionBarActivity {

    private static final String TAG = "MENUACTIVITY";
    private static final String DEFAULTEAM = "Peru";
    private Button mBttnPlay;
    private Button mBttnSettings;
    private Button mBttnQuit;
    private EditText mEditTextTeam;
    private int countryIndex;
    private int opponentIndex;
    static private final int GET_TEXT_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);

        mBttnPlay = (Button) findViewById(R.id.bttnPlay);
        mBttnSettings = (Button) findViewById(R.id.bttnSettings);
        mBttnQuit =(Button) findViewById(R.id.bttnExit);
        mEditTextTeam = (EditText) findViewById(R.id.mEditTextTeam);

        mBttnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent().setClass(
                        MenuActivity.this,MainFutlbolActivity.class);
                mainIntent.putExtra("CountryTeam",countryIndex);
                mainIntent.putExtra("OpponentTeam",opponentIndex);
                startActivity(mainIntent);
            }
        });
        mBttnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MenuActivity.this,SettingsActivity.class);
                // TODO - Start an Activity using that intent and the request code defined above
                startActivityForResult(mIntent,GET_TEXT_REQUEST_CODE );
            }
        });
        mBttnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mEditTextTeam.setText(DEFAULTEAM);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i(TAG, "Entered onActivityResult()");

        // TODO - Process the result only if this method received both a
        // RESULT_OK result code and a recognized request code
        // If so, update the Textview showing the user-entered text.
        if(requestCode == GET_TEXT_REQUEST_CODE ){
            if(resultCode == RESULT_OK){
                countryIndex = (Integer) data.getIntExtra("mCountryTeam",0);
                opponentIndex = (Integer) data.getIntExtra("mOpponentTeam",0);
                Resources res = getResources();
                String[] myCountry = res.getStringArray(R.array.country_arrays);

                mEditTextTeam.setText(myCountry[countryIndex]);
            }
        }


    }
}
