package com.example.carribeandeveloping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

import com.example.carribeandeveloping.Modelo.Conection;

public class MainActivity extends AppCompatActivity {
    //Variables por usar en el programa
    private EditText usuario;
    private EditText password;
    private TextView msg;
    private Button login;
    private String userType;
    //private Conection db = new Conection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario= (EditText)findViewById(R.id.usuario);
        password= (EditText)findViewById(R.id.password);
        login= (Button)findViewById(R.id.btn_login);
        msg =(TextView)findViewById(R.id.errorMsg);

        userType="huevobanano";
        validate();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkIn();
            }
        });
    }

    /**
     * Esta función valida los datos por entrar al sistema

     */
    private void validate(){

        if(userType.equals("admin")){

            Intent intent = new Intent(MainActivity.this, menu_Sourveyor_Reefer.class);
            startActivity(intent);
        }
        else{
            //msg.setText("Usuario o contraseña incorrecto");
        }
    }
    private void checkIn(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    Conection db = new Conection();
                    Integer n=db.getLogin(usuario.getText().toString(),password.getText().toString());

                    if(db.getLogin(usuario.getText().toString(),password.getText().toString())==1){
                        userType="admin";
                    }
                    else{
                        userType="null";
                    }
                    System.out.println(usuario.getText().toString()+" "+password.getText().toString());


                }
                catch (Exception e)
                { e.printStackTrace(); }
            }
        });
        thread.start();
        while(thread.isAlive()){

        }
        validate();
    }
}
