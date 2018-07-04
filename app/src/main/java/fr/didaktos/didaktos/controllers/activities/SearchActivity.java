package fr.didaktos.didaktos.controllers.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.didaktos.didaktos.R;

public class SearchActivity extends AppCompatActivity
    implements AdapterView.OnItemSelectedListener{

    String TAG = "Search Activity";

    //FOR DESIGN
    @BindView(R.id.activity_search_toolbar) Toolbar mToolbar;
    @BindView(R.id.search_topic) Spinner spinnerTopic;

    //FOR DATA
    private String topicSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        this.configureToolbar();
        this.configureSpinner(R.array.search_topic, spinnerTopic);
    }

    //--------------------------------
    //CONFIGURATION
    //--------------------------------

    private void configureToolbar(){
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void configureSpinner(int idRStringArray, Spinner spinner){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                idRStringArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    //--------------------------------
    //ACTIONS
    //--------------------------------

    @OnClick(R.id.search_btn)
    void searchBtnClick(){
        Log.e(TAG, "topic selected = " + topicSelected);
        Intent intentSearch = new Intent(this, MainActivity.class);
        intentSearch.putExtra("topicSelected", topicSelected);
        startActivity(intentSearch);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        topicSelected = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

}
