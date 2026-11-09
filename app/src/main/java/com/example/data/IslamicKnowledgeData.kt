package com.example.data

import com.example.model.GuideSection
import com.example.model.IslamicGuideItem

object IslamicKnowledgeData {

  val guides: List<IslamicGuideItem> = listOf(
    IslamicGuideItem(
      id = "pillars",
      category = "Foundation",
      titleBn = "ইসলামের ৫টি মূল স্তম্ভ",
      titleEn = "Five Pillars of Islam",
      summaryBn = "ইসলাম ধর্মের মূল ভিত্তি পাঁচটি মৌলিক স্তম্ভের উপর প্রতিষ্ঠিত।",
      summaryEn = "The fundamental five practices that every Muslim upholds.",
      sections = listOf(
        GuideSection(
          headingBn = "১. কালিমা শাহাদাত (বিশ্বাস ঘোষণা)",
          headingEn = "1. Shahadah (Declaration of Faith)",
          contentBn = "মুখে উচ্চারণ ও অন্তরে দৃঢ় বিশ্বাস করা যে, আল্লাহ ছাড়া কোনো সত্য ইলাহ নেই এবং হযরত মুহাম্মদ (সা.) তাঁর বান্দা ও রাসূল।",
          contentEn = "Bearing witness that none has the right to be worshipped but Allah alone, and Muhammad (PBUH) is His Messenger.",
          arabic = "أَشْهَدُ أَنْ لاَ إِلَهَ إِلاَّ اللَّهُ وَأَشْهَدُ أَنَّ مُحَمَّدًا عَبْدُهُ وَرَسُولُهُ"
        ),
        GuideSection(
          headingBn = "২. সালাত (নামাজ কায়েম)",
          headingEn = "2. Salah (Prayer)",
          contentBn = "দৈনিক ৫ ওয়াক্ত সালাত নির্দিষ্ট সময়ে সঠিক নিয়মে একাগ্রচিত্তে আদায় করা। এটি মুমিন ও কাফিরের মাঝে মূল পার্থক্যকারী।",
          contentEn = "Performing the five obligatory daily prayers at their prescribed times with devotion."
        ),
        GuideSection(
          headingBn = "৩. সিয়াম (রমজানের রোজা)",
          headingEn = "3. Sawm (Fasting)",
          contentBn = "পবিত্র রমজান মাসের ৩০ দিন সুবহে সাদিক থেকে সূর্যাস্ত পর্যন্ত পানাহার, ইন্দ্রিয় প্ররোচনা ও পাপাচার থেকে বিরত থাকা।",
          contentEn = "Fasting during the daylight hours of the blessed month of Ramadan from dawn until dusk."
        ),
        GuideSection(
          headingBn = "৪. যাকাত (বাধ্যতামূলক দান)",
          headingEn = "4. Zakah (Almsgiving)",
          contentBn = "নিসাব পরিমাণ সম্পদের মালিককে প্রতি বছর তার অতিরিক্ত উদ্বৃত্ত সম্পদের ২.৫% নির্দিষ্ট ৮টি খাতে অভাবীদের প্রদান করা।",
          contentEn = "Purifying one's wealth by giving 2.5% of eligible surplus assets to the poor and needy."
        ),
        GuideSection(
          headingBn = "৫. হজ (বাইতুল্লাহ যিয়ারত)",
          headingEn = "5. Hajj (Pilgrimage)",
          contentBn = "শারীরিক ও আর্থিক সামর্থ্যবান মুসলমানের জন্য জীবনে অন্তত একবার মক্কায় পবিত্র কাবা শরিফ তাওয়াফ ও হজ সম্পন্ন করা।",
          contentEn = "Undertaking the pilgrimage to the sacred sanctuary of Makkah once in a lifetime for those physically and financially able."
        )
      )
    ),
    IslamicGuideItem(
      id = "wudu",
      category = "Purity",
      titleBn = "অজু করার সঠিক নিয়ম ও ফরজ",
      titleEn = "Step-by-Step Wudu Guide",
      summaryBn = "নামাজ ও কুরআন স্পর্শের পূর্বশর্ত হলো পবিত্রতা অর্জন। অজুর ধারাবাহিক সঠিক পদ্ধতি।",
      summaryEn = "Ritual purification necessary before prayer with obligatory and sunnah steps.",
      sections = listOf(
        GuideSection(
          headingBn = "অজুর ৪টি ফরজ (বাধ্যতামূলক কাজ)",
          headingEn = "4 Obligatory Acts (Fard)",
          contentBn = "১. সমস্ত মুখমণ্ডল অন্তত একবার ভালো করে ধৌত করা।\n২. উভয় হাত কনুইসহ একবার ধৌত করা।\n৩. মাথার চার ভাগের এক ভাগ মাসেহ করা।\n৪. উভয় পা টাখনু বা গোড়ালিসহ ধৌত করা।\n(উক্ত চারটি কাজের কোনো একটি সামান্য পরিমাণও শুকনো থাকলে অজু হবে না)।",
          contentEn = "1. Washing the face from forehead to chin and ear to ear.\n2. Washing both arms up to and including the elbows.\n3. Wiping at least a quarter of the head.\n4. Washing both feet up to and including the ankles."
        ),
        GuideSection(
          headingBn = "অজুর সুন্নাত ও মোস্তাহাব ধারাবাহিকতা",
          headingEn = "Sunnah Method",
          contentBn = "১. অন্তরে নিয়ত করা ও 'বিসমিল্লাহ' বলা।\n২. উভয় হাত কব্জি পর্যন্ত ৩ বার ধৌত করা।\n৩. মেসওয়াক করা বা আঙুল দিয়ে দাঁত পরিষ্কার করে ৩ বার কুলি করা।\n৪. ডান হাতে নাকে পানি দিয়ে বাঁ হাত দিয়ে ৩ বার নাক পরিষ্কার করা।\n৫. ৩ বার মুখমণ্ডল ধৌত করা।\n৬. প্রথমে ডান হাত পরে বাঁ হাত কনুই পর্যন্ত ৩ বার ধৌত করা।\n৭. পুরো মাথা, কান ও ঘাড় ভিজা হাত দিয়ে মাসেহ করা।\n৮. প্রথমে ডান পা পরে বাঁ পা গোড়ালীসহ ৩ বার ধৌত করা।",
          contentEn = "Intention, Bismillah, washing hands thrice, rinsing mouth, sniffing water into nose, washing face thrice, washing forearms to elbows thrice, wiping head/ears, and washing feet to ankles."
        ),
        GuideSection(
          headingBn = "অজু শেষে দোয়া",
          headingEn = "Dua After Wudu",
          contentBn = "অজু শেষে আকাশের দিকে তাকিয়ে কালিমা শাহাদাত ও ক্ষমা প্রার্থনার দোয়া পড়লে জান্নাতের ৮টি দরজাই উন্মুক্ত হয়ে যায়।",
          contentEn = "Reciting the Shahadah and supplication opens the eight gates of Paradise.",
          arabic = "أَشْهَدُ أَنْ لاَ إِلَهَ إِلاَّ اللَّهُ وَحْدَهُ لاَ شَرِيكَ لَهُ وَأَشْهَدُ أَنَّ مُحَمَّدًا عَبْدُهُ وَرَسُولُهُ، اللَّهُمَّ اجْعَلْنِي مِنَ التَّوَّابِينَ وَاجْعَلْنِي مِنَ الْمُتَطَهِّرِينَ"
        ),
        GuideSection(
          headingBn = "অজু ভঙ্গের ৭টি কারণ",
          headingEn = "Nullifiers of Wudu",
          contentBn = "১. পায়খানা ও পেশাবের রাস্তা দিয়ে কোনো কিছু বের হওয়া।\n২. মুখ ভরে বমি হওয়া।\n৩. শরীরের কোনো স্থান হতে রক্ত বা পুঁজ বের হয়ে গড়িয়ে পড়া।\n৪. থুথুর সঙ্গে রক্তের ভাগ সমান বা বেশি হওয়া।\n৫. চিৎ বা কাত হয়ে ঠেস দিয়ে ঘুমানো।\n৬. মাতাল বা অচেতন হওয়া।\n৭. রুকু-সিজদাবিশিষ্ট নামাজে প্রাপ্তবয়স্কের উচ্চস্বরে হেসে ওঠা।",
          contentEn = "Passing bodily waste/gas, vomiting mouthful, flowing blood/pus, sleeping deeply on one's side, losing consciousness, or laughing out loud in prayer."
        )
      )
    ),
    IslamicGuideItem(
      id = "salah",
      category = "Worship",
      titleBn = "নামাজ শিক্ষা ও সঠিক পদ্ধতি",
      titleEn = "Complete Salah Guide",
      summaryBn = "তাকবীরে তাহরিমা থেকে সালাম পর্যন্ত সহীহ সুন্নাহ মোতাবেক নামাজ আদায়ের বিবরণ।",
      summaryEn = "Comprehensive guide on postures, recitation and rules of daily prayers.",
      sections = listOf(
        GuideSection(
          headingBn = "দৈনিক ৫ ওয়াক্ত নামাজের রাকাত সংখ্যা",
          headingEn = "Daily Prayer Rak'ahs",
          contentBn = "• ফজর: ২ রাকাত সুন্নাত + ২ রাকাত ফরজ (মোট ৪ রাকাত)\n• যোহর: ৪ রাকাত সুন্নাত + ৪ রাকাত ফরজ + ২ রাকাত সুন্নাত (ঐচ্ছিক ২ নফল)\n• আসর: ৪ রাকাত ফরজ (পূর্বে ৪ রাকাত সুন্নতে গায়রে মুয়াক্কাদা)\n• মাগরিব: ৩ রাকাত ফরজ + ২ রাকাত সুন্নাত (ঐচ্ছিক ২ নফল)\n• ইশা: ৪ রাকাত ফরজ + ২ রাকাত সুন্নাত + ৩ রাকাত বিতর (পূর্বে ৪ সুন্নাত, পরে নফল)",
          contentEn = "Fajr (2 Sunnah, 2 Fard); Dhuhr (4 Sunnah, 4 Fard, 2 Sunnah); Asr (4 Fard); Maghrib (3 Fard, 2 Sunnah); Isha (4 Fard, 2 Sunnah, 3 Witr)."
        ),
        GuideSection(
          headingBn = "নামাজের প্রারম্ভিক তাকবীর ও সানা",
          headingEn = "Takbeer and Thana",
          contentBn = "কিবলামুখী হয়ে সোজা দাঁড়িয়ে তাকবীরে তাহরিমা 'আল্লাহু আকবার' বলে দুই হাত কান/কাঁধ পর্যন্ত উঠিয়ে নাভির উপরে বা বুকে বাঁধতে হবে। এরপর সানা পড়া:\n'সুবহানাকা আল্লাহুম্মা ওয়া বিহামদিকা ওয়া তাবারাকাসমুকা ওয়া তাআলা জাদ্দুকা ওয়া লা ইলাহা গাইরুক'।",
          contentEn = "Facing Qibla with hands raised saying Allahu Akbar, then placing right over left hand and reciting the opening glorification."
        ),
        GuideSection(
          headingBn = "রুকু ও সিজদা",
          headingEn = "Ruku and Sujud",
          contentBn = "রুকুতে পিঠ সোজা করে দুই হাত হাঁটুতে রেখে বলতে হবে: 'সুবহানা রব্বিয়াল আযীম' (কমপক্ষে ৩ বার)। সোজা হয়ে দাঁড়িয়ে বলতে হবে: 'সামিআল্লাহু লিমান হামিদাহ', এরপর 'রব্বানা লাকাল হামদ'। এরপর আল্লাহু আকবার বলে সিজদায় গিয়ে অন্তত ৩ বার বলা: 'সুবহানা রব্বিয়াল আ'লা'।",
          contentEn = "Bowing with flat back in Ruku reciting Subhana Rabbiyal Adheem, standing straight, then prostrating in Sujud reciting Subhana Rabbiyal A'la."
        ),
        GuideSection(
          headingBn = "তাশাহহুদ ও দরূদ শরিফ",
          headingEn = "Tashahhud and Durood",
          contentBn = "শেষ বৈঠকে বসে তাশাহহুদ (আত্তাহিয়্যাতু লিল্লাহি...), দরূদে ইব্রাহিম (আল্লাহুম্মা সাল্লি আলা মুহাম্মাদ...) এবং দোয়ায়ে মাসূরা পড়ে ডানে ও বাঁয়ে 'আসসালামু আলাইকুম ওয়া রাহমাতুল্লাহ' বলে সালাম ফিরানো।",
          contentEn = "Sitting for Tashahhud, sending blessings upon the Prophet through Durood Ibrahim, reciting supplication and concluding with Salam to both sides."
        )
      )
    ),
    IslamicGuideItem(
      id = "ghusl",
      category = "Purity",
      titleBn = "ফরজ গোসলের সঠিক নিয়ম",
      titleEn = "Ghusl (Full Ritual Bath) Guide",
      summaryBn = "ফরজ গোসল ওয়াজিব হওয়ার কারণ এবং এর ৩টি অপরিহার্য ফরজ ও সুন্নাত নিয়ম।",
      summaryEn = "Obligatory and recommended steps for total ritual body purification.",
      sections = listOf(
        GuideSection(
          headingBn = "ফরজ গোসলের ৩টি মূল ফরজ",
          headingEn = "3 Compulsory Acts of Ghusl",
          contentBn = "১. ভালোভাবে গড়গড়ার সঙ্গে কুলি করা (রোজাদার হলে গড়গড়া ছাড়া সাধারণ কুলি)।\n২. নাকের নরম হাড় পর্যন্ত পানি পৌঁছানো ও পরিষ্কার করা।\n৩. সম্পূর্ণ শরীর এমনভাবে ধৌত করা যেন মাথার চুল থেকে পায়ের তলা পর্যন্ত এক বিন্দু জায়গাও শুকনো না থাকে।",
          contentEn = "1. Gargling/rinsing mouth thoroughly.\n2. Sniffing water to clean the nasal passage.\n3. Pouring clean water over the entire body leaving no dry spot."
        ),
        GuideSection(
          headingBn = "গোসলের সুন্নাত পদ্ধতি",
          headingEn = "Sunnah Procedure",
          contentBn = "প্রথমে দুই হাত ধোয়া, শরীরের অপবিত্র স্থান পরিষ্কার করা, নামাজের মতো পূর্ণ অজু করা, এরপর মাথায় তিনবার পানি ঢালা এবং সর্বশেষে পুরো শরীরে ডানে ও বাঁয়ে পানি প্রবাহিত করে গা মলে পরিষ্কার করা।",
          contentEn = "Washing hands, cleansing impurities, performing full wudu, pouring water thrice over head, and washing the entire right then left sides of body."
        )
      )
    ),
    IslamicGuideItem(
      id = "ramadan_rules",
      category = "Worship",
      titleBn = "রোজা রাখার নিয়ম ও মাসায়েল",
      titleEn = "Fasting Rules & Guidelines",
      summaryBn = "সিয়াম পালনের নিয়ত, সাহরি ও ইফতারের সুন্নাত এবং রোজা ভঙ্গের কারণসমূহ।",
      summaryEn = "Essential Islamic rulings regarding fasting, Sehri, Iftar and invalidations.",
      sections = listOf(
        GuideSection(
          headingBn = "রোজার নিয়ত ও সময়সীমা",
          headingEn = "Niyyah and Timings",
          contentBn = "সুবহে সাদিক থেকে সূর্যাস্ত পর্যন্ত নিয়তসহ পানাহার ও দাম্পত্য সম্পর্ক পরিহার করা। সাহরি খাওয়া সুন্নাত ও বরকতময়। সূর্য অস্তমিত হওয়ার সাথে সাথে ত্বরিত ইফতার করা সুন্নাত।",
          contentEn = "Abstaining from food, drink and marital relations from true dawn until sunset with pure intention."
        ),
        GuideSection(
          headingBn = "যেসব কারণে রোজা ভেঙে যায়",
          headingEn = "Actions that Invalidate Fasting",
          contentBn = "১. ইচ্ছাকৃতভাবে পানাহার বা ধূমপান করা।\n২. রোজা স্মরণ থাকা অবস্থায় ইচ্ছাকৃত মুখভরে বমি করা।\n৩. দাম্পত্য মিলন ঘটানো।\n৪. পুষ্টিবর্ধক ইনজেকশন বা স্যালাইন গ্রহণ করা।\n৫. ভুলবশত পানাহার করার পর রোজা ভেঙে গেছে ভেবে ইচ্ছাকৃতভাবে পুনরায় খাওয়া।",
          contentEn = "Deliberate eating, drinking, smoking, induced vomiting, marital intimacy, or receiving nutritional IV drips."
        )
      )
    )
  )
}
