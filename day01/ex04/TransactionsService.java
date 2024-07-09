package ex04;

import java.util.UUID;

public class TransactionsService {
    private final UsersList users = new UsersArrayList();

    public void addUser(User user) {
        users.addUser(user);
    }

    public Integer getUserBalance(Integer id) {
        return users.getById(id).getBalance();
    }

    public void performTransaction(Integer user1Id, Integer user2Id, int amount) {
        UUID transactionId = UUID.randomUUID();
        Transaction user1Transaction = new Transaction();
        Transaction user2Transaction = new Transaction();
        User user1 = users.getById(user1Id);
        User user2 = users.getById(user2Id);

        TransferCategory category = amount < 0 ? TransferCategory.OUTCOME : TransferCategory.INCOME;

        user1Transaction.setId(transactionId);
        user1Transaction.setSender(user1);
        user1Transaction.setRecipient(user2);
        user1Transaction.setTransferCategory(category);
        user1Transaction.setAmount(amount);
        if (user1.getTransactionsList() == null) {
            user1.setTransactionsList(new TransactionsLinkedList());
        }
        user1.getTransactionsList().add(user1Transaction);

        user2Transaction.setId(transactionId);
        user2Transaction.setSender(user2);
        user2Transaction.setRecipient(user1);
        user2Transaction.setTransferCategory(category.equals(TransferCategory.OUTCOME) ? TransferCategory.INCOME : TransferCategory.OUTCOME);
        user2Transaction.setAmount(-amount);
        if (user2.getTransactionsList() == null) {
            user2.setTransactionsList(new TransactionsLinkedList());
        }
        user2.getTransactionsList().add(user2Transaction);
    }

    public Transaction[] getUserTransactions(Integer userId) {
        return users.getById(userId)
                .getTransactionsList()
                .toArray();
    }

    public void removeTransaction(UUID transactionId, Integer userId) {
        users.getById(userId)
                .getTransactionsList()
                .remove(transactionId);
    }

    public void checkValidityTransaction() {
        for (int i = 0; i < users.size(); i++) {
            Transaction[] transactions = users.getByIndex(i).getTransactionsList().toArray();


        }
    }
}
