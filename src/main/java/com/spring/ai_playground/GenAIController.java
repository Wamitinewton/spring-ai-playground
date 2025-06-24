package com.spring.ai_playground;

import java.util.List;

import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class GenAIController {

    private final ChatService chatService;
    private final ImageService imageService;
    private final RecipeService recipeService;

    public GenAIController(ChatService chatService, ImageService imageService, RecipeService recipeService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

    @GetMapping("/ask-ai/options")
    public String getResponseWithOptions(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

    @GetMapping("/generate-images")
    public void generateImages(HttpServletResponse response, @RequestParam String prompt) throws Exception {
        ImageResponse imageResponse = imageService.generateImage(prompt);
        String url = imageResponse.getResult().getOutput().getUrl();
        response.sendRedirect(url);
    }

    @GetMapping("/recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
     @RequestParam(defaultValue = "any") String cuisine,
    @RequestParam(defaultValue = "") String dietryRestrictions
    ) {
        return recipeService.createRecipe(ingredients, cuisine, dietryRestrictions);
    }
    

}