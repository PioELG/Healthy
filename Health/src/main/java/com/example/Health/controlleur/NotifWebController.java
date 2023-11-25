package com.example.Health.controlleur;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
@RestController
@RequestMapping("/notifications")
public class NotifWebController
{

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping(path = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        return emitter;
    }

    @GetMapping("/sendNotification")
    public String sendNotification(String message) {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event().data(message));
            } catch (IOException e) {
                emitter.complete();
                emitters.remove(emitter);
            }
        }
        return "Notification sent";
    }
}
