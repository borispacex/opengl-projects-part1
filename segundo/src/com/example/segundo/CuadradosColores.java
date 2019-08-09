package com.example.segundo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class CuadradosColores {

	private float vertices[] = {
			-2, -2, // 0
			
			-2, 2, // 1
			2, 2, // 2
			2, -2 //3
 
	};

	
	
	FloatBuffer bufVertices;

	public CuadradosColores(){
		// BUFFER 1
		//en aca se multiplica por 4 torque es float
		ByteBuffer bufByte = ByteBuffer.allocateDirect(vertices.length * 4);
		bufByte.order(ByteOrder.nativeOrder());	// Utiliza el orden del byte nativo
		bufVertices = bufByte.asFloatBuffer();	// Convierte de byte a float
		bufVertices.put(vertices);
		bufVertices.rewind();					// puntero al principio del buffer
		
		
	}
	
	public void dibuja(GL10 gl){
		/* Se activa el arreglo de v�rtices */
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		// BUFFER 1
		/* Se especifica los datos del arreglo de v�rtices */
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices);
		/* Se establece el color en (r,g,b,a) */
		gl.glColor4f(1, 0, 0, 1);
		/* Dibuja el tri�ngulo */
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
		/* Se desactiva el arreglo de v�rtices */
		
		
		
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
	// metodo para dar color al fondo
	
}
