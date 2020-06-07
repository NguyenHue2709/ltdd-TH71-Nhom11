package com.example.mytheduc.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.fragment.app.DialogFragment;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.mytheduc.Fragment.Fragment_TimePickerNhacNho;
import com.example.mytheduc.R;

import java.net.ConnectException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public  class CaiDatActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, NumberPicker.OnValueChangeListener {

    static Dialog d ;
     Button btnAlertDialog;
     TextView txtAc;

     Button nPRelay;
     TextView NumberRelay;

     Button npTime;
     TextView NumberTime;

     Button nPSafe;
     TextView NumberSafe;

     Button nPCountDown;
     TextView NumberCountDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);
        /*Thanh Tiêu đề*/
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Cài đặt");

        btnAlertDialog = (Button) findViewById(R.id.btn_Ac);
        txtAc = (TextView) findViewById(R.id.txt_Ac);
        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAlertDialog();
            }
        });

        nPRelay = (Button) findViewById(R.id.btn_DialogNumberPicker);
        NumberRelay = (TextView) findViewById(R.id.txt_Laplai);
        NumberRelay.setText("1 lần");
        nPRelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPickerDialog();
            }
        });

        npTime = (Button) findViewById(R.id.btn_DialogTimePicker1);
        NumberTime = (TextView) findViewById(R.id.txt_MoiBaiTap);
        NumberTime.setText("10 giây");
        npTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog1();
            }
        });

        nPSafe = (Button) findViewById(R.id.btn_DialogTimePicker2);
        NumberSafe = (TextView) findViewById(R.id.txt_NghiNgoi);
        NumberSafe.setText("5 giây");
        nPSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog2();
            }
        });

        nPCountDown = (Button) findViewById(R.id.btn_DialogTimePicker3);
        NumberCountDown = (TextView) findViewById(R.id.txt_DemNguoc);
        NumberCountDown.setText("10 giây");
        nPCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog3();
            }
        });

        /* Switch Nhắc tập mỗi ngày */
        final LinearLayout ll = (LinearLayout) findViewById(R.id.llNhacTapMoiNgay);
        final Switch sh = (Switch) findViewById(R.id.switch_NhacTapMoiNgay);
        sh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(sh.isChecked())
                {
                    sh.setVisibility(View.VISIBLE);
                    ll.setVisibility(View.VISIBLE);
                }
                else
                {
                    sh.setVisibility(View.VISIBLE);
                    ll.setVisibility(View.GONE);
                }
            }
        });
        /* Mở đồng hồ thiết lập lặp lại */
        Button btn_Nhacnho = (Button) findViewById(R.id.btn_NhacTapThoiGian);
        btn_Nhacnho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timepickerNhacNho = new Fragment_TimePickerNhacNho();
                timepickerNhacNho.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayAlertDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
        final EditText etUsername = (EditText) alertLayout.findViewById(R.id.et_Username);
        final EditText etPassword = (EditText) alertLayout.findViewById(R.id.et_Password);
        final CheckBox cbShowPassword = (CheckBox) alertLayout.findViewById(R.id.cb_ShowPassword);

        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    etPassword.setTransformationMethod(null);
                else
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }

        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login");
        builder.setView(alertLayout);
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // code for matching password
                String user = etUsername.getText().toString();
                String pass = etPassword.getText().toString();
                Toast.makeText(getBaseContext(), "Username: " + user + " Password: " + pass, Toast.LENGTH_SHORT).show();
                txtAc.setText(etUsername.getText().toString());
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        Log.i("value is",""+newVal);

    }
    private void numberPickerDialog() {
        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.dialog1);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(6);
        np.setMinValue(1);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                NumberRelay.setText(String.valueOf(np.getValue()) + " lần"); //set the value to textview
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();

    }

    private void timePickerDialog1() {
        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.dialog2);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(60);
        np.setMinValue(10);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                NumberTime.setText(String.valueOf(np.getValue()) + " giây"); //set the value to textview
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();

    }

    private void timePickerDialog2() {
        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.dialog3);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(30);
        np.setMinValue(5);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                NumberSafe.setText(String.valueOf(np.getValue()) + " giây"); //set the value to textview
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();

    }

    public void timePickerDialog3() {
        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.dialog4);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(15);
        np.setMinValue(10);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                NumberCountDown.setText(String.valueOf(np.getValue()) + " giây"); //set the value to textview
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();

    }

    /* Gán giờ vào textview tại mục Lặp lại*/
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView txtv = (TextView) findViewById(R.id.txtNhacTapThoiGian);
        txtv.setText(hourOfDay + ":" + minute);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}