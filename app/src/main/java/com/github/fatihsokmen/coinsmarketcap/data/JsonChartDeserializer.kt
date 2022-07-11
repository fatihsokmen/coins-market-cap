package com.github.fatihsokmen.coinsmarketcap.data

import com.google.gson.*
import java.lang.reflect.Type

class JsonChartDeserializer : JsonDeserializer<CryptoAssetChartResponse?> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement, typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CryptoAssetChartResponse {
        val data = json.asJsonObject.getAsJsonArray("chart")
        return CryptoAssetChartResponse(
            chart = data.map {
                ChartEntryDto(time = it.asJsonArray[0].asLong, price = it.asJsonArray[1].asFloat)
            }
        )
    }
}