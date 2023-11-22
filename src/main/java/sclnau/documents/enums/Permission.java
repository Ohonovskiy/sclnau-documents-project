package sclnau.documents.enums;

import lombok.Getter;

@Getter
public enum Permission {
    ADMIN_ROLE("admin"),
    USER_ROLE("user");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
}
