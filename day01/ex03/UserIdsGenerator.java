public class UserIdsGenerator {
    private Integer id = -1;
    static private UserIdsGenerator instance = null;

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    public int generateId() {
        return ++id;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
