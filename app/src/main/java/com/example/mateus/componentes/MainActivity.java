package com.example.mateus.componentes;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mapeamento
        mViewHolder.mButtonToast = (Button) findViewById(R.id.toast_button);

        this.setListener();
    }

    private void setListener(){
        mViewHolder.mButtonToast.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.toast_button){

            Toast toast = Toast.makeText(this, "Mensagem du caralho!", Toast.LENGTH_LONG);

            // inflate xml toast_notification para ser um layout
            LayoutInflater inflater = getLayoutInflater();
            // parâmetros: xml que será o layout, e o id do layout dentro do xml
            View toastLayout = inflater.inflate(R.layout.toast_notification, (ViewGroup) findViewById(R.id.linear_layout_toast));
            // manda a view para o toast
            toast.setView(toastLayout);

            // Muda a cor do texto do toast
            TextView textView = (TextView) toast.getView().findViewById(R.id.text_view_toast);
            textView.setTextColor(Color.WHITE);

            // Muda a cor do texto do toast
            //TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
            //textView.setTextColor(Color.YELLOW);

            // Muda o background o texto do toast
            //View view = toast.getView();
            //view.setBackgroundColor(Color.BLACK);

            toast.show();
        }
    }

    private static class ViewHolder{
        private  Button mButtonToast;
    }

}


