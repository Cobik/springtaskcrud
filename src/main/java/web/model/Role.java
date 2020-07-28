package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    public Role() {
    }

    public Role(long id) {
        this.id = id;
    }

    public Role(long id, Set<User> users) {
        this.id = id;
        this.users = users;
    }

    public Role(long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role(long id, String roleName, Set<User> users) {
        this.id = id;
        this.roleName = roleName;
        this.users = users;
    }


    public Role(Set<User> users) {
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String roleName;


    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> users;

    public Role(String role) {
        this.roleName = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRole(String role) {
        this.roleName = role;
    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != role.id) return false;
        if (!roleName.equals(role.roleName)) return false;
        return users.equals(role.users);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + roleName.hashCode();
        result = 31 * result + users.hashCode();
        return result;
    }
}
