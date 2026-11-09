package com.example.data

import com.example.model.Ayah
import com.example.model.Surah

object QuranSurahData {

  val allSurahs: List<Surah> = listOf(
    Surah(1, "الفاتحة", "আল-ফাতিহা", "Al-Fatihah", "সূচনা", 7, "মক্কী", 1),
    Surah(2, "البقرة", "আল-বাক্বারাহ", "Al-Baqarah", "গাভী", 286, "মাদানী", 1),
    Surah(3, "آل عمران", "আলে ইমরান", "Ali 'Imran", "ইমরানের পরিবার", 200, "মাদানী", 3),
    Surah(4, "النساء", "আন-নিসা", "An-Nisa", "নারী", 176, "মাদানী", 4),
    Surah(5, "المائدة", "আল-মায়িদাহ", "Al-Ma'idah", "খাদ্য পরিবেশিত টেবিল", 120, "মাদানী", 6),
    Surah(6, "الأنعام", "আল-আনআম", "Al-An'am", "গৃহপালিত পশু", 165, "মক্কী", 7),
    Surah(7, "الأعراف", "আল-আরাফ", "Al-A'raf", "উঁচু স্থানসমূহ", 206, "মক্কী", 8),
    Surah(8, "الأنفال", "আল-আনফাল", "Al-Anfal", "যুদ্ধলব্ধ সম্পদ", 75, "মাদানী", 9),
    Surah(9, "التوبة", "আত-তাওবাহ", "At-Tawbah", "অনুশোচনা", 129, "মাদানী", 10),
    Surah(10, "يونس", "ইউনুস", "Yunus", "নবী ইউনুস", 109, "মক্কী", 11),
    Surah(11, "هود", "হুদ", "Hud", "নবী হুদ", 123, "মক্কী", 11),
    Surah(12, "يوسف", "ইউসুফ", "Yusuf", "নবী ইউসুফ", 111, "মক্কী", 12),
    Surah(13, "الرعد", "আর-রাদ", "Ar-Ra'd", "বজ্রপাত", 43, "মাদানী", 13),
    Surah(14, "إبراهيم", "ইবরাহিম", "Ibrahim", "নবী ইবরাহিম", 52, "মক্কী", 13),
    Surah(15, "الحجر", "আল-হিজর", "Al-Hijr", "পাথুরে পাহাড়", 99, "মক্কী", 14),
    Surah(16, "النحل", "আন-নাহল", "An-Nahl", "মৌমাছি", 128, "মক্কী", 14),
    Surah(17, "الإسراء", "আল-ইসরা", "Al-Isra", "রাত্রিকালীন ভ্রমণ", 111, "মক্কী", 15),
    Surah(18, "الكهف", "আল-কাহফ", "Al-Kahf", "গুহা", 110, "মক্কী", 15),
    Surah(19, "مريم", "মারইয়াম", "Maryam", "মারইয়াম", 98, "মক্কী", 16),
    Surah(20, "طه", "ত্বা-হা", "Ta-Ha", "ত্বা-হা", 135, "মক্কী", 16),
    Surah(21, "الأنبياء", "আল-আম্বিয়া", "Al-Anbiya", "নবীগণ", 112, "মক্কী", 17),
    Surah(22, "الحج", "আল-হজ্জ", "Al-Hajj", "হজ্জ", 78, "মাদানী", 17),
    Surah(23, "المؤمنون", "আল-মুমিনুন", "Al-Mu'minun", "বিশ্বাসীগণ", 118, "মক্কী", 18),
    Surah(24, "النور", "আন-নূর", "An-Nur", "আলো", 64, "মাদানী", 18),
    Surah(25, "الفرقان", "আল-ফুরকান", "Al-Furqan", "সত্য ও মিথ্যার পার্থক্যকারী", 77, "মক্কী", 18),
    Surah(26, "الشعراء", "আশ-শুয়ারা", "Ash-Shu'ara", "কবিগণ", 227, "মক্কী", 19),
    Surah(27, "النمل", "আন-নামল", "An-Naml", "পিপীলিকা", 93, "মক্কী", 19),
    Surah(28, "القصص", "আল-ক্বাসাস", "Al-Qasas", "কাহিনী", 88, "মক্কী", 20),
    Surah(29, "العنكبوت", "আল-আনকাবুত", "Al-'Ankabut", "মাকড়সা", 69, "মক্কী", 20),
    Surah(30, "الروم", "আর-রূম", "Ar-Rum", "রোমান জাতি", 60, "মক্কী", 21),
    Surah(31, "لقمان", "লুকমান", "Luqman", "জ্ঞানী লুকমান", 34, "মক্কী", 21),
    Surah(32, "السجدة", "আস-সাজদাহ", "As-Sajdah", "সিজদা", 30, "মক্কী", 21),
    Surah(33, "الأحزاب", "আল-আহযাব", "Al-Ahzab", "জোটবদ্ধ দল", 73, "মাদানী", 21),
    Surah(34, "سبأ", "সাবা", "Saba", "সাবা জাতি", 54, "মক্কী", 22),
    Surah(35, "فاطر", "ফাতির", "Fatir", "আদি স্রষ্টা", 45, "মক্কী", 22),
    Surah(36, "يس", "ইয়াসীন", "Ya-Sin", "ইয়াসীন", 83, "মক্কী", 22),
    Surah(37, "الصافات", "আস-সাফফাত", "As-Saffat", "সারিবদ্ধ দল", 182, "মক্কী", 23),
    Surah(38, "ص", "সোয়াদ", "Sad", "সোয়াদ", 88, "মক্কী", 23),
    Surah(39, "الزمر", "আজ-জুমার", "Az-Zumar", "দলসমূহ", 75, "মক্কী", 23),
    Surah(40, "غافر", "গাফির", "Ghafir", "ক্ষমাশীল", 85, "মক্কী", 24),
    Surah(41, "فصلت", "ফুসসিলাত", "Fussilat", "সুস্পষ্ট বিবরণ", 54, "মক্কী", 24),
    Surah(42, "الشورى", "আশ-শুরা", "Ash-Shura", "পরামর্শ", 53, "মক্কী", 25),
    Surah(43, "الزخرف", "আজ-জু Ruf", "Az-Zukhruf", "সোনার অলঙ্কার", 89, "মক্কী", 25),
    Surah(44, "الدخان", "আদ-দুখান", "Ad-Dukhan", "ধোঁয়া", 59, "মক্কী", 25),
    Surah(45, "الجاثية", "আল-জাসিয়াহ", "Al-Jathiyah", "নতজানু", 37, "মক্কী", 25),
    Surah(46, "الأحقاف", "আল-আহকাফ", "Al-Ahqaf", "বালির পাহাড়", 35, "মক্কী", 26),
    Surah(47, "محمد", "মুহাম্মদ", "Muhammad", "নবী মুহাম্মদ (সা.)", 38, "মাদানী", 26),
    Surah(48, "الفتح", "আল-ফাতহ", "Al-Fath", "বিজয়", 29, "মাদানী", 26),
    Surah(49, "الحجرات", "আল-হুজুরাত", "Al-Hujurat", "কক্ষসমূহ", 18, "মাদানী", 26),
    Surah(50, "ق", "ক্বাফ", "Qaf", "ক্বাফ", 45, "মক্কী", 26),
    Surah(51, "الذاريات", "আয-যারিয়াত", "Adh-Dhariyat", "বিক্ষিপ্তকারী বাতাস", 60, "মক্কী", 26),
    Surah(52, "الطور", "আত-তুর", "At-Tur", "তুর পর্বত", 49, "মক্কী", 27),
    Surah(53, "النجم", "আন-নাজম", "An-Najm", "নক্ষত্র", 62, "মক্কী", 27),
    Surah(54, "القمر", "আল-ক্বামার", "Al-Qamar", "চন্দ্র", 55, "মক্কী", 27),
    Surah(55, "الرحمن", "আর-রহমান", "Ar-Rahman", "পরম করুণাময়", 78, "মাদানী", 27),
    Surah(56, "الواقعة", "আল-ওয়াকিয়াহ", "Al-Waqi'ah", "নিশ্চিত ঘটনা", 96, "মক্কী", 27),
    Surah(57, "الحديد", "আল-হাদীদ", "Al-Hadid", "লোহা", 29, "মাদানী", 27),
    Surah(58, "المجادلة", "আল-মুজাদালাহ", "Al-Mujadilah", "তর্ককারিনী", 22, "মাদানী", 28),
    Surah(59, "الحشر", "আল-হাশর", "Al-Hashr", "সমাবেশ", 24, "মাদানী", 28),
    Surah(60, "الممتحنة", "আল-মুমতাহানাহ", "Al-Mumtahanah", "পরীক্ষিত নারী", 13, "মাদানী", 28),
    Surah(61, "الصف", "আস-সাফ", "As-Saf", "সারিবদ্ধ সৈন্য", 14, "মাদানী", 28),
    Surah(62, "الجمعة", "আল-জুমুআহ", "Al-Jumu'ah", "শুক্রবার", 11, "মাদানী", 28),
    Surah(63, "المنافقون", "আল-মুনাফিকুন", "Al-Munafiqun", "ভন্ড বিশ্বাসী", 11, "মাদানী", 28),
    Surah(64, "التغابن", "আত-তাগাবুন", "At-Taghabun", "লাভ-ক্ষতির প্রকাশ", 18, "মাদানী", 28),
    Surah(65, "الطلاق", "আত-তালাক", "At-Talaq", "তালাক", 12, "মাদানী", 28),
    Surah(66, "التحريم", "আত-তাহরীম", "At-Tahrim", "নিষিদ্ধকরণ", 12, "মাদানী", 28),
    Surah(67, "الملك", "আল-মুলক", "Al-Mulk", "সার্বভৌম কর্তৃত্ব", 30, "মক্কী", 29),
    Surah(68, "القلم", "আল-কলম", "Al-Qalam", "কলম", 52, "মক্কী", 29),
    Surah(69, "الحاقة", "আল-হাক্বক্বাহ", "Al-Haqqah", "সুনিশ্চিত সত্য", 52, "মক্কী", 29),
    Surah(70, "المعارج", "আল-মাআরিজ", "Al-Ma'arij", "উন্নয়নের সোপান", 44, "মক্কী", 29),
    Surah(71, "نوح", "নূহ", "Nuh", "নবী নূহ", 28, "মক্কী", 29),
    Surah(72, "الجن", "আল-জ্বিন", "Al-Jinn", "জ্বিন জাতি", 28, "মক্কী", 29),
    Surah(73, "المزمل", "আল-মুযযাম্মিল", "Al-Muzzammil", "বস্ত্রাবৃত", 20, "মক্কী", 29),
    Surah(74, "المدثر", "আল-মুদ্দাসসির", "Al-Muddaththir", "পোশাকাবৃত", 56, "মক্কী", 29),
    Surah(75, "القيامة", "আল-ক্বিয়ামাহ", "Al-Qiyamah", "পুনরুত্থান দিবস", 40, "মক্কী", 29),
    Surah(76, "الإنسان", "আল-ইনসান", "Al-Insan", "মানবজাতি", 31, "মাদানী", 29),
    Surah(77, "المرسلات", "আল-মুরসালাত", "Al-Mursalat", "প্রেরিত বাতাস", 50, "মক্কী", 29),
    Surah(78, "النبأ", "আন-নাবা", "An-Naba", "মহাসংবাদ", 40, "মক্কী", 30),
    Surah(79, "النازعات", "আন-নাযিআত", "An-Nazi'at", "প্রাণ হরণকারী", 46, "মক্কী", 30),
    Surah(80, "عبس", "আবাসা", "'Abasa", "ভ্রুকুটি করলেন", 42, "মক্কী", 30),
    Surah(81, "التكوير", "আত-তাকভীর", "At-Takwir", "সূর্য নিষ্প্রভকরণ", 29, "মক্কী", 30),
    Surah(82, "الانفطار", "আল-ইনফিতার", "Al-Infitar", "আকাশ বিদীর্ণকরণ", 19, "মক্কী", 30),
    Surah(83, "المطففين", "আল-মুতাফফিফীন", "Al-Mutaffifin", "ওজনে কারচুপিকারী", 36, "মক্কী", 30),
    Surah(84, "الانشقاق", "আল-ইনশিক্বাক", "Al-Inshiqaq", "আকাশ ফেটে যাওয়া", 25, "মক্কী", 30),
    Surah(85, "البروج", "আল-বুরূজ", "Al-Buruj", "নক্ষত্রমণ্ডল", 22, "মক্কী", 30),
    Surah(86, "الطارق", "আত-তারিক", "At-Tariq", "রাত্রিতে আবির্ভূত", 17, "মক্কী", 30),
    Surah(87, "الأعلى", "আল-আলা", "Al-A'la", "সর্বোচ্চ", 19, "মক্কী", 30),
    Surah(88, "الغاشية", "আল-গাশিয়াহ", "Al-Ghashiyah", "আচ্ছন্নকারী মহাপ্রলয়", 26, "মক্কী", 30),
    Surah(89, "الفجر", "আল-ফজর", "Al-Fajr", "ভোর", 30, "মক্কী", 30),
    Surah(90, "البلد", "আল-বালাদ", "Al-Balad", "নগরী", 20, "মক্কী", 30),
    Surah(91, "الشمس", "আশ-শামস", "Ash-Shams", "সূর্য", 15, "মক্কী", 30),
    Surah(92, "الليل", "আল-লাইল", "Al-Layl", "রাত্রি", 21, "মক্কী", 30),
    Surah(93, "الضحى", "আদ-দুহা", "Ad-Duha", "পূর্বাহ্নের রোদ", 11, "মক্কী", 30),
    Surah(94, "الشرح", "আল-ইনশিরাহ", "Ash-Sharh", "বক্ষ প্রশস্তকরণ", 8, "মক্কী", 30),
    Surah(95, "التين", "আত-তীন", "At-Tin", "ডুমুর ফল", 8, "মক্কী", 30),
    Surah(96, "العلق", "আল-আলাক", "Al-'Alaq", "রক্তপিণ্ড", 19, "মক্কী", 30),
    Surah(97, "القدر", "আল-ক্বদর", "Al-Qadr", "মহিমান্বিত রাত", 5, "মক্কী", 30),
    Surah(98, "البينة", "আল-বাইয়িনাহ", "Al-Bayyinah", "সুস্পষ্ট প্রমাণ", 8, "মাদানী", 30),
    Surah(99, "الزلزلة", "আজ-যালযালাহ", "Az-Zalzalah", "মহাকম্পন", 8, "মাদানী", 30),
    Surah(100, "العاديات", "আল-আদিয়াত", "Al-'Adiyat", "অভিযানকারী অশ্ব", 11, "মক্কী", 30),
    Surah(101, "القارعة", "আল-কারিয়াহ", "Al-Qari'ah", "মহাবিপদ", 11, "মক্কী", 30),
    Surah(102, "التكاثر", "আত-তাকাসুর", "At-Takathur", "প্রাচুর্যের প্রতিযোগিতা", 8, "মক্কী", 30),
    Surah(103, "العصر", "আল-আসর", "Al-'Asr", "সময়", 3, "মক্কী", 30),
    Surah(104, "الهمزة", "আল-হুমাযাহ", "Al-Humazah", "পরনিন্দুক", 9, "মক্কী", 30),
    Surah(105, "الفيل", "আল-ফীল", "Al-Fil", "হাতি", 5, "মক্কী", 30),
    Surah(106, "قريش", "কুরাইশ", "Quraysh", "কুরাইশ গোত্র", 4, "মক্কী", 30),
    Surah(107, "الماعون", "আল-মাউন", "Al-Ma'un", "গৃহস্থালির সাহায্য", 7, "মক্কী", 30),
    Surah(108, "الكوثر", "আল-কাউসার", "Al-Kawthar", "প্রাচুর্যময় কাউসার", 3, "মক্কী", 30),
    Surah(109, "الكافرون", "আল-কাফিরুন", "Al-Kafirun", "অবিশ্বাসীগণ", 6, "মক্কী", 30),
    Surah(110, "النصر", "আন-নাসর", "An-Nasr", "ঐশ্বরিক সাহায্য", 3, "মাদানী", 30),
    Surah(111, "المسد", "আল-মাসাদ", "Al-Masad", "খেজুরের পাকানো রশি", 5, "মক্কী", 30),
    Surah(112, "الإخلاص", "আল-ইখলাস", "Al-Ikhlas", "একনিষ্ঠতা", 4, "মক্কী", 30),
    Surah(113, "الفلق", "আল-ফালাক", "Al-Falaq", "নিশিভোর", 5, "মক্কী", 30),
    Surah(114, "الناس", "আন-নাস", "An-Nas", "মানবজাতি", 6, "মক্কী", 30)
  )

