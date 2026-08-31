package com.insureai.insureai_backend.controller;

import com.insureai.insureai_backend.dto.VoiceQueryrequest;
import com.insureai.insureai_backend.dto.VoiceQueryresponse;
import com.insureai.insureai_backend.service.Geminiservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer/voice")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class Voicecontroller {

    private final Geminiservice geminiservice;

    @PostMapping("/query")
    public ResponseEntity<VoiceQueryresponse> handleVoiceQuery(
            @RequestBody VoiceQueryrequest request) {
        String aiResponse = geminiservice.askGemini(request.getQuery());
        return ResponseEntity.ok(
                new VoiceQueryresponse(aiResponse));
    }
}