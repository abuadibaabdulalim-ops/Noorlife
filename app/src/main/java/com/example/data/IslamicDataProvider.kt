package com.example.data

import com.example.model.AllahName
import com.example.model.DistrictLocation
import com.example.model.DuaItem
import com.example.model.GoodDeed
import com.example.model.HadithItem
import com.example.model.IslamicEvent

object IslamicDataProvider {

  val bangladeshDistricts: List<DistrictLocation> = listOf(
    // Dhaka Division
    DistrictLocation("dhaka", "Dhaka", "ঢাকা", "Dhaka", "ঢাকা", 23.8103, 90.4125),
    DistrictLocation("gazipur", "Gazipur", "গাজীপুর", "Dhaka", "ঢাকা", 24.0023, 90.4264),
    DistrictLocation("narayanganj", "Narayanganj", "নারায়ণগঞ্জ", "Dhaka", "ঢাকা", 23.6238, 90.5000),
    DistrictLocation("tangail", "Tangail", "টাঙ্গাইল", "Dhaka", "ঢাকা", 24.2513, 89.9167),
    DistrictLocation("faridpur", "Faridpur", "ফরিদপুর", "Dhaka", "ঢাকা", 23.6071, 89.8429),
    DistrictLocation("narsingdi", "Narsingdi", "নরসিংদী", "Dhaka", "ঢাকা", 23.9197, 90.7202),
    // Chittagong Division
    DistrictLocation("chittagong", "Chattogram", "চট্টগ্রাম", "Chattogram", "চট্টগ্রাম", 22.3569, 91.7832),
    DistrictLocation("coxsbazar", "Cox's Bazar", "কক্সবাজার", "Chattogram", "চট্টগ্রাম", 21.4272, 92.0058),
    DistrictLocation("comilla", "Cumilla", "কুমিল্লা", "Chattogram", "চট্টগ্রাম", 23.4607, 91.1809),
    DistrictLocation("noakhali", "Noakhali", "নোয়াখালী", "Chattogram", "চট্টগ্রাম", 22.8696, 91.0998),
    DistrictLocation("feni", "Feni", "ফেনী", "Chattogram", "চট্টগ্রাম", 23.0159, 91.3976),
    DistrictLocation("brahmanbaria", "Brahmanbaria", "ব্রাহ্মণবাড়িয়া", "Chattogram", "চট্টগ্রাম", 23.9571, 91.1119),
    // Sylhet Division
    DistrictLocation("sylhet", "Sylhet", "সিলেট", "Sylhet", "সিলেট", 24.8949, 91.8687),
    DistrictLocation("moulvibazar", "Moulvibazar", "মৌলভীবাজার", "Sylhet", "সিলেট", 24.4829, 91.7774),
    DistrictLocation("habiganj", "Habiganj", "হবিগঞ্জ", "Sylhet", "সিলেট", 24.3749, 91.4155),
    DistrictLocation("sunamganj", "Sunamganj", "সুনামগঞ্জ", "Sylhet", "সিলেট", 25.0658, 91.3950),
    // Rajshahi Division
    DistrictLocation("rajshahi", "Rajshahi", "রাজশাহী", "Rajshahi", "রাজশাহী", 24.3745, 88.6042),
    DistrictLocation("bogra", "Bogura", "বগুড়া", "Rajshahi", "রাজশাহী", 24.8465, 89.3777),
    DistrictLocation("pabna", "Pabna", "পাবনা", "Rajshahi", "রাজশাহী", 24.0064, 89.2372),
    DistrictLocation("sirajganj", "Sirajganj", "সিরাজগঞ্জ", "Rajshahi", "রাজশাহী", 24.4534, 89.7008),
    DistrictLocation("naogaon", "Naogaon", "নওগাঁ", "Rajshahi", "রাজশাহী", 24.7936, 88.9318),
    // Khulna Division
    DistrictLocation("khulna", "Khulna", "খুলনা", "Khulna", "খুলনা", 22.8456, 89.5403),
    DistrictLocation("jessore", "Jashore", "যশোর", "Khulna", "খুলনা", 23.1664, 89.2081),
    DistrictLocation("kushtia", "Kushtia", "কুষ্টিয়া", "Khulna", "খুলনা", 23.9013, 89.1205),
    DistrictLocation("satkhira", "Satkhira", "সাতক্ষীরা", "Khulna", "খুলনা", 22.7185, 89.0705),
    // Barisal Division
    DistrictLocation("barisal", "Barishal", "বরিশাল", "Barishal", "বরিশাল", 22.7010, 90.3535),
    DistrictLocation("patuakhali", "Patuakhali", "পটুয়াখালী", "Barishal", "বরিশাল", 22.3596, 90.3298),
    DistrictLocation("bhola", "Bhola", "ভোলা", "Barishal", "বরিশাল", 22.6859, 90.6481),
    // Rangpur Division
    DistrictLocation("rangpur", "Rangpur", "রংপুর", "Rangpur", "রংপুর", 25.7439, 89.2752),
    DistrictLocation("dinajpur", "Dinajpur", "দিনাজপুর", "Rangpur", "রংপুর", 25.6217, 88.6355),
    DistrictLocation("kurigram", "Kurigram", "কুড়িগ্রাম", "Rangpur", "রংপুর", 25.8054, 89.6362),
    // Mymensingh Division
    DistrictLocation("mymensingh", "Mymensingh", "ময়মনসিংহ", "Mymensingh", "ময়মনসিংহ", 24.7471, 90.4203),
    DistrictLocation("jamalpur", "Jamalpur", "জামালপুর", "Mymensingh", "ময়মনসিংহ", 24.9375, 89.9378),
    DistrictLocation("netrokona", "Netrokona", "নেত্রকোণা", "Mymensingh", "ময়মনসিংহ", 24.8709, 90.7279),
    // International Holy Cities
    DistrictLocation("makkah", "Makkah", "মক্কা মুকাররমা", "Saudi Arabia", "সৌদি আরব", 21.4225, 39.8262),
    DistrictLocation("madinah", "Madinah", "মদিনা মুনাওয়ারা", "Saudi Arabia", "সৌদি আরব", 24.5247, 39.5692)
  )

