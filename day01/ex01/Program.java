public class Program {
    public static final int USERS_COUNT = 20;

    public static void main(String[] args) {
        User[] users = new User[USERS_COUNT];

        for (int i = 0; i < USERS_COUNT; i++) {
            users[i] = new User();
            users[i].setName("John");
            users[i].setBalance(400);
        }

        System.out.println("Last generated ID: " + UserIdsGenerator.getInstance());
        for (User user : users) {
            System.out.println(user);
        }
    }
}
