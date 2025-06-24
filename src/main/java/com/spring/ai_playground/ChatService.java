package com.spring.ai_playground;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getResponse(String prompt) {
        return chatModel.call(prompt);
    }
    
    public String getResponseOptions(String prompt) {
        Prompt chatPrompt =  
            new Prompt(
                prompt, 
                OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(0.4)
                .build()
            );
            ChatResponse response = chatModel.call(chatPrompt);
            return extractContent(response);
    }

    private String extractContent(ChatResponse response) {
        return response.getResults().get(0).getOutput().getText();
    }
}
