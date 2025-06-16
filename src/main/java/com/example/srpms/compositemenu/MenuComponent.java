package com.example.srpms.compositemenu;

import java.util.List;

public interface MenuComponent {
    String getTitle();
    String getIcon();
    String getUrl();
    List<MenuComponent> getChildren();
    boolean isActive(String activePage);
}
