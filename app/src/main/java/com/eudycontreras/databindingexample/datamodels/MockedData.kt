
package com.eudycontreras.databindingexample.datamodels

import java.util.*

object MockedData {
    private const val MALE_AVATAR: String = "https://i.imgur.com/mZPvZ33.png"
    private const val FEMALE_AVATAR: String = "https://i.imgur.com/URoH0Er.png"

    private const val TEXT_1: String = "pers pi ciatis unde omn is iste natus ipsum error sit volup tatem accu unde omn is iste natus ipsum unde omn is iste"
    private const val TEXT_2: String = "totam lorem rem ape riam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae"

    private val randomId: String
        get() = UUID.randomUUID().toString()

    val dataA = DemoData.TypeA(
        id = randomId,
        text = TEXT_1,
        imageUrl = MALE_AVATAR
    )

    val dataB = DemoData.TypeB(
        id = randomId,
        textOne = TEXT_1,
        textTwo = TEXT_2,
        imageUrl = FEMALE_AVATAR
    )
}