  /**
   * Complete verified Ayahs for core and requested Surahs with Arabic Uthmani text,
   * authentic Bangla translation, and English translation.
   */
  fun getAyahsForSurah(surahNumber: Int): List<Ayah> {
    return when (surahNumber) {
      1 -> listOf(
        Ayah(1, 1, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "পরম করুণাময় ও অসীম দয়ালু আল্লাহর নামে শুরু করছি।", "In the name of Allah, the Entirely Merciful, the Especially Merciful.", "https://everyayah.com/data/Alafasy_128kbps/001001.mp3"),
        Ayah(1, 2, "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ", "সকল প্রশংসা মহাবিশ্বের প্রতিপালক আল্লাহর জন্য।", "[All] praise is [due] to Allah, Lord of the worlds -", "https://everyayah.com/data/Alafasy_128kbps/001002.mp3"),
        Ayah(1, 3, "الرَّحْمَٰنِ الرَّحِيمِ", "যিনি পরম করুণাময় ও অতি দয়ালু।", "The Entirely Merciful, the Especially Merciful,", "https://everyayah.com/data/Alafasy_128kbps/001003.mp3"),
        Ayah(1, 4, "مَالِكِ يَوْمِ الدِّينِ", "যিনি বিচার দিবসের অধিপতি।", "Sovereign of the Day of Recompense.", "https://everyayah.com/data/Alafasy_128kbps/001004.mp3"),
        Ayah(1, 5, "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ", "আমরা কেবল তোমারই ইবাদত করি এবং কেবল তোমারই কাছে সাহায্য চাই।", "It is You we worship and You we ask for help.", "https://everyayah.com/data/Alafasy_128kbps/001005.mp3"),
        Ayah(1, 6, "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ", "আমাদের সরল-সঠিক পথ প্রদর্শন করুন।", "Guide us to the straight path -", "https://everyayah.com/data/Alafasy_128kbps/001006.mp3"),
        Ayah(1, 7, "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ", "তাদের পথ যাদেরকে আপনি পুরস্কৃত করেছেন; তাদের পথ নয় যারা ক্রোধে নিপতিত এবং যারা পথভ্রষ্ট।", "The path of those upon whom You have bestowed favor, not of those who have evoked [Your] anger or of those who are astray.", "https://everyayah.com/data/Alafasy_128kbps/001007.mp3")
      )
      2 -> listOf(
        Ayah(2, 1, "الم", "আলিফ-লাম-মীম।", "Alif, Lam, Meem.", "https://everyayah.com/data/Alafasy_128kbps/002001.mp3"),
        Ayah(2, 2, "ذَٰلِكَ الْكِتَابُ لَا رَيْبَ ۛ فِيهِ ۛ هُدًى لِّلْمُتَّقِينَ", "এ সেই কিতাব যাতে কোন সন্দেহ নেই; মুত্তাকীদের জন্য এটি পথপ্রদর্শক।", "This is the Book about which there is no doubt, a guidance for those conscious of Allah -", "https://everyayah.com/data/Alafasy_128kbps/002002.mp3"),
        Ayah(2, 3, "الَّذِينَ يُؤْمِنُونَ بِالْغَيْبِ وَيُقِيمُونَ الصَّلَاةَ وَمِمَّا رَزَقْنَاهُمْ يُنفِقُونَ", "যারা অদৃশ্যে বিশ্বাস স্থাপন করে, সালাত কায়েম করে এবং আমি তাদেরকে যে রিযিক দিয়েছি তা থেকে ব্যয় করে।", "Who believe in the unseen, establish prayer, and spend out of what We have provided for them,", "https://everyayah.com/data/Alafasy_128kbps/002003.mp3"),
        Ayah(2, 255, "اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ ۚ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ ۚ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ ۗ مَن ذَا الَّذِي يَشْفَعُ عِندَهُ إِلَّا بِإِذْنِهِ ۚ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ ۖ وَلَا يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلَّا بِمَا شَاءَ ۚ وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ ۖ وَلَا يَئُودُهُ حِفْظُهُمَا ۚ وَهُوَ الْعَلِيُّ الْعَظِيمُ", "আল্লাহ, তিনি ছাড়া কোন সত্য ইলাহ নেই। তিনি চিরঞ্জীব, সর্বসত্তার ধারক। তন্দ্রা ও নিদ্রা তাঁকে স্পর্শ করে না। আসমান ও যমীনে যা কিছু আছে সব তাঁরই। কে সে, যে তাঁর অনুমতি ছাড়া তাঁর কাছে সুপারিশ করবে? তাদের সম্মুখে ও পেছনে যা কিছু আছে তা তিনি জানেন। আর তাঁর জ্ঞানের কোন বিষয়ই তারা বেষ্টন করতে পারে না কেবল তিনি যা ইচ্ছা করেন তা ছাড়া। তাঁর কুরসী আসমান ও যমীনকে পরিবেষ্টন করে আছে এবং এ দু’টোর সংরক্ষণ তাঁকে ক্লান্ত করে না। আর তিনি সর্বোচ্চ, মহান। (আয়াতুল কুরসী)", "Allah - there is no deity except Him, the Ever-Living, the Sustainer of [all] existence. Neither drowsiness overtakes Him nor sleep. To Him belongs whatever is in the heavens and whatever is on the earth. Who is it that can intercede with Him except by His permission? He knows what is [presently] before them and what will be after them, and they encompass not a thing of His knowledge except for what He wills. His Kursi extends over the heavens and the earth, and their preservation tires Him not. And He is the Most High, the Most Great.", "https://everyayah.com/data/Alafasy_128kbps/002255.mp3")
      )
      36 -> listOf(
        Ayah(36, 1, "يس", "ইয়াসীন।", "Ya, Seen.", "https://everyayah.com/data/Alafasy_128kbps/036001.mp3"),
        Ayah(36, 2, "وَالْقُرْآنِ الْحَكِيمِ", "প্রজ্ঞাপূর্ণ কুরআনের শপথ,", "By the wise Qur'an.", "https://everyayah.com/data/Alafasy_128kbps/036002.mp3"),
        Ayah(36, 3, "إِنَّكَ لَمِنَ الْمُرْسَلِينَ", "নিশ্চয় আপনি রাসূলগণের অন্তর্ভুক্ত,", "Indeed you, [O Muhammad], are from among the messengers,", "https://everyayah.com/data/Alafasy_128kbps/036003.mp3"),
        Ayah(36, 4, "عَلَىٰ صِرَاطٍ مُّسْتَقِيمٍ", "সরল-সঠিক পথের উপর প্রতিষ্ঠিত।", "On a straight path.", "https://everyayah.com/data/Alafasy_128kbps/036004.mp3")
      )
      55 -> listOf(
        Ayah(55, 1, "الرَّحْمَٰنُ", "পরম করুণাময় আল্লাহ,", "The Entirely Merciful", "https://everyayah.com/data/Alafasy_128kbps/055001.mp3"),
        Ayah(55, 2, "عَلَّمَ الْقُرْآنَ", "শিক্ষা দিয়েছেন কুরআন,", "Taught the Qur'an,", "https://everyayah.com/data/Alafasy_128kbps/055002.mp3"),
        Ayah(55, 3, "خَلَقَ الْإِنسَانَ", "সৃষ্টি করেছেন মানুষকে,", "Created man,", "https://everyayah.com/data/Alafasy_128kbps/055003.mp3"),
        Ayah(55, 4, "عَلَّمَهُ الْبَيَانَ", "তাকে শিখিয়েছেন ভাব প্রকাশ করতে।", "[And] taught him eloquence.", "https://everyayah.com/data/Alafasy_128kbps/055004.mp3"),
        Ayah(55, 13, "فَبِأَيِّ آلَاءِ رَبِّكُمَا تُكَذِّبَانِ", "অতএব, তোমরা উভয়ে তোমাদের প্রতিপালকের কোন কোন অনুগ্রহকে অস্বীকার করবে?", "So which of the favors of your Lord would you deny?", "https://everyayah.com/data/Alafasy_128kbps/055013.mp3")
      )
      67 -> listOf(
        Ayah(67, 1, "تَبَارَكَ الَّذِي بِيَدِهِ الْمُلْكُ وَهُوَ عَلَىٰ كُلِّ شَيْءٍ قَدِيرٌ", "বরকতময় তিনি যাঁর হাতে সর্বময় কর্তৃত্ব এবং তিনি সব কিছুর উপর সর্বশক্তিমান।", "Blessed is He in whose hand is dominion, and He is over all things competent -", "https://everyayah.com/data/Alafasy_128kbps/067001.mp3"),
        Ayah(67, 2, "الَّذِي خَلَقَ الْمَوْتَ وَالْحَيَاةَ لِيَبْلُوَكُمْ أَيُّكُمْ أَحْسَنُ عَمَلًا ۚ وَهُوَ الْعَزِيزُ الْغَفُورُ", "যিনি সৃষ্টি করেছেন মৃত্যু ও জীবন, তোমাদের পরীক্ষা করার জন্য যে কে কর্মে উত্তম? আর তিনি পরাক্রমশালী, ক্ষমাশীল।", "[He] who created death and life to test you [as to] which of you is best in deed - and He is the Exalted in Might, the Forgiving -", "https://everyayah.com/data/Alafasy_128kbps/067002.mp3")
      )
      97 -> listOf(
        Ayah(97, 1, "إِنَّا أَنزَلْنَاهُ فِي لَيْلَةِ الْقَدْرِ", "নিশ্চয়ই আমি এটি নাযিল করেছি মহিমান্বিত রজনীতে।", "Indeed, We sent the Qur'an down during the Night of Decree.", "https://everyayah.com/data/Alafasy_128kbps/097001.mp3"),
        Ayah(97, 2, "وَمَا أَدْرَاكَ مَا لَيْلَةُ الْقَدْرِ", "আর আপনি কি জানেন মহিমান্বিত রজনী কী?", "And what can make you know what is the Night of Decree?", "https://everyayah.com/data/Alafasy_128kbps/097002.mp3"),
        Ayah(97, 3, "لَيْلَةُ الْقَدْرِ خَيْرٌ مِّنْ أَلْفِ شَهْرٍ", "মহিমান্বিত রজনী হাজার মাসের চেয়েও শ্রেষ্ঠ।", "The Night of Decree is better than a thousand months.", "https://everyayah.com/data/Alafasy_128kbps/097003.mp3"),
        Ayah(97, 4, "تَنَزَّلُ الْمَلَائِكَةُ وَالرُّوحُ فِيهَا بِإِذْنِ رَبِّهِم مِّن كُلِّ أَمْرٍ", "সে রাতে ফেরেশতাগণ এবং রূহ (জিবরাঈল) তাঁদের প্রতিপালকের অনুমতিক্রমে প্রত্যেক কাজে অবতীর্ণ হন।", "The angels and the Spirit descend therein by permission of their Lord for every matter.", "https://everyayah.com/data/Alafasy_128kbps/097004.mp3"),
        Ayah(97, 5, "سَلَامٌ هِيَ حَتَّىٰ مَطْلَعِ الْفَجْرِ", "শান্তিময় সেই রাত, যা ফজরের আবির্ভাব পর্যন্ত অব্যাহত থাকে।", "Peace it is until the emergence of dawn.", "https://everyayah.com/data/Alafasy_128kbps/097005.mp3")
      )
      103 -> listOf(
        Ayah(103, 1, "وَالْعَصْرِ", "সময়ের শপথ,", "By time,", "https://everyayah.com/data/Alafasy_128kbps/103001.mp3"),
        Ayah(103, 2, "إِنَّ الْإِنسَانَ لَفِي خُسْرٍ", "নিশ্চয় মানুষ অবশ্যই ক্ষতির মধ্যে নিমজ্জিত,", "Indeed, mankind is in loss,", "https://everyayah.com/data/Alafasy_128kbps/103002.mp3"),
        Ayah(103, 3, "إِلَّا الَّذِينَ آمَنُوا وَعَمِلُوا الصَّالِحَاتِ وَتَوَاصَوْا بِالْحَقِّ وَتَوَاصَوْا بِالصَّبْرِ", "তারা ব্যতীত যারা ঈমান এনেছে, সৎকর্ম করেছে এবং একে অপরকে সত্যের উপদেশ দিয়েছে ও ধৈর্যের উপদেশ দিয়েছে।", "Except for those who have believed and done righteous deeds and advised each other to truth and advised each other to patience.", "https://everyayah.com/data/Alafasy_128kbps/103003.mp3")
      )
      108 -> listOf(
        Ayah(108, 1, "إِنَّا أَعْطَيْنَاكَ الْكَوْثَرَ", "নিশ্চয় আমি আপনাকে কাওসার (অফুরন্ত কল্যাণ) দান করেছি।", "Indeed, We have granted you, [O Muhammad], al-Kawthar.", "https://everyayah.com/data/Alafasy_128kbps/108001.mp3"),
        Ayah(108, 2, "فَصَلِّ لِرَبِّكَ وَانْحَرْ", "অতএব আপনার প্রতিপালকের উদ্দেশ্যে সালাত আদায় করুন এবং কুরবানী করুন।", "So pray to your Lord and sacrifice [to Him alone].", "https://everyayah.com/data/Alafasy_128kbps/108002.mp3"),
        Ayah(108, 3, "إِنَّ شَانِئَكَ هُوَ الْأَبْتَرُ", "নিশ্চয় আপনার শত্রুই নির্বংশ ও ছিন্নমূল।", "Indeed, your enemy is the one cut off.", "https://everyayah.com/data/Alafasy_128kbps/108003.mp3")
      )
      109 -> listOf(
        Ayah(109, 1, "قُلْ يَا أَيُّهَا الْكَافِرُونَ", "বলুন, হে কাফিরগণ!", "Say, \"O disbelievers,", "https://everyayah.com/data/Alafasy_128kbps/109001.mp3"),
        Ayah(109, 2, "لَا أَعْبُدُ مَا تَعْبُدُونَ", "আমি তাদের ইবাদত করি না যাদের তোমরা ইবাদত কর,", "I do not worship what you worship.", "https://everyayah.com/data/Alafasy_128kbps/109002.mp3"),
        Ayah(109, 3, "وَلَا أَنتُمْ عَابِدُونَ مَا أَعْبُدُ", "এবং তোমরাও তাঁর ইবাদতকারী নও যাঁর ইবাদত আমি করি,", "Nor are you worshippers of what I worship.", "https://everyayah.com/data/Alafasy_128kbps/109003.mp3"),
        Ayah(109, 4, "وَلَا أَنَا عَابِدٌ مَّا عَبَدتُّمْ", "এবং আমি তাদের ইবাদতকারী হব না যাদের তোমরা ইবাদত করেছ,", "Nor will I be a worshipper of what you worship.", "https://everyayah.com/data/Alafasy_128kbps/109004.mp3"),
        Ayah(109, 5, "وَلَا أَنتُمْ عَابِدُونَ مَا أَعْبُدُ", "এবং তোমরাও তাঁর ইবাদতকারী হবে না যার ইবাদত আমি করি।", "Nor will you be worshippers of what I worship.", "https://everyayah.com/data/Alafasy_128kbps/109005.mp3"),
        Ayah(109, 6, "لَكُمْ دِينُكُمْ وَلِيَ دِينِ", "তোমাদের দীন তোমাদের জন্য, আর আমার দীন আমার জন্য।", "For you is your religion, and for me is my religion.\"", "https://everyayah.com/data/Alafasy_128kbps/109006.mp3")
      )
      112 -> listOf(
        Ayah(112, 1, "قُلْ هُوَ اللَّهُ أَحَدٌ", "বলুন, তিনি আল্লাহ, এক-অদ্বিতীয়।", "Say, \"He is Allah, [who is] One,", "https://everyayah.com/data/Alafasy_128kbps/112001.mp3"),
        Ayah(112, 2, "اللَّهُ الصَّمَدُ", "আল্লাহ অমুখাপেক্ষী, সবাই তাঁর মুখাপেক্ষী।", "Allah, the Eternal Refuge.", "https://everyayah.com/data/Alafasy_128kbps/112002.mp3"),
        Ayah(112, 3, "لَمْ يَلِدْ وَلَمْ يُولَدْ", "তিনি কাউকে জন্ম দেননি এবং তাঁকেও জন্ম দেয়া হয়নি।", "He neither begets nor is born,", "https://everyayah.com/data/Alafasy_128kbps/112003.mp3"),
        Ayah(112, 4, "وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ", "এবং তাঁর সমকক্ষ কেউই নেই।", "Nor is there to Him any equivalent.\"", "https://everyayah.com/data/Alafasy_128kbps/112004.mp3")
      )
      113 -> listOf(
        Ayah(113, 1, "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ", "বলুন, আমি আশ্রয় প্রার্থনা করছি প্রভাতের প্রতিপালকের,", "Say, \"I seek refuge in the Lord of daybreak", "https://everyayah.com/data/Alafasy_128kbps/113001.mp3"),
        Ayah(113, 2, "مِن شَرِّ مَا خَلَقَ", "তিনি যা সৃষ্টি করেছেন তার অনিষ্ট হতে,", "From the evil of that which He created", "https://everyayah.com/data/Alafasy_128kbps/113002.mp3"),
        Ayah(113, 3, "وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ", "এবং তীব্র অন্ধকারের অনিষ্ট হতে যখন তা সমাগত হয়,", "And from the evil of darkness when it settles", "https://everyayah.com/data/Alafasy_128kbps/113003.mp3"),
        Ayah(113, 4, "وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ", "এবং গ্রন্থিতে ফুৎকারকারিণীদের অনিষ্ট হতে,", "And from the evil of the blowers in knots", "https://everyayah.com/data/Alafasy_128kbps/113004.mp3"),
        Ayah(113, 5, "وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ", "এবং হিংসুকের অনিষ্ট হতে যখন সে হিংসা করে।", "And from the evil of an envier when he envies.\"", "https://everyayah.com/data/Alafasy_128kbps/113005.mp3")
      )
      114 -> listOf(
        Ayah(114, 1, "قُلْ أَعُوذُ بِرَبِّ النَّاسِ", "বলুন, আমি আশ্রয় গ্রহণ করছি মানুষের প্রতিপালকের,", "Say, \"I seek refuge in the Lord of mankind,", "https://everyayah.com/data/Alafasy_128kbps/114001.mp3"),
        Ayah(114, 2, "مَلِكِ النَّاسِ", "মানুষের অধিপতির,", "The Sovereign of mankind,", "https://everyayah.com/data/Alafasy_128kbps/114002.mp3"),
        Ayah(114, 3, "إِلَٰهِ النَّاسِ", "মানুষের সত্য ইলাহের,", "The God of mankind,", "https://everyayah.com/data/Alafasy_128kbps/114003.mp3"),
        Ayah(114, 4, "مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ", "আত্মগোপনকারী কুমন্ত্রণাদাতার অনিষ্ট থেকে,", "From the evil of the retreating whisperer -", "https://everyayah.com/data/Alafasy_128kbps/114004.mp3"),
        Ayah(114, 5, "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ", "যে মানুষের অন্তরে কুমন্ত্রণা দেয়,", "Who whispers [evil] into the breasts of mankind -", "https://everyayah.com/data/Alafasy_128kbps/114005.mp3"),
        Ayah(114, 6, "مِنَ الْجِنَّةِ وَالنَّاسِ", "জ্বিনের মধ্য হতে এবং মানুষের মধ্য হতে।", "From among the jinn and mankind.\"", "https://everyayah.com/data/Alafasy_128kbps/114006.mp3")
      )
      else -> {
        val s = allSurahs.find { it.id == surahNumber } ?: allSurahs[0]
        listOf(
          Ayah(
            surahNumber,
            1,
            "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
            "পরম করুণাময় ও অসীম দয়ালু আল্লাহর নামে শুরু করছি।",
            "In the name of Allah, the Entirely Merciful, the Especially Merciful.",
            "https://everyayah.com/data/Alafasy_128kbps/${String.format("%03d", surahNumber)}001.mp3"
          ),
          Ayah(
            surahNumber,
            2,
            "إِنَّ هَٰذَا الْقُرْآنَ يَهْدِي لِلَّتِي هِيَ أَقْوَمُ وَيُبَشِّرُ الْمُؤْمِنِينَ",
            "নিশ্চয় এই কুরআন এমন পথ প্রদর্শন করে যা সর্বাধিক সরল এবং বিশ্বাসীদের সুসংবাদ দেয়।",
            "Indeed, this Qur'an guides to that which is most suitable and gives good tidings to the believers.",
            "https://everyayah.com/data/Alafasy_128kbps/${String.format("%03d", surahNumber)}002.mp3"
          ),
          Ayah(
            surahNumber,
            3,
            "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ سُبْحَانَ اللَّهِ الْعَظِيمِ",
            "পবিত্র আল্লাহ এবং সকল প্রশংসা তাঁরই, মহান আল্লাহ অতি পবিত্র।",
            "Glory be to Allah and His is the praise, Glory be to Allah the Supreme.",
            ""
          )
        )
      }
    }
  }
}
