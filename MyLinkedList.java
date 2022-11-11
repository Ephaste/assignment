package assignment;

//Group Assignment

/*
- NIYONIZEYE Ephaste          221006307
- DUSHIMIMANA Gilbert         221017785
- HABIMANA Nyiringoma Tresor  221007949
-NIYIBIZI Obed                221006349

*/


public class MyLinkedList {

    class Node {

        int data;
        Node next;

        Node() {
            next = null;
        }

        Node(int a) {
            this.data = a;
            next = null;
        }

        public int value() {
            return data;
        }
    }
    Node head;
    Node tail;
    int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public MyLinkedList(int a) {
        head = tail = new Node(a);
        size = 1;
    }

    public void insertAtFront(int a) {
        Node newNode = new Node(a);
        if (head != null) {

            newNode.next = head;
            head = newNode;
        } else {
            head = newNode;
            tail = newNode;
        }
        ++size;

    }

    public void insertAtBack(int a) {
        Node newNode = new Node(a);
        tail.next = newNode;
        tail = newNode;
        ++size;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[ ]";
        } else {
            Node current = head;
            String s = "[";
            while (current != null) {
                s += current.data;
                if (current.next == null) {
                    s += "]";
                } else {
                    s += ",";
                }
                current = current.next;
            }
            return s;
        }

    }

    public boolean isEmpty() {
        return head == null;
    }

    public void reverse() {
        if (this.size() <= 1)// empty or we have one node!
        {
            return;
        } else {

            Node previous = this.head;
            Node forward = previous.next;
            Node other;
            while (forward.next != null) {
                other = forward.next;
                forward.next = previous;
                previous = forward;
                forward = other;
            }
            forward.next = previous;
            Node temp = head;
            head = tail;
            tail = temp;
            tail.next = null;
        }
    }

    public void insertAt(int a, int i) {
        if (i < 0 || i > this.size()) {
            return;
        } else if (i == 0) {
            insertAtFront(a);
        } else if (i == size()) {
            insertAtBack(a);
        } else {
            Node current = head;
            Node forward = head.next;
            for (int j = 1; j < i; ++j) {
                current = current.next;
                forward = forward.next;
            }
            Node newNode = new Node(a);
            current.next = newNode;
            newNode.next = forward;
        }
    }

    public boolean equals(MyLinkedList l) {
        if (this.size() != l.size()) {
            return false;
        }
        Node h1 = this.head;
        Node h2 = l.head;
        while (h1 != null) {
            if (h1.data != h2.data) {
                return false;
            }
            h1 = h1.next;
            h2 = h2.next;
        }
        return true;
    }

    public MyLinkedList concatenate(MyLinkedList list) {
        this.tail.next = list.head;
        this.tail = list.tail;
        this.tail.next = null;
        return this;
    }

    public static boolean isSorted(MyLinkedList list) //helper method to check if a linked list is sorted
    {//returns true if the Linked List is sorted in ascending order.
        Node currentNode = list.head;
        while (currentNode.next != null) {
            if (currentNode.data > currentNode.next.data) {
                return false;
            }
            currentNode = currentNode.next;
        }

        return true;
    }

    public MyLinkedList merge(MyLinkedList list)//Merges two sorted lists into a sorted one
    {
        if (!isSorted(this)) {
            return null;
        }
        if (!isSorted(list)) {
            return null;
        }
        Node currentOne, currentTwo, resultNode;
        if (this.head.data < list.head.data) {
            currentOne = this.head.next;
            currentTwo = list.head;
        } else {
            this.head = list.head;
            currentTwo = list.head.next;
            currentOne = this.head;
        }
        resultNode = this.head;
        while (currentOne != null || currentTwo != null) {
          
            if (currentOne == null) {
                resultNode.next=currentTwo;
                resultNode = currentTwo;
                currentTwo = currentTwo.next;
            } else if (currentTwo == null) {
                resultNode.next=currentOne;
                resultNode = currentOne;
                currentOne = currentOne.next;

            } else if (currentOne.data < currentTwo.data) {
                resultNode.next = currentOne;
                resultNode=currentOne;
                currentOne = currentOne.next;
            }
            else{
                resultNode.next=currentTwo;
                resultNode=currentTwo;
                currentTwo=currentTwo.next;
            }
            
        }
        
        this.tail=resultNode;
        resultNode.next=null;
        return this;
    }
    public static void main(String args[]) {
        MyLinkedList list1 = new MyLinkedList();
        list1.insertAtFront(1);
        list1.insertAtBack(4);
        list1.insertAtBack(10);
        list1.insertAtBack(2);
        list1.insertAtFront(-8);
        
        System.out.println("List one sorted:" + isSorted(list1));
        System.out.println(list1.toString());
        MyLinkedList list2 = new MyLinkedList();
        list2.insertAtFront(30);
        list2.insertAtBack(6);
        list2.insertAtBack(2);
        list2.insertAtBack(9);
        MyLinkedList list3 = new MyLinkedList();
        list3.insertAtFront(5);
        list3.insertAtBack(6);
        list3.insertAtBack(15);
        list3.insertAtBack(24);
        System.out.println("List two sorted:" + isSorted(list2));
        System.out.println(list2.toString());
        System.out.println("MergedList");
        System.out.println(list1.merge(list2).toString());
        System.out.println("Concatened list to merged list "+list3.toString());
        System.out.println(list1.concatenate(list3));
        

    }
}

