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

import com.spoonacular.client.model.GetRecipeInformation200ResponseExtendedIngredientsInnerMeasures;
import java.math.BigDecimal;
import java.util.*;
import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class GetRecipeInformation200ResponseExtendedIngredientsInner {
  
  @SerializedName("aisle")
  private String aisle = null;
  @SerializedName("amount")
  private BigDecimal amount = null;
  @SerializedName("consitency")
  private String consitency = null;
  @SerializedName("id")
  private Integer id = null;
  @SerializedName("image")
  private String image = null;
  @SerializedName("measures")
  private GetRecipeInformation200ResponseExtendedIngredientsInnerMeasures measures = null;
  @SerializedName("meta")
  private List<String> meta = null;
  @SerializedName("name")
  private String name = null;
  @SerializedName("original")
  private String original = null;
  @SerializedName("originalName")
  private String originalName = null;
  @SerializedName("unit")
  private String unit = null;

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getAisle() {
    return aisle;
  }
  public void setAisle(String aisle) {
    this.aisle = aisle;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public BigDecimal getAmount() {
    return amount;
  }
  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getConsitency() {
    return consitency;
  }
  public void setConsitency(String consitency) {
    this.consitency = consitency;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public GetRecipeInformation200ResponseExtendedIngredientsInnerMeasures getMeasures() {
    return measures;
  }
  public void setMeasures(GetRecipeInformation200ResponseExtendedIngredientsInnerMeasures measures) {
    this.measures = measures;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public List<String> getMeta() {
    return meta;
  }
  public void setMeta(List<String> meta) {
    this.meta = meta;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getOriginal() {
    return original;
  }
  public void setOriginal(String original) {
    this.original = original;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getOriginalName() {
    return originalName;
  }
  public void setOriginalName(String originalName) {
    this.originalName = originalName;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getUnit() {
    return unit;
  }
  public void setUnit(String unit) {
    this.unit = unit;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetRecipeInformation200ResponseExtendedIngredientsInner getRecipeInformation200ResponseExtendedIngredientsInner = (GetRecipeInformation200ResponseExtendedIngredientsInner) o;
    return (this.aisle == null ? getRecipeInformation200ResponseExtendedIngredientsInner.aisle == null : this.aisle.equals(getRecipeInformation200ResponseExtendedIngredientsInner.aisle)) &&
        (this.amount == null ? getRecipeInformation200ResponseExtendedIngredientsInner.amount == null : this.amount.equals(getRecipeInformation200ResponseExtendedIngredientsInner.amount)) &&
        (this.consitency == null ? getRecipeInformation200ResponseExtendedIngredientsInner.consitency == null : this.consitency.equals(getRecipeInformation200ResponseExtendedIngredientsInner.consitency)) &&
        (this.id == null ? getRecipeInformation200ResponseExtendedIngredientsInner.id == null : this.id.equals(getRecipeInformation200ResponseExtendedIngredientsInner.id)) &&
        (this.image == null ? getRecipeInformation200ResponseExtendedIngredientsInner.image == null : this.image.equals(getRecipeInformation200ResponseExtendedIngredientsInner.image)) &&
        (this.measures == null ? getRecipeInformation200ResponseExtendedIngredientsInner.measures == null : this.measures.equals(getRecipeInformation200ResponseExtendedIngredientsInner.measures)) &&
        (this.meta == null ? getRecipeInformation200ResponseExtendedIngredientsInner.meta == null : this.meta.equals(getRecipeInformation200ResponseExtendedIngredientsInner.meta)) &&
        (this.name == null ? getRecipeInformation200ResponseExtendedIngredientsInner.name == null : this.name.equals(getRecipeInformation200ResponseExtendedIngredientsInner.name)) &&
        (this.original == null ? getRecipeInformation200ResponseExtendedIngredientsInner.original == null : this.original.equals(getRecipeInformation200ResponseExtendedIngredientsInner.original)) &&
        (this.originalName == null ? getRecipeInformation200ResponseExtendedIngredientsInner.originalName == null : this.originalName.equals(getRecipeInformation200ResponseExtendedIngredientsInner.originalName)) &&
        (this.unit == null ? getRecipeInformation200ResponseExtendedIngredientsInner.unit == null : this.unit.equals(getRecipeInformation200ResponseExtendedIngredientsInner.unit));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.aisle == null ? 0: this.aisle.hashCode());
    result = 31 * result + (this.amount == null ? 0: this.amount.hashCode());
    result = 31 * result + (this.consitency == null ? 0: this.consitency.hashCode());
    result = 31 * result + (this.id == null ? 0: this.id.hashCode());
    result = 31 * result + (this.image == null ? 0: this.image.hashCode());
    result = 31 * result + (this.measures == null ? 0: this.measures.hashCode());
    result = 31 * result + (this.meta == null ? 0: this.meta.hashCode());
    result = 31 * result + (this.name == null ? 0: this.name.hashCode());
    result = 31 * result + (this.original == null ? 0: this.original.hashCode());
    result = 31 * result + (this.originalName == null ? 0: this.originalName.hashCode());
    result = 31 * result + (this.unit == null ? 0: this.unit.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetRecipeInformation200ResponseExtendedIngredientsInner {\n");
    
    sb.append("  aisle: ").append(aisle).append("\n");
    sb.append("  amount: ").append(amount).append("\n");
    sb.append("  consitency: ").append(consitency).append("\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  image: ").append(image).append("\n");
    sb.append("  measures: ").append(measures).append("\n");
    sb.append("  meta: ").append(meta).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  original: ").append(original).append("\n");
    sb.append("  originalName: ").append(originalName).append("\n");
    sb.append("  unit: ").append(unit).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
