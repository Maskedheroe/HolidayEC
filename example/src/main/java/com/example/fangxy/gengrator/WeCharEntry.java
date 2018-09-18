package com.example.fangxy.gengrator;

import com.example.fangxy.latte_annotations.EntryGenerator;
import com.example.fangxy.latte_core.wechat.templates.WXEntryTemplate;

@EntryGenerator(
        packageName = "com.example.fangxy.holidayec",
        entryTemplete = WXEntryTemplate.class
)
public interface WeCharEntry {


}