  val allahNames: List<AllahName> = listOf(
    AllahName(1, "الرَّحْمَنُ", "আর-রহমান", "Ar-Rahman", "পরম দয়ালু", "The Beneficent", "যিনি সমগ্র সৃষ্টির প্রতি অসীম ও শর্তহীন দয়া বর্ষণ করেন।"),
    AllahName(2, "الرَّحِيمُ", "আর-রাহীম", "Ar-Raheem", "অতিশয় মেহেরবান", "The Merciful", "যিনি পরকালে মুমিন বান্দাদের প্রতি বিশেষ অনুগ্রহ ও রহমতকারী।"),
    AllahName(3, "الْمَلِكُ", "আল-মালিক", "Al-Malik", "সার্বভৌম অধিপতি", "The King", "মহাবিশ্বের একমাত্র প্রকৃত রাজা ও সার্বভৌম ক্ষমতার অধিকারী।"),
    AllahName(4, "الْقُدُّوسُ", "আল-কুদ্দুস", "Al-Quddus", "মহাপবিত্র ও নিষ্কলুষ", "The Most Holy", "সকল দোষ-ত্রুটি, অপূর্ণতা ও সীমাবদ্ধতা হতে সম্পূর্ণ পবিত্র।"),
    AllahName(5, "السَّلاَمُ", "আস-সালাম", "As-Salam", "শান্তিদাতা ও নিরাপত্তা দানকারী", "The Source of Peace", "যিনি শান্তি দান করেন এবং যাঁর সত্তা সকল বিপদ থেকে নিরাপদ।"),
    AllahName(6, "الْمُؤْمِنُ", "আল-মুমিন", "Al-Mu'min", "নিরাপত্তা ও বিশ্বাস বিধায়ক", "The Guardian of Faith", "যিনি বান্দাদের নিরাপত্তা দান করেন এবং প্রতিশ্রুতি রক্ষা করেন।"),
    AllahName(7, "الْمُهَيْمِنُ", "আল-মুহাইমিন", "Al-Muhaymin", "রক্ষণাবেক্ষণকারী ও অভিভাবক", "The Protector", "যিনি সমগ্র সৃষ্টির সার্বক্ষণিক তত্ত্বাবধান ও সুরক্ষা করেন।"),
    AllahName(8, "الْعَزِيزُ", "আল-আযীয", "Al-Aziz", "পরাক্রমশালী ও অপ্রতিরোধ্য", "The Almighty", "যাঁর শক্তির সমকক্ষ কেউ নেই এবং যিনি চিরবিজয়ী।"),
    AllahName(9, "الْجَبَّارُ", "আল-জাব্বার", "Al-Jabbar", "মহাপ্রভাবশালী ও সংস্কারক", "The Compeller", "যিনি যা ইচ্ছা তা বাস্তবায়নে সক্ষম এবং ভগ্ন হৃদয় জোড়া লাগান।"),
    AllahName(10, "الْمُتَكَبِّرُ", "আল-মুতাকাব্বির", "Al-Mutakabbir", "গৌরবান্বিত ও শ্রেষ্ঠ", "The Majestic", "যিনি সকল মহত্ত্ব ও শ্রেষ্ঠত্বের একমাত্র প্রকৃত অধিকারী।"),
    AllahName(11, "الْخَالِقُ", "আল-খালিক্ব", "Al-Khaliq", "সৃষ্টিকর্তা", "The Creator", "যিনি শূন্য হতে সবকিছু সৃষ্টি করেছেন।"),
    AllahName(12, "الْبَارِئُ", "আল-বারি", "Al-Bari", "রূপকার ও প্রাণদাতা", "The Evolver", "যিনি কোনো ত্রুটি ছাড়াই নিখুঁত সৃষ্টি করেন।"),
    AllahName(13, "الْمُصَوِّرُ", "আল-মুসাওয়ির", "Al-Musawwir", "অনুপম রূপদাতা", "The Flawless Shaper", "যিনি প্রতিটি বস্তুকে অনন্য রূপ ও আকৃতি দিয়েছেন।"),
    AllahName(14, "الْغَفَّارُ", "আল-গাফফার", "Al-Ghaffar", "মহাক্ষমাশীল", "The All-Forgiving", "যিনি বারবার বান্দার পাপ মার্জনা করেন।"),
    AllahName(15, "الْقَهَّارُ", "আল-ক্বাহহার", "Al-Qahhar", "কঠোর পরাক্রান্ত", "The All-Prevailing", "যাঁর কর্তৃত্বের সম্মুখে সমগ্র সৃষ্টি অবনত।"),
    AllahName(16, "الْوَهَّابُ", "আল-ওয়াহহাব", "Al-Wahhab", "মহাদানকারী", "The Supreme Bestower", "যিনি প্রতিদান ছাড়াই অবারিত দান করেন।"),
    AllahName(17, "الرَّزَّاقُ", "আর-রাযযাক", "Ar-Razzaq", "রিযিকদাতা", "The Provider", "যিনি প্রতিটি সৃষ্টির আহার ও জীবিকার ব্যবস্থা করেন।"),
    AllahName(18, "الْفَتَّاحُ", "আল-ফাত্তাহ", "Al-Fattah", "বিজয়দানকারী ও উন্মোচনকারী", "The Supreme Solver", "যিনি রহমত ও বিজয়ের দরজা উন্মুক্ত করে দেন।"),
    AllahName(19, "الْعَلِيمُ", "আল-আলীম", "Al-Aleem", "সর্বজ্ঞাত", "The All-Knowing", "যিনি প্রকাশ্যে ও গোপনে ঘটিত সবকিছু জানেন।"),
    AllahName(20, "الْقَابِضُ", "আল-ক্বাবিদ", "Al-Qabid", "সংকোচনকারী", "The Withholder", "যিনি পরীক্ষা ও প্রজ্ঞার খাতিরে রিযিক বা জীবন সংকুচিত করেন।"),
    AllahName(21, "الْبَاسِطُ", "আল-বাসিত", "Al-Basit", "সম্প্রসারণকারী", "The Extender", "যিনি রিযিক, রহমত ও অনুগ্রহ প্রসারিত করেন।"),
    AllahName(22, "الْخَافِضُ", "আল-খাফিদ", "Al-Khafid", "অবনমিতকারী", "The Abaser", "যিনি অহংকারী ও সত্য অস্বীকারকারীদের লাঞ্ছিত করেন।"),
    AllahName(23, "الرَّافِعُ", "আর-রাফি", "Ar-Rafi", "উন্নতকারী ও মর্যাদা দানকারী", "The Exalter", "যিনি বিনয়ী ও মুমিনদের মর্যাদা সমুন্নত করেন।"),
    AllahName(24, "الْمُعِزُّ", "আল-মুইযয", "Al-Mu'izz", "সম্মানদানকারী", "The Bestower of Honor", "যিনি যাকে চান সম্মান ও মর্যাদা দান করেন।"),
    AllahName(25, "الْمُذِلُّ", "আল-মুযিল্ল", "Al-Muzill", "অপমানকারী", "The Dishonorer", "যিনি সীমা লঙ্ঘনকারীকে অপদস্থ করেন।"),
    AllahName(26, "السَّمِيعُ", "আস-সামী", "As-Samee", "সর্বশ্রোতা", "The All-Hearing", "যিনি প্রতিটি মনের নিভৃত ফিসফিসানিও শোনেন।"),
    AllahName(27, "الْبَصِيرُ", "আল-বাসীর", "Al-Baseer", "সর্বদ্রষ্টা", "The All-Seeing", "যাঁর দৃষ্টির বাইরে কিছুই নেই।"),
    AllahName(28, "الْحَكَمُ", "আল-হাকাম", "Al-Hakam", "বিচারক ও হুকুমকর্তা", "The Impartial Judge", "যাঁর ফয়সালা চূড়ান্ত ও ত্রুটিমুক্ত।"),
    AllahName(29, "الْعَدْلُ", "আল-আদল", "Al-Adl", "পরম ন্যায়পরায়ণ", "The Utterly Just", "যিনি এক চুল পরিমাণও অবিচার করেন না।"),
    AllahName(30, "اللَّطِيفُ", "আল-লাতীফ", "Al-Lateef", "সূক্ষ্মদর্শী ও মেহেরবান", "The Subtle One", "যিনি অদৃশ্য সূক্ষ্ম উপায়ে বান্দার প্রতি করুণা করেন।"),
    AllahName(31, "الْخَبِيرُ", "আল-খাবীর", "Al-Khabeer", "সর্ববিষয়ে সম্যক অবহিত", "The All-Aware", "যিনি প্রতিটি ক্ষুদ্রাতিক্ষুদ্র রহস্যের খবর রাখেন।"),
    AllahName(32, "الْحَلِيمُ", "আল-হালীম", "Al-Haleem", "মহাধৈর্যশীল", "The Most Forbearing", "যিনি পাপে লিপ্তদের সঙ্গে সঙ্গে শাস্তি না দিয়ে অবকাশ দেন।"),
    AllahName(33, "الْعَظِيمُ", "আল-আযীম", "Al-Azeem", "মহিমাম্বিত ও মহামহিম", "The Magnificent", "যাঁর শ্রেষ্ঠত্ব কল্পনাতীত ও অতুলনীয়।"),
    AllahName(34, "الْغَفُورُ", "আল-গাফুর", "Al-Ghafoor", "পরম ক্ষমাশীল", "The Forgiving", "যিনি অনুতপ্ত বান্দার গুনাহ ঢেকে দেন ও মাফ করেন।"),
    AllahName(35, "الشَّكُورُ", "আশ-শাকূর", "Ash-Shakoor", "কৃতজ্ঞতা গ্রহণকারী ও পুরস্কারদাতা", "The Most Appreciative", "যিনি সামান্য সৎকাজের বিনিময়ে অসীম সওয়াব দেন।"),
    AllahName(36, "الْعَلِيُّ", "আল-আলী", "Al-Alee", "সর্বোচ্চ", "The Most High", "যিনি সত্তাগত ও মর্যাদাগতভাবে সবার ঊর্ধ্বে।"),
    AllahName(37, "الْكَبِيرُ", "আল-কাবীর", "Al-Kabeer", "সর্বশ্রেষ্ঠ ও সর্ববৃহৎ", "The Greatest", "যাঁর মহত্ত্বের কাছে বিশ্বব্রহ্মাণ্ড নগণ্য।"),
    AllahName(38, "الْحَفِيظُ", "আল-হাফীয", "Al-Hafeez", "মহারক্ষক", "The Preserver", "যিনি সকল সৃষ্টিকে ধ্বংসের হাত থেকে রক্ষা করেন।"),
    AllahName(39, "المُقِيتُ", "আল-মুক্বীত", "Al-Muqeet", "সকলের আহার্য ও শক্তিদাতা", "The Sustainer", "যিনি আত্মিক ও শারীরিক খোরাক যোগান।"),
    AllahName(40, "الْحَسِيبُ", "আল-হাসীব", "Al-Haseeb", "হিসাব গ্রহণকারী ও যথেষ্ট সত্তা", "The Reckoner", "যিনি বান্দার প্রতিটি আমলের পুঙ্খানুপুঙ্খ হিসাব নেন।"),
    AllahName(41, "الْجَلِيلُ", "আল-জালীল", "Al-Jaleel", "প্রতাপশালী ও মহিমান্বিত", "The Majestic", "যাঁর মর্যাদা ও প্রতাপ সর্বোচ্চ।"),
    AllahName(42, "الْكَرِيمُ", "আল-কারীম", "Al-Kareem", "মহামহিম ও পরম দাতা", "The Most Generous", "যিনি প্রার্থনার চাইতেও অধিক ও উত্তম দান করেন।"),
    AllahName(43, "الرَّقِيبُ", "আর-রাক্বীব", "Ar-Raqeeb", "চিরসতর্ক পরিদর্শক", "The Watchful", "যিনি প্রতিটি মুহূর্ত নজরদারি করেন।"),
    AllahName(44, "الْمُجِيبُ", "আল-মুজিব", "Al-Mujeeb", "প্রার্থনায় সাড়া দানকারী", "The Responsive", "যিনি ব্যাকুল বান্দার দোয়ায় সাড়া দেন।"),
    AllahName(45, "الْوَاسِعُ", "আল-ওয়াসি", "Al-Wasi", "সর্বব্যাপী ও অসীম", "The Vast", "যাঁর রহমত ও জ্ঞান সবকিছু পরিবেষ্টন করে আছে।"),
    AllahName(46, "الْحَكِيمُ", "আল-হাকীম", "Al-Hakeem", "মহাপ্রজ্ঞাময়", "The All-Wise", "যাঁর প্রতিটি বিধান ও সৃষ্টি প্রজ্ঞাপূর্ণ।"),
    AllahName(47, "الْوَدُودُ", "আল-ওয়াদূদ", "Al-Wadood", "পরম স্নেহশীল ও ভালোবাসাময়", "The Most Loving", "যিনি সৎ বান্দাদের গভীরভাবে ভালোবাসেন।"),
    AllahName(48, "الْمَجِيدُ", "আল-মাজীদ", "Al-Majeed", "মহাগৌরবান্বিত", "The Glorious", "যিনি গৌরব ও খ্যাতির পরাকাষ্ঠা।"),
    AllahName(49, "الْبَاعِثُ", "আল-বাইস", "Al-Ba'ith", "পুনরুজ্জীবিতকারী", "The Resurrector", "যিনি মৃত্যুর পর কিয়ামত দিবসে সবাইকে জীবিত করবেন।"),
    AllahName(50, "الشَّهِيدُ", "আশ-শাহীদ", "Ash-Shaheed", "প্রত্যক্ষদর্শী ও সাক্ষী", "The Witness", "যাঁর সামনে সবকিছু প্রকাশিত।"),
    AllahName(51, "الْحَقُّ", "আল-হাক্ক্ব", "Al-Haqq", "চিরন্তন সত্য", "The Truth", "একমাত্র তিনিই চিরন্তন, সত্য ও অপরিবর্তনীয়।"),
    AllahName(52, "الْوَكِيلُ", "আল-ওয়াকীল", "Al-Wakeel", "উত্তম কর্মবিধায়ক", "The Trustee", "যাঁর উপর নির্ভর করলে বান্দা কখনো হতাশ হয় না।"),
    AllahName(53, "الْقَوِيُّ", "আল-ক্বাউইয়্যু", "Al-Qawiyy", "মহাশক্তিশালী", "The All-Strong", "যাঁর ক্ষমতায় কোনো ক্লান্তি বা দুর্বলতা নেই।"),
    AllahName(54, "الْمَتِينُ", "আল-মাতীন", "Al-Mateen", "সুদৃঢ় ও অটল", "The Steadfast", "যাঁর শক্তি চিরস্থায়ী ও অবিচল।"),
    AllahName(55, "الْوَلِيُّ", "আল-ওয়ালীয়্যু", "Al-Waliyy", "পরম বন্ধু ও সাহায্যকারী", "The Protecting Friend", "যিনি মুমিন বান্দার শ্রেষ্ঠ অভিভাবক।"),
    AllahName(56, "الْحَمِيدُ", "আল-হামীদ", "Al-Hameed", "সর্বপ্রশংসিত", "The Praiseworthy", "সমগ্র সৃষ্টি যাঁর হামদ ও প্রশংসায় মুখরিত।"),
    AllahName(57, "الْمُحْصِي", "আল-মুহসী", "Al-Muhsee", "পুঙ্খানুপুঙ্খ হিসাবকারী", "The Accounter", "যিনি সমগ্র সৃষ্টির সকল কর্ম গণনা করে রেখেছেন।"),
    AllahName(58, "الْمُبْدِئُ", "আল-মুবদি", "Al-Mubdi", "প্রথম সৃষ্টিকর্তা", "The Originator", "যিনি পূর্বের কোনো নমুনা ছাড়া সৃষ্টি শুরু করেছেন।"),
    AllahName(59, "الْمُعِيدُ", "আল-মুঈদ", "Al-Mu'eed", "পুনরায় ফিরিয়ে আনয়নকারী", "The Restorer", "যিনি ধ্বংসপ্রাপ্ত সৃষ্টিকে পুনরায় ফিরিয়ে আনবেন।"),
    AllahName(60, "الْمُحْيِي", "আল-মুহয়ী", "Al-Muhyee", "জীবনদানকারী", "The Giver of Life", "যিনি প্রাণ সৃষ্টি ও জীবন দান করেন।"),
    AllahName(61, "اَلْمُمِيتُ", "আল-মুমীত", "Al-Mumeet", "মৃত্যুদানকারী", "The Bringer of Death", "যাঁর হুকুমে সকল প্রাণীর মৃত্যু ঘটে।"),
    AllahName(62, "الْحَيُّ", "আল-হাইয়্যু", "Al-Hayy", "চিরঞ্জীব", "The Ever-Living", "যাঁর কোনো শুরু নেই এবং মৃত্যু নেই।"),
    AllahName(63, "الْقَيُّومُ", "আল-ক্বাইয়্যূম", "Al-Qayyum", "স্বয়ম্ভূ ও সর্বসত্তার ধারক", "The Sustainer", "যিনি নিজে অটুট থেকে সমগ্র সৃষ্টিকে ধারণ করে আছেন।"),
    AllahName(64, "الْوَاجِدُ", "আল-ওয়াজিদ", "Al-Wajid", "অফুরন্ত সম্পদের অধিকারী", "The Perceiver", "যিনি কখনো কোনো কিছুর অভাব অনুভব করেন না।"),
    AllahName(65, "الْمَاجِدُ", "আল-মাজিদ", "Al-Majid", "মহিমান্বিত ও অনুপম", "The Illustrious", "যাঁর অনুগ্রহ ও দান অনন্ত।"),
    AllahName(66, "الْوَاحِدُ", "আল-ওয়াহিদ", "Al-Wahid", "এক ও অনন্য", "The One", "যাঁর সত্তা ও গুণে কোনো দ্বিতীয় নেই।"),
    AllahName(67, "الأَحَد", "আল-আহাদ", "Al-Ahad", "একক ও অদ্বিতীয়", "The Indivisible", "যাঁর কোনো অংশীদার বা সমকক্ষ নেই।"),
    AllahName(68, "الصَّمَدُ", "আস-সামাদ", "As-Samad", "অমুখাপেক্ষী", "The Eternal", "যিনি কারও মুখাপেক্ষী নন, সবাই তাঁর মুখাপেক্ষী।"),
    AllahName(69, "الْقَادِرُ", "আল-ক্বাদির", "Al-Qadir", "সর্বশক্তিমান", "The Capable", "যিনি যেকোনো কিছু করতে সম্পূর্ণরূপে সক্ষম।"),
    AllahName(70, "الْمُقْتَدِرُ", "আল-মুক্বতাদির", "Al-Muqtadir", "মহাপ্রভাবশালী সিদ্ধান্তকারী", "The All-Powerful", "যাঁর শক্তির প্রভাব কোনো বাধা মানে না।"),
    AllahName(71, "الْمُقَدِّمُ", "আল-মুক্বাদ্দিম", "Al-Muqaddim", "অগ্রবর্তীকারী", "The Expediter", "যিনি যাকে চান প্রজ্ঞাপূর্বক এগিয়ে দেন।"),
    AllahName(72, "الْمُؤَخِّرُ", "আল-মুআখখির", "Al-Mu'akhkhir", "পশ্চাদ্বর্তীকারী", "The Delayer", "যিনি যাকে চান পিছিয়ে দেন।"),
    AllahName(73, "الأوَّلُ", "আল-আউয়াল", "Al-Awwal", "অনাদি ও প্রথম", "The First", "যাঁর অস্তিত্বের কোনো শুরু নেই।"),
    AllahName(74, "الآخِرُ", "আল-আখির", "Al-Akhir", "অনন্ত ও সর্বশেষ", "The Last", "সবকিছু ধ্বংস হওয়ার পরও যিনি চিরকাল থাকবেন।"),
    AllahName(75, "الظَّاهِرُ", "আজ-জাহির", "Az-Zahir", "প্রকাশ্য", "The Manifest", "সৃষ্টির স্পষ্ট নিদর্শনে যাঁর অস্তিত্ব ভাস্বর।"),
    AllahName(76, "الْبَاطِنُ", "আল-বাতিন", "Al-Batin", "গোপন ও অপ্রকাশ্য", "The Hidden", "যাঁকে মানুষের চর্মচক্ষু দেখতে পায় না।"),
    AllahName(77, "الْوَالِي", "আল-ওয়ালী", "Al-Wali", "একচ্ছত্র মালিক ও শাসক", "The Patron", "যিনি মহাবিশ্বের একমাত্র সার্বভৌম পরিচালক।"),
    AllahName(78, "الْمُتَعَالِي", "আল-মুতাআলী", "Al-Muta'ali", "সর্বোচ্চ মর্যাদাপূর্ণ", "The Supreme Exalted", "যিনি সকল মানবিক চিন্তার অনেক ঊর্ধ্বে।"),
    AllahName(79, "الْبَرُّ", "আল-বার্র", "Al-Barr", "পরম কল্যাণকারী", "The Source of Goodness", "যিনি সদা সর্বদা বান্দার কল্যাণ সাধন করেন।"),
    AllahName(80, "التَّوَّابُ", "আত-তাওয়াব", "At-Tawwab", "তওবা কবুলকারী", "The Ever-Pardoning", "যিনি বান্দার আন্তরিক তওবা বারবার কবুল করেন।"),
    AllahName(81, "الْمُنْتَقِمُ", "আল-মুনতাক্বিম", "Al-Muntaqim", "প্রতিশোধ গ্রহণকারী", "The Avenger", "যিনি উদ্ধত জালিমদের সমুচিত শাস্তি দেন।"),
    AllahName(82, "العَفُوُّ", "আল-আফুউ", "Al-Afuww", "পরম মার্জনাশীল", "The Pardoner", "যিনি পাপের চিহ্ন পর্যন্ত মুছে দেন।"),
    AllahName(83, "الرَّؤُوفُ", "আর-রাউফ", "Ar-Ra'oof", "পরম সহানুভূতিশীল", "The Most Kind", "যাঁর অন্তহীন মমতা সৃষ্টির উপর বর্ষিত।"),
    AllahName(84, "مَالِكُ الْمُلْكِ", "মালিকুল মুলক", "Malik-ul-Mulk", "বিশ্বজগতের সার্বভৌম মালিক", "Owner of All Sovereignty", "যিনি দৃশ্য ও অদৃশ্যের একচ্ছত্র অধিপতি।"),
    AllahName(85, "ذُو الْجَلاَلِ وَالإِكْرَامِ", "যুল জালালি ওয়াল ইকরাম", "Dhul-Jalali wal-Ikram", "মহিমা ও সম্মানের অধিকারী", "Lord of Glory and Honor", "যিনি শ্রেষ্ঠত্ব ও অনুগ্রহের একমাত্র উৎস।"),
    AllahName(86, "الْمُقْسِطُ", "আল-মুক্বসিত", "Al-Muqsit", "ইনসাফকারী", "The Equitable", "যিনি ন্যায়বিচারের মানদণ্ড বজায় রাখেন।"),
    AllahName(87, "الْجَامِعُ", "আল-জামি", "Al-Jami", "একত্রকারী", "The Gatherer", "যিনি হাশরের ময়দানে সমগ্র মানবজাতিকে জড়ো করবেন।"),
    AllahName(88, "الْغَنِيُّ", "আল-গানীয়্যু", "Al-Ghaniyy", "স্বয়ংসম্পূর্ণ ধনী", "The Self-Sufficient", "যাঁর কোনো কিছুর প্রয়োজন হয় না।"),
    AllahName(89, "الْمُغْنِي", "আল-মুগনী", "Al-Mughni", "সমৃদ্ধিদাতা", "The Enricher", "যিনি যাকে চান অভাবমুক্ত ও ধনী করেন।"),
    AllahName(90, "المَانِعُ", "আল-মানি", "Al-Mani", "প্রতিরোধকারী", "The Withholder", "যিনি প্রজ্ঞাবশত ক্ষতিকর বিষয় থেকে নিবৃত্ত করেন।"),
    AllahName(91, "الضَّارُّ", "আদ-দার্র", "Ad-Darr", "ক্ষতিসাধক", "The Distressor", "যাঁর হুকুম ছাড়া কারো কোনো ক্ষতি হতে পারে না।"),
    AllahName(92, "النَّافِعُ", "আন-নাফি", "An-Nafi", "উপকারকারী", "The Propitious", "যাঁর অনুগ্রহ ছাড়া কারো কোনো উপকার লাভ সম্ভব নয়।"),
    AllahName(93, "النُّورُ", "আন-নূর", "An-Nur", "জ্যোতি ও আলো", "The Light", "যিনি আকাশমণ্ডল ও পৃথিবীর জ্যোতি।"),
    AllahName(94, "الْهَادِي", "আল-হাদী", "Al-Hadi", "পথপ্রদর্শক", "The Guide", "যিনি সঠিক পথের সন্ধান দান করেন।"),
    AllahName(95, "الْبَدِيعُ", "আল-বাদী", "Al-Badee", "অনুপম স্রষ্টা", "The Incomparable Originator", "যিনি অভিনবত্বে সৃষ্টির কোনো তুলনা রাখেননি।"),
    AllahName(96, "الْبَاقِي", "আল-বাক্বী", "Al-Baqi", "চিরস্থায়ী", "The Everlasting", "সবকিছু শেষ হয়ে গেলেও যাঁর অস্তিত্ব চিরঞ্জীব।"),
    AllahName(97, "الْوَارِثُ", "আল-ওয়ারিস", "Al-Warith", "সর্বস্বত্বাধিকারী", "The Ultimate Inheritor", "জগতের সবকিছু ধ্বংসের পর একমাত্র তিনিই থাকবেন।"),
    AllahName(98, "الرَّشِيدُ", "আর-রাশীদ", "Ar-Rasheed", "সঠিক নির্দেশক", "The Rightly Guiding", "যাঁর কর্ম ও সিদ্ধান্ত অভ্রান্ত।"),
    AllahName(99, "الصَّبُورُ", "আস-সাবূর", "As-Saboor", "অনন্ত ধৈর্যশীল", "The Most Patient", "যিনি পাপীদের ত্বরিত শাস্তি না দিয়ে ধৈর্য ধারণ করেন।")
  )

