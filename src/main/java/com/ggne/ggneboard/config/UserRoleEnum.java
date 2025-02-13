package com.ggne.ggneboard.config;

public enum UserRoleEnum {
    USER("USER"),
    ADMIN("ADMIN");

    private String roleName;

    UserRoleEnum(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static UserRoleEnum fromString(String roleName) {
        for (UserRoleEnum role : UserRoleEnum.values()) {
            if (role.roleName.equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role name: " + roleName);
    }
}
