package ch03_linked_list;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class DLLNodeTest {
    private Logger log = LoggerFactory.getLogger(DLLNodeTest.class);

    @Test
    public void insert_delete() throws Exception {
        DLLNode headNode = DLLNode.insert(null, new DLLNode(1), 1);
        assertThat(DLLNode.getDLLLength(headNode)).isEqualTo(1);

        headNode = DLLNode.insert(headNode, new DLLNode(2), 2);
        assertThat(DLLNode.getDLLLength(headNode)).isEqualTo(2);
        assertThat(headNode.getData()).isEqualTo(1);
        assertThat(headNode.getNext().getData()).isEqualTo(2);

        headNode = DLLNode.insert(headNode, new DLLNode(7), 2);
        assertThat(DLLNode.getDLLLength(headNode)).isEqualTo(3);
        assertThat(headNode.getNext().getData()).isEqualTo(7);
        assertThat(headNode.getNext().getNext().getData()).isEqualTo(2);

        headNode = DLLNode.delete(headNode, 1);
        assertThat(DLLNode.getDLLLength(headNode)).isEqualTo(2);
        assertThat(headNode.getData()).isEqualTo(7);

        headNode = DLLNode.delete(headNode, 2);
        assertThat(DLLNode.getDLLLength(headNode)).isEqualTo(1);
        assertThat(headNode.getData()).isEqualTo(7);
    }
}
