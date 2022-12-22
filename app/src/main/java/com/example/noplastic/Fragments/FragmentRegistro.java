package com.example.noplastic.Fragments;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.NotificationManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.noplastic.R;
import com.example.noplastic.db.DbHelper;
import com.example.noplastic.db.DbPlastico;

public class FragmentRegistro extends Fragment {


    EditText txtNombrePlastic, txtFecPlastic, txtOrigenPlastic, txtUbicacionPlastic, txtDescripcionPlastic;
    Spinner categoriaPlastic;
    Button btnRegistrarPlastic, btnTomarFotoPlastic;
    ImageView imgfotoPlastic;
    public FragmentRegistro() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initUI(view); //asignaciones
        crearDB(); //creación DB

        btnTomarFotoPlastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camaraLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE)); //obtener foto y mostrarlo
                btnTomarFotoPlastic.setText("CAMBIAR IMAGEN PLÁSTICO");
            }
        });

        btnRegistrarPlastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String NombrePl = txtNombrePlastic.getText().toString();
                String FecPl = txtFecPlastic.getText().toString();
                String OrigenPL = txtOrigenPlastic.getText().toString();
                String UbicacionPL = txtUbicacionPlastic.getText().toString();
                String DescripcionPL = txtDescripcionPlastic.getText().toString();
                String categoriaPL = categoriaPlastic.getSelectedItem().toString();

                if (!NombrePl.isEmpty() && !FecPl.isEmpty() && !OrigenPL.isEmpty() && !UbicacionPL.isEmpty() && !DescripcionPL.isEmpty()) {
                    DbPlastico dbPlastico = new DbPlastico(getActivity());
                    long id = dbPlastico.registrarPlastico(NombrePl, FecPl, OrigenPL, UbicacionPL, DescripcionPL, categoriaPL);

                    if( id > 0) {
                        Toast.makeText(getActivity(), "Plástico registrado exitasamente!!", Toast.LENGTH_LONG).show();
                        Limpiar();

                    } else {
                        Toast.makeText(getActivity(), "Ups, Ocurrió un error en registro!!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Debe agregar todos los datos para el Registro!!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    ActivityResultLauncher<Intent> camaraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK) {
                Bundle extras = result.getData().getExtras();
                Bitmap imgBitmap = (Bitmap) extras.get("data");
                imgfotoPlastic.setImageBitmap(imgBitmap);
            }
        }
    });
    private void crearDB() {
        DbHelper dbHelper = new DbHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase(); //escribir en db
        if(db != null) {
            Toast.makeText(getActivity(), "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "ERROR AL CREAR BASE DE DATOS", Toast.LENGTH_LONG).show();
        }
    }

    //Limpiar campos registro
    private void Limpiar() {
        txtNombrePlastic.setText("");
        txtFecPlastic.setText("");
        txtOrigenPlastic.setText("");
        txtUbicacionPlastic.setText("");
        txtDescripcionPlastic.setText("");
    }

    //initUI
    private void initUI(View view) {
        //Asignaciones
        txtNombrePlastic = view.findViewById(R.id.txtNamePlastic);
        txtFecPlastic =view.findViewById(R.id.txtFecPlastic);
        txtOrigenPlastic= view.findViewById(R.id.txtOrigenPlastic);
        txtUbicacionPlastic = view.findViewById(R.id.txtUbicationPlastic);
        txtDescripcionPlastic= view.findViewById(R.id.txtDescriptionPlastic);
        categoriaPlastic = view.findViewById(R.id.categoria_plastic);

        btnRegistrarPlastic = view.findViewById(R.id.btnRegistrar);
        btnTomarFotoPlastic = view.findViewById(R.id.btnCamara);

        imgfotoPlastic = view.findViewById(R.id.FotoPlastic); //por procesar
    }
}