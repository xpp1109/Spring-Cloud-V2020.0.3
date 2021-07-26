package org.xpp.discovery.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * 服务监听，对于服务注册以及下线等事件进行监听，发送邮件通知
 * @author xpp
 */
@Component
@Slf4j
public class DiscoveryListener {
    @Autowired
    private MailSender mailSender;

    /**
     * 服务断线事件监听
     *
     * @param event eureka实例取消事件
     */
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(event.getAppName() + "服务Canceled");
        message.setText(event.getAppName() + "服务于" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") + " Canceled");
        mailSender.send(message);
    }

    /**
     * 服务注册事件监听
     * @param event 注册事件
     */
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(event.getInstanceInfo().getAppName() + "服务Registered");
        message.setText(event.getInstanceInfo().getAppName() + "服务于" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") + " Registered");
        mailSender.send(message);
    }
    @EventListener//(condition = "#event.replication==false")
    public void listen(EurekaInstanceRenewedEvent event) {
        // 服务续约
        log.info(">>>>>>>>>>>>>>>Server续约:" + event.getServerId());
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        // 注册中心启动
        log.info(">>>>>>>>>>>>>>>Server注册中心:" + event);
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        // Server启动
        log.info(">>>>>>>>>>>>>>>Server启动:" + event);
    }
}
