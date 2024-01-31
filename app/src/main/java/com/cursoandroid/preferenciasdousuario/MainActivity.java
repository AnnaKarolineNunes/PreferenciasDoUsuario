package com.cursoandroid.preferenciasdousuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private Button buttonSalvar;
    private TextInputEditText editNome;
    private TextView textResultado;
    private static String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSalvar = findViewById(R.id.buttonSalvar);
        editNome = findViewById(R.id.editNome);
        textResultado = findViewById(R.id.textResultado);

        // evento de clique
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // classe para salvar as preferencias do usuario. Ela cria um arquivo xml, e dentro dele conseguimos salvar as informaçoes
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0); // o modo zero define o modo privado, permitindo apenas este aplicativo salvar e ler os arquivos
                SharedPreferences.Editor editor = preferences.edit();

                // validar o nome
                if (editNome.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Preencha o nome", Toast.LENGTH_LONG).show();
                }else {
                    String nome = editNome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit();
                    textResultado.setText("Olá, " + nome);
                }
            }
        });

        //Recuperar dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);

        // Valida se temos o nome preferencias
        if (preferences.contains("")){
            String nome = preferences.getString("nome", "usuario nao definido");
            textResultado.setText("Olá, " + nome);
        }else {
            textResultado.setText("Olá, usuario nao definido");
        }
    }
}
