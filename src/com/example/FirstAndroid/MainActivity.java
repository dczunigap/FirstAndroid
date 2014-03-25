package com.example.FirstAndroid;

import android.app.ActionBar;
import android.app.AlertDialog;
import com.example.FirstAndroid.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity {
    //Variables para controlar la suma.
    private EditText et1, et2, etmail;
    private TextView tv3;
    private RadioButton rb1, rb2;
    SharedPreferences preferences;

    //variables para TabView
    ActionBar.Tab tab1, tab2, tab3;
    Fragment fragmentTab1 = new FragmentTab1();
    Fragment fragmentTab2 = new Tab1();
    Fragment fragmentTab3 = new FragmentTab1();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Inicializamos los tabs
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        tab1 = actionBar.newTab().setText("Sumatoria");
        tab2 = actionBar.newTab().setText("Votación");
        tab3 = actionBar.newTab().setText("Nada");

        tab1.setTabListener(new MyTabListener(fragmentTab1));
        tab2.setTabListener(new MyTabListener(fragmentTab2));
        tab3.setTabListener(new MyTabListener(fragmentTab3));

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);
    }

}
