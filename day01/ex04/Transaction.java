package ex04;

import java.util.UUID;

public class Transaction {
    private UUID id;
    private User recipient;
    private User sender;
    private TransferCategory transferCategory;
    private Integer amount;

    public UUID getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public TransferCategory getTransferCategory() {
        return transferCategory;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setTransferCategory(TransferCategory transferCategory) {
        this.transferCategory = transferCategory;
    }

    public void setAmount(Integer amount) {
        if ((transferCategory == TransferCategory.INCOME && amount > 0) ||
                (transferCategory == TransferCategory.OUTCOME && amount < 0)) {
            this.amount = amount;
        }
    }

    @Override
    public String toString() {
        return sender.getName() +
                " -> " + recipient.getName() +
                ", " + amount +
                ", " + transferCategory.name() +
                ", " + id;
    }
}
