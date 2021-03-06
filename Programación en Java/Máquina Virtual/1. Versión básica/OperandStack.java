
public class OperandStack {
	public static final int MAX_STACK = 200; 
	private int[] stack;
	private int numElems;
	
	/**
	 * Constructora
	 */
	public OperandStack(){
		this.numElems = 0;
		this.stack = new int[OperandStack.MAX_STACK];
	}
	/**
	 * Imprimir Pila
	 */
	public String toString(){
		String mensaje = "  Pila:";
		for (int i = 0; i < numElems; i++){
			mensaje += " " + this.stack[i];
		}
		if (this.isEmpty())
			mensaje += " <vacia>";
		return mensaje;
	}
	/**
	 * Devuelve si la pila está vacía (observadora)
	 * @return
	 */
	public boolean isEmpty(){
		return this.numElems == 0;
	}
	/**
	 * Añade un elemento (modificadora)
	 * @param elem
	 */
	public boolean push(int value){
		if(this.numElems < OperandStack.MAX_STACK && value != -1){ // Si es -1 es que la posición de memoria leída no ha sido escrita
			this.stack[this.numElems] = value;
			this.numElems++;
			return true;
		}else
			return false;
	}
	/**
	 * Quita un elemento y lo devuelve (modificadora)
	 */
	public int pop(){
		if(!this.isEmpty()){
			this.numElems--;
			return this.stack[this.numElems]; // Aquí no va '-1' porque precisamente se devuelve el elemento "eliminado"
		}else
			return -1;
	}
	/**
	 * Operación OUT (observadora)
	 */
	public int getCima(){
		if(!this.isEmpty())
			return this.stack[this.numElems - 1]; // '-1' porque la pila comienza en la posición 0
		else
			return -1;
	}
}