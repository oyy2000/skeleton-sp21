package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{
    private class Node {
        private Node left;
        private Node right;
        private V value;
        private K key;
        Node(K key, V value){
            left = null;
            right = null;
            this.value = value;
            this.key = key;
        }
    }
    private int size;
    private Node root;
    BSTMap(){
        size = 0;
    }
    BSTMap(K key, V value){
        root = new Node(key, value);
        size = 0;
    }
    public void printInOrder() {
        printInOrder(root);
    }


    private void printInOrder(Node current){
        if(current !=null){
            printInOrder(current.left);
            System.out.println(current.key + "-->" + current.value);
            printInOrder(current.right);
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }


    public boolean containsKey(Node current,K key) {
        if(current == null){
            return false;
        }
        int comparison = key.compareTo(current.key);
        if(comparison > 0){
            return containsKey(current.right, key);
        }else if(comparison < 0){
            return containsKey(current.left, key);
        }else {
            return true;
        }
    }
    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    public V get(Node current, K key){
        if(current == null){
            return null;
        }
        int comparison = key.compareTo(current.key);
        if(comparison > 0){
            return get(current.right, key);
        }else if(comparison < 0){
            return get(current.left, key);
        }else {
            return current.value;
        }
    }
    @Override
    public V get(K key) {
        return get(root,key);
    }

    @Override
    public int size() {
        return size;
    }

    public Node put(Node current, K key, V value) {
        if(current == null){
            return new Node(key, value);
        }
        if (key.compareTo(current.key) < 0 ){ // key < current key
            current.left = put(current.left, key, value);
        }else if (key.compareTo(current.key) > 0){
            current.right = put(current.right, key, value);
        }else {
            current.value = value;
        }
        return current;
    }
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
        size += 1;
    }
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }


    public static void main(String[] args) {
        BSTMap<String, Integer> bst = new BSTMap<>();
        bst.put("Z",26);
        bst.put("A",1);
        bst.put("B",2);
        bst.put("F",6);
        bst.put("C",3);
        bst.printInOrder();
    }
}
