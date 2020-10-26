package orderingProducts.model;

import lombok.*;

@Getter
@Setter
@ToString(exclude = "password")
@EqualsAndHashCode(of = "user")
public class User {
    private int id;
    private String user;
    @Setter(AccessLevel.NONE)
    private String password;

    public User(int id, String user, String password) {
        this.id = id;
        this.user = user;
        this.password = password;
    }
}
