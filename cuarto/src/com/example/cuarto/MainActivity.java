package com.example.cuarto;

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
    	//Renderiza renderiza = new Renderiza();
    	Renderiza1 renderiza1 = new Renderiza1();
    	//Renderiza2 renderiza2 = new Renderiza2();
    	
    	/* GLSurfaceView <- Renderiza : Inicia el renderizado */
    	//superficie.setRenderer(renderiza);
    	superficie.setRenderer(renderiza1);
    	//superficie.setRenderer(renderiza2);
    	
    	/*
    	* Activity <- GLSurfaceView : Coloca la Vista de la Superficie del
    	* OpenGL como un Contexto de ésta Actividad.
    	*/
    	setContentView(superficie);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
/*
 *  320px480p
 * 
 * 	x = (posx * (8/320)) - 4
 *
 *	y = (12 - posy * (12/480) ) -6
 */
