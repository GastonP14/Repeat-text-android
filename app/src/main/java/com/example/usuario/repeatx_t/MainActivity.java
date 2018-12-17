package com.example.usuario.repeatx_t;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText input, veces, textoResultado;
    Button enviar, borrar, copiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.txt_ingreso);
        veces = (EditText) findViewById(R.id.txt_veces);
        textoResultado = (EditText) findViewById(R.id.txt_campoResultado);
        enviar = (Button) findViewById(R.id.btn_enviar);
        borrar = (Button) findViewById(R.id.btn_borrar);
        copiar = (Button) findViewById(R.id.botonCopiar);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidad = Integer.parseInt(veces.getText().toString());
                String cadena = "";
                for (int i = 0; i <= cantidad; i++) {
                    String dato = input.getText().toString();
                    cadena += dato;
                }
                textoResultado.setText(cadena);
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                textoResultado.setText("");
                veces.setText("");
            }
        });

//METODO PARA COPIAR AL PORTAPAPELES

        copiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sdk = android.os.Build.VERSION.SDK_INT;
                if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(textoResultado.getText().toString());
                    Toast.makeText(MainActivity.this, "¡Texto copiado!", Toast.LENGTH_SHORT).show();
                } else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("Etiqueta",textoResultado.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(MainActivity.this, "¡Texto copiado!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

