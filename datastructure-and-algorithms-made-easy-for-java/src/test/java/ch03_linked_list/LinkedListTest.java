package ch03_linked_list;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class LinkedListTest {
    private Logger log = LoggerFactory.getLogger(LinkedListTest.class);

    private LinkedList linkedList;

    @Before
    public void setUp() throws Exception {
        linkedList = new LinkedList();
    }

    @Test
    public void insert_delete() throws Exception {
        assertThat(linkedList.listLength()).isEqualTo(0);
        ListNode listNode = linkedList.insertLinkedList(new ListNode(999), 1);

        assertThat(linkedList.listLength()).isEqualTo(1);
        assertThat(listNode.getData()).isEqualTo(999);
        assertThat(listNode.getNext()).isNull();

        listNode = linkedList.insertLinkedList(new ListNode(888), 2);
        listNode = listNode.getNext();
        assertThat(linkedList.listLength()).isEqualTo(2);
        assertThat(listNode.getData()).isEqualTo(888);
        assertThat(listNode.getNext()).isNull();

        listNode = linkedList.deleteNodeFromLinkedList(2);
        assertThat(linkedList.listLength()).isEqualTo(1);
        listNode = linkedList.deleteNodeFromLinkedList(1);
        assertThat(linkedList.listLength()).isEqualTo(0);
    }
}
