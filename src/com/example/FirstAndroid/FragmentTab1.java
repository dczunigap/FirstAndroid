package com.example.FirstAndroid;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by dczunigap on 23/03/14.
 */
public class FragmentTab1 extends Fragment {
    private EditText et1, et2, etmail;
    private TextView tv3;
    private RadioButton rb1, rb2;
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab, container, false);

        //Seteamos las variables del Layout principal
        et1 = (EditText) view.findViewById(R.id.et1);
        et2 = (EditText) view.findViewById(R.id.et2);
        tv3 = (TextView) view.findViewById(R.id.tv3);
        rb1 = (RadioButton) view.findViewById(R.id.rb1);
        rb2 = (RadioButton) view.findViewById(R.id.rb2);
        etmail = (EditText) view.findViewById(R.id.etmail);

        preferences = view.getContext().getSharedPreferences("datos", Context.MODE_PRIVATE);
        etmail.setText(preferences.getString("mail", ""));

        return view;
    }

    //Este metodo se ejecutara cuando se seleccione Suma
    public void sumar(View view){
        Integer nro1 = Integer.parseInt(et1.getText().toString());
        Integer nro2 = Integer.parseInt(et2.getText().toString());
        Integer suma = nro1+nro2;

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Amazing!!!");
        builder.setMessage(etmail.getText().toString().concat(", tu resultado es: ").concat(String.valueOf(suma)));
        builder.setPositiveButton("Ok", null);
        builder.create();
        builder.show();

        tv3.setText("... Amazing!!! ¬¬'");
    }

    //Este metodo se ejecutara cuando se seleccione Resta
    private void restar(View view) {
        Integer nro1 = Integer.parseInt(et1.getText().toString());
        Integer nro2 = Integer.parseInt(et2.getText().toString());
        Integer resta = nro1-nro2;

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Amazing!!!");
        builder.setMessage(etmail.getText().toString().concat(", tu resultado es: ").concat(String.valueOf(resta)));
        builder.setPositiveButton("Ok", null);
        builder.create();
        builder.show();

        tv3.setText("... Amazing!!! ¬¬'");
    }

    //Metodo que ejecutara la operacion seleccionada
    public void ejecutar(View view){
    	preferences = view.getContext().getSharedPreferences("datos", Context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = preferences.edit();
    	editor.putString("mail", etmail.getText().toString());
    	editor.commit();

        if(rb1.isChecked()){
            sumar(view);
        }else if (rb2.isChecked()){
            restar(view);
        }
    }

    //Metodo para lanzar ventana
    /*public void lanzar(View view){
        Intent i = new Intent(this, Tab1.class);
        startActivity(i);
    }*/
}
