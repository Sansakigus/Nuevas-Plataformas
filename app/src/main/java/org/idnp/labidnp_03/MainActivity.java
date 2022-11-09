package org.idnp.labidnp_03;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.GetContent;
import java.util.ArrayList;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_POSTULANTE = 1;
    public Postulante postulante;
    private TextView txtTabla;
    private Button btnRegistro;
    public Button btnLogin;
    public static final String objeto = "objeto";
    public static final String lista = "lista";
    public static final ArrayList<Postulante> listaPostulantes = new ArrayList<Postulante>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnLogin = findViewById(R.id.btnLogin1);
        btnRegistro = findViewById(R.id.btnActivityMenu);
        txtTabla =findViewById(R.id.textTabla);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), LoginActivity.class),REQUEST_CODE_POSTULANTE);
                Intent intent = new Intent();
                intent.putExtra(lista,  listaPostulantes);
            }
        });


        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), PostulanteRegistroActivity.class), REQUEST_CODE_POSTULANTE);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_POSTULANTE) {
            if(resultCode == RESULT_OK){
                postulante = (Postulante)data.getExtras().getSerializable(objeto);
                listaPostulantes.add(postulante);
            }else if(resultCode == RESULT_CANCELED){
                if(postulante == null){
                    txtTabla.setText("NO SE PUDO REGISTRAR AL POSTULANTE");
                }
            }
        }
    }
}