/**
 * spoonacular API
 * The spoonacular Nutrition, Recipe, and Food API allows you to access over thousands of recipes, thousands of ingredients, 800,000 food products, over 100,000 menu items, and restaurants. Our food ontology and semantic recipe search engine makes it possible to search for recipes using natural language queries, such as \"gluten free brownies without sugar\" or \"low fat vegan cupcakes.\" You can automatically calculate the nutritional information for any recipe, analyze recipe costs, visualize ingredient lists, find recipes for what's in your fridge, find recipes based on special diets, nutritional requirements, or favorite ingredients, classify recipes into types and cuisines, convert ingredient amounts, or even compute an entire meal plan. With our powerful API, you can create many kinds of food and especially nutrition apps.  Special diets/dietary requirements currently available include: vegan, vegetarian, pescetarian, gluten free, grain free, dairy free, high protein, whole 30, low sodium, low carb, Paleo, ketogenic, FODMAP, and Primal.
 *
 * The version of the OpenAPI document: 1.1
 * Contact: mail@spoonacular.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.spoonacular.client.model;

import java.util.*;
import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

/**
 * 
 **/
@ApiModel(description = "")
public class GetIngredientSubstitutes200Response {
  
  @SerializedName("ingredient")
  private String ingredient = null;
  @SerializedName("substitutes")
  private List<String> substitutes = null;
  @SerializedName("message")
  private String message = null;

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getIngredient() {
    return ingredient;
  }
  public void setIngredient(String ingredient) {
    this.ingredient = ingredient;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public List<String> getSubstitutes() {
    return substitutes;
  }
  public void setSubstitutes(List<String> substitutes) {
    this.substitutes = substitutes;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetIngredientSubstitutes200Response getIngredientSubstitutes200Response = (GetIngredientSubstitutes200Response) o;
    return (this.ingredient == null ? getIngredientSubstitutes200Response.ingredient == null : this.ingredient.equals(getIngredientSubstitutes200Response.ingredient)) &&
        (this.substitutes == null ? getIngredientSubstitutes200Response.substitutes == null : this.substitutes.equals(getIngredientSubstitutes200Response.substitutes)) &&
        (this.message == null ? getIngredientSubstitutes200Response.message == null : this.message.equals(getIngredientSubstitutes200Response.message));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.ingredient == null ? 0: this.ingredient.hashCode());
    result = 31 * result + (this.substitutes == null ? 0: this.substitutes.hashCode());
    result = 31 * result + (this.message == null ? 0: this.message.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetIngredientSubstitutes200Response {\n");
    
    sb.append("  ingredient: ").append(ingredient).append("\n");
    sb.append("  substitutes: ").append(substitutes).append("\n");
    sb.append("  message: ").append(message).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}