  val duas: List<DuaItem> = listOf(
    // Morning & Evening
    DuaItem(
      id = "m1",
      category = "Morning",
      categoryBn = "সকালের দোয়া",
      titleEn = "Morning supplication for protection",
      titleBn = "সকালের সুরক্ষার দোয়া",
      arabic = "أَصْبَحْنَا وَأَصْبَحَ الْمُلْكُ لِلَّهِ، وَالْحَمْدُ لِلَّهِ، لاَ إِلَـهَ إِلاَّ اللهُ وَحْدَهُ لاَ شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ",
      pronunciationBn = "আসবাহনা ওয়া আসবাহাল মুলকু লিল্লাহ, ওয়াল হামদু লিল্লাহ, লা ইলাহা ইল্লাল্লাহু ওয়াহদাহু লা শারীকা লাহ, লাহুল মুলকু ওয়া লাহুল হামদু ওয়া হুয়া আলা কুল্লি শাইয়িন ক্বাদীর।",
      meaningBn = "আমরা সকালে উপনীত হয়েছি এবং আল্লাহর সমগ্র রাজ্যও সকালে উপনীত হয়েছে। সকল প্রশংসা আল্লাহর জন্য। এক আল্লাহ ব্যতীত কোনো সত্য ইলাহ নেই, তাঁর কোনো শরিক নেই। রাজত্ব একমাত্র তাঁরই, প্রশংসাও একমাত্র তাঁরই এবং তিনি সব কিছুর উপর সর্বশক্তিমান।",
      meaningEn = "We have entered a new day and, with it, all the kingdom belongs to Allah, praise is to Allah. None has the right to be worshipped except Allah alone, without partner.",
      reference = "সহীহ মুসলিম: ২৭২৩"
    ),
    DuaItem(
      id = "m2",
      category = "Morning",
      categoryBn = "সকালের দোয়া",
      titleEn = "Sayyidul Istighfar (Chief of forgiveness)",
      titleBn = "সায়্যিদুল ইস্তিগফার (ক্ষমা প্রার্থনার শ্রেষ্ঠ দোয়া)",
      arabic = "اللَّهُمَّ أَنْتَ رَبِّي لاَ إِلَهَ إِلاَّ أَنْتَ، خَلَقْتَنِي وَأَنَا عَبْدُكَ، وَأَنَا عَلَى عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ، أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ، أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ، وَأَبُوءُ بِذَنْبِي فَاغْفِرْ لِي فَإِنَّهُ لاَ يَغْفِرُ الذُّنُوبَ إِلاَّ أَنْتَ",
      pronunciationBn = "আল্লাহুম্মা আনতা রব্বী লা ইলাহা ইল্লা আনতা, খালাক্বতানী ওয়া আনা আবদুকা, ওয়া আনা আলা আহদিকা ওয়া ওয়া'দিকা মাসতাত্বা'তু, আউযু বিকা মিন শাররি মা সানা'তু, আবূউ লাকা বিনি'মাতিকা আলাইয়্যা, ওয়া আবূউ বিযানবী ফাগফির লী, ফাইন্নাহু লা ইয়াগফিরুয যুনূবা ইল্লা আনতা।",
      meaningBn = "হে আল্লাহ! আপনিই আমার প্রতিপালক। আপনি ছাড়া কোনো সত্য ইলাহ নেই। আপনি আমাকে সৃষ্টি করেছেন এবং আমি আপনার বান্দা। আমি আমার সাধ্যমত আপনার প্রতিশ্রুতি ও অঙ্গীকারে অবিচল রয়েছি। আমি যে পাপ করেছি তার অনিষ্ট হতে আপনার আশ্রয় প্রার্থনা করছি। আমার উপর আপনার যে নিয়ামত রয়েছে তা স্বীকার করছি এবং আমার গুনাহও স্বীকার করছি। অতএব আপনি আমাকে ক্ষমা করে দিন; কারণ আপনি ব্যতীত গুনাহ ক্ষমা করার আর কেউ নেই।",
      meaningEn = "O Allah, You are my Lord, none has the right to be worshipped except You. You created me and I am Your slave. I abide by Your covenant as much as I am able. Forgive me, for none forgives sins except You.",
      reference = "সহীহ বুখারী: ৬৩০৬"
    ),
    DuaItem(
      id = "e1",
      category = "Evening",
      categoryBn = "সন্ধ্যার দোয়া",
      titleEn = "Evening refuge in Allah's words",
      titleBn = "সন্ধ্যার অনিষ্ট থেকে বাঁচার দোয়া",
      arabic = "أَعُوذُ بِكَلِمَاتِ اللَّهِ التَّامَّاتِ مِنْ شَرِّ مَا خَلَقَ",
      pronunciationBn = "আউযু বিকালিমাতিল্লাহিত তাম্মা-তি মিন শাররি মা খালাক্ব।",
      meaningBn = "আমি আল্লাহর পরিপূর্ণ বাক্যসমূহের মাধ্যমে তাঁর সৃষ্ট সকল বস্তুর অনিষ্ট থেকে আশ্রয় প্রার্থনা করছি। (৩ বার)",
      meaningEn = "I seek refuge in the perfect words of Allah from the evil of what He has created. (3 times)",
      reference = "সহীহ মুসলিম: ২৭০৯"
    ),
    // Sleep & Wake up
    DuaItem(
      id = "s1",
      category = "Sleeping",
      categoryBn = "ঘুমানোর দোয়া",
      titleEn = "Supplication before sleeping",
      titleBn = "ঘুমানোর পূর্বে পাঠ করার দোয়া",
      arabic = "بِاسْمِكَ اللَّهُمَّ أَمُوتُ وَأَحْيَا",
      pronunciationBn = "বিসমিকাল্লাহুম্মা আমূতু ওয়া আহয়া।",
      meaningBn = "হে আল্লাহ! আপনারই নামে আমি মৃত্যুবরণ (ঘুমাই) করছি এবং পুনরুজ্জীবিত (জাগ্রত) হব।",
      meaningEn = "In Your name, O Allah, I die and I live.",
      reference = "সহীহ বুখারী: ৬৩১২"
    ),
    DuaItem(
      id = "w1",
      category = "Waking Up",
      categoryBn = "ঘুম থেকে ওঠার দোয়া",
      titleEn = "Supplication on waking up",
      titleBn = "ঘুম থেকে জাগ্রত হওয়ার দোয়া",
      arabic = "الْحَمْدُ لِلَّهِ الَّذِي أَحْيَانَا بَعْدَ مَا أَمَاتَنَا وَإِلَيْهِ النُّشُورُ",
      pronunciationBn = "আলহামদু লিল্লাহিল্লাযী আহয়ানা বা'দা মা আমাতানা ওয়া ইলাইহিন নুশূর।",
      meaningBn = "সকল প্রশংসা আল্লাহর জন্য, যিনি আমাদেরকে মৃত্যু (ঘুম) দেওয়ার পর পুনরায় জীবিত করলেন। আর তাঁরই নিকট আমাদের পুনরুত্থান।",
      meaningEn = "All praise is for Allah, who gave us life after having taken it from us, and unto Him is the resurrection.",
      reference = "সহীহ বুখারী: ৬৩১৪"
    ),
    // Eating & Daily
    DuaItem(
      id = "et1",
      category = "Eating",
      categoryBn = "খাবারের দোয়া",
      titleEn = "Supplication before eating",
      titleBn = "খাবার শুরুর পূর্বে দোয়া",
      arabic = "بِسْمِ اللَّهِ وَعَلَى بَرَكَةِ اللَّهِ",
      pronunciationBn = "বিসমিল্লাহি ওয়া আলা বারাকাতিল্লাহ।",
      meaningBn = "আল্লাহর নামে এবং আল্লাহর বরকতের উপর শুরু করছি।",
      meaningEn = "In the name of Allah and with the blessings of Allah.",
      reference = "সুনান আবু দাউদ: ৩৭৬৭"
    ),
    DuaItem(
      id = "et2",
      category = "Eating",
      categoryBn = "খাবারের দোয়া",
      titleEn = "Supplication after eating",
      titleBn = "খাবার শেষের দোয়া",
      arabic = "الْحَمْدُ لِلَّهِ الَّذِي أَطْعَمَنَا وَسَقَانَا وَجَعَلَنَا مِنَ الْمُسْلِمِينَ",
      pronunciationBn = "আলহামদু লিল্লাহিল্লাযী আত'আমানা ওয়া সাক্বানা ওয়া জা'আলানা মিনাল মুসলিমীন।",
      meaningBn = "সকল প্রশংসা আল্লাহর জন্য, যিনি আমাদের আহার করিয়েছেন, পান করিয়েছেন এবং মুসলমানদের অন্তর্ভুক্ত করেছেন।",
      meaningEn = "Praise belongs to Allah Who has fed us and given us drink and made us Muslims.",
      reference = "সুনান আবু দাউদ: ৩৮৫০"
    ),
    // Travel
    DuaItem(
      id = "tr1",
      category = "Travel",
      categoryBn = "সফরের দোয়া",
      titleEn = "Supplication for traveling / vehicle",
      titleBn = "যানবাহনে চড়ার ও সফরের দোয়া",
      arabic = "سُبْحَانَ الَّذِي سَخَّرَ لَنَا هَٰذَا وَمَا كُنَّا لَهُ مُقْرِنِينَ وَإِنَّا إِلَىٰ رَبِّنَا لَمُنقَلِبُونَ",
      pronunciationBn = "সুবহানাল্লাযী সাখখারা লানা হাযা ওয়ামা কুন্না লাহু মুক্বরিনীন, ওয়া ইন্না ইলা রব্বিনা লামুনক্বালিবূন।",
      meaningBn = "পবিত্র সেই সত্তা যিনি এই বাহনকে আমাদের বশীভূত করে দিয়েছেন, অথচ আমরা একে বশীভূত করতে সক্ষম ছিলাম না। আর আমরা অবশ্যই আমাদের প্রতিপালকের নিকট প্রত্যাবর্তন করব।",
      meaningEn = "Glory to Him who has subjected this to us, when we could never have done it by ourselves. And verily, unto our Lord we shall return.",
      reference = "সূরা যুখরুফ: ১৩-১৪ / সহীহ মুসলিম: ১৩৪২"
    ),
    // Prayer
    DuaItem(
      id = "pr1",
      category = "Prayer",
      categoryBn = "নামাজের দোয়া",
      titleEn = "Dua in Sujud (Prostration)",
      titleBn = "সিজদার মধ্যে পড়ার দোয়া",
      arabic = "سُبْحَانَ رَبِّيَ الأَعْلَى",
      pronunciationBn = "সুবহানা রব্বিয়াল আ'লা।",
      meaningBn = "আমার মহিমাময় মহান প্রতিপালক অতি পবিত্র। (কমপক্ষে ৩ বার)",
      meaningEn = "Glory is to my Lord, the Most High.",
      reference = "সহীহ মুসলিম: ৭৭২"
    ),
    // Ramadan / Fasting
    DuaItem(
      id = "ram1",
      category = "Daily",
      categoryBn = "রমজান ও রোজা",
      titleEn = "Iftar Supplication",
      titleBn = "ইফতারের দোয়া",
      arabic = "ذَهَبَ الظَّمَأُ وَابْتَلَّتِ الْعُرُوقُ وَثَبَتَ الأَجْرُ إِنْ شَاءَ اللَّهُ",
      pronunciationBn = "যাহাবায যমউ ওয়াবতাল্লাতিল উরূকু ওয়া সাবাতাল আজরু ইনশাআল্লাহ।",
      meaningBn = "পিপাসা নিবৃত্ত হয়েছে, শিরা-উপশিরা সিক্ত হয়েছে এবং ইনশাআল্লাহ প্রতিদানও সুনিশ্চিত হয়েছে।",
      meaningEn = "The thirst is gone, the veins are moistened, and the reward is confirmed, if Allah wills.",
      reference = "সুনান আবু দাউদ: ২৩৫৭"
    )
  )

