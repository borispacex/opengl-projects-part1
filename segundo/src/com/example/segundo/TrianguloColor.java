package com.example.segundo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class TrianguloColor {
	/**
	* 		    2
	* 		    /\
	* 		   /  \
	* 		  /    \
	* 		 /      \
	* 		/________\
	* 		0        1
	*/
	
	private float vertices[] = {
			-2, -2, // 0
			2, -2, // 1
			0, 2 // 2
 
	};
	private float vertices1[] = {
			-2, -3, // 0
			1, -3, // 1
			-1, 1 // 2
 
	};
	
	
	FloatBuffer bufVertices;
	FloatBuffer bufVertices1;
	public TrianguloColor(){
		// BUFFER 1
		//en aca se multiplica por 4 torque es float
		ByteBuffer bufByte = ByteBuffer.allocateDirect(vertices.length * 4);
		bufByte.order(ByteOrder.nativeOrder());	// Utiliza el orden del byte nativo
		bufVertices = bufByte.asFloatBuffer();	// Convierte de byte a float
		bufVertices.put(vertices);
		bufVertices.rewind();					// puntero al principio del buffer
		// BUFFER 2
		//en aca se multiplica por 4 porque es float
		ByteBuffer bufByte1 = ByteBuffer.allocateDirect(vertices.length * 4);
		bufByte1.order(ByteOrder.nativeOrder());
		bufVertices1 = bufByte1.asFloatBuffer();
		bufVertices1.put(vertices1);
		bufVertices1.rewind();
		
		
	}
	
	public void dibuja(GL10 gl){
		/* Se activa el arreglo de vértices */
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		// BUFFER 1
		/* Se especifica los datos del arreglo de vértices */
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices);
		/* Se establece el color en (r,g,b,a) */
		gl.glColor4f(1, 0, 0, 1);
		/* Dibuja el triángulo */
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		/* Se desactiva el arreglo de vértices */
		
		// BUFFER 2
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices1);
		gl.glColor4f(1, 0, 1, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
	// metodo para dar color al fondo
	
}
