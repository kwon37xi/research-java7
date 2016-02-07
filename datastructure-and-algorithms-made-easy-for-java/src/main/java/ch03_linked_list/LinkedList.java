package ch03_linked_list;

public class LinkedList {

    private ListNode headNode;

    public int listLength() {
        int length = 0;
        ListNode currentNode = headNode;
        while (currentNode != null) {
            length++;
            currentNode = currentNode.getNext();
        }
        return length;
    }

    public ListNode insertLinkedList(ListNode nodeToInsert, int position) {
        if (headNode == null) {
            headNode = nodeToInsert;
            return nodeToInsert;
        }
        int size = listLength();
        if (position > size + 1 || position < 1) {
            System.out.println("Position of node to insert is invalid. The valid inputs are 1 to " + (size + 1));
            return headNode;
        }

        if (position == 1) {
            nodeToInsert.setNext(headNode);
            return nodeToInsert;
        } else {
            ListNode previousNode = headNode;
            int count = 1;
            while (count < position - 1) {
                previousNode = previousNode.getNext();
                count++;
            }

            ListNode currentNode = previousNode.getNext();
            nodeToInsert.setNext(currentNode);
            previousNode.setNext(nodeToInsert);
        }

        return headNode;
    }

    public ListNode deleteNodeFromLinkedList(int position) {
        int size = listLength();
        if (position > size || position < 1) {
            System.out.println("Position of node to delete is invalid. The valid inputs are 1 to " + size);
            return headNode;
        }

        if (position == 1) {
            ListNode currentNode = headNode.getNext();
            headNode = currentNode;
            return currentNode;
        } else {
            ListNode previousNode = headNode;
            int count = 1;
            while (count < position - 1) {
                previousNode = previousNode.getNext();
                count++;
            }

            ListNode currentNode = previousNode.getNext();
            previousNode.setNext(currentNode.getNext());
            currentNode = null;
        }
        return headNode;
    }

    /**
     * Java에서는 무의미하다.
     */
    public void deleteLinkedList() {
        ListNode auxilaryNode, iterator = headNode;

        while (iterator != null) {
            auxilaryNode = iterator.getNext();
            iterator = null;
            iterator = auxilaryNode;
        }

        headNode = null;
    }
}
