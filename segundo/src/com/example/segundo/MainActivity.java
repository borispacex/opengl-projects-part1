package com.example.segundo;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Se crea el objeto GLSurfaceView */
        GLSurfaceView superficie = new GLSurfaceView(this);
        /* Se crea el objeto Renderiza */
        Renderiza renderiza = new Renderiza();
        /* GLSurfaceView <- Renderiza : Inicia el renderizado */
        superficie.setRenderer(renderiza);
        /*
        * Activity <- GLSurfaceView : Coloca la Vista de la Superficie del
        * OpenGL como un Contexto de ésta Actividad.
        */
        setContentView(superficie);//basicamente un cout XD
        
        //setContentView(R.layout.activity_main);//con este codigo se llama a la vista
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
