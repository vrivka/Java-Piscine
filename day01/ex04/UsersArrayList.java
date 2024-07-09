package ex04;

public class UsersArrayList implements UsersList {
    private int size = 0;
    private int capacity = 10;
    private User[] users = new User[capacity];

    private void expendUsersArray() {
        capacity *= 2;

        User[] tmp = new User[capacity];

        for (int i = 0; i < users.length; i++) {
            tmp[i] = users[i];
        }
        users = tmp;
    }

    @Override
    public void addUser(User user) {
        if (size == capacity) {
            expendUsersArray();
        }
        users[size] = user;
        size++;
    }

    @Override
    public User getById(Integer id) throws UserNotFoundException {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new UserNotFoundException("User with id " + id + " does not exist");
    }

    @Override
    public User getByIndex(int index) {
        return users[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String res = "[";
        for (int i = 0; i < size; i++) {
            res += users[i];
            if (i + 1 != size) {
                res += ", ";
            }
        }
        res += "]";
        return res;
    }
}
