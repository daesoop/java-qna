package codesquad.user;

import javax.persistence.*;

// 데이터베이스와 메핑하는 애너테이션
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 20, unique = true)
    private String userId;
    @Column(nullable = false, length = 20)
    private String password;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 30)
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //스프링에서 알아서 get뒤의 메소드를 갖고온다.
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User update(User newUser) {
        this.password = newUser.password;
        this.name = newUser.name;
        this.email = newUser.email;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}