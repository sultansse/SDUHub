package com.softwareit.sduhub.ui.screens.home_screen.categories.services.faculties_screen

import com.softwareit.sduhub.R
import com.softwareit.sduhub.core.base.BaseViewModel
import com.softwareit.sduhub.ui.model.FacultyContactDIO
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
    deanImage = R.drawable.img_library,
    facultyName = "Факультет Права и Социальных наук",
    facultyImage = R.drawable.img_faculty_law,
    facultyDescription = "Дорогие студенты, добро пожаловать на факультет права и социальных наук! Наш факультет поможет вам приобрести и развить профессиональные качества и навыки и использовать их не только в обучении, но и в жизни. Мир постоянно меняется, новые технологии порождают новые потребности, которые становятся жизненно важными и неизбежными. Знания и практические навыки, которые Вы здесь приобритаете, помогут Вашему дальнейшему саморазвитию, непрерывному обучению и научат Вам быть в курсе всех изменений. Вдобавок ко всему, мы считаем, что вне зависимости от выбранной специальности, наше образование вносит свою лепту в развитие научного потенциала нашей страны, равенства, демократической позиции и социальной жизни в целом.",
    facultyContacts = listOf(
        FacultyContactDIO(
            contactName = "Мадияр Саудбаев, PhD",
            contactRole = "Декан Факультета Права и Социальных наук",
            contactLink = "madiyar.saudbayev@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Мехмет Таш",
            contactRole = "Заместитель декана по социальным делам",
            contactLink = "mehmet.tas@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Шайкенова Айсулу",
            contactRole = "Заведующая кафедрой социальных наук",
            contactLink = "aisulu.shaikenova@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Айжан Копбаева",
            contactRole = "Заведующая кафедрой Правоведения",
            contactLink = "aizhan.kopbayeva@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Дархан Орынбасаров",
            contactRole = "Директор магистрских программ",
            contactLink = "darkhan.orynbassarov@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Айтбек Максат",
            contactRole = "Координатор по социальным делам",
            contactLink = "maksat.aitbek@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Сагындыков Баубек",
            contactRole = "Специалист департамента по работе с выпускниками и развитию карьеры",
            contactLink = "baubek.sagyndykov@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Юсупова Дурдона",
            contactRole = "Председатель учебно-методического совета",
            contactLink = "durdona.yussupova@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Кулян Конарбаева",
            contactRole = "Главный специалист",
            contactLink = "kulyan.konarbayeva@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Жанель Сабирова",
            contactRole = "Координатор программы “Международные отношения”",
            contactLink = "zhanel.sabirova@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Талгат Гульдана",
            contactRole = "Координатор программы” Журналистика (ТВ и мультимедиа)”",
            contactLink = "guldana.talgat@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Адильхан Абзалбек",
            contactRole = "Координатор программы “Международное право”",
            contactLink = "adilkhan.abzalbek@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Балжан Турсынова",
            contactRole = "Координатор программы “Прикладное право”",
            contactLink = "balzhan.tursynova@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Абай Магауия",
            contactRole = "Координатор по программе магистратуры “IT право”",
            contactLink = "abay.magauya@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Бахтияр Нурумов",
            contactRole = "Координатор программ магистратуры “Журналистика”",
            contactLink = "bakhtyr.nurumov@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Айгуль Касенова",
            contactRole = "Координатор по программе докторантуры “Прикладное право”",
            contactLink = "aigul.kassenova@sdu.edu.kz"
        )
    )
)


