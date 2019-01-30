package com.google.firebase.example.seamosmejoresmaestros;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class EditarPubActivity extends AppCompatActivity {

    private ImageView imagePub;
    private EditText nombrePub, apellidoPub, telefono, correo;
    private TextView fdiscurso, fayudante, fsustitucion;
    private RadioButton radioHombre, radioMujer;
    private CheckBox cbHabilitar;
    private String idPb;
    private Integer dia, mes, anual, diaAsignacion, mesAsignacion, anualAsignacion, diaAyudante, mesAyudante, anualAyudante, diaSust, mesSust, anualSust;
    private ProgressDialog progress;
    private Uri mipath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pub);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarEditPub);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        imagePub = (ImageView) findViewById(R.id.imageBtPublicador);
        nombrePub = (EditText) findViewById(R.id.etNombre);
        apellidoPub = (EditText) findViewById(R.id.etApellido);
        telefono = (EditText) findViewById(R.id.etTelefono);
        correo = (EditText) findViewById(R.id.etCorreo);
        radioHombre = (RadioButton) findViewById(R.id.radioHombre);
        radioMujer = (RadioButton) findViewById(R.id.radioMujer);
        cbHabilitar = (CheckBox) findViewById(R.id.cbHabilitar);
        fdiscurso = (TextView) findViewById(R.id.etfdiscurso);
        fayudante = (TextView) findViewById(R.id.etfayudante);
        fsustitucion = (TextView) findViewById(R.id.etfsustitucion);

        Bundle myBundle = this.getIntent().getExtras();
        idPb = myBundle.getString("idEditar");

        progress = new ProgressDialog(EditarPubActivity.this);
        progress.setMessage("Cargando...");
        progress.show();
        cargarDetalles ();

        FloatingActionButton fabImagen = (FloatingActionButton)findViewById(R.id.fabEditImage);
        fabImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen ();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_editar_pub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_save_pub) {
            guardarPublicador();
            return true;
        } else if (id == R.id.menu_cancel_pub) {
            Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void cargarDetalles() {
        FirebaseFirestore dbFirestore = FirebaseFirestore.getInstance();
        CollectionReference reference = dbFirestore.collection("publicadores");

        reference.document(idPb).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    nombrePub.setText(doc.getString("nombre"));
                    apellidoPub.setText(doc.getString("apellido"));
                    correo.setText(doc.getString("correo"));
                    telefono.setText(doc.getString("telefono"));

                    if (doc.getString("genero").equals("Hombre")) {
                        radioHombre.setChecked(true);
                        imagePub.setImageResource(R.drawable.ic_caballero);
                    } else if (doc.getString("genero").equals("Mujer")) {
                        imagePub.setImageResource(R.drawable.ic_dama);
                        radioMujer.setChecked(true);
                    }

                    if (doc.getBoolean("habilitado") == false) {
                        cbHabilitar.setChecked(true);
                    }

                    progress.dismiss();

                } else {
                    progress.dismiss();
                    Toast.makeText(getApplicationContext(), "Error al cargar. Intente nuevamente", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void guardarPublicador() {
        final ProgressDialog progressDialog = new ProgressDialog(EditarPubActivity.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();

        if (mipath != null) {
            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(mipath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    Toast.makeText(getApplicationContext(), "Imagen Guardada", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Error al guardar", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Subiendo " + (int)progress+ "%");
                }
            });
        }
    }

    private void cargarImagen() {
        Intent myintent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        myintent.setType("image/");
        startActivityForResult(myintent.createChooser(myintent, "Seleccione ubicación"), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            mipath = data.getData();
            imagePub.setImageURI(mipath);

        }

    }
}
