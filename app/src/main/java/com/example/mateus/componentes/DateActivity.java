package com.example.mateus.componentes;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener {
    private ViewHolder mViewHodler = new ViewHolder();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        mViewHodler.mButtonDatePicker = (Button) findViewById(R.id.button_date_picker);

        mViewHodler.mButtonDatePicker.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_date_picker){
            this.carregaDatePicker();
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

    private void carregaDatePicker(){

        // Pega a data de hoje
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);

        // Mostra datePicker na activity, juntamente com a data de hoje
        new DatePickerDialog(this, this, year, month, day).show();
    }



    public static class ViewHolder{
        Button mButtonDatePicker;
    }

}