val facultyBusinessSchool = FacultyDIO(
    deanName = "Берик Сабденалиев",
    deanImage = R.drawable.img_library,
    facultyName = "Школа Бизнеса SDU",
    facultyImage = R.drawable.img_faculty_business,
    facultyDescription = "Добро пожаловать в Школу Бизнеса SDU! Мир не стоит на месте и это четко отражается и на процессе обучения. Обучение, основанное только на знаниях, потеряло свою актуальность в условиях современного мира. Мы убеждены, что основной фокус должен быть направлен на студентов. Наша главная цель – обеспечить нашим учащимся возможность преумножить вложенное время и усилия, став успешными предпринимателями, лидерами и выдающимися работниками. Это и мотивирует нас стать одной из ведущих Бизнес-Школ в нашем регионе. В нашей команде работают молодые и амбициозные профессионалы, которые обладают высоким потенциалом для создания наилучшей обучающей и дружеской среды. Будучи членом этой семьи, я сделаю все возможное для того, чтобы осуществить наши стратегические цели. Приглашаю вас присоединиться к нам, чтобы внести свой вклад.",
    facultyContacts = listOf(
        FacultyContactDIO(
            contactName = "Берик Сабденалиев",
            contactRole = "Декан Бизнес Школы SDU",
            contactLink = "berik.sabdenaliyev@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Бек Базатбеков",
            contactRole = "Заместитель декана по социальным вопросам",
            contactLink = "bek.bazatbekov@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Айнур Абдразакова",
            contactRole = "Заведующая кафедрой экономики и бизнеса",
            contactLink = "ainur.abdrazakova@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Дархан Заманбеков",
            contactRole = "Директор магистерских программ",
            contactLink = "darkhan.zamanbekov@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Илияс Кенесары",
            contactRole = "Координатор по социальным вопросам",
            contactLink = "kenessary.iliyas@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Аружан Жаксылык",
            contactRole = "Старший эдвайзер",
            contactLink = "aruzhan.zhaksylyk@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Ардак Коржинбаева",
            contactRole = "Заведующая учебно-методического комитета",
            contactLink = "ardak.korzhinbayeva@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Айгерим Турсынбекова",
            contactRole = "Координатор программ EMBA, MBA и магистерских программ",
            contactLink = "aigerim.tursynbekova@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Бибижамал Амангельды",
            contactRole = "Координатор программы “Бухгалтерский учет и Аудит”",
            contactLink = "bibizhamal.amangeldi@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Жулдыз Нуржанова",
            contactRole = "Координатор программ “Экономика”, “Менеджмент”, “Финансовые технологии”",
            contactLink = "zhuldyz.nurzhanova@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Женис Жансая",
            contactRole = "Координатор программы “Финансы”",
            contactLink = "zhansaya.zhengis@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Гульнура Арзанбекова",
            contactRole = "Координатор программ “Менеджмент”",
            contactLink = "gulnura.arzanbekova@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Талшын Баймулданова",
            contactRole = "Координатор программы “Цифровой маркетинг”",
            contactLink = "talshyn.baimuldanova@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Мухамметжан Сейтжаппарулы",
            contactRole = "Координатор программы “Экономика”",
            contactLink = "mukhametzhan.seitzhapparuly@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Алтынай Омирзакова",
            contactRole = "Международный координатор",
            contactLink = "altynay.omirzakova@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Жаркынай Онгарова",
            contactRole = "Главный эксперт",
            contactLink = "zharkynay.ongarova@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Гаухар Алипбай",
            contactRole = "Координатор практики",
            contactLink = "gaukhar.alipbay@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Чобан Угур",
            contactRole = "Научный координатор",
            contactLink = "ugur.coban@sdu.edu.kz"
        )
    )
)

val facultyEducationAndHumanities = FacultyDIO(
    deanName = "Жайнагуль Дуйсебекова",
    deanImage = R.drawable.img_library,
    facultyName = "Факультет педагогики и гуманитарных наук",
    facultyImage = R.drawable.img_faculty_education,
    facultyDescription = "Я рад приветствовать вас на нашем факультете педагогики и гуманитарных наук СДУ. Наши двери открыты для светлых умов как среди студентов, так и среди преподавательского состава. Помимо научных знаний и профессионального образования, мы стараемся также развивать ценности глобальной гражданственности. Мы предлагаем профессиональную среду с современными лабораториями для развития и укрепления ваших знаний и навыков, а также предоставляем теплую дружескую атмосферу. Наш преданный состав готов помочь вам в достижении ваших стремлений и раскрытия потенциала.",
    facultyContacts = listOf(
        FacultyContactDIO(
            contactName = "Жайнагул Дуйсебекова",
            contactRole = "Декан факультета педагогики и гуманитарных наук",
            contactLink = "cemal.ozdemir@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Бабур Рашидов",
            contactRole = "Заместитель декана по социальным и воспитательным делам",
            contactLink = "babur.rashidov@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Акмаржан Ногайбаева",
            contactRole = "Заведующий кафедрой “языковое образование”",
            contactLink = "a.nogaibayeva@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Жаныл Абильбек",
            contactRole = "Заведующая кафедрой “педагогика естественных наук”",
            contactLink = "zhangyl.abilbek@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Дана Молдабаева",
            contactRole = "Заведующая кафедрой “Гуманитарные науки”",
            contactLink = "dana.moldabayeva@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Гульжайна Нурманова",
            contactRole = "Координатор программы “казахский язык и литература”",
            contactLink = "gulzhaina.nurmanova@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Газиза Отарбаева",
            contactRole = "Координатор программы “Прикладная филология”",
            contactLink = "gaziza.otarbayeva@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Айгерим Омарова",
            contactRole = "Координатор программы “Иностранный язык: два иностранных языка”",
            contactLink = "aigerim.omarova@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Айсулу Гатиат",
            contactRole = "Координатор программы  “Переводческое дело”",
            contactLink = "aisulu.gatiat@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Айжан Асылбек",
            contactRole = "Координатор программ  “Дошкольное воспитание и обучение”, “Педагогика и методика начального обучения”",
            contactLink = "aizhan.asylbek@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Сабира Жайыкбаева",
            contactRole = "Координатор программ” Педагогика и психология”,” Социальная педагогика и самопознание на основе ценностей”",
            contactLink = "sabira.zhaikbayeva@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Алмас Абдуллах",
            contactRole = "Координатор программ математики бакалавриата, магистратуры и докторантуры",
            contactLink = "abdullah.almas@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Самат Максутов",
            contactRole = "Координатор программ бакалавриата “Физика-Информатика” и магистратуры “Физика”",
            contactLink = "samat.maxutov@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Бота Жумакаева",
            contactRole = "Координатор программы “Химия-биология”",
            contactLink = "bota.zhumakayeva@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Акбота Абиыр",
            contactRole = "Координатор программ магистратуры и докторантуры” казахский язык и литература”",
            contactLink = "akbota.abiyr@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Хейрия Деловарова",
            contactRole = "Координатор программ магистратуры и докторантуры “Иностранный язык: два иностранных языка”",
            contactLink = "kheiriya.delovarova@sdu.edu.kz"
        )
    )
)