  val hadiths: List<HadithItem> = listOf(
    HadithItem(
      id = "h1",
      category = "Faith",
      categoryBn = "ঈমান ও নিয়ত",
      titleEn = "Actions are according to intentions",
      titleBn = "কাজের ফলাফল নিয়তের উপর নির্ভরশীল",
      arabic = "إِنَّمَا الأَعْمَالُ بِالنِّيَّاتِ، وَإِنَّمَا لِكُلِّ امْرِئٍ مَا نَوَى",
      banglaText = "সকল কাজের ফলাফল নিয়তের উপর নির্ভরশীল। মানুষ যা নিয়ত করে তাই সে পায়।",
      englishText = "The reward of deeds depends upon the intentions and every person will get the reward according to what he has intended.",
      narrator = "উমর ইবনুল খাত্তাব (রা.)",
      source = "সহীহ বুখারী: ১, সহীহ মুসলিম: ১৯০৭"
    ),
    HadithItem(
      id = "h2",
      category = "Prayer",
      categoryBn = "সালাত ও ইবাদত",
      titleEn = "The key to Paradise is prayer",
      titleBn = "জান্নাতের চাবি হলো নামাজ",
      arabic = "مِفْتَاحُ الْجَنَّةِ الصَّلاَةُ وَمِفْتَاحُ الصَّلاَةِ الْوُضُوءُ",
      banglaText = "রাসূলুল্লাহ (সা.) বলেছেন: জান্নাতের চাবি হলো নামাজ, আর নামাজের চাবি হলো পবিত্রতা (অজু)।",
      englishText = "The key to Paradise is prayer, and the key to prayer is cleanliness (wudu).",
      narrator = "জাবির ইবনে আব্দুল্লাহ (রা.)",
      source = "জামে তিরমিযী: ৪"
    ),
    HadithItem(
      id = "h3",
      category = "Character",
      categoryBn = "সদাচার ও চরিত্র",
      titleEn = "The best among you in character",
      titleBn = "তোমাদের মধ্যে সেই উত্তম যার চরিত্র সর্বোত্তম",
      arabic = "إِنَّ مِنْ خِيَارِكُمْ أَحْسَنَكُمْ أَخْلاَقًا",
      banglaText = "রাসূলুল্লাহ (সা.) বলেছেন: নিশ্চয়ই তোমাদের মধ্যে সর্বাপেক্ষা উত্তম ব্যক্তি সে, যার চরিত্র ও আচরণ সবচেয়ে সুন্দর।",
      englishText = "The best amongst you are those who have the best character and manners.",
      narrator = "আব্দুল্লাহ ইবনে আমর (রা.)",
      source = "সহীহ বুখারী: ৩৫৫৯, সহীহ মুসলিম: ২৩২১"
    ),
    HadithItem(
      id = "h4",
      category = "Charity",
      categoryBn = "দান ও পরোপকার",
      titleEn = "A smiling face is charity",
      titleBn = "হাসিমুখে কথা বলাও সদকা",
      arabic = "تَبَسُّمُكَ فِي وَجْهِ أَخِيكَ لَكَ صَدَقَةٌ",
      banglaText = "রাসূলুল্লাহ (সা.) বলেছেন: তোমার ভাইয়ের মুখের দিকে তাকিয়ে তোমার মুচকি হাসাও তোমার জন্য একটি সদকা।",
      englishText = "Your smiling in the face of your brother is charity for you.",
      narrator = "আবু যর গিফারী (রা.)",
      source = "জামে তিরমিযী: ১৯৫৬"
    ),
    HadithItem(
      id = "h5",
      category = "Quran",
      categoryBn = "কুরআনের মর্যাদা",
      titleEn = "The best is he who learns the Quran",
      titleBn = "তোমাদের মাঝে শ্রেষ্ঠ সে যে কুরআন শেখে ও শেখায়",
      arabic = "خَيْرُكُمْ مَنْ تَعَلَّمَ الْقُرْآنَ وَعَلَّمَهُ",
      banglaText = "রাসূলুল্লাহ (সা.) বলেছেন: তোমাদের মধ্যে সর্বোত্তম ব্যক্তি সে, যে নিজে কুরআন শিক্ষা করে এবং অন্যকে তা শিক্ষা দেয়।",
      englishText = "The best among you are those who learn the Quran and teach it.",
      narrator = "উসমান ইবনে আফফান (রা.)",
      source = "সহীহ বুখারী: ৫০২৭"
    )
  )

