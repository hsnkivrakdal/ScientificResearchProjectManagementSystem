package com.example.srpms.compositemenu;

import java.util.Collections;
import java.util.List;

public class MenuItem implements MenuComponent{

    private final String title;
    private final String icon;
    private final String url;


    public MenuItem(String title, String icon, String url) {
        this.title = title;
        this.icon = icon;
        this.url = url;
    }

    @Override
    public String getTitle() { return title; }

    @Override
    public String getIcon() { return icon; }

    @Override
    public String getUrl() { return url; }

    @Override
    public List<MenuComponent> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public boolean isActive(String activePage) {
        return url.contains(activePage);
    }
}
