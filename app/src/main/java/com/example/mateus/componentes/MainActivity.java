package com.example.mateus.componentes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener {
    ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mapeamento
        mViewHolder.mTextSeek = (TextView) findViewById(R.id.text_seek);
        mViewHolder.mButtonToast = (Button) findViewById(R.id.toast_button);
        mViewHolder.mButtonSnackBar = (Button) findViewById(R.id.snackbar_button);
        mViewHolder.mButtonSetSpinner = (Button) findViewById(R.id.set_spinner);
        mViewHolder.mButtonGetSpinner = (Button) findViewById(R.id.get_spinner);
        mViewHolder.mButtonProgressDialog = (Button) findViewById(R.id.button_progress_dialog);
        mViewHolder.mButtonGetSeek = (Button) findViewById(R.id.get_seek);
        mViewHolder.mButtonSetSeek = (Button) findViewById(R.id.set_seek);
        mViewHolder.mConstraintLayout = (ConstraintLayout) findViewById(R.id.constraint_layout);
        mViewHolder.mSpinnerDynamic = (Spinner) findViewById(R.id.dynamic_spinner);
        mViewHolder.mSeekbar = (SeekBar) findViewById(R.id.seekbar);
        mViewHolder.mProgressDialog = new ProgressDialog(this);

    }

    private void setListener(){
        mViewHolder.mButtonToast.setOnClickListener(this);
        mViewHolder.mButtonSnackBar.setOnClickListener(this);
        mViewHolder.mButtonSetSpinner.setOnClickListener(this);
        mViewHolder.mButtonGetSpinner.setOnClickListener(this);
        mViewHolder.mButtonGetSeek.setOnClickListener(this);
        mViewHolder.mButtonSetSeek.setOnClickListener(this);
        mViewHolder.mButtonProgressDialog.setOnClickListener(this);
    }

    private void carregaSpinners(){
        List<String> lista = new ArrayList<>();
        lista.add("Purreta dinâmica");
        lista.add("Cry me a river");
        lista.add("Jurassic World Evolution");

        // Cria o adapter do spinner: contexto, layout padrão, lista de objetos
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lista);
        mViewHolder.mSpinnerDynamic.setAdapter(adapter);

    }

    @Override
    public void onResume(){
        super.onResume();

        this.setListener();
        this.carregaSpinners();

        // On item selected listener
        this.mViewHolder.mSpinnerDynamic.setOnItemSelectedListener(this);

        // On Seek bar change listener
        this.mViewHolder.mSeekbar.setOnSeekBarChangeListener(this);
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

        }else if(v.getId() == R.id.snackbar_button){

            // Cria snackbar
            Snackbar snackbar = Snackbar.make(mViewHolder.mConstraintLayout, "Snackbar Motherfucker!", Snackbar.LENGTH_LONG);

            // Button snackbar interativo, como "Desfazer"
            snackbar.setAction("Desfazer", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(mViewHolder.mConstraintLayout, "Desfeito com sucesso", Snackbar.LENGTH_LONG).show();
                }
            });

            // Muda cor do texto
            TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.BLACK);

            // Muda cor do background do snack
            View view = snackbar.getView();
            view.setBackgroundColor(Color.BLUE);


            snackbar.show();

        }else if(v.getId() == R.id.get_spinner){
            // Pega o que está selecionado no spinner
            String value = mViewHolder.mSpinnerDynamic.getSelectedItem().toString();

            Snackbar.make(mViewHolder.mSpinnerDynamic, value, Snackbar.LENGTH_LONG).show();


        }else if(v.getId() == R.id.set_spinner){
            mViewHolder.mSpinnerDynamic.setSelection(2);

        }else if(v.getId() == R.id.button_progress_dialog){
            mViewHolder.mProgressDialog.setTitle("Progress Dialog - Depreciated!");
            mViewHolder.mProgressDialog.setMessage("Infelizmente está em desuso, testar notificações ou ProgressBar " +
                    "(ver comentários videoaula deste assunto");
            mViewHolder.mProgressDialog.show();

        }else if(v.getId() == R.id.get_seek){
            // Pega progresso e joga na tela
            int value = mViewHolder.mSeekbar.getProgress();
            Toast.makeText(this, String.valueOf(value), Toast.LENGTH_LONG).show();

        }else if(v.getId() == R.id.set_seek){
            mViewHolder.mSeekbar.setProgress(50);
        }
    }


    // Ao clicar no item selecionado, os métodos são disparados
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // pega o valor selecionado do spinner
        String value = parent.getItemAtPosition(position).toString();
        //escreve no toast o valor selecionado
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // Quando o valor do seekbar for alterado
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        // se for o seekbar que estou mexendo, faça
        if(seekBar.getId() == R.id.seekbar){
            this.mViewHolder.mTextSeek.setText(String.valueOf(progress));
        }



    }

    // quando o touch estiver em cima
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    // quando o touch não estiver em cima
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    private static class ViewHolder{
        private TextView mTextSeek;
        private Button mButtonToast;
        private Button mButtonSnackBar;
        private Button mButtonSetSpinner;
        private Button mButtonGetSpinner;
        private Button mButtonSetSeek;
        private Button mButtonGetSeek;
        private SeekBar mSeekbar;
        private Button mButtonProgressDialog;
        private ConstraintLayout mConstraintLayout;
        private Spinner mSpinnerDynamic;
        private ProgressDialog mProgressDialog;

    }

}


