package com.example.mateus.componentes;

import android.app.DatePickerDialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        View.OnClickListener, TimePicker.OnTimeChangedListener {
    private ViewHolder mViewHodler = new ViewHolder();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        mViewHodler.mButtonDatePicker = (Button) findViewById(R.id.button_date_picker);
        mViewHodler.mButtonGetTimePicker = (Button) findViewById(R.id.get_timepicker);
        mViewHodler.mButtonSetTimePicker = (Button) findViewById(R.id.set_timeícker);
        mViewHodler.mTimePicker = (TimePicker) findViewById(R.id.time_picker);

        this.setListener();

    }

    @Override
    public void onClick(View v) {
        
        if (v.getId() == R.id.button_date_picker) {
            this.carregaDatePicker();

        } else if (v.getId() == R.id.get_timepicker) {

            // Verifica se a versão API do android for maior que 23, usa os métodos atuais, senão, os métodos depreciated
            if (Build.VERSION.SDK_INT >= 23) {
                String hora = String.valueOf(mViewHodler.mTimePicker.getHour());
                String minuto = String.valueOf(mViewHodler.mTimePicker.getMinute());
                Toast.makeText(this, hora + ":" + minuto, Toast.LENGTH_LONG).show();
            } else {
                String hora = String.valueOf(mViewHodler.mTimePicker.getCurrentHour());
                String minuto = String.valueOf(mViewHodler.mTimePicker.getCurrentMinute());
                Toast.makeText(this, hora + ":" + minuto, Toast.LENGTH_LONG).show();
            }


        } else if (v.getId() == R.id.set_timeícker) {
            // Verifica se a versão API do android for maior que 23, usa os métodos atuais, senão, os métodos depreciated
            if (Build.VERSION.SDK_INT >= 23) {
                mViewHodler.mTimePicker.setHour(22);
                mViewHodler.mTimePicker.setMinute(00);

            } else {
                mViewHodler.mTimePicker.setCurrentHour(22);
                mViewHodler.mTimePicker.setCurrentMinute(00);
            }

        }
    }


    // Se o usuário apertar "Ok" no datapicker, é chamado esse método
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);

        // Padrozina a data do formato
        String date = simpleDateFormat.format(calendar.getTime());
        this.mViewHodler.mButtonDatePicker.setText(date);

    }

    private void setListener() {
        mViewHodler.mButtonDatePicker.setOnClickListener(this);
        mViewHodler.mButtonSetTimePicker.setOnClickListener(this);
        mViewHodler.mButtonGetTimePicker.setOnClickListener(this);
        mViewHodler.mTimePicker.setOnTimeChangedListener(this);
    }

    // Toda vez que o tempo for mudado no time picker esse método é chamado
    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

        String tempo = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
        Toast.makeText(this, tempo, Toast.LENGTH_SHORT).show();
    }

    private void carregaDatePicker() {

        // Pega a data de hoje
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);

        // Mostra datePicker na activity, juntamente com a data de hoje
        new DatePickerDialog(this, this, year, month, day).show();
    }


    public static class ViewHolder {
        Button mButtonDatePicker;
        Button mButtonGetTimePicker;
        Button mButtonSetTimePicker;
        TimePicker mTimePicker;
    }

}
