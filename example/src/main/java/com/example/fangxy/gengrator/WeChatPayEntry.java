package com.example.fangxy.gengrator;


import com.example.fangxy.latte_annotations.EntryGenerator;
import com.example.fangxy.latte_annotations.PayEntryGenerator;
import com.example.fangxy.latte_core.wechat.templates.WXEntryTemplate;
import com.example.fangxy.latte_core.wechat.templates.WXPayEntryTemplate;

@PayEntryGenerator(
        packageName = "com.example.fangxy.holidayec",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry  {
}
