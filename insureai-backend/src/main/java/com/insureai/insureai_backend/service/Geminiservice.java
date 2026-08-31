package com.insureai.insureai_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.Map;

@Service
public class Geminiservice {

    private final String apiKey;
    private final String apiUrl;
    private final WebClient webClient;

    public Geminiservice(
            WebClient.Builder webClientBuilder,
            @Value("${gemini.api.key}") String apiKey,
            @Value("${gemini.api.url}") String apiUrl) {
        this.webClient = webClientBuilder.build();
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
    }

    public String askGemini(String userQuery) {
        String prompt = """
                You are a helpful insurance assistant for InsureAI.
                Answer the following customer query clearly in 2-3
                sentences. Only answer insurance related questions.
                If question is not related to insurance politely
                say you can only answer insurance queries.
                Query: """ + userQuery;

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)))));

        try {
            Map response = webClient.post()
                    .uri(apiUrl + "?key=" + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            List candidates = (List) response.get("candidates");
            Map firstCandidate = (Map) candidates.get(0);
            Map content = (Map) firstCandidate.get("content");
            List parts = (List) content.get("parts");
            Map firstPart = (Map) parts.get(0);
            return (String) firstPart.get("text");

        } catch (Exception e) {
            return "Sorry I could not process your query. " +
                    "Please try again!";
        }
    }
}