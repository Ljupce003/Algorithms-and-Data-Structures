package APS_Struct.hash_tables;

public interface DoublyHashable<K> extends Comparable<K> {

    public int hashCode ();
    // Return the (raw) hash code to use for this key.

    public int stepCode ();
    // Return the (raw) step size to use for this key.

}
