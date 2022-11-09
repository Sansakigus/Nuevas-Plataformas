package org.idnp.labidnp_03;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static org.idnp.labidnp_03.MainActivity.REQUEST_CODE_POSTULANTE;
import static org.idnp.labidnp_03.MainActivity.lista;
import static org.idnp.labidnp_03.MainActivity.objeto;
import static org.idnp.labidnp_03.MainActivity.listaPostulantes;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    public Button btnIngresar;
    public EditText userPos;
    public EditText contrasenia;
    public TextView nota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userPos = findViewById(R.id.userPostu);
        contrasenia = findViewById(R.id.contraPostu);
        btnIngresar = findViewById(R.id.btnActivityLogin);
        nota = findViewById(R.id.nota);
        
        btnIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivityForResult(new Intent(getApplicationContext(),PostulanteInfoActivity.class),REQUEST_CODE_POSTULANTE);
                Intent intent = new Intent();
                String userPostulante = userPos.getText().toString();
                String contraseniaPostulante = contrasenia.getText().toString();
                for(int i=0;i< listaPostulantes.size();i++){
                    if(userPostulante.equals( listaPostulantes.get(i).getNombres())){
                        if(contraseniaPostulante.equals(listaPostulantes.get(i).getId())){
                            intent.putExtra(objeto, listaPostulantes);
                            setResult(RESULT_OK, intent);
                            Toast.makeText(getApplicationContext(), "Sesión iniciada correctamente" , Toast.LENGTH_SHORT).show();
                            startActivityForResult(new Intent(getApplicationContext(),MenuActivity.class),REQUEST_CODE_POSTULANTE);
                        }else{
                            Toast.makeText(getApplicationContext(), "Ingrese contraseña correctamente" , Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Ingrese su usuario nuevamente" , Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                Intent intent1 = new Intent();
                intent1.putExtra(lista, listaPostulantes);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_POSTULANTE) {
            if(resultCode == RESULT_OK){
                nota.setText(listaPostulantes.get(listaPostulantes.size()-1).getId());
            }else if(resultCode == RESULT_CANCELED){
                nota.setText("NO SE PUDO INICIAR SESIÓN");
            }
        }
    }
}