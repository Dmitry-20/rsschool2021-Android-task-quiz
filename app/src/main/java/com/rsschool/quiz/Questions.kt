package com.rsschool.quiz

import java.io.Serializable

class Question(var question: String,var answers: List<String>) : Serializable

const val questions1 = "РИСУНОК НА ЛБУ ТИГРА ПОХОЖ НА КИТАЙСКИЙ ИЕРОГЛИФ. ЧТО ОН ОЗНАЧАЕТ?"
val answers1 = listOf(
    "Ярость",
    "Пушистик",
    "Воин",
    "Царь",
    "Гном"
)

const val questions2 = "ГДЕ У БАБОЧКИ НАХОДЯТСЯ ВКУСОВЫЕ РЕЦЕПТОРЫ?"
val answers2 = listOf(
    "В крыльях",
    "В лапках",
    "В усиках",
    "В языке",
    "В попке"
)


const val questions3 = "С КАКОЙ СКОРОСТЬЮ БЬЁТСЯ СЕРДЦЕ ЕЖИКА В СПЯЧКЕ?"
val answers3 = listOf(
    "Около 0",
    "Примерно 100",
    "Примерно 150",
    "Сердце останавливается",
    "Меньше 50"
)


const val questions4 = "ВЕС ЯЗЫКА СИНЕГО КИТА МОЖНО СРАВНИТЬ С ВЕСОМ..."
val answers4 = listOf(
    "Кота",
    "Льва ",
    "Лошади",
    "Слона",
    "Динозавра"
)


const val questions5 = "А КОЖА БЕЛОГО МЕДВЕДЯ КАКОГО ЦВЕТА?"
val answers5 = listOf(
    "Белого",
    "Черного",
    "Желтого ",
    "Бледная, как у русского",
    "Прозрачная"
)

val questions = listOf(
    Question(questions1, answers1),
    Question(questions2, answers2),
    Question(questions3, answers3),
    Question(questions4, answers4),
    Question(questions5, answers5)
)

val statusBarColors = listOf(
    R.color.deep_orange_100_dark,
    R.color.yellow_100_dark,
    R.color.light_green_100_dark,
    R.color.cyan_100_dark,
    R.color.deep_purple_100_dark
)

val rightAnswers = listOf(
    3,
    1,
    4,
    3,
    1
)

