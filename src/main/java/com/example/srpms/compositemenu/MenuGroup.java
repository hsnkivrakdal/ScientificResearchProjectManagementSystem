package com.example.srpms.compositemenu;

import java.util.ArrayList;
import java.util.List;

public class MenuGroup implements MenuComponent{
    private final String title;
    private final String icon;
    private final List<MenuComponent> children;

    public MenuGroup(String title, String icon) {
        this.title = title;
        this.icon = icon;
        this.children = new ArrayList<>();
    }

    public void add(MenuComponent component) {
        children.add(component);
    }

    @Override
    public String getTitle() { return title; }

    @Override
    public String getIcon() { return icon; }

    @Override
    public String getUrl() { return null; }

    @Override
    public List<MenuComponent> getChildren() { return children; }

    @Override
    public boolean isActive(String activePage) {
        return children.stream().anyMatch(child -> child.isActive(activePage));
    }
}
