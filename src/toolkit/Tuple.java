package toolkit;

public class Tuple {
	private Object[] objects;
	private int size;
	
	public Tuple(Object... objects) {
		this.objects = new Object[objects.length];
		
		for (int i = 0; i < objects.length; i++) {
			this.objects[i] = objects[i];
			System.out.println(objects[i]);
		}
		
		this.size = objects.length;
	}

	public Object get(int i) {
		return objects[i];
	}

	public int getSize() {
		return size;
	}
}
