
package com.eudycontreras.databindingexample.datamodels

object MockedData {
    private const val MALE_AVATAR: String = "https://i.imgur.com/mZPvZ33.png"
    private const val FEMALE_AVATAR: String = "https://i.imgur.com/URoH0Er.png"

    private const val TEXT_1: String = "pers pi ciatis unde omn is iste natus ipsum error sit volup tatem accu unde omn is iste natus ipsum unde omn is iste"
    private const val TEXT_2: String = "totam lorem rem ape riam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae"

    val dataA = DemoData.TypeA(
        text = TEXT_1,
        imageUrl = MALE_AVATAR
    )

    val dataB = DemoData.TypeB(
        textOne = TEXT_1,
        textTwo = TEXT_2,
        imageUrl = FEMALE_AVATAR
    )
}