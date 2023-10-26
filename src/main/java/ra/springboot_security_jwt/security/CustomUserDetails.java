package ra.springboot_security_jwt.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ra.springboot_security_jwt.model.entity.Users;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Data // Chi co khoi tao constructor khong tham so
@AllArgsConstructor // Nen dung them khoi tao tat ca tham so

// UserDetails la 1 interface (cua Security) dai dien thong tin xac thuc nguoi dung
public class CustomUserDetails implements UserDetails {
    private int userId;
    private String userName;
    @JsonIgnore
    private String password;
    private String email;
    private String phone;
    private boolean userStatus;
    // Luu danh sach cac quyen han (hoac roles) ma nguoi dung duoc cap phep
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    // Thong tin user -> thong tin CustomUserDetails
    public static CustomUserDetails mapUserToUserDetail(Users user) {
        // Lay cac quyen tu doi tuong user
        List<GrantedAuthority> listAuthorities = user.getListRoles()
                .stream().map(roles -> new SimpleGrantedAuthority(roles.getRoleName().name()))
                .collect(Collectors.toList());
                // SimpleGrantedAuthority implements GrantedAuthority: bieu dien quyen han cua nguoi dung.

        // Tra ve doi tuong CustomUserDetails
        return new CustomUserDetails(
                user.getUserId(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getPhone(),
                user.isUserStatus(),
                listAuthorities
        );
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
