package com.example.exerrcisecovid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //Alert Dialog
    private Button Btn_Daftar;
    //Validation
    EditText etnamadepan,etnamabelakang,ettempatlahir,ettanggallahir1,etalamat,ettelepon,etemail,etpassword,etulangi;
    Button btndaftar;

    AwesomeValidation awesomeValidation;

    //DatePacker
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    EditText ettanggallahir;

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AlertDialog
        Btn_Daftar = (Button) findViewById(R.id.btn_daftar);
        Btn_Daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        //Assign Variable
        etnamadepan = findViewById(R.id.et_namadepan);
        etnamabelakang = findViewById(R.id.et_namabelakang);
        ettempatlahir = findViewById(R.id.et_tempatlahir);
        ettanggallahir1 = findViewById(R.id.ettanggallahir);
        etalamat = findViewById(R.id.et_alamat);
        ettelepon = findViewById(R.id.et_telepon);
        etemail = findViewById(R.id.et_email);
        etpassword = findViewById(R.id.et_password);
        etulangi = findViewById(R.id.et_ulangi);
        btndaftar = findViewById(R.id.btn_daftar);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.et_namadepan,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.et_namabelakang,
                RegexTemplate.NOT_EMPTY,R.string.invalid_belakang);
        awesomeValidation.addValidation(this,R.id.et_tempatlahir,
                RegexTemplate.NOT_EMPTY,R.string.invalid_tl);
        awesomeValidation.addValidation(this,R.id.ettanggallahir,
                RegexTemplate.NOT_EMPTY,R.string.invalid_tanggal);
        awesomeValidation.addValidation(this,R.id.et_alamat,
                RegexTemplate.NOT_EMPTY,R.string.invalid_alamat);
        awesomeValidation.addValidation(this,R.id.et_telepon,
                RegexTemplate.NOT_EMPTY,R.string.invalid_telepon);
        awesomeValidation.addValidation(this,R.id.et_email,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(this,R.id.et_password,
                ".{6,}",R.string.invalid_password);
        awesomeValidation.addValidation(this,R.id.et_ulangi,
                R.id.et_password,R.string.invalid_ulangi);

        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    showDialog();
                }else {
                    Toast.makeText(getApplicationContext(),
                            "",Toast.LENGTH_SHORT).show();
                }
            }
        });


        //DatePicker
        ettanggallahir = (EditText) findViewById(R.id.ettanggallahir);

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        ettanggallahir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    //AlertDialog
    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("Konfirmasi...");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Apakah Data yang anda masukkan sudah benar?")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        MainActivity.this.finish();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }


    //Untuk button exit
    public void clickexit(View v)
    {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    //Date Picker
    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        ettanggallahir.setText(sdf.format(myCalendar.getTime()));
    }

}

