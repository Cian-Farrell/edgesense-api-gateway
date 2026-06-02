package com.edgesense.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class GatewayController {

    private final RestTemplate restTemplate;

    @Value("${storage.service.url}")
    private String storageServiceUrl;

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    public GatewayController() {
        this.restTemplate = new RestTemplate();
    }

    //get all sensor readings
    @GetMapping("/readings")
    public ResponseEntity<?> getAllReadings() {
        String url = storageServiceUrl + "/api/readings";
        return ResponseEntity.ok(restTemplate.getForObject(url, Object.class));
    }

    //get anomaly readings only
    @GetMapping("/readings/anomalies")
    public ResponseEntity<?> getAnomalyReadings() {
        String url = storageServiceUrl + "/api/readings/anomalies";
        return ResponseEntity.ok(restTemplate.getForObject(url, Object.class));
    }

    //send anomaly alert
    @PostMapping("/notifications/anomaly")
    public ResponseEntity<?> sendAnomalyAlert(@RequestBody Object alert) {
        String url = notificationServiceUrl + "/api/notifications/anomaly";
        return ResponseEntity.ok(restTemplate.postForObject(url, alert, Object.class));
    }
}
