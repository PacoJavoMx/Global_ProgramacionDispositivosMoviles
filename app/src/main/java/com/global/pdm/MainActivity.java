package com.global.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    TextView totalPreguntasView;
    TextView preguntasTextView;
    Button resA,resB,resC,resD;
    Button enviarBtn;

    int puntaje = 0;
    int totalPreguntas = RespuestasPreguntas.pregunta.length;
    int actualPreguntaIndex = 0;
    String respuestaSeleccionada = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalPreguntasView = findViewById(R.id.total_pregunta);
        preguntasTextView = findViewById(R.id.pregunta);
        resA = findViewById(R.id.res_A);
        resB = findViewById(R.id.res_B);
        resC = findViewById(R.id.res_C);
        resD = findViewById(R.id.res_D);
        enviarBtn = findViewById(R.id.enviar_btn);

        resA.setOnClickListener(this);
        resB.setOnClickListener(this);
        resC.setOnClickListener(this);
        resD.setOnClickListener(this);
        enviarBtn.setOnClickListener(this);

        totalPreguntasView.setText("Total Preguntas: " + totalPreguntas);

        loadNuevaPregunta();
    }

    @Override
    public void onClick(View view){

        resA.setBackgroundColor(Color.WHITE);
        resB.setBackgroundColor(Color.WHITE);
        resC.setBackgroundColor(Color.WHITE);
        resD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.enviar_btn){
            if(respuestaSeleccionada.equals(RespuestasPreguntas.respuestaCorrecta[actualPreguntaIndex])){
                puntaje++;
            }
            actualPreguntaIndex++;
            loadNuevaPregunta();
        }else{
            //Opciones del button seleccionado
            respuestaSeleccionada = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.RED);
        }

    }

        void loadNuevaPregunta(){

            if(actualPreguntaIndex == totalPreguntas){
                terminarQuiz();
                return;
            }


            preguntasTextView.setText(RespuestasPreguntas.pregunta[actualPreguntaIndex]);
            resA.setText(RespuestasPreguntas.opciones[actualPreguntaIndex][0]);
            resB.setText(RespuestasPreguntas.opciones[actualPreguntaIndex][1]);
            resC.setText(RespuestasPreguntas.opciones[actualPreguntaIndex][2]);
            resD.setText(RespuestasPreguntas.opciones[actualPreguntaIndex][3]);

        }

        void terminarQuiz(){
            String passStatus = "";
            if(puntaje > totalPreguntas*0.60){
                passStatus = "Felicidades";
            }else{
                passStatus = "Sigue intentando";
            }

            new AlertDialog.Builder(this)
                    .setTitle(passStatus)
                    .setMessage("Puntaje total es " + puntaje*10)
                    .setPositiveButton("Reiniciar preguntas",(dialogInterface, i) -> reiniciarQuiz() )
                    .setCancelable(false)
                    .show();

        }

        void reiniciarQuiz(){
            puntaje = 0;
            actualPreguntaIndex = 0;
            loadNuevaPregunta();
        }


    }
