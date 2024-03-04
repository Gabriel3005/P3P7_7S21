package mx.edu.isc.tesoem.agcp.p3p7_7s21;

import static com.android.volley.Request.*;
import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import Concentrado.Peticiones;
import DTO.DatosDTO;

public class PrincipalActivity extends AppCompatActivity {

    String URL = "http://10.0.2.2/7s2123/api/peticion/server.php";
    GridView gvdatos;
    List<DatosDTO> ldatos = new ArrayList<>();
    List<String> sdatos = new ArrayList<>();
    EditText txtid, txtnombre, txtedad, txtcorreo;

    Button btninsertar, btnactualiza, btnelimina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtid = findViewById(R.id.txtid);
        txtnombre = findViewById(R.id.txtnombre);
        txtedad = findViewById(R.id.txtedad);
        txtcorreo = findViewById(R.id.txtcorreo);

        btninsertar = findViewById(R.id.btninsertar);
        btnactualiza = findViewById(R.id.btnactualiza);
        btnelimina = findViewById(R.id.btneliminar);

        gvdatos = findViewById(R.id.gvDATOS);
        Peticiones peticionweb = new Peticiones(this);
        peticionweb.regilla(gvdatos);
        peticionweb.CargarDatos();
        ldatos = peticionweb.getLdatos();
        sdatos = peticionweb.getSdatos();
        //peticionweb.cargarRegistroact("5");
        //peticionweb.RegistraNuevo("Prueba6","6","prueba6");
        //peticionweb.ActualizaRegistro("14","pru 6", "66", "pru6@prue6.com");
        //peticionweb.EliminarRegistro("8");

        gvdatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int celda, long l) {
                if ((celda>1) && ((celda % 2)==0)){
                    for (DatosDTO dato:ldatos) {
                        if (dato.getId() == Integer.parseInt(sdatos.get(celda))){
                            txtid.setText(String.valueOf(dato.getId()));
                            txtnombre.setText(dato.getNombre());
                            txtedad.setText(String.valueOf(dato.getEdad()));
                            txtcorreo.setText(dato.getCorreo());
                        }
                    }
                }
            }
        });

        btninsertar.setOnClickListener(v-> {
            String nombre = txtnombre.getText().toString();
            String edadStr = txtedad.getText().toString();
            String correo = txtcorreo.getText().toString();

            if (!nombre.isEmpty() && !edadStr.isEmpty() && !correo.isEmpty()){
                try {
                    int edad = Integer.parseInt(edadStr);

                    txtnombre.setText(nombre);
                    txtedad.setText(String.valueOf(edad));
                    txtcorreo.setText(correo);

                    peticionweb.RegistraNuevo(nombre, String.valueOf(edad), correo);
                }catch (NumberFormatException e){
                    Toast.makeText(PrincipalActivity.this, "La edad debe ser un valor entero valido", Toast.LENGTH_SHORT).show();

                }
            }else{
                Toast.makeText(PrincipalActivity.this, "Completa los datos", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
            limpiarDatosGridView();
            peticionweb.CargarDatos();
        });

        btnactualiza.setOnClickListener(v-> {
            String idS = txtid.getText().toString();
            String nombre = txtnombre.getText().toString();
            String edadStr = txtedad.getText().toString();
            String correo = txtcorreo.getText().toString();

            if (!idS.isEmpty()){
                try {
                    int id = Integer.parseInt(idS);
                    int edad = Integer.parseInt(edadStr);

                    peticionweb.ActualizaRegistro(String.valueOf(id), nombre, String.valueOf(edad), correo);
                }catch (NumberFormatException e){

                }
            }else {

            }
            Toast.makeText(this, "Se actualizo correctamente", Toast.LENGTH_SHORT).show();
            limpiarDatosGridView();
            peticionweb.CargarDatos();

        });

        btnelimina.setOnClickListener(v-> {
            String idS = txtid.getText().toString();

            if (!idS.isEmpty()){
                try {
                    int id = Integer.parseInt(idS);

                    peticionweb.EliminarRegistro(String.valueOf(id));
                }catch (NumberFormatException e){

                }
            }else {

            }
            Toast.makeText(this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
            limpiarDatosGridView();
            peticionweb.CargarDatos();
        });
    }

    private void limpiarDatosGridView(){
        sdatos.clear();
        ArrayAdapter<String> adaptador = (ArrayAdapter<String>) gvdatos.getAdapter();
        if (adaptador !=null){
            adaptador.notifyDataSetChanged();
        }
    }


}