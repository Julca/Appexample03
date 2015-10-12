package com.example.ulices.appexample03;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class Activity_logeo extends ActionBarActivity {
    sqlite cx;
    EditText edit1,edit2;
    Cursor lista_usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_logeo);
        cx = new sqlite(this, "BDventa", null, 1);
        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
    }
    public  void valida(View v){
        lista_usuarios=cx.getReadableDatabase().rawQuery("SELECT campo1,campo2 FROM usuario",null);
        //una bandera
        boolean flag=false;
        int n=0;
        if(lista_usuarios.moveToFirst()){
            do {
                if(lista_usuarios.getString(0).toString().equals(edit1.getText().toString())){
                    if(lista_usuarios.getString(1).toString().equals(edit2.getText().toString())){
                        flag=true;
                        break;
                    }
                    else{
                            flag=false;
                    }
                }
                else{
                    flag=false;

                }
            }while (lista_usuarios.moveToNext());
        }
        if(flag){
            Intent i=new Intent(Activity_logeo.this,MainActivity.class);
            finish();
            startActivity(i);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(Activity_logeo.this);
            builder.setMessage("Usuario incorrecto").show();
        }
    }
}
