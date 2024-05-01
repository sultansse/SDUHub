package com.softwareit.sduhub.ui.screens.home_screen.categories.services.faculties_screen

import com.softwareit.sduhub.R
import com.softwareit.sduhub.core.base.BaseViewModel
import com.softwareit.sduhub.ui.model.FacultyDIO

class FacultiesViewModel : BaseViewModel<FacultiesContract.Event, FacultiesContract.State, FacultiesContract.Effect>() {

    override fun setInitialState(): FacultiesContract.State {
        return FacultiesContract.State
    }

    override fun handleEvent(event: FacultiesContract.Event) {
        when (event) {
            is FacultiesContract.Event.EmptyEffect -> {
                setEffect { FacultiesContract.Effect.Idle }
            }

            is FacultiesContract.Event.OnFacultyClick -> {
                setEffect { FacultiesContract.Effect.FacultyDialog(event.faculty) }
            }
        }
    }
}

val facultyOfLawAndSocialSciences = FacultyDIO(
    deanName = "Мадияр Саудбаев, PhD",
    deanImage = R.drawable.img_dean_law,
    facultyName = "Факультет Права и Социальных наук",
    facultyImage = R.drawable.img_faculty_law,
    facultyDescription = "Дорогие студенты, добро пожаловать на факультет права и социальных наук! Наш факультет поможет вам приобрести и развить профессиональные качества и навыки и использовать их не только в обучении, но и в жизни. Мир постоянно меняется, новые технологии порождают новые потребности, которые становятся жизненно важными и неизбежными. Знания и практические навыки, которые Вы здесь приобритаете, помогут Вашему дальнейшему саморазвитию, непрерывному обучению и научат Вам быть в курсе всех изменений. Вдобавок ко всему, мы считаем, что вне зависимости от выбранной специальности, наше образование вносит свою лепту в развитие научного потенциала нашей страны, равенства, демократической позиции и социальной жизни в целом.",
)

val facultyBusinessSchool = FacultyDIO(
    deanName = "Берик Сабденалиев",
    deanImage = R.drawable.img_dean_bs,
    facultyName = "Школа Бизнеса SDU",
    facultyImage = R.drawable.img_faculty_business,
    facultyDescription = "Добро пожаловать в Школу Бизнеса SDU! Мир не стоит на месте и это четко отражается и на процессе обучения. Обучение, основанное только на знаниях, потеряло свою актуальность в условиях современного мира. Мы убеждены, что основной фокус должен быть направлен на студентов. Наша главная цель – обеспечить нашим учащимся возможность преумножить вложенное время и усилия, став успешными предпринимателями, лидерами и выдающимися работниками. Это и мотивирует нас стать одной из ведущих Бизнес-Школ в нашем регионе. В нашей команде работают молодые и амбициозные профессионалы, которые обладают высоким потенциалом для создания наилучшей обучающей и дружеской среды. Будучи членом этой семьи, я сделаю все возможное для того, чтобы осуществить наши стратегические цели. Приглашаю вас присоединиться к нам, чтобы внести свой вклад.",
)

val facultyEducationAndHumanities = FacultyDIO(
    deanName = "Жайнагуль Дуйсебекова",
    deanImage = R.drawable.img_dean_edu,
    facultyName = "Факультет педагогики и гуманитарных наук",
    facultyImage = R.drawable.img_faculty_education,
    facultyDescription = "Я рад приветствовать вас на нашем факультете педагогики и гуманитарных наук СДУ. Наши двери открыты для светлых умов как среди студентов, так и среди преподавательского состава. Помимо научных знаний и профессионального образования, мы стараемся также развивать ценности глобальной гражданственности. Мы предлагаем профессиональную среду с современными лабораториями для развития и укрепления ваших знаний и навыков, а также предоставляем теплую дружескую атмосферу. Наш преданный состав готов помочь вам в достижении ваших стремлений и раскрытия потенциала.",
)

val facultyEngineeringAndNaturalSciences = FacultyDIO(
    deanName = "Рамис Ахмедов",
    deanImage = R.drawable.img_dean_eng,
    facultyName = "Факультет Инженерных и Естественных наук",
    facultyImage = R.drawable.img_faculty_engineering,
    facultyDescription = "Добро пожаловать на Факультет Инженерных и Естественных наук! Наша главная цель – сделать ваше пребывание в SDU полезным и наполненным. Для этого мы предлагаем различные направления и программы для обучения: Наука о данных (Data Science), Машинное обучение (Machine Learning), Финансовая Математика, Beta Career Хакатоны. Широкий выбор лабораторий ИТ, таких как Cisco, Red Hat, Oracle и др. Большое внимание мы уделяем развитию навыков самостоятельного и группового обучения студентов. Наши студенты изучают текущие и новые практики, имеющие доказательную базу, стремятся расширять критическое мышление для того, чтобы успевать за стремительными изменениями в сфере ИТ XXI века.",
)
