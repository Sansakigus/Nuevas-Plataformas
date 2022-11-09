package org.idnp.labidnp_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static org.idnp.labidnp_03.MainActivity.REQUEST_CODE_POSTULANTE;
import static org.idnp.labidnp_03.MainActivity.lista;
import static org.idnp.labidnp_03.MainActivity.objeto;
import static org.idnp.labidnp_03.MainActivity.listaPostulantes;

public class MenuActivity extends AppCompatActivity {
    public Postulante postulante;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnPostRegistro = findViewById(R.id.btnActivityPostulanteRegistro);

        btnPostRegistro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), PostulanteRegistroActivity.class), REQUEST_CODE_POSTULANTE);
                Intent intent = new Intent();
                intent.putExtra(lista, listaPostulantes);
            }
        });


        Button btnPostInfo = findViewById(R.id.btnActivityPostulanteInfo);

        btnPostInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PostulanteInfoActivity.class);
                startActivity(intent);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_POSTULANTE) {
            if(resultCode == RESULT_OK){
                postulante = (Postulante)data.getExtras().getSerializable(objeto);
                listaPostulantes.add(postulante);
            }else if(resultCode == RESULT_CANCELED){
                if(postulante == null){
                    Toast.makeText(getApplicationContext(), "No se pudo registrar al Postulante" , Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}