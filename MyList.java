import java.util.Arrays;

public class MyList<T> {
    private int capacity;
    private T[] list;

    public MyList(){
        this.capacity = 10;
        this.list = (T[]) new Object[10];
    }
    public MyList(int capacity){
        this.capacity = capacity;
        list = (T[]) new Object[capacity];
    }

    public int size(){
        int count = 0;
        for (T element : list) 
            if (element != null) count++;

        return count;
    }

    public int getCapacity(){
        return list.length;
    }

    public void add(T item){
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                list[i] = item;
                break;
            }
            else if (list[i] != null && i == (getCapacity() - 1)) {
                list = Arrays.copyOf(list, list.length * 2);
                list[list.length / 2] = item;
                break;
            }
        }
    }

    public T get(int index){
        if (index >= 0 && index < getCapacity()) 
            return list[index];
        else    
            return null;
    }

    public T remove(int index){
        T temp = get(index);
        for(int i = index; i < list.length; i++){
            list[i] = list[i + 1];
        }
        list[getCapacity()-1] = null;
        return temp;
    }

    public void set(int index, T data){
        if (index < list.length){
            list[index] = data;               
        }
    }
    public int indexOf(T data) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (list[i] == data) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int lastIndexOf(T data) {
        int index = -1;
        for (int i = size() - 1; i >= 0; i--) {
            if (list[i] == data) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean isEmpty() {
        return size() == 0 ? true : false;
    }

    public T[] toArray() {
        // T[] newArr = new T[size()];
        // for (int i = 0; i < size(); i++) {
        //     newArr[i] = list[i];
        // }
        // return newArr;

        return Arrays.copyOfRange(list, 0, size());
    }

    public void clear() {
        for (int i = 0; i < getCapacity(); i++)
            list[i] = null;
    }

    public MyList<T> subList(int start, int finish) {
        MyList<T> newList = new MyList<>((finish - start + 1));

        for (int i = start; i <= finish; i++)
            newList.add(list[i]);

        return newList;
    }

    public boolean contains(T data) {

        for (int i = 0; i < size(); i++)
            if (list[i] == data)
                return true;   

        return false;
    }

    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < list.length; i++)
            if (list[i] != null)
                result += list[i] + " ";
    
        return result.toString();
    }

    public static void main(String[] args) {
        MyList<Integer> liste = new MyList<>();
        System.out.println("Liste Durumu : " + (liste.isEmpty() ? "Boş" : "Dolu"));
        liste.add(10);
        liste.add(20);
        liste.add(30);
        liste.add(40);
        liste.add(20);
        liste.add(50);
        liste.add(60);
        liste.add(70);

        System.out.println("Liste Durumu : " + (liste.isEmpty() ? "Boş" : "Dolu"));

        // Bulduğu ilk indeksi verir
        System.out.println("20 için ilk Indeks : " + liste.indexOf(20));

        // Bulamazsa -1 döndürür
        System.out.println("100 için ilk Indeks :" + liste.indexOf(100));

        // Bulduğu son indeksi verir
        System.out.println("20 için son Indeks : " + liste.lastIndexOf(20));

        // Listeyi Object[] dizisi olarak geri verir.
        Object[] dizi = liste.toArray();
        System.out.println("Object dizisinin ilk elemanı :" + dizi[0]);

        // Liste veri türünde alt bir liste oluşturdu
        MyList<Integer> altListem = liste.subList(0, 3);
        System.out.println(altListem.toString());

        // Değerim listedeki olup olmadığını sorguladı
        System.out.println("Listemde 20 değeri : " + liste.contains(20));
        System.out.println("Listemde 120 değeri : " + liste.contains(120));

        // Listeyi tamamen boşaltır ve varsayılan boyutuna çevirir
        liste.clear();
        System.out.println(liste.toString());
    }
    
}
