public class Program {

    public static void main(String[] args) {
        UsersList usersList = new UsersArrayList();

        for (int i = 0; i < 20; ++i) {
            usersList.addUser(new User());
            usersList.getByIndex(i).setName("John");
            usersList.getByIndex(i).setBalance(200);
        }

        System.out.println(usersList.size());
        System.out.println(usersList);
        System.out.println(usersList.getById(15));

        try {
            usersList.getById(600);
        } catch (RuntimeException err) {
            System.err.println(err.getMessage());
        }
    }
}
