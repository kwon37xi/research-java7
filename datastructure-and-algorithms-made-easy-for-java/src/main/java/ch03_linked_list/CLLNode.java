package ch03_linked_list;

/**
 * 원형 연결 리스트(Circualar Linked List).
 */
public class CLLNode {
    private int data;
    private CLLNode next;

    public CLLNode(int data) {
        this.data = data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setNext(CLLNode next) {
        this.next = next;
    }

    public CLLNode getNext() {
        return this.next;
    }
    public static int cirualrListLength(CLLNode headNode) {
        int length = 0;
        CLLNode currentNode= headNode;
        while (currentNode != null) {
            length++;
            currentNode = currentNode.getNext();
            if (currentNode == headNode) { // node가 다시 head로 돌아오면 멈춘다.
                break;
            }
        }
        return length;
    }

    public static void printCircularListData(CLLNode headNode) {
        CLLNode currentNode = headNode;
        while (currentNode != null) {
            System.out.println(currentNode.getData() + "-> ");
            currentNode = currentNode.getNext();
            if (currentNode == headNode) {
                break;
            }
            System.out.println("(" + currentNode.getData() + ")headNode");
        }
    }

    public static void insertAtEndInCLL(CLLNode headNode, CLLNode nodeToInsert) {
        CLLNode currentNode = headNode;

        while(currentNode.getNext() != headNode) { // 마지막 노드 찾기
            currentNode.setNext(currentNode.getNext()); // 왜하지? 할필요가 없는데.
        }

        nodeToInsert.setNext(nodeToInsert);
        if (headNode == null) {
            headNode = nodeToInsert; // 무의미한 코드. C 언어의 포인터 개념에서는 가능할 듯.
        } else {
            nodeToInsert.setNext(headNode);
            currentNode.setNext(nodeToInsert);
        }
    }

    public static void insertAtBeginInCLL(CLLNode headNode, CLLNode nodeToInsert) {
        CLLNode currentNode = headNode;

        while (currentNode.getNext() != headNode) {
            currentNode.setNext(currentNode.getNext());  // useless
        }

        nodeToInsert.setNext(nodeToInsert);
        if (headNode == null) {
            headNode = nodeToInsert; // useless
        } else {
            nodeToInsert.setNext(headNode);
            currentNode.setNext(nodeToInsert);
            headNode = nodeToInsert;
        }
    }

    public static void deleteLastNodeFromCLL(CLLNode headNode) {
        CLLNode temp = headNode;
        CLLNode currentNode = headNode;

        if (headNode == null) {
            System.out.println("List empty");
            return;
        }

        while (currentNode.getNext() != headNode) {
            temp = currentNode;
            currentNode = currentNode.getNext();
        }
        currentNode = null;
        temp.setNext(headNode);
        return;
    }

    public static void deleteFrontNodeFromCLL(CLLNode headNode) {
        CLLNode temp = headNode;
        CLLNode current = headNode;

        if (headNode == null) {
            System.out.println("List empty");
            return;
        }
        while (current.getNext() != headNode) {
            current.setNext(current.getNext());
        }

        current.setNext(headNode.getNext()); // head를 건너뜀
        headNode = headNode.getNext(); // useless
        temp = null; // useless
        return;
    }
}
