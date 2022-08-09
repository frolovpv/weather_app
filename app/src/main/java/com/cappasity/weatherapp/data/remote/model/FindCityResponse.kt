package com.cappasity.weatherapp.data.remote.model
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class FindCityResponse(
    @Json(name = "attribution")
    val attribution: String? = null,
    @Json(name = "features")
    val features: List<Feature>? = null,
    @Json(name = "query")
    val query: List<String>? = null,
    @Json(name = "type")
    val type: String? = null
)

@JsonClass(generateAdapter = true)
data class Feature(
    @Json(name = "bbox")
    val bbox: List<Double>? = null,
    @Json(name = "center")
    val center: List<Double>? = null,
    @Json(name = "context")
    val context: List<Context>? = null,
    @Json(name = "geometry")
    val geometry: Geometry? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "language")
    val language: String? = null,
    @Json(name = "language_ru")
    val languageRu: String? = null,
    @Json(name = "place_name")
    val placeName: String? = null,
    @Json(name = "place_name_ru")
    val placeNameRu: String? = null,
    @Json(name = "place_type")
    val placeType: List<String>? = null,
    @Json(name = "properties")
    val properties: Properties? = null,
    @Json(name = "relevance")
    val relevance: Int? = null,
    @Json(name = "text")
    val text: String? = null,
    @Json(name = "text_ru")
    val textRu: String? = null,
    @Json(name = "type")
    val type: String? = null
)

@JsonClass(generateAdapter = true)
data class Context(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "language")
    val language: String? = null,
    @Json(name = "language_ru")
    val languageRu: String? = null,
    @Json(name = "short_code")
    val shortCode: String? = null,
    @Json(name = "text")
    val text: String? = null,
    @Json(name = "text_ru")
    val textRu: String? = null,
    @Json(name = "wikidata")
    val wikidata: String? = null
)

@JsonClass(generateAdapter = true)
data class Geometry(
    @Json(name = "coordinates")
    val coordinates: List<Double>? = null,
    @Json(name = "type")
    val type: String? = null
)

@JsonClass(generateAdapter = true)
data class Properties(
    @Json(name = "wikidata")
    val wikidata: String? = null
)