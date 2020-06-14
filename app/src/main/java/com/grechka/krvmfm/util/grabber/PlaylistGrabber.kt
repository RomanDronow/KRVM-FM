package com.grechka.krvmfm.util.grabber

import org.jsoup.Jsoup
import kotlin.collections.ArrayList

private val URL = "https://spinitron.com/KRVM/"

class PlaylistGrabber {
    fun getPlaylist(): ArrayList<String> {
        val page = Jsoup.connect("https://spinitron.com/KRVM/").get()
        val spins = page.select("tr.spin-item")
        val spinsList: ArrayList<String> = ArrayList()
        for (spin in spins) {
            val imgUrl = spin.selectFirst("img").attr("src").replace("170x170","400x400")
            val tmp =
                spin.attributes()["data-spin"].replaceFirst("\"i\":\"[A-Z0-9]+\",", "")
                    .replace("}", ",\"t\":\"" + spin.selectFirst("td.spin-time").text() + "\",\"img\":\"$imgUrl\"}")
            tmp.replace("}", "")
            spinsList.add(tmp)
        }
        return spinsList
    }
}