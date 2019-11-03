/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author ADMIN
 */
public class Node<T> {
    
    public T info;
    public Node<T> next;

    public Node() {
    }

    public Node(T info, Node<T> next) {
        this.info = info;
        this.next = next;
    }
    
    public Node(T info) {
        this(info,null);
    }
}
