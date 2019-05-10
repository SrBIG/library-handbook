package model.user;

public enum UserRole {
    USER(0),
    ADMIN(1),
    OWNER(2);
    private int role;

    public int getRole(){
        return this.role;
    }

    UserRole(int role) {
        this.role = role;
    }
}
