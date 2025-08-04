package com.example.wisatadekatku.data

import com.example.wisatadekatku.model.Wisata
import com.example.wisatadekatku.R

object WisataRepository {

    fun getAllWisata(): List<Wisata> {
        return listOf(
            Wisata(
                id = 1,
                nama = "Taman Bungkul Surabaya",
                lokasi = "Jalan Raya Darmo, Kelurahan Darmo, Kecamatan Wonokromo, Kota Surabaya, Jawa Timur",
                latitude = -7.291062,
                longitude = 112.739567,
                deskripsi = "Taman Bungkul adalah taman kota modern yang menjadi ikon kota Surabaya. Dilengkapi dengan area bermain anak, jalur jogging, amfiteater, dan sentral kuliner,taman ini cocok untuk rekreasi keluarga dan tempat berkumpul anak muda di pusat kota",
                jarak = 1.2,
                gambarResId = R.drawable.taman_bungkul
            ),
            Wisata(
                id = 2,
                nama = "Kebun Binatang Surabaya",
                lokasi = "Jalan Setail No. 1, Darmo, Wonokromo, Surabaya, Jawa Timur 60241",
                latitude = -7.2960962,
                longitude = 112.7358714,
                deskripsi = "Kebun Binatang Surabaya adalah salah satu kebun binatang tertua di Indonesia yang menyajikan koleksi satwa yang sangat beragam dari dalam dan luar negeri. Tempat ini edukatif, cocok untuk rekreasi keluarga dan anak-anak.",
                jarak = 2.5,
                gambarResId = R.drawable.kebun_binatang_surabaya
            ),
            Wisata(
                id = 3,
                nama = "Monumen Kapal Selam",
                lokasi = "Jalan Pemuda Nomor 39, Embong Kaliasin, Kecamatan Genteng, Surabaya, Jawa Timur",
                latitude = -7.265606,
                longitude = 112.750323,
                deskripsi = "Monumen Kapal Selam adalah museum berbentuk kapal selam KRI Pasopati 410 milik TNI AL yang dapat dimasuki pengunjung. Di dalamnya terdapat dokumentasi sejarah dan teknologi militer laut Indonesia.",
                jarak = 3.0,
                gambarResId = R.drawable.monumen_kapal_selam
            ),
            Wisata(
                id = 4,
                nama = "Taman Lansia Surabaya",
                lokasi = "Jalan Kalimantan No. 12, Gubeng, Surabaya, Jawa Timur",
                latitude = -7.270975,
                longitude = 112.749981,
                deskripsi = "Taman Lansia merupakan taman kota yang asri dan tenang, dikelilingi oleh pepohonan rindang dan jalur jalan kaki yang landai. Sangat cocok untuk relaksasi, olahraga ringan, dan interaksi sosial warga senior.",
                jarak = 2.0,
                gambarResId = R.drawable.taman_lansia
            ),
            Wisata(
                id = 5,
                nama = "Alun-Alun Kota Surabaya",
                lokasi = "Jalan Gubernur Suryo Nomor 15, Embong Kaliasin, Kecamatan Genteng, Surabaya, Jawa Timur",
                latitude = -7.263968,
                longitude = 112.745679,
                deskripsi = "Alun-Alun Kota Surabaya merupakan ruang publik yang modern dan representatif. Tempat ini memiliki area bawah tanah, ruang pameran seni, dan taman luar yang ramai dikunjungi untuk kegiatan masyarakat dan wisata budaya.",
                jarak = 2.7,
                gambarResId = R.drawable.alun_alun_surabaya
            ),
            Wisata(
                id = 6,
                nama = "Taman 10 November Surabaya",
                lokasi = "Jalan Juwet, Tambaksari, Surabaya, Jawa Timur",
                latitude = -7.251565,
                longitude = 112.755073,
                deskripsi = "Taman 10 Surabaya merupakan ruang terbuka hijau yang berada tepat depan Stadion Gelora 10 November. Taman ini cocok untuk bersantai, berolahraga ringan, dan menikmati suasana kota yang tenang di kawasan bersejarah Surabaya.",
                jarak = 3.5,
                gambarResId = R.drawable.taman_senov
            ),
            Wisata(
                id = 7,
                nama = "Monumen Tugu Pahlawan",
                lokasi = "Jl. Pahlawan, Alun-alun Contong, Kec. Bubutan, Surabaya",
                latitude = -7.245483,
                longitude = 112.737825,
                deskripsi = "Tugu Pahlawan adalah ikon perjuangan rakyat Surabaya pada 10 November 1945. Monumen ini dikelilingi taman luas dan museum bawah tanah yang menyimpan dokumentasi sejarah pertempuran.",
                jarak = 2.9,
                gambarResId = R.drawable.monumen_tugu_pahlawan
            ),
            Wisata(
                id = 8,
                nama = "Museum Surabaya (Siola)",
                lokasi = "Jl. Tunjungan No.1, Genteng, Surabaya",
                latitude = -7.2561990,
                longitude = 112.7378197,
                deskripsi = "Museum Surabaya atau Museum Siola menyajikan berbagai koleksi sejarah Kota Pahlawan, mulai dari dokumentasi pemerintahan, artefak masa kolonial, hingga perkembangan teknologi dan budaya lokal.",
                jarak = 2.6,
                gambarResId = R.drawable.museum_surabaya
            ),
            Wisata(
                id = 9,
                nama = "Hutan Bambu Keputih",
                lokasi = "Jl. Raya Marina Asri, Keputih, Surabaya",
                latitude = -7.294112,
                longitude = 112.801958,
                deskripsi = "Hutan Bambu Keputih adalah destinasi wisata alam dengan suasana yang sejuk dan tenang. Daya tarik utama adalah lorong pohon bambu yang tinggi dan rimbun, menciptakan nuansa seperti di Jepang dan sangat cocok untuk berfoto.",
                jarak = 6.7,
                gambarResId = R.drawable.hutan_bambu
            ),
            Wisata(
                id = 10,
                nama = "Taman Flora Surabaya (Bratang)",
                lokasi = "Jl. Bratang Binangun, Baratajaya, Surabaya",
                latitude = -7.294158,
                longitude = 112.761696,
                deskripsi = "Taman Flora Bratang adalah taman edukasi dan konservasi flora di tengah kota Surabaya. Memiliki banyak koleksi tanaman, rumah anggrek, area satwa ringan,serta arena bermain anak-anak. Sangat cocok untuk bereaksi keluarga dan edukasi lingkungan.",
                jarak = 3.8,
                gambarResId = R.drawable.taman_flora
            )
        )
    }

    fun getWisataById(id: Int): Wisata? {
        return getAllWisata().find { it.id == id }
    }
}
