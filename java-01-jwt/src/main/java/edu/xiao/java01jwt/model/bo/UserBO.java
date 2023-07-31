package edu.xiao.java01jwt.model.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.xiao.java01jwt.model.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Schema(title = "用户业务类")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBO implements UserDetails {

    /**
     * 用户信息
     */
    @Getter
    private User user;

    /**
     * 拥有的角色列表
     */
    private Set<String> resources;

    public UserBO() {}

    public UserBO(User user, Set<String> resources) {
        this.user = user;
        this.resources = resources;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    /**
     * 返回授予用户的权限。无法返回null
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @JsonIgnore(value = true)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.resources.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public Set<String> getResources() {
        return resources;
    }

    public void setResources(Set<String> resources) {
        this.resources = resources;
    }

    /**
     * 返回用于验证用户身份的密码。
     *
     * @return the password
     */
    @JsonIgnore(value = true)
    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    /**
     * 返回用于验证用户身份的用户名。无法返回null 。
     *
     * @return 用户名 (绝不为null)
     */
    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    /**
     * 指示用户的帐户是否已过期。过期的帐户无法进行身份验证
     *
     * @return 如果用户的帐户有效（即未过期）， true ；如果不再有效（即过期）， false
     */
    @JsonIgnore(value = true)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指示用户是否被锁定或解锁。锁定的用户无法进行身份验证
     *
     * @return 如果用户未锁定则为true ，否则为false
     */
    @JsonIgnore(value = true)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示用户的凭据（密码）是否已过期。过期的凭据会阻止身份验证
     *
     * @return 如果用户的凭据有效（即未过期）， true ；如果不再有效（即过期）， false
     */
    @JsonIgnore(value = true)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 指示用户是启用还是禁用。无法对禁用的用户进行身份验证。
     *
     * @return 如果用户已启用true ，否则为false
     */
    @JsonIgnore(value = true)
    @Override
    public boolean isEnabled() {
        return this.user.getStatus().equals(1);
    }
}
