package org.idnp.labidnp_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import static org.idnp.labidnp_03.MainActivity.listaPostulantes;

public class PostulanteInfoActivity extends AppCompatActivity {
    public Button btnBuscar;
    public Button btnCerrar;
    public EditText buscPos;
    public TextView dniInf;
    public TextView nombreInfo;
    public TextView apellidosInfo;
    public TextView fecNacInfo;
    public TextView coleInfo;
    public TextView CarrInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postulanteinfo);
        dniInf = findViewById(R.id.dniInfo);
        nombreInfo = findViewById(R.id.nombreInfo);
        apellidosInfo = findViewById(R.id.apellidosInfo);
        fecNacInfo = findViewById(R.id.fecNacInfo);
        coleInfo = findViewById(R.id.coleInfo);
        CarrInfo = findViewById(R.id.CarrInfo);
        buscPos = findViewById(R.id.buscPos);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnCerrar = findViewById(R.id.btnCerrar);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                Toast.makeText(getApplicationContext(), "Sesión cerrada exitosamente" , Toast.LENGTH_SHORT).show();
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buscar = buscPos.getText().toString();
                boolean encontrado = false;
                if(!buscar.equals("")){
                    for(int i=0;i<(int)listaPostulantes.size() && encontrado==false;i++){
                        if(buscar.equals(listaPostulantes.get(i).getId())){
                            encontrado=true;
                            dniInf.setText(listaPostulantes.get(i).getId());
                            nombreInfo.setText(listaPostulantes.get(i).getNombres());
                            apellidosInfo.setText(listaPostulantes.get(i).getApellidoPaterno()+" "+listaPostulantes.get(i).getApellidoMaterno());
                            fecNacInfo.setText(listaPostulantes.get(i).getFechaNacimiento());
                            coleInfo.setText(listaPostulantes.get(i).getColegioProcedencia());
                            CarrInfo.setText(listaPostulantes.get(i).getCarreraPostula());
                            Toast.makeText(getApplicationContext(), "Postulante Encontrado" , Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(encontrado==false){
                        Toast.makeText(getApplicationContext(), "No se Encontró al Postulante" , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}