val facultyEngineeringAndNaturalSciences = FacultyDIO(
    deanName = "Рамис Ахмедов",
    deanImage = R.drawable.img_library,
    facultyName = "Факультет Инженерных и Естественных наук",
    facultyImage = R.drawable.img_faculty_engineering,
    facultyDescription = "Добро пожаловать на Факультет Инженерных и Естественных наук! Наша главная цель – сделать ваше пребывание в SDU полезным и наполненным. Для этого мы предлагаем различные направления и программы для обучения: Наука о данных (Data Science), Машинное обучение (Machine Learning), Финансовая Математика, Beta Career Хакатоны. Широкий выбор лабораторий ИТ, таких как Cisco, Red Hat, Oracle и др. Большое внимание мы уделяем развитию навыков самостоятельного и группового обучения студентов. Наши студенты изучают текущие и новые практики, имеющие доказательную базу, стремятся расширять критическое мышление для того, чтобы успевать за стремительными изменениями в сфере ИТ XXI века.",
    facultyContacts = listOf(
        FacultyContactDIO(
            contactName = "Рамис Ахмедов",
            contactRole = "Декан факультета инженерных и естественных наук",
            contactLink = "ramis.akhmedov@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Жанар Мукаш",
            contactRole = "Заведующий кафедрой компьютерных наук",
            contactLink = "zhanar.mukash@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Дархан Куанышбай",
            contactRole = "Заведующий кафедрой информационных систем",
            contactLink = "darkhan.kuanyshbay@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Зеин Онгаров",
            contactRole = "Координатор по социальным делам",
            contactLink = "zeiin.onggarov@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Уалихан Садык",
            contactRole = "Координатор программы докторантуры по компьютерным наукам",
            contactLink = "ualikhan.sadyk@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Бауыржан Берликожа",
            contactRole = "Координатор программы по информационным системам",
            contactLink = "bauirzhan.berlikozha@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Динара Хашимова",
            contactRole = "Координатор программы магистратуры по математике",
            contactLink = "dinara.khashimova@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Сындар Женис",
            contactRole = "Координатор магистерской программы по математическому и компьютерному моделированию",
            contactLink = "syndar.zhenis@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Марат Урманов",
            contactRole = "Координатор программы мультимедийных наук",
            contactLink = "marat.urmanov@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Хуршида Паттулаева",
            contactRole = "Координатор программы докторантуры по статистике и науке о данных",
            contactLink = "k.patullayeva@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Камила Орынбекова",
            contactRole = "Координатор программы докторантуры магистра компьютерных наук",
            contactLink = "kamila.orynbekova@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Асем Таласбек",
            contactRole = "Координатор программы докторантуры в области компьютерных наук",
            contactLink = "assem.talasbek@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Биржан Аянбаев",
            contactRole = "Координатор программы для магистра и доктора математических наук",
            contactLink = "birzhan.ayanbayev@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Рашид Баймукашев",
            contactRole = "Директор программы магистратуры и докторантуры",
            contactLink = "rashid.baimukashev@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Елнур Муталиев",
            contactRole = "Старший эдвайзер",
            contactLink = "yelnur.mutaliyev@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Гульнара Саймассай",
            contactRole = "Международный координатор",
            contactLink = "gulnara.saimassay@sdu.edu.kz"
        ),
        FacultyContactDIO(
            contactName = "Мадина Алиманова",
            contactRole = "Координатор",
            contactLink = "madina.alimanova@sdu.edu.kz"
        )
    )
)
