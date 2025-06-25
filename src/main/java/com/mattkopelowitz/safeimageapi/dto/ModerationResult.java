package com.mattkopelowitz.safeimageapi.dto;

import java.util.List;

public class ModerationResult {
    private boolean nsfw;
    private boolean violent;
    private boolean gory;
    private boolean drugsAndAlcohol;

    private double nsfwConfidence;
    private double violenceConfidence;
    private double goreConfidence;
    private double drugsAndAlcoholConfidence;

    private List<LabelResult> tags;

    // Getters and Setters
    public boolean isNsfw() { return nsfw; }
    public void setNsfw(boolean nsfw) { this.nsfw = nsfw; }

    public boolean isViolent() { return violent; }
    public void setViolent(boolean violent) { this.violent = violent; }

    public boolean isGory() { return gory; }
    public void setGory(boolean gory) { this.gory = gory; }

    public boolean isDrugsAndAlcohol() { return drugsAndAlcohol; }
    public void setDrugsAndAlcohol(boolean drugsAndAlcohol) { this.drugsAndAlcohol = drugsAndAlcohol; }

    public double getNsfwConfidence() { return nsfwConfidence; }
    public void setNsfwConfidence(double nsfwConfidence) { this.nsfwConfidence = nsfwConfidence; }

    public double getViolenceConfidence() { return violenceConfidence; }
    public void setViolenceConfidence(double violenceConfidence) { this.violenceConfidence = violenceConfidence; }

    public double getGoreConfidence() { return goreConfidence; }
    public void setGoreConfidence(double goreConfidence) { this.goreConfidence = goreConfidence; }

    public double getDrugsAndAlcoholConfidence() { return drugsAndAlcoholConfidence; }
    public void setDrugsAndAlcoholConfidence(double drugsAndAlcoholConfidence) { this.drugsAndAlcoholConfidence = drugsAndAlcoholConfidence; }

    public List<LabelResult> getTags() { return tags; }
    public void setTags(List<LabelResult> tags) { this.tags = tags; }

    public static class LabelResult {
        private String tag;
        private double confidence;

        public LabelResult(String tag, double confidence) {
            this.tag = tag;
            this.confidence = confidence;
        }

        public String getTag() { return tag; }
        public double getConfidence() { return confidence; }
    }
}

