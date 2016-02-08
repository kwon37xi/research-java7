package ch03_linked_list;

/**
 * Double Linked List Node
 */
public class DLLNode {
    private int data;
    private DLLNode next;
    private DLLNode previous;

    public DLLNode(int data) {
        this.data = data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setNext(DLLNode next) {
        this.next = next;
    }

    public DLLNode getNext() {
        return this.next;
    }

    public void setPrevious(DLLNode previous) {
        this.previous = previous;
    }

    public DLLNode getPrevious() {
        return previous;
    }

    public static int getDLLLength(DLLNode headNode) {
        int length = 0;
        DLLNode currentNode = headNode;

        while (currentNode != null) {
            length++;
            currentNode = currentNode.getNext();
        }

        return length;
    }

    public static DLLNode insert(DLLNode headNode, DLLNode nodeToInsert, int position) {
        // 새로운 headNode
        if (headNode == null) {
            return nodeToInsert;
        }

        int size = getDLLLength(headNode);

        if (position > size + 1 || position < 1) {
            System.out.println("Position of nodeToInsert is invalid. " + "The valid inputs are 1 to " + (size + 1));
            return headNode;
        }

        // 맨 앞에 삽입
        if (position == 1) {
            nodeToInsert.setNext(headNode);
            headNode.setPrevious(nodeToInsert);
            return nodeToInsert;
        } else {
            DLLNode previousNode = headNode;
            int count = 1;

            while (count < position - 1) {
                previousNode = previousNode.getNext();
                count++;
            }

            DLLNode currentNode = previousNode.getNext();
            nodeToInsert.setNext(currentNode);
            if (currentNode != null) {
                currentNode.setPrevious(nodeToInsert);
            }

            previousNode.setNext(nodeToInsert);
            nodeToInsert.setPrevious(previousNode);
        }

        return headNode;
    }

    public static DLLNode delete(DLLNode headNode, int position) {
        int size = getDLLLength(headNode);
        if (position > size || position < 1) {
            System.out.println("Position of node to delete is invalid. The valid inputs are 1 to " + size);
            return headNode;
        }

        if (position == 1) {
            DLLNode currentNode = headNode.getNext();
            headNode = null;
            currentNode.setPrevious(null);
            return currentNode;
        } else {
            DLLNode previousNode = headNode;
            int count = 1;
            while (count < position - 1) {
                previousNode = previousNode.getNext();
                count++;
            }

            DLLNode currentNode = previousNode.getNext(); // 삭제할 노드 선택
            DLLNode laterNode = currentNode.getNext();
            previousNode.setNext(laterNode);
            if (laterNode != null) {
                laterNode.setPrevious(previousNode);
                currentNode = null;
            }

            return headNode;
        }


    }
}
