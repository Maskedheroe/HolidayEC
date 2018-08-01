package com.example.fangxy.latteec.icon;


import com.joanzapata.iconify.Icon;

public enum ECIcons implements Icon {
    icon_scan(),
    icon_ali_pay();

    private char character;

    @Override
    public String key() {
        return name().replace('_', '-');
    }


    @Override
    public char character() {
        return character;
    }
}
