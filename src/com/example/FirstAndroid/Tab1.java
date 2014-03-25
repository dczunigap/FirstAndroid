package com.example.FirstAndroid;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.example.FirstAndroid.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by dzuniga on 22/02/14.
 */
public class Tab1 extends Fragment {
    private EditText etDni, etNomApe, etColegio, etMesa;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);
        setContext(view.getContext());

        etDni = (EditText) view.findViewById(R.id.etDni);
        etNomApe = (EditText) view.findViewById(R.id.etNomApe);
        etColegio = (EditText) view.findViewById(R.id.etColegio);
        etMesa = (EditText) view.findViewById(R.id.etMesa);

        return view;
    }

    public void alta(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(view.getContext(), "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String dni = etDni.getText().toString();
        String nombre = etNomApe.getText().toString();
        String colegio = etColegio.getText().toString();
        String nomesa = etMesa.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("dni", dni);
        registro.put("nombre", nombre);
        registro.put("colegio", colegio);
        registro.put("nomesa", nomesa);

        if (bd != null) {
            bd.insert("votantes", null, registro);
            bd.close();
        }

        reset();

        Toast.makeText(getContext(), "Se cargaron los datos de la persona", Toast.LENGTH_SHORT).show();
    }

    public void consulta(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(view.getContext(), "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String dni = etDni.getText()!=null ? etDni.getText().toString() : "";
        if(dni.length()>0){
            Cursor fila = null;
            if (bd != null) {
                fila = bd.rawQuery("select nombre, colegio, nomesa from votantes where dni=".concat(dni), null);
                if(fila.moveToFirst()){
                    etNomApe.setText(fila.getString(0));
                    etColegio.setText(fila.getString(1));
                    etMesa.setText(fila.getString(2));
                }else{
                    bd.close();
                    Toast.makeText(getContext(), "No existe la persona con el dni especificado", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            if (bd != null) {
                bd.close();
            }
            Toast.makeText(getContext(), "Debe teclear un numero de dni válido", Toast.LENGTH_SHORT).show();
        }
    }

    public void baja(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(view.getContext(), "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String dni = etDni.getText()!=null ? etDni.getText().toString() : "";
        if(dni.length()>0){
            int cant = 0;
            if (bd != null) {
                cant = bd.delete("votantes", "dni=".concat(dni), null);
                reset();

                if(cant==1) {
                    Toast.makeText(getContext(), "Se borró la persona con el dni especificado", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(), "No existe la persona con el dni especificado", Toast.LENGTH_SHORT).show();
                }
                bd.close();
            }
        }else{
            if (bd != null) {
                bd.close();
            }
            Toast.makeText(getContext(), "Debe teclear un numero de dni válido", Toast.LENGTH_SHORT).show();
        }
    }

    public void cambio(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getContext(), "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = etDni.getText()!=null ? etDni.getText().toString() : "";
        String nombre = etNomApe.getText().toString();
        String colegio = etColegio.getText().toString();
        String nomesa = etMesa.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("dni", dni);
        registro.put("nombre", nombre);
        registro.put("colegio", colegio);
        registro.put("nomesa", nomesa);

        if(dni.length()>0){
            int cant = 0;
            if (bd != null) {
                cant = bd.update("votantes", registro, "dni=".concat(dni), null);
                bd.close();
            }

            if(cant==1)
                Toast.makeText(getContext(), "Se modificaron los datos", Toast.LENGTH_SHORT).show();
            else{
                if (bd != null) {
                    bd.close();
                }
                Toast.makeText(getContext(), "No existe la persona con el dni especificado", Toast.LENGTH_SHORT).show();
            }
        }else{
            if (bd != null) {
                bd.close();
            }
            Toast.makeText(getContext(), "Debe teclear un numero de dni válido", Toast.LENGTH_SHORT).show();
        }
    }

    public void reset(){
        etDni.setText("");
        etNomApe.setText("");
        etColegio.setText("");
        etMesa.setText("");
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
