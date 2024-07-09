import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        User Max = new User();
        User Mike = new User();
        Transaction MaxToMike = new Transaction();
        TransactionsList transactionsList = new TransactionsLinkedList();

        Max.setName("Max");
        Max.setBalance(500);
        Mike.setName("Mike");
        Mike.setBalance(1000);

        MaxToMike.setId(UUID.randomUUID());
        MaxToMike.setSender(Max);
        MaxToMike.setRecipient(Mike);
        MaxToMike.setTransferCategory(TransferCategory.INCOME);
        MaxToMike.setAmount(250);

        transactionsList.add(MaxToMike);

        UUID MaxToMikeId = MaxToMike.getId();
        for (int i = 0; i < 15; i++) {
            User user1 = new User();
            User user2 = new User();
            Transaction transaction = new Transaction();

            user1.setName("user1");
            user1.setBalance(5500);
            user2.setName("user2");
            user2.setBalance(800);

            transaction.setId(UUID.randomUUID());
            transaction.setSender(user1);
            transaction.setRecipient(user2);
            transaction.setTransferCategory(TransferCategory.INCOME);
            transaction.setAmount(300);
            transactionsList.add(transaction);
        }

        transactionsList.remove(MaxToMikeId);

        Transaction[] transactions = transactionsList.toArray();

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
        try {
            transactionsList.remove(UUID.randomUUID());
        } catch (TransactionNotFoundException err) {
            System.err.println(err.getMessage());
        }
    }
}
