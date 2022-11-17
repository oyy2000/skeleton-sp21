package bstmap;

import java.util.HashSet;
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


    private boolean containsKey(Node current,K key) {
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

    private V get(Node current, K key){
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

    private Node put(Node current, K key, V value) {
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
        HashSet<K> set = new HashSet<>();
        addKeys(root, set);
        return set;
    }

    private void addKeys(Node node, Set<K> set) {
        if (node == null) {
            return;
        }
        set.add(node.key);
        addKeys(node.left, set);
        addKeys(node.right, set);
    }

    private Node getMinChild(Node node){
        if(node.left == null){
            return node;
        }
        return getMinChild(node);
    }
    private Node remove(Node current, K key){
        if (current == null){
            return null;
        }
        int comparison = key.compareTo(current.key);
        if(comparison < 0){
           current.left = remove(current.left, key);
        }else if(comparison > 0){
            current.right = remove(current.right, key);
        }else{
            if(current.left == null){
                return current.right;
            }
            if(current.right == null){
                return current.left;
            }
            Node originalNode = current;
            current = getMinChild(current.right);
            current.left = originalNode.left;
            current.right = remove(originalNode.right, current.key);
        }
        return current;
    }

    @Override
    public V remove(K key) {
        if(containsKey(key)){
            V val = get(key);
            root = remove(root, key);
            size -= 1;
            return val;
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        if(containsKey(key)){
            V val = get(key);
            if(val.equals(value)){
                root = remove(root, key);
                size -= 1;
                return val;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

}
