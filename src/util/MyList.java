/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Book;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author ADMIN
 */
public class MyList {

    Node<Book> head, tail;

    //contructor MyList
    public MyList() {
        head = tail = null;
    }

    //check if the list is empty or not
    public boolean isEmpty() {
        return head == null;
    }

    //add a new Book to the end of list
    public void addLast(Book b) {
        
        Node nodeNew = new Node(b);
        if (tail == null) {
            head = nodeNew;
            tail = nodeNew;
        } else {

            tail.next = nodeNew;
            tail = nodeNew;
        }
    }

    //add a new Book to the begining of list
    public void addFirst(Book b) {

        Node<Book> nodeNew = new Node(b);
        if (head == null) {
            head = nodeNew;
            tail = nodeNew;
        } else {
            nodeNew.next = head;
            head = nodeNew;
        }
    }

    //output information of all books in the list
    public void traverse() {

        Node<Book> current = head;
        if (current == null) {
            System.out.println("This list contains no items.");
        } else {
            while (current != null) {
                System.out.println(current.info);
                current = current.next;

            }
        }
    }

    //return number of nodes/elements in the list
    public int size() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    public void sortList(Comparator<Book> b) {

        //Node current will point to head  
        Node<Book> current = head, index = null;
        Book temp;

        if (head == null) {
            return;
        } else {
            while (current != null) {
                //Node index will point to node next to current  
                index = current.next;

                while (index != null) {
                    //If current node's data is greater than index's node data, swap the data between them  
                    // if(current.info.getbCode().compareTo(index.info.getbCode()) > 0 )
                    if (b.compare(current.info, index.info) > 0) // chú ý
                    {
                        temp = current.info;
                        current.info = index.info;
                        index.info = temp;
                    }
                    index = index.next;
                }
                current = current.next;
            }
        }
    }

    //return a Node at position k, starting position is 0
    public Node<Book> getNode(int k) {
        int size = 0;
        Node current = head;

        while (current != null) {
            if (size == k) {
                return current;
            }
            size++;
            current = current.next;
        }
        return current;

    }

    //add a new book after a position k
    public void addAfter(Book b, int k) {

        Node<Book> node = new Node(b);
        if (this.head == null) {
            if (k != 0) {
                return;
            } else {
                this.head = node;
                this.tail = node;
            }
        }
        if (head != null && k == 0) {
            node.next = this.head;
            this.head = node;
            return;
        }
        Node current = this.head;
        Node previous = null;

        int i = 0;
        while (i < k) {
            previous = current;
            current = current.next;
            if (current == null) {
                break;
            }
            i++;
        }
        if (i == k) {
            if (current == null) {
                tail = node;
            }
            node.next = current;
            previous.next = node;
        }

    }

    //delete a book at position k
    public void deleteAt(int k) {

        Node current = head;
        Node parent = null;
        int index = 0;

        while (current != null && index < k) {
            parent = current;
            current = current.next;

            index++;
        }
        if (current != null && parent == null) {
            if (current.next != null) {
                head = current.next;
            } else {
                head = null;
                tail = null;
            }
        } else if (current != null) {
            parent.next = current.next;
            if (current.next == null) {
                tail = parent;
            }
        }

    }

    //search a Node by a given book code
    public Node<Book> search(String bCode) {

        Node<Book> current = head;
        while (current != null) {
            if (current.info.getbCode().equals(bCode)) {
                return current;
            }
            current = current.next;

        }
        return current;
    }

}