  val importantIslamicEvents: List<IslamicEvent> = listOf(
    IslamicEvent("পবিত্র আশুরা", "Day of Ashura", "১০ মুহররম", "10 Muharram", "জুলাই/আগস্ট", "হযরত মুসা (আ.) ও বনী ইসরাঈলের মুক্তি এবং কারবালার ঐতিহাসিক শহীদ হওয়ার দিন।", "Day of deliverance for Musa (AS) and martyrdom of Hussain (RA)."),
    IslamicEvent("শবে মেরাজ", "Shab-e-Miraj", "২৭ রজব", "27 Rajab", "জানুয়ারি/ফেব্রুয়ারি", "মহানবী (সা.)-এর ঊর্ধ্বাকাশে ভ্রমণ ও পাঁচ ওয়াক্ত সালাতের উপহার প্রাপ্তির রজনী।", "The miraculous Night Journey and Ascension of Prophet Muhammad (PBUH)."),
    IslamicEvent("শবে বরাত", "Shab-e-Barat", "১৫ শাবান", "15 Sha'ban", "ফেব্রুয়ারি/মার্চ", "ক্ষমা, বরকত ও ভাগ্য নির্ধারণের মহিমান্বিত বরকতময় রজনী।", "Night of forgiveness, record-reckoning, and mercy."),
    IslamicEvent("পবিত্র রমজানুল মুবারক শুরু", "First day of Ramadan", "১ রমজান", "1 Ramadan", "মার্চ/এপ্রিল", "পবিত্র সিয়াম সাধনার মাসের সূচনা ও কুরআন অবতীর্ণের বরকত।", "Beginning of the blessed month of fasting and Quran revelation."),
    IslamicEvent("শবে কদর (লায়লাতুল কদর)", "Laylatul Qadr", "২৭ রমজান", "27 Ramadan", "এপ্রিল", "হাজার মাসের চেয়েও উত্তম মহিমান্বিত ভাগ্য রজনী।", "The Night of Power, better than a thousand months."),
    IslamicEvent("ঈদুল ফিতর", "Eid-ul-Fitr", "১ শাওয়াল", "1 Shawwal", "এপ্রিল/মে", "দীর্ঘ এক মাস সিয়াম সাধনা শেষে মুসলিম উম্মাহর মহান আনন্দ উৎসব।", "Celebration at the conclusion of Ramadan fasting."),
    IslamicEvent("আরাফার দিন", "Day of Arafah", "৯ জিলহজ্জ", "9 Dhu al-Hijjah", "জুন", "হজের প্রধান দিন এবং সিয়াম পালনের মাধ্যমে দুই বছরের গুনাহ মাফ হওয়ার দিন।", "The climactic day of Hajj with vast mercy and expiation of sins."),
    IslamicEvent("ঈদুল আজহা (কোরবানি)", "Eid-ul-Adha", "১০ জিলহজ্জ", "10 Dhu al-Hijjah", "জুন", "হযরত ইব্রাহিম (আ.)-এর চরম আত্মত্যাগের স্মরণে পশু কোরবানির মহান উৎসব।", "Festival of Sacrifice commemorating Ibrahim's (AS) devotion.")
  )

