package project.tables;

public enum Role {
    GRAND_ADMIN(0), ADMIN(1);

    private int id;

    Role(int id) {
        this.setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name();
    }

    public static Role getRole(int id) {
        return Role.values()[id];
    }

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}
