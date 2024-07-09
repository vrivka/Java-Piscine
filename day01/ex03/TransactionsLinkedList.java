import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private static class Node {
        public Transaction elem;
        public Node next = null;
        public Node prev = null;
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    @Override
    public void add(Transaction transaction) {
        Node newNode = new Node();

        newNode.elem = transaction;
        if (head == null) {
            head = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
        }
        tail = newNode;
        ++size;
    }

    @Override
    public void remove(UUID id) {
        Node toDelete = findElement(id);

        if (toDelete.prev == null && toDelete.next == null) {
            head = null;
            tail = null;
        } else if (toDelete.prev == null) {
            head = head.next;
            head.prev = null;
            toDelete.next = null;
        } else if (toDelete.next == null) {
            tail = tail.prev;
            tail.next = null;
            toDelete.prev = null;
        } else {
            Node prev = toDelete.prev;
            Node next = toDelete.next;

            prev.next = next;
            next.prev = prev;
            toDelete.prev = null;
            toDelete.next = null;
        }
        --size;
    }

    private Node findElement(UUID id) {
        Node tmp = head;

        for (; tmp != null; tmp = tmp.next) {
            if (tmp.elem.getId().equals(id)) {
                return tmp;
            }
        }
        throw new TransactionNotFoundException("Transaction with id: " + id + " not found");
    }

    @Override
    public Transaction[] toArray() {
        if (head == null) {
            return new Transaction[0];
        }
        Transaction[] transactions = new Transaction[size];
        Node tmp = head;

        for (int i = 0; i < size; i++) {
            transactions[i] = tmp.elem;
            tmp = tmp.next;
        }
        return transactions;
    }
}