  val defaultGoodDeeds: List<GoodDeed> = listOf(
    GoodDeed("d1", "আজকের ৫ ওয়াক্ত নামাজ জামাতে/সময়ে আদায় করেছি", "Performed all 5 daily prayers on time", false),
    GoodDeed("d2", "সকালে ও সন্ধ্যায় মাসনূন দোয়া পড়েছি", "Recited morning and evening daily Adhkar", false),
    GoodDeed("d3", "পবিত্র কুরআন অন্তত এক পৃষ্ঠা তিলাওয়াত করেছি", "Recited at least one page of the Holy Quran", false),
    GoodDeed("d4", "কমপক্ষে ১০০ বার দরূদ বা ইস্তিগফার পাঠ করেছি", "Sent 100 Salawat or Istighfar on digital tasbih", false),
    GoodDeed("d5", "কাউকে সদকা বা মুখে হাসি দিয়ে সাহায্য করেছি", "Gave charity or helped a fellow brother/sister", false),
    GoodDeed("d6", "মা-বাবা বা পরিবারের খোঁজ নিয়েছি ও দোয়া করেছি", "Served parents or showed kindness to family", false),
    GoodDeed("d7", "রাগ সংবরণ করেছি এবং মিথ্যা/গিবত বর্জন করেছি", "Restrained anger and avoided backbiting/falsehood", false)
  )
}
