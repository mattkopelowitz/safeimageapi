# AI Image Moderation Microservice

Accepts image uploads to endpoint /api/images/upload

Uses Amazon Rekognition to analyze the image for NSFW, violent, gory, and drug-related content

Returns confidence ratings for NSFW, violent, gory, and drug-related content, and tags for the image
