import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        User Max = new User();
        User Mike = new User();
        Transaction MaxToMike = new Transaction();

        Max.setId(1);
        Max.setName("Max");
        Max.setBalance(500);
        Mike.setId(2);
        Mike.setName("Mike");
        Mike.setBalance(1000);

        MaxToMike.setId(UUID.randomUUID());
        MaxToMike.setSender(Max);
        MaxToMike.setRecipient(Mike);
        MaxToMike.setTransferCategory(TransferCategory.INCOME);
        MaxToMike.setAmount(250);

        System.out.println(Max);
        System.out.println(Mike);
        System.out.println(MaxToMike);
    }
}
