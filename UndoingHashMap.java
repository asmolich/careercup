//
// We need to be able to wrap a key-value store (Hash|Object|Dictionary) with another kind of object, the UndoRedo object. It should have the following functionalities:
//
// O(1)
// get(key)
// Returns the value associated with the key.
//
// O(1)
// set(key, value)
// Assigns the value to the key. If the key does not exist, create it.
//
// O(1)
// delete(key)
// Remove the key from the object.
//
//
// undo()
// Undoes the last operation on the object.
//
//
// redo()
// Redoes the last undone operation. Redo is only possible after an undo.


// LinkedHashMap
//

/*
  c = new Class()
  c.set('a', 1)
  c.set('a', 2)
  c.undo()
  c.get('a') -> 1
  c.redo()
  c.get('a') -> 2
*/

import java.util.HashMap;
import java.util.LinkedList;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class UndoingHashMap {
    public static void main(String[] args) {
        System.out.println("Test");

        UndoingHashMap c = new UndoingHashMap();

        c.set("a", 1);
        c.set("a", 2);

        c.undo();

        System.out.println(c.get("a"));//  -> 1
        c.redo();
        System.out.println(c.get("a"));//  -> 2


        c.set("b", 5);
        System.out.println(c.get("b"));//  -> 5
        c.delete("b");
        System.out.println(c.get("b"));//  -> null
        c.undo();
        System.out.println(c.get("b"));//  -> 5


        System.out.println("=========");
        System.out.println(c);

        c = new UndoingHashMap();
        c.set("a", 1);
        c.set("a", 2);
        c.set("b", 5);
        c.delete("b");

        c.undo();
        c.undo();
        c.undo();
        c.undo();
        c.undo();
        c.undo();
        c.undo();
        c.undo();
        c.undo();
        c.undo();
        System.out.println(c.get("a"));//  -> null
        System.out.println(c.get("b"));//  -> null

        //System.out.println(c);

        c.redo();
        c.redo();
        c.redo();
        c.redo();
        c.redo();
        c.redo();
        c.redo();
        c.redo();
        c.redo();
        c.redo();
        c.redo();
        c.redo();
        System.out.println(c.get("a"));//  -> 2
        System.out.println(c.get("b"));//  -> null

    }

    static class Operation {
        String key;
        Integer oldValue;
        Integer newValue;

        Operation(String k, Integer ov, Integer nv) {
            key = k;
            oldValue = ov;
            newValue = nv;
        }

        public String toString() {
            return "(key=" + key + ", old=" + oldValue + ", new=" + newValue + ")";
        }
    }

    private final HashMap<String, Integer> map = new HashMap<>();
    private final LinkedList<Operation> undo = new LinkedList<>();
    private final LinkedList<Operation> redo = new LinkedList<>();

    public Integer get(String key) {
        return map.get(key);
    }

    public void set(String key, Integer value) {
        Integer oldValue = map.get(key);
        map.put(key, value);
        undo.add(new Operation(key, oldValue, value));
    }

    public void delete(String key) {
        Integer oldValue = map.get(key);
        map.remove(key);
        undo.add(new Operation(key, oldValue, null));
    }

    public void undo() {
        if (undo.isEmpty()) return;

        Operation last = undo.removeLast();
        if (last.oldValue == null) map.remove(last.key);
        else map.put(last.key, last.oldValue);
        redo.add(last);
    }

    public void redo() {
        if (redo.isEmpty()) return;

        Operation last = redo.removeLast();
        if (last.newValue == null) map.remove(last.key);
        else map.put(last.key, last.newValue);
        undo.add(last);
    }

    public String toString() {
        return "map:" + map + "\nundo:" + undo + "\nredo:" + redo;
    }